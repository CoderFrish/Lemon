package dev.coderfrish.lirael.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

import java.util.function.Predicate;

public class LiraelCommand {
    private static final Predicate<CommandSourceStack> adminPermission = Commands.hasPermission(Commands.LEVEL_ADMINS);
    private static final Predicate<CommandSourceStack> allPermission = Commands.hasPermission(Commands.LEVEL_ALL);

    private static final LiteralArgumentBuilder<CommandSourceStack> version = Commands.literal("version");
    private static final LiteralArgumentBuilder<CommandSourceStack> lirael = Commands.literal("lirael");

    private static void register(LiteralArgumentBuilder<CommandSourceStack> builder) {
    }

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder<CommandSourceStack> builder = lirael.requires(allPermission);
        register(builder);
        dispatcher.register(builder);
    }
}
