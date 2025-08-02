package me.coderfrish.plugin;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.ThreeArgFunction;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

import java.util.ArrayList;

public class PluginCommand {
    public final LuaFunction register = new ThreeArgFunction() {
        @Override
        public LuaValue call(LuaValue name, LuaValue executor, LuaValue meta) {
            LuaValue description = meta.get("description");
            if (description == NIL)
                description = LuaValue.valueOf("");

            LuaValue usage = meta.get("usage");
            if (usage == NIL)
                usage = LuaValue.valueOf("/");

            Bukkit.getCommandMap().register(name.tojstring(), new LuaCommand(name.tojstring(), description.tojstring(), usage.tojstring()) {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String name, @NotNull String @NotNull [] args) {
                    return executor.call(
                            CoerceJavaToLua.coerce(sender),
                            LuaValue.valueOf(name),
                            CoerceJavaToLua.coerce(args)
                    ).toboolean();
                }
            });
            return NIL;
        }
    };

    private static abstract class LuaCommand extends BukkitCommand {
        protected LuaCommand(@NotNull String name, @NotNull String description, @NotNull String usageMessage) {
            super(name, description, usageMessage, new ArrayList<>());
        }

        @Override
        public abstract boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String @NotNull [] args);
    }
}
