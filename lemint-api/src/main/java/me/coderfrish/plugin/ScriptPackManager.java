package me.coderfrish.plugin;

import me.coderfrish.constant.NumberConstant;
import me.coderfrish.plugin.exception.InvalidPluginPackException;
import me.coderfrish.plugin.pack.PackEntry;
import me.coderfrish.plugin.pack.PluginPack;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ScriptPackManager {
    private static final List<PluginPack> packs = new CopyOnWriteArrayList<>();

    public static PluginPack loadPack(File file, String sha) {
        try(FileInputStream fis = new FileInputStream(file);
            DataInputStream dis = new DataInputStream(fis)) {
            int magicNumber = dis.readInt();

            if (magicNumber != NumberConstant.PLUGIN_PACK_MAGIC_NUMBER) {
                throw new InvalidPluginPackException("Invalid plugin pack file.");
            }

            int version = dis.readInt();
            String main = dis.readUTF();

            List<PackEntry> entries = new ArrayList<>();
            for (;;) {
                String type = dis.readUTF();
                if (type.equals("entries end")) {
                    break;
                }

                String name = dis.readUTF();
                int size = dis.readInt();

                byte[] data = new byte[size];
                dis.readFully(data);
                entries.add(new PackEntry(type, name, data));
            }

            PluginPack pluginPack = new PluginPack(version, main, sha, entries);
            packs.add(pluginPack);
            return pluginPack;
        } catch (IOException e) {
            throw new InvalidPluginPackException(e);
        }
    }

    public static List<PluginPack> getPacks() {
        return packs;
    }
}
