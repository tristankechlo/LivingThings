package com.tristankechlo.livingthings.util;

import net.minecraft.util.StringRepresentable;

import java.net.URI;
import java.net.URISyntaxException;

public enum ProjectLinks implements StringRepresentable {

    GITHUB("github", "https://github.com/tristankechlo/LivingThings", "Check out the source code on GitHub: "),
    GITHUB_ISSUES("issue", GITHUB.getUri() + "/issues", "If you found an issue, submit it here: "),
    GITHUB_WIKI("wiki", GITHUB.getUri() + "/wiki", "The wiki can be found here: "),
    DISCORD("discord", "https://discord.gg/bhUaWhq", "Join the Discord here: "),
    CURSEFORGE("curseforge", "https://curseforge.com/minecraft/mc-mods/living-things", "Check out the CurseForge page here: "),
    MODRINTH("modrinth", "https://modrinth.com/mod/living-things", "Check out the Modrinth page here: ");

    private final String name;
    private final URI uri;
    private final String url;
    private final String message;

    ProjectLinks(String name, String url, String message) {
        this.name = name;
        try {
            this.uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid URL for " + name + ": " + url, e);
        }
        this.url = url;
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public URI getUri() {
        return uri;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

}
