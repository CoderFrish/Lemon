package me.coderfrish.traium.command;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public abstract class TraiumSubCommand extends BukkitCommand{
    TraiumPermission permission = TraiumPermission.TRAIUM_USER_PERMISSION;

    public TraiumSubCommand(@NotNull String name, @NotNull String description, @NotNull TraiumPermission permission) {
        this(name, description, "", List.of());
        this.setPermission(permission.keyword());
        this.permission = permission;
    }

    private TraiumSubCommand(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
        super(name, description, usageMessage, aliases);
    }

    public abstract boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String @NotNull [] arg);

    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String @NotNull [] args) {
        return List.of();
    }
}
