package me.coderfrish.traium.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TraiumCommand extends Command {
    private static final Component PERMISSION_MSG = Component.text("No permission to execute this command!").color(NamedTextColor.RED);
    private static final Component SUB_COMMAND_NOT_EXIST_MSG = Component.text("Not exist command: ").color(NamedTextColor.RED);
    private static final Map<String, TraiumSubCommand> subCommandMap = new ConcurrentHashMap<>();

    public static void register(TraiumSubCommand subCommand) {
        String name = subCommand.getName();
        if (subCommandMap.containsKey(name)) {
            throw new IllegalArgumentException("There is already a command named " + name + ".");
        }

        subCommandMap.put(name, subCommand);
    }

    public void register() {
        Bukkit.getCommandMap().register("traium", this);
    }

    public TraiumCommand() {
        super("traium", "A Main Command for traium.", "/traium <subcommand>", new ArrayList<>());
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String @NotNull [] args) {
        if (args.length == 0) {
            sender.sendMessage(Component.text("Usage: ").append(Component.text(this.getUsage())));
        }

        if (args.length > 0) {
            String subCommand = args[0];

            if (subCommandMap.containsKey(subCommand)) {
                TraiumSubCommand traiumSubCommand = subCommandMap.get(subCommand);
                if (sender.hasPermission(traiumSubCommand.permission.keyword())) {
                    return subCommandMap.get(subCommand).execute(sender, subCommand, Arrays.copyOfRange(args, 1, args.length));
                } else {
                    sender.sendMessage(PERMISSION_MSG);
                }
            } else {
                sender.sendMessage(SUB_COMMAND_NOT_EXIST_MSG.append(Component.text(subCommand).color(NamedTextColor.RED)));
                return false;
            }
        }

        return true;
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String @NotNull [] args) throws IllegalArgumentException {
        if (args.length == 1){
            return subCommandMap.keySet().stream().filter(i -> sender.hasPermission(
                    subCommandMap.get(i).permission.keyword())).toList();
        }

        if (args.length > 1 && subCommandMap.containsKey(args[0])) {
            TraiumSubCommand traiumSubCommand = subCommandMap.get(args[0]);
            if (sender.hasPermission(traiumSubCommand.permission.keyword())) {
                return traiumSubCommand.tabComplete(sender, alias, Arrays.copyOfRange(args, 1, args.length));
            } else {
                return List.of();
            }
        }

        return List.of();
    }
}
