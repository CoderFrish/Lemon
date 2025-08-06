package me.coderfrish.plugin.api;

import me.coderfrish.plugin.ScriptPluginManager;
import me.coderfrish.plugin.ScriptPluginMeta;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginBase;
import org.graalvm.polyglot.Value;
import org.graalvm.polyglot.proxy.ProxyExecutable;

import java.util.ArrayList;
import java.util.Arrays;

public class ScriptPlugin {
    private boolean enable = false;

    public void registerEvent(Class<? extends Event> event, ProxyExecutable listener) {
        Bukkit.getPluginManager().registerEvent(event,
                new Listener() {},
                EventPriority.NORMAL,
                (listener0, event0) -> {
                    listener.execute(Value.asValue(event0));
                },
                getBukkitJavaPlugin()
        );
    }

    public void registerCommand(String command, ProxyExecutable executor, Value meta) {
        PluginCommand pluginCommand = new PluginCommand(command, getBukkitJavaPlugin());
        pluginCommand.setExecutor((sender, command0, label, args) ->
                (boolean) executor.execute(Value.asValue(sender), Value.asValue(command0), Value.asValue(label), Value.asValue(args)));

        Value description = meta.getMember("description");
        if (description != null) {
            pluginCommand.setUsage(description.asString());
        }

        Value usage = meta.getMember("usage");
        if (usage != null) {
            pluginCommand.setUsage(usage.asString());
        }

        Value aliases = meta.getMember("aliases");
        if (aliases != null) {
            pluginCommand.setAliases(new ArrayList<>(Arrays.asList(aliases.as(String[].class))));
        }

        Value permission = meta.getMember("permission");
        if (permission != null) {
            pluginCommand.setPermission(permission.asString());
        }

        Value permissionMessage = meta.getMember("permissionMessage");
        if (permissionMessage != null) {
            pluginCommand.permissionMessage(net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer.legacySection().deserialize(permissionMessage.toString()));
        }

        Bukkit.getCommandMap().register(getMeta().getName(), pluginCommand);
    }

    private PluginBase getBukkitJavaPlugin() {
        return ScriptPluginManager.getJavaPlugins().get(this);
    }

    public ScriptPluginMeta getMeta() {
        return ScriptPluginManager.getPlugins().get(this);
    }

    public synchronized boolean isEnable() {
        return enable;
    }

    public synchronized void setEnable(boolean enable) {
        if (enable) {
            ScriptPluginManager.enablePlugin(this);
        } else {
            ScriptPluginManager.disablePlugin(this);
        }
        this.enable = enable;
    }
}
