package me.coderfrish.test;

import me.coderfrish.plugin.ScriptPluginManager;
import org.junit.jupiter.api.Test;

import java.io.File;

public class ScriptPluginTest {
    @Test
    public void test() {
        ScriptPluginManager.loadPlugins(new File("D:\\LemonMint\\run\\plugins").listFiles());
    }
}
