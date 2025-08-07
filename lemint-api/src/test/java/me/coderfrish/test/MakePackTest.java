package me.coderfrish.test;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class MakePackTest {
    @Test
    public void test() {
        try(DataOutputStream stream = new DataOutputStream(new FileOutputStream("D:\\LemonMint\\lemint-api\\src\\test\\resources\\dist\\test.pack"))) {
            stream.writeInt(0x1F7C07FB); // Magic Number
            stream.writeInt(0); // Version
            stream.writeUTF("test.js"); // Main

            File file = new File("D:\\LemonMint\\lemint-api\\src\\test\\resources\\src");
            for (String listFile : listFilesSmartlyOptimized(file)) {
                if (listFile.endsWith(".js") || listFile.endsWith(".mjs")) {
                    stream.writeUTF("source");
                    stream.writeUTF(listFile);
                    byte[] bytes = Files.readAllBytes(new File(file, listFile).toPath());
                    stream.writeInt(bytes.length);
                    stream.write(bytes);
                } else {
                    stream.writeUTF("resource");
                    stream.writeUTF(listFile);
                    byte[] bytes = Files.readAllBytes(new File(file, listFile).toPath());
                    stream.writeInt(bytes.length);
                    stream.write(bytes);
                }
            }

            stream.writeUTF("entries end");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> listFilesSmartlyOptimized(File rootDir) {
        List<String> result = new ArrayList<>();

        if (!rootDir.exists() || !rootDir.isDirectory()) {
            throw new IllegalArgumentException("提供的路径不是有效目录: " + rootDir.getAbsolutePath());
        }

        Collection<File> files = FileUtils.listFiles(
                rootDir,
                TrueFileFilter.INSTANCE,
                TrueFileFilter.INSTANCE
        );

        for (File file : files) {
            // 获取相对于根目录的路径
            String relativePath = rootDir.toURI().relativize(file.toURI()).getPath();
            // 移除最后的文件名分隔符（如果有）
            relativePath = relativePath.replaceAll("/$", "");
            result.add(relativePath);
        }

        return result;
    }
}
