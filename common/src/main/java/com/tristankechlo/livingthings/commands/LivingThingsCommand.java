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
import net.minecraft.network.chat.TextComponent;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

public final class LivingThingsCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder<CommandSourceStack> command = literal(LivingThings.MOD_ID)
                .then(literal("github").executes((context) -> display(context, ProjectLinks.GITHUB)))
                .then(literal("issue").executes((context) -> display(context, ProjectLinks.GITHUB_ISSUES)))
                .then(literal("wiki").executes((context) -> display(context, ProjectLinks.GITHUB_WIKI)))
                .then(literal("discord").executes((context) -> display(context, ProjectLinks.DISCORD)))
                .then(literal("curseforge").executes((context) -> display(context, ProjectLinks.CURSEFORGE)))
                .then(literal("modrinth").executes((context) -> display(context, ProjectLinks.MODRINTH)));
        dispatcher.register(command);
        LivingThings.LOGGER.info("Command '/{}' registered", LivingThings.MOD_ID);
    }

    private static int display(CommandContext<CommandSourceStack> context, ProjectLinks type) {
        CommandSourceStack source = context.getSource();
        Component link = clickableLink(type.getUrl());
        Component message = new TextComponent(type.getMessage()).withStyle(ChatFormatting.WHITE).append(link);
        sendMessage(source, message, false);
        return 1;
    }

    private static MutableComponent start() {
        return new TextComponent("[" + LivingThings.MOD_NAME + "] ").withStyle(ChatFormatting.GOLD);
    }

    private static void sendMessage(CommandSourceStack source, Component message, boolean broadcastToOps) {
        MutableComponent start = start().append(message);
        source.sendSuccess(start, broadcastToOps);
    }

    private static MutableComponent clickableLink(String url) {
        MutableComponent mutableComponent = new TextComponent(url);
        mutableComponent.withStyle(ChatFormatting.GREEN, ChatFormatting.UNDERLINE);
        mutableComponent.withStyle(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url)));
        return mutableComponent;
    }
}
