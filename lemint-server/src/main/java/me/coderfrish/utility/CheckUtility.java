package me.coderfrish.utility;

import me.coderfrish.plugin.exception.InvalidPluginPackException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CheckUtility {
    public static String computeSHA_1(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            return org.apache.commons.codec.digest.DigestUtils.sha1Hex(fis);
        } catch (IOException e) {
            throw new InvalidPluginPackException(e);
        }
    }
}
