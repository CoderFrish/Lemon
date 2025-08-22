package me.coderfrish.transformer;

import me.coderfrish.utility.CheckUtility;
import org.apache.commons.io.FileUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import java.io.*;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

import static org.objectweb.asm.ClassReader.EXPAND_FRAMES;
import static org.objectweb.asm.Opcodes.*;

public class PluginTransformer {
    public static PluginTransformer create(File pluginDirectory) {
        return new PluginTransformer(pluginDirectory);
    }

    public static PluginTransformer instance;

    public static PluginTransformer getInstance() {
        return instance;
    }

    private final File rootFolder;
    private final File pluginDirectory;

    public PluginTransformer(File pluginDirectory) {
        instance = this;
        this.pluginDirectory = pluginDirectory;
        this.rootFolder = new File(pluginDirectory, ".plugin-transferred");

        if (!rootFolder.exists()) {
            rootFolder.mkdirs();
        }
    }

    public JarFile transformer(JarFile jarFile) throws IOException {
        String name = jarFile.getName();
        String fileName = name.substring(name.lastIndexOf("\\") + 1);
        File basePluginJar = new File(pluginDirectory, fileName);

        File destPluginJar = new File(rootFolder, basePluginJar.getName());
        if (!destPluginJar.exists()) {
            FileUtils.copyFile(basePluginJar, destPluginJar);

            return processFile(basePluginJar, destPluginJar);
        } else {
            String sha = CheckUtility.computeSHA_1(basePluginJar);
            String sha1 = CheckUtility.computeSHA_1(destPluginJar);

            if (!sha1.equals(sha)) {
                FileUtils.delete(destPluginJar);
                FileUtils.copyFile(basePluginJar, destPluginJar);

                return processFile(basePluginJar, destPluginJar);
            }
        }

        return new JarFile(destPluginJar);
    }

    private JarFile processFile(File input, File output) throws IOException {
        try(JarFile jarFile = new JarFile(input);
            OutputStream fos = new FileOutputStream(output);
            JarOutputStream jos = new JarOutputStream(fos)) {
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();

                JarEntry newEntry = new JarEntry(entry);
                jos.putNextEntry(newEntry);

                if (entry.getName().endsWith(".class") && !entry.isDirectory()) {
                    try(InputStream is = jarFile.getInputStream(entry)) {
                        ClassReader cr = new ClassReader(is);
                        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);

                        cr.accept(processClass(cw), EXPAND_FRAMES);
                        jos.write(cw.toByteArray());
                    }
                } else {
                    try(InputStream is = jarFile.getInputStream(entry)) {
                        jos.write(is.readAllBytes());
                    }
                }
            }
        }

        return new JarFile(output);
    }

    private ClassVisitor processClass(ClassWriter cw) {
        return new ClassVisitor(ASM9, cw) {
        };
    }
}
