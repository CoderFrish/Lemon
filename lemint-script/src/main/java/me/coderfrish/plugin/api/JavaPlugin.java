package me.coderfrish.plugin.api;

import io.papermc.paper.plugin.configuration.PluginMeta;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginBase;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

public class JavaPlugin extends PluginBase {
    private final ScriptPlugin scriptPlugin;

    public JavaPlugin(ScriptPlugin scriptPlugin) {
        this.scriptPlugin = scriptPlugin;
    }

    @Override
    public @NotNull File getDataFolder() {
        return new File(Bukkit.getPluginsFolder(), getName());
    }

    @Override
    public @NotNull PluginDescriptionFile getDescription() {
        return new PluginDescriptionFile(scriptPlugin.getPluginMeta().getName(), scriptPlugin.getPluginMeta().getVersion(), "nms");
    }

    @Override
    public @NotNull PluginMeta getPluginMeta() {
        return getDescription();
    }

    @Override
    public @NotNull FileConfiguration getConfig() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public @Nullable InputStream getResource(@NotNull String filename) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void saveConfig() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void saveDefaultConfig() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void saveResource(@NotNull String resourcePath, boolean replace) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void reloadConfig() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public @NotNull PluginLoader getPluginLoader() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public @NotNull Server getServer() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void onDisable() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void onLoad() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void onEnable() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public boolean isNaggable() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void setNaggable(boolean canNag) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public @Nullable ChunkGenerator getDefaultWorldGenerator(@NotNull String worldName, @Nullable String id) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public @Nullable BiomeProvider getDefaultBiomeProvider(@NotNull String worldName, @Nullable String id) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public @NotNull Logger getLogger() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public @NotNull LifecycleEventManager<Plugin> getLifecycleManager() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        throw new UnsupportedOperationException("Not supported.");
    }
}
