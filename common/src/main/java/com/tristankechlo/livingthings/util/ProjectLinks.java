package com.tristankechlo.livingthings.util;

import net.minecraft.util.StringRepresentable;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ProjectLinks implements StringRepresentable {

    GITHUB("github", "https://github.com/tristankechlo/LivingThings", "Check out the source code on GitHub: "),
    GITHUB_ISSUES("issue", GITHUB.getUrl() + "/issues", "If you found an issue, submit it here: "),
    GITHUB_WIKI("wiki", GITHUB.getUrl() + "/wiki", "The wiki can be found here: "),
    DISCORD("discord", "https://discord.gg/bhUaWhq", "Join the Discord here: "),
    CURSEFORGE("curseforge", "https://curseforge.com/minecraft/mc-mods/living-things", "Check out the CurseForge page here: "),
    MODRINTH("modrinth", "https://modrinth.com/mod/living-things", "Check out the Modrinth page here: ");

    public static final ProjectLinks[] VALUES = values();
    public static final Map<String, ProjectLinks> BY_NAME;
    private final String name;
    private final String url;
    private final String message;

    ProjectLinks(String name, String url, String message) {
        this.name = name;
        this.url = url;
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public String getMessage() {
        return message;
    }

    public static ProjectLinks byName(String name, ProjectLinks fallback) {
        ProjectLinks type = BY_NAME.get(name);
        return type != null ? type : fallback;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    public static Stream<String> getNames() {
        return Stream.of(VALUES).map(ProjectLinks::getSerializedName);
    }

    static {
        BY_NAME = Stream.of(VALUES).collect(Collectors.toMap(ProjectLinks::getSerializedName, type -> type));
    }

}
