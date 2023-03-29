package com.tristankechlo.livingthings;

import com.tristankechlo.livingthings.init.ModBlocks;
import com.tristankechlo.livingthings.init.ModEntities;
import com.tristankechlo.livingthings.init.ModItems;
import com.tristankechlo.livingthings.init.ModSounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LivingThings {

    public static final String MOD_ID = "livingthings";
    public static final String MOD_NAME = "Living Things";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static final String GITHUB_URL = "https://github.com/tristankechlo/LivingThings";
    public static final String GITHUB_ISSUE_URL = GITHUB_URL + "/issues";
    public static final String GITHUB_WIKI_URL = GITHUB_URL + "/wiki";
    public static final String DISCORD_URL = "https://discord.gg/bhUaWhq";
    public static final String CURSEFORGE_URL = "https://curseforge.com/minecraft/mc-mods/living-things";
    public static final String MODRINTH_URL = "https://modrinth.com/mod/living-things";

    public static void init() {
        //make sure all classes are loaded
        ModItems.init();
        ModBlocks.init();
        ModSounds.init();
        ModEntities.init();
    }

}
