package com.example.examplemod.commands;


import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;

public class HungerCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("hunger")
                .requires((commandSource) -> commandSource.hasPermission(2))
                .then(Commands.argument("count", IntegerArgumentType.integer(0,20))
                        .executes((commandSourceCommandContext) -> hunger(commandSourceCommandContext.getSource(), IntegerArgumentType.getInteger(commandSourceCommandContext, "count")))));
    }

    private static int hunger(CommandSource commandSource, int count) throws CommandSyntaxException {
            ServerPlayerEntity playerEntity = commandSource.getPlayerOrException();
            playerEntity.getFoodData().setFoodLevel(count);
            commandSource.sendSuccess(new TranslationTextComponent("commands.hunger.success", playerEntity.getDisplayName(),count), true);
            return 1;
    }
}