package me.coderfrish.transformer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import me.coderfrish.exception.LemonMintRuntimeException;
import me.coderfrish.utility.DigestUtility;
import org.apache.commons.io.FileUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ASMTransformer {
    private static ASMTransformer INSTANCE;
    private static Logger logger = LoggerFactory.getLogger("ASMTransformer");

    public static ASMTransformer create(File pluginFolder) {
        if (INSTANCE == null) {
            INSTANCE = new ASMTransformer(pluginFolder);
        }

        return INSTANCE;
    }

    public static ASMTransformer getINSTANCE() {
        return INSTANCE;
    }

    private JsonObject manager = new JsonObject();
    private final File pluginFolder;
    private final File rootFolder;
    private final File indexFile;

    public ASMTransformer(File pluginFolder) {
        this.pluginFolder = pluginFolder;
        this.rootFolder = new File(pluginFolder, ".plugin-transferred");
        this.indexFile = new File(rootFolder, "index.json");

        if (me.coderfrish.config.experiment.LegacyBukkitPluginConfig.transformer) {
            if (indexFile.exists()) {
                try {
                    this.manager = GSON.fromJson(FileUtils.readFileToString(indexFile, StandardCharsets.UTF_8), JsonObject.class);
                } catch (IOException ex) {
                    LemonMintRuntimeException e = new LemonMintRuntimeException(ex);
                    logger.warn("Failed to transform jar file - {}", ex.getMessage());
                    logger.error(ex.getMessage(), e);
                    this.manager = new JsonObject();
                }
            }
        }
    }

    public JarFile transform(JarFile jarFile) {
        try {
            if (!me.coderfrish.config.experiment.LegacyBukkitPluginConfig.transformer)
                return jarFile;

            String jarName = jarFile.getName();
            String name = jarName.substring(jarName.indexOf("\\") + 1);
            File originalFile = new File(pluginFolder, name);

            if (isFoliaSupported(originalFile))
                return jarFile;

            File transformedFile = new File(rootFolder, originalFile.getName());

            if (!transformedFile.getParentFile().exists())
                transformedFile.getParentFile().mkdirs();

            if (!manager.has(originalFile.getName())) {
                manager.addProperty(originalFile.getName(), DigestUtility.computeFileSHA256(originalFile));
                return transform(originalFile, transformedFile, name);
            }

            if (manager.has(originalFile.getName())) {
                String sha256_0 = DigestUtility.computeFileSHA256(originalFile);
                String sha256_1 = manager.get(originalFile.getName()).getAsString();

                if (!sha256_0.equals(sha256_1)) {
                    manager.remove(originalFile.getName());
                    FileUtils.delete(transformedFile);

                    return transform(jarFile);
                }
            }

            return new JarFile(transformedFile);
        } catch (Exception ex) {
            LemonMintRuntimeException e = new LemonMintRuntimeException(ex);
            logger.warn("Failed to transform jar file - {}", ex.getMessage());
            logger.error(ex.getMessage(), e);
            return jarFile;
        }
    }

    public static final int ASM_VERSION = Opcodes.ASM9;

    private JarFile transform(File originalFile, File transformedFile, String path) {
        logger.info("Patching legacy bukkit plugin - {}", path);
        try {
            try(JarFile file = new JarFile(originalFile); OutputStream fos = new FileOutputStream(transformedFile);
                JarOutputStream jos = new JarOutputStream(fos)) {
                Enumeration<JarEntry> entries = file.entries();
                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();

                    JarEntry newEntry = new JarEntry(entry);
                    jos.putNextEntry(newEntry);

                    if (entry.getName().endsWith(".class") && !entry.isDirectory()) {
                        try(InputStream is = file.getInputStream(entry)) {
                            ClassReader cr = new ClassReader(is);
                            ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);

                            cr.accept(new EntityTransformer(cw), ClassReader.EXPAND_FRAMES);
                            jos.write(cw.toByteArray());
                        }
                    } else {
                        try(InputStream is = file.getInputStream(entry)) {
                            jos.write(is.readAllBytes());
                        }
                    }
                }
            }

            return new JarFile(transformedFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public void save() {
        if (!me.coderfrish.config.experiment.LegacyBukkitPluginConfig.transformer)
            return;

        try {
            FileUtils.write(indexFile, GSON.toJson(manager), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            LemonMintRuntimeException e = new LemonMintRuntimeException(ex);
            logger.warn("Failed to transform jar file - {}", ex.getMessage());
            logger.error(ex.getMessage(), e);
        }
    }

    public static boolean isFoliaSupported(File jarFile) throws IOException {
        try (ZipInputStream zis = new ZipInputStream(Files.newInputStream(jarFile.toPath()))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("plugin.yml") || entry.getName().equals("paper-plugin.yml")) {
                    String content = new String(zis.readAllBytes(), StandardCharsets.UTF_8);
                    return content.lines().anyMatch(line -> line.trim().equalsIgnoreCase("folia-supported: true"));
                }
            }
        }
        return false;
    }
}
