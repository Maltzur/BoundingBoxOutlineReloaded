package com.irtimaled.bbor.client.commands;

import com.irtimaled.bbor.client.providers.SlimeChunkProvider;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.command.Commands;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.util.text.StringTextComponent;

public class SeedCommand {
    public static void register(CommandDispatcher<ISuggestionProvider> commandDispatcher) {
        LiteralArgumentBuilder command = Commands.literal("bbor:seed")
                .then(Commands.argument("seed", StringArgumentType.string())
                        .executes(context -> {
                            String argument = StringArgumentType.getString(context, "seed");
                            handleSeedCommand(argument);
                            return 0;
                        }))
                .executes(context -> {
                    throw INCOMPLETE_COMMAND.createWithContext(new StringReader(context.getInput()));
                });
        commandDispatcher.register(command);
    }

    private static void handleSeedCommand(String argument) {
        Long seed = parseNumericSeed(argument);
        if (seed == null) {
            seed = (long) argument.hashCode();
        }
        SlimeChunkProvider.setSeed(seed);
    }

    private static Long parseNumericSeed(String argument) {
        try {
            return Long.parseLong(argument);
        } catch (final NumberFormatException ex) {
            return null;
        }
    }

    private static final SimpleCommandExceptionType INCOMPLETE_COMMAND =
            new SimpleCommandExceptionType(new StringTextComponent("Missing argument (expected seed)"));

}
