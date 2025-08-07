package me.coderfrish.plugin;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import me.coderfrish.plugin.exception.InvalidPluginPackException;
import me.coderfrish.plugin.pack.PackEntry;
import me.coderfrish.plugin.pack.PluginPack;
import me.coderfrish.utils.CheckUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.bukkit.util.FileUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class ScriptUnpackManager {
    private static final JsonObject index = new JsonObject();
    public static File dataFolder;

    public static void init(File file) {
        dataFolder = new File(file, ".script-unpacked");
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }

        try {
            File index = new File(dataFolder, "index.json");
            if (index.exists()) {
                String s = Files.readString(index.toPath(), StandardCharsets.UTF_8);
                new Gson().fromJson(s, JsonObject.class).asMap()
                        .forEach(ScriptUnpackManager.index::add);
            }
        } catch (IOException e) {
            throw new InvalidPluginPackException(e);
        }
    }

    public static void register(File file) {
        File copyFile = new File(dataFolder, file.getName());
        if (!copyFile.exists()) {
            try {
                FileUtils.copyFile(file, copyFile);
            } catch (IOException e) {
                throw new InvalidPluginPackException(e);
            }

            try(FileInputStream fis = new FileInputStream(file)) {
                index.addProperty(file.getName(), DigestUtils.sha256Hex(fis));
            } catch (IOException e) {
                throw new InvalidPluginPackException(e);
            }
        }

        if (!CheckUtils.verifyFile(file, copyFile)) {
            File data = new File(dataFolder, index.get(file.getName()).getAsString());
            try {
                FileUtils.delete(copyFile);
                FileUtils.copyFile(file, copyFile);
                FileUtils.deleteDirectory(data);
            } catch (IOException e) {
                throw new InvalidPluginPackException(e);
            }

            try(FileInputStream fis = new FileInputStream(file)) {
                if (index.has(file.getName())) {
                    index.remove(file.getName());
                    index.addProperty(file.getName(), DigestUtils.sha256Hex(fis));
                }
            } catch (IOException e) {
                throw new InvalidPluginPackException(e);
            }
        }

        File data0 = new File(dataFolder, index.get(file.getName()).getAsString());
        PluginPack pluginPack = ScriptPackManager.loadPack(file, index.get(file.getName()).getAsString());
        if (!data0.exists()) {
            for (PackEntry entry : pluginPack.getEntries()) {
                File entryFile = new File(data0, entry.getName());
                if (!entryFile.getParentFile().exists()) {
                    entryFile.getParentFile().mkdirs();
                }

                if (!entryFile.exists()) {
                    try {
                        entryFile.createNewFile();
                        FileUtils.writeByteArrayToFile(entryFile, entry.getBody());
                    } catch (IOException e) {
                        throw new InvalidPluginPackException(e);
                    }
                }
            }
        }
    }

    public static void save() {
        File dataFile =  new File(dataFolder, "index.json");
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                throw new InvalidPluginPackException(e);
            }
        }

        try {
            Files.writeString(dataFile.toPath(),
                    new Gson().toJson(index)
                    , StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new InvalidPluginPackException(e);
        }
    }
}
