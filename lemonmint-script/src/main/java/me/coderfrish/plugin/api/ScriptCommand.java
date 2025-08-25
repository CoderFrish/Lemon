package me.coderfrish.plugin.api;

import me.coderfrish.plugin.ScriptPluginManager;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.PluginBase;
import org.graalvm.polyglot.Value;
import org.graalvm.polyglot.proxy.ProxyExecutable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScriptCommand {
    private final ScriptPlugin plugin;
    private final ScriptPluginMeta meta;

    public ScriptCommand(ScriptPlugin plugin, ScriptPluginMeta meta) {
        this.plugin = plugin;
        this.meta = meta;
    }

    public void register(String command, ProxyExecutable executor, ProxyExecutable tabCompleter, Value meta) {
        PluginCommand pluginCommand = null;
        //  new PluginCommand(command, getBukkitJavaPlugin())
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

        ProxyExecutable finallyTabCompleter;
        if (tabCompleter == null) {
            finallyTabCompleter = arguments -> new String[0];
        } else {
            finallyTabCompleter = tabCompleter;
        }


        TabCompleter completer = (sender, command1, label, args) -> {
            String[] strings = Value.asValue(finallyTabCompleter.execute(
                    Value.asValue(sender), Value.asValue(command1), Value.asValue(label), Value.asValue(args)
            )).as(String[].class);

            return new ArrayList<>(Arrays.asList(strings));
        };

        pluginCommand.setTabCompleter(completer);
        Bukkit.getCommandMap().register(this.meta.getName(), pluginCommand);
    }

    private PluginBase getBukkitJavaPlugin() {
        return ScriptPluginManager.getJavaPlugins().get(plugin);
    }
}
