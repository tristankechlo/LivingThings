package com.tristankechlo.livingthings.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.util.ProjectLinks;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

public final class LivingThingsCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder<CommandSourceStack> command = literal(LivingThings.MOD_ID)
                .then(argument("type", ProjectLinksArgumentType.get()).executes(LivingThingsCommand::execute));
        dispatcher.register(command);
        LivingThings.LOGGER.info("Command '/{}' registered", LivingThings.MOD_ID);
    }

    private static int execute(CommandContext<CommandSourceStack> context) {
        ProjectLinks type = context.getArgument("type", ProjectLinks.class);
        CommandSourceStack source = context.getSource();
        Component link = clickableLink(type.getUrl());
        Component message = Component.literal(type.getMessage()).withStyle(ChatFormatting.WHITE).append(link);
        sendMessage(source, message, false);
        return 1;
    }

    private static MutableComponent start() {
        return Component.literal("[" + LivingThings.MOD_NAME + "] ").withStyle(ChatFormatting.GOLD);
    }

    private static void sendMessage(CommandSourceStack source, Component message, boolean broadcastToOps) {
        MutableComponent start = start().append(message);
        source.sendSuccess(start, broadcastToOps);
    }

    private static MutableComponent clickableLink(String url) {
        MutableComponent mutableComponent = Component.literal(url);
        mutableComponent.withStyle(ChatFormatting.GREEN, ChatFormatting.UNDERLINE);
        mutableComponent.withStyle(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url)));
        return mutableComponent;
    }
}
