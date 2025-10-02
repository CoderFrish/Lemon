package me.coderfrish.traium.command;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class TraiumSubCommand {
    private final String name;
    private final String description;

    public TraiumSubCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public abstract boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String @NotNull [] arg);

    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String @NotNull [] args) {
        return List.of();
    }
}
