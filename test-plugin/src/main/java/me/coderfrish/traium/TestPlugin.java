package me.coderfrish.traium;

import org.bukkit.plugin.java.JavaPlugin;

public class TestPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        System.out.println("TestPlugin has been enabled");
        super.onEnable();
    }
}
