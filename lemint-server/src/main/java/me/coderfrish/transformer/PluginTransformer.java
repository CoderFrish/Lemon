package me.coderfrish.transformer;

import me.coderfrish.utility.CheckUtility;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

import static org.objectweb.asm.Opcodes.*;

public class PluginTransformer {
    private final static Logger Logger = LoggerFactory.getLogger("PluginTransformer");

    public static PluginTransformer create(File pluginDirectory) {
        return new PluginTransformer(pluginDirectory);
    }

    public static PluginTransformer instance;

    public static PluginTransformer getInstance() {
        return instance;
    }

    private static final int ASM_API = ASM9;

    private final File rootFolder;
    private final File pluginDirectory;

    public PluginTransformer(File pluginDirectory) {
        instance = this;
        this.pluginDirectory = pluginDirectory;
        this.rootFolder = new File(pluginDirectory, ".plugin-transferred");
    }

    private static final Map<String, MappingInfo> SCHEDULER_MAPPINGS;

    static {
        SCHEDULER_MAPPINGS = new ConcurrentHashMap<>() {
            {
                put(
                        "org/bukkit/scheduler/BukkitScheduler.runTaskTimer(Lorg/bukkit/plugin/Plugin;Ljava/lang/Runnable;JJ)Lorg/bukkit/scheduler/BukkitTask;",
                        new MappingInfo("me/coderfrish/scheduler/TransferScheduler", "runTaskTimer", "(Lorg/bukkit/plugin/Plugin;Ljava/lang/Runnable;JJ)Lorg/bukkit/scheduler/BukkitTask;")
                );

                put(
                        "org/bukkit/scheduler/BukkitScheduler.runTaskLater(Lorg/bukkit/plugin/Plugin;Ljava/lang/Runnable;J)Lorg/bukkit/scheduler/BukkitTask;",
                        new MappingInfo("me/coderfrish/scheduler/TransferScheduler", "runTaskLater", "(Lorg/bukkit/plugin/Plugin;Ljava/lang/Runnable;J)Lorg/bukkit/scheduler/BukkitTask;")
                );

                put(
                        "org/bukkit/scheduler/BukkitScheduler.runTask(Lorg/bukkit/plugin/Plugin;Ljava/lang/Runnable;)Lorg/bukkit/scheduler/BukkitTask;",
                        new MappingInfo("me/coderfrish/scheduler/TransferScheduler", "runTask", "(Lorg/bukkit/plugin/Plugin;Ljava/lang/Runnable;)Lorg/bukkit/scheduler/BukkitTask;")
                );
            }
        };
    }

    private record MappingInfo(String owner, String name, String descriptor) {}

    public JarFile transformer(JarFile jarFile) throws IOException {
        String name = jarFile.getName().substring(jarFile.getName().lastIndexOf('\\') + 1);
        if (!this.isFoliaSupported(jarFile)) {
            File copiedOriginalJar = new File(rootFolder, "original-classpath" + "/" +  name);
            File transferredJar = new File(rootFolder, "transferred-classpath" + "/" +  name);
            File originalJar = new File(pluginDirectory, name);

            if (!copiedOriginalJar.getParentFile().exists())
                copiedOriginalJar.getParentFile().mkdirs();

            if (!transferredJar.getParentFile().exists())
                transferredJar.getParentFile().mkdirs();

            if (!copiedOriginalJar.exists()) {
                FileUtils.copyFile(originalJar, copiedOriginalJar);
                return transformer(copiedOriginalJar, transferredJar, jarFile.getName());
            }

            if (copiedOriginalJar.exists()) {
                String sha0 = CheckUtility.computeSHA_1(copiedOriginalJar);
                String sha1 = CheckUtility.computeSHA_1(originalJar);

                if (!sha0.equals(sha1)) {
                    FileUtils.delete(copiedOriginalJar);
                    FileUtils.delete(transferredJar);

                    return transformer(jarFile);
                }
            }

            return new JarFile(transferredJar);
        }

        return jarFile;
    }

    private JarFile transformer(File input, File output, String name) throws IOException {
        Logger.info("Patching legacy bukkit plugin - {}", name);
        try(JarFile file = new JarFile(input);OutputStream fos = new FileOutputStream(output);JarOutputStream jos = new JarOutputStream(fos)) {
            Enumeration<JarEntry> entries = file.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();

                JarEntry newEntry = new JarEntry(entry);
                jos.putNextEntry(newEntry);

                if (entry.getName().endsWith(".class") && !entry.isDirectory()) {
                    try(InputStream is = file.getInputStream(entry)) {
                        ClassReader cr = new ClassReader(is);
                        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);

                        cr.accept(classVisitor(cw), 0);
                        jos.write(cw.toByteArray());
                    }
                } else {
                    try(InputStream is = file.getInputStream(entry)) {
                        jos.write(is.readAllBytes());
                    }
                }
            }
        }

        return new JarFile(output);
    }

    private boolean isFoliaSupported(JarFile file) throws IOException {
        Enumeration<JarEntry> entries = file.entries();
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            if (entry.getName().equals("plugin.yml") || entry.getName().equals("paper-plugin.yml")) {
                try(InputStream i = file.getInputStream(entry)) {
                    String content = IOUtils.toString(i, StandardCharsets.UTF_8);
                    return content.lines().anyMatch(line ->
                            line.trim().equalsIgnoreCase("folia-supported: true"));
                }
            }
        }
        return false;
    }

    private ClassVisitor classVisitor(ClassWriter cw) {
        return new ClassVisitor(ASM_API, cw) {
            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);
                return new SchedulerMethodVisitor(methodVisitor);
            }
        };
    }

    private static final class SchedulerMethodVisitor extends MethodVisitor {
        SchedulerMethodVisitor(MethodVisitor methodVisitor) {
            super(ASM_API, methodVisitor);
        }

        @Override
        public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
            String methodKey = owner + "." + name + descriptor;
            if (SCHEDULER_MAPPINGS.containsKey(methodKey)) {
                if ("org/bukkit/scheduler/BukkitScheduler".equals(owner) && opcode == INVOKEINTERFACE) {
                    MappingInfo mappingInfo = SCHEDULER_MAPPINGS.get(methodKey);
                    String newDesc = "(Lorg/bukkit/scheduler/BukkitScheduler;" + mappingInfo.descriptor.substring(1);
                    super.visitMethodInsn(INVOKESTATIC, mappingInfo.owner, mappingInfo.name, newDesc, false);
                    return;
                }
            }

            super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
        }
    }
}
