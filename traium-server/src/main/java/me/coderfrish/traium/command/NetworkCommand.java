package me.coderfrish.traium.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

import static me.coderfrish.traium.command.TraiumCommand.SUB_COMMAND_NOT_EXIST_MSG;

public class NetworkCommand extends TraiumSubCommand {
    public NetworkCommand(@NotNull String name, @NotNull TraiumPermission permission) {
        super(name, "This is a command about network.", permission);
        this.setUsage("/traium network <command>");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String @NotNull [] args) {
        if (args.length == 0) {
            sender.sendMessage(Component.text("Usage: ").append(Component.text(this.getUsage())));
        }

        if (args.length > 0) {
            String command = args[0];

            if (command.equals("profiler")) {
                @NotNull String[] arg = Arrays.copyOfRange(args, 1, args.length);

                if (arg.length == 0) {
                    sender.sendMessage(Component.text("Usage: /traium network profiler <command>"));
                }

                if (arg.length > 0) {
                    String profilerCommand = arg[0];

                    if (profilerCommand.equals("start")) {
                        if (me.coderfrish.traium.profiler.NetworkProfiler.isProfiling()) {
                            sender.sendMessage(Component.text("Network profiler is already running").color(NamedTextColor.RED));
                            return true;
                        }

                        me.coderfrish.traium.profiler.NetworkProfiler.start();
                        sender.sendMessage(Component.text("Network profiler is Started").color(NamedTextColor.GREEN));
                    } else if (profilerCommand.equals("stop")) {
                        if (!me.coderfrish.traium.profiler.NetworkProfiler.isProfiling()) {
                            sender.sendMessage(Component.text("Network profiler is not running").color(NamedTextColor.RED));
                            return true;
                        }

                        me.coderfrish.traium.profiler.NetworkProfiler.stop();
                        sender.sendMessage(Component.text("Network profiler is stopped").color(NamedTextColor.GREEN));
                    } else {
                        sender.sendMessage(SUB_COMMAND_NOT_EXIST_MSG.append(Component.text(profilerCommand).color(NamedTextColor.RED)));
                    }
                }
            } else {
                sender.sendMessage(SUB_COMMAND_NOT_EXIST_MSG.append(Component.text(command).color(NamedTextColor.RED)));
            }
        }

        return true;
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String @NotNull [] args) {
        if (args.length > 1) {
            if (args[0].equals("profiler")) {
                return List.of("start", "stop");
            }
        }

        return List.of("profiler");
    }
}
