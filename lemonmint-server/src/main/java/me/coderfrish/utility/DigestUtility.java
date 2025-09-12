package me.coderfrish.utility;

//import me.coderfrish.plugin.exception.InvalidPluginPackException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Deprecated
public class DigestUtility {
    public static String computeFileSHA256(File file) {
        try (FileInputStream fis = new FileInputStream(file);) {
            return org.apache.commons.codec.digest.DigestUtils.sha256Hex(fis);
        } catch (IOException e) {
//            throw new InvalidPluginPackException(e);
            return "null";
        }
    }
}
