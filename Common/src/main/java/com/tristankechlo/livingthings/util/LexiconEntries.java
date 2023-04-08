package com.tristankechlo.livingthings.util;

import com.tristankechlo.livingthings.LivingThings;
import net.minecraft.resources.ResourceLocation;

public final class LexiconEntries {

    public static final ResourceLocation OSTRICH_NEST_BLOCK = create("items/ostrich_nest");

    public static final ResourceLocation ANCIENT_BLAZE = create("hostile_mobs/ancient_blaze");
    public static final ResourceLocation BABY_ENDER_DRAGON = create("hostile_mobs/baby_ender_dragon");
    public static final ResourceLocation CRAB = create("neutral_mobs/crab");
    public static final ResourceLocation ELEPHANT = create("neutral_mobs/elephant");
    public static final ResourceLocation FLAMINGO = create("passive_mobs/flamingo");
    public static final ResourceLocation GIRAFFE = create("neutral_mobs/giraffe");
    public static final ResourceLocation KOALA = create("passive_mobs/koala");
    public static final ResourceLocation LION = create("hostile_mobs/lion");
    public static final ResourceLocation MANTARAY = create("passive_mobs/mantaray");
    public static final ResourceLocation MONKEY = create("neutral_mobs/monkey");
    public static final ResourceLocation NETHER_KNIGHT = create("hostile_mobs/nether_knight");
    public static final ResourceLocation OSTRICH = create("passive_mobs/ostrich");
    public static final ResourceLocation OWL = create("passive_mobs/owl");
    public static final ResourceLocation PENGUIN = create("passive_mobs/penguin");
    public static final ResourceLocation RACCOON = create("neutral_mobs/raccoon");
    public static final ResourceLocation SEAHORSE = create("passive_mobs/seahorse");
    public static final ResourceLocation SHARK = create("neutral_mobs/shark");
    public static final ResourceLocation SHROOMIE = create("passive_mobs/shroomie");
    public static final ResourceLocation SNAIL = create("passive_mobs/snail");


    private static ResourceLocation create(String path) {
        return new ResourceLocation(LivingThings.MOD_ID, path);
    }

}
