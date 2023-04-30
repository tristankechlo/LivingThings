package com.tristankechlo.livingthings.commands;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.tristankechlo.livingthings.util.ProjectLinks;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.TextComponent;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public final class ProjectLinksArgumentType implements ArgumentType<ProjectLinks> {

    private static final DynamicCommandExceptionType ERROR_INVALID = new DynamicCommandExceptionType((o) -> new TextComponent("Invalid type: " + o));
    private static final ProjectLinksArgumentType INSTANCE = new ProjectLinksArgumentType();
    private static final Collection<String> EXAMPLES = ProjectLinks.getNames().toList();

    @Override
    public ProjectLinks parse(StringReader reader) throws CommandSyntaxException {
        String s = reader.readUnquotedString();
        ProjectLinks type = ProjectLinks.byName(s, null);
        if (type == null) {
            throw ERROR_INVALID.createWithContext(reader, s);
        } else {
            return type;
        }
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        if (context.getSource() instanceof SharedSuggestionProvider) {
            return SharedSuggestionProvider.suggest(ProjectLinks.getNames(), builder);
        }
        return Suggestions.empty();
    }

    @Override
    public Collection<String> getExamples() {
        return EXAMPLES;
    }

    public static ProjectLinksArgumentType get() {
        return INSTANCE;
    }

}
