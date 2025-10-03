package me.coderfrish.traium.command.subcommands;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class TraiumSubCommand extends BukkitCommand {
    public TraiumSubCommand(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
        super(name, description, usageMessage, aliases);
    }

    public TraiumSubCommand(@NotNull String name) {
        super(name);
    }

    public abstract boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String @NotNull [] arg);

    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String @NotNull [] args) {
        return List.of();
    }
}
