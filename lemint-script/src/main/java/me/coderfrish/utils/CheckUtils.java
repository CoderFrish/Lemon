package me.coderfrish.utils;

import me.coderfrish.plugin.exception.InvalidPluginPackException;

import java.io.*;

public class CheckUtils {
    public static boolean verifyFile(File file, File file0) {
        try (FileInputStream fis = new FileInputStream(file); FileInputStream fis0 = new FileInputStream(file0)) {
            String actualHash = org.apache.commons.codec.digest.DigestUtils.sha256Hex(fis);
            String expectedHash = org.apache.commons.codec.digest.DigestUtils.sha256Hex(fis0);
            return actualHash.equalsIgnoreCase(expectedHash);
        } catch (IOException e) {
            throw new InvalidPluginPackException(e);
        }
    }
}
