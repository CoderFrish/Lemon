package me.coderfrish.plugin;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.jetbrains.annotations.NotNull;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.ThreeArgFunction;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.CoerceLuaToJava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.luaj.vm2.LuaValue.NIL;

public class PluginCommand {
    private final Plugin plugin;

    public PluginCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    public void register(String command, LuaFunction executor, LuaTable meta) {
        this.register(command, executor, new ThreeArgFunction() {
            @Override
            public LuaValue call(LuaValue arg1, LuaValue arg2, LuaValue arg3) {
                return LuaTable.tableOf();
            }
        }, meta);
    }

    public void register(String command, LuaFunction executor, LuaFunction compiler, LuaTable meta) {
        LuaValue description = meta.get("description");
        if (description == NIL)
            description = LuaValue.valueOf("");

        LuaValue usage = meta.get("usage");
        if (usage == NIL)
            usage = LuaValue.valueOf("/");

        LuaValue aliases = meta.get("aliases");
        if (aliases == NIL)
            aliases = CoerceJavaToLua.coerce(new String[0]);

        String[] a = (String[]) CoerceLuaToJava.coerce(aliases, String[].class);
        List<String> buffer = new ArrayList<>(Arrays.asList(a));

        Bukkit.getCommandMap().register(command, plugin.meta.getName(), new LuaCommand(command, description.tojstring(), usage.tojstring(), buffer) {
            @Override
            public boolean execute(@NotNull CommandSender sender, @NotNull String name, @NotNull String @NotNull [] args) {
                return executor.call(
                        CoerceJavaToLua.coerce(sender),
                        LuaValue.valueOf(name),
                        CoerceJavaToLua.coerce(args)
                ).toboolean();
            }

            @Override
            public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String name, @NotNull String @NotNull [] args) throws IllegalArgumentException {
                LuaValue tab = compiler.call(
                        CoerceJavaToLua.coerce(sender),
                        LuaValue.valueOf(name),
                        CoerceJavaToLua.coerce(args)
                );

                List<String> result = new ArrayList<>();
                for (LuaValue key : tab.checktable().keys()) {
                    result.add(tab.get(key).tojstring());
                }

                return result;
            }
        });
    }

    private static abstract class LuaCommand extends BukkitCommand {
        protected LuaCommand(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
            super(name, description, usageMessage, aliases);
        }

        @Override
        public abstract boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String @NotNull [] args);
    }
}
