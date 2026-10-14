package com.tristankechlo.livingthings.util;

import com.tristankechlo.livingthings.LivingThings;
import net.minecraft.resources.Identifier;

public final class LexiconEntries {

    public static final Identifier OSTRICH_NEST_BLOCK = create("items/ostrich_nest");

    public static final Identifier ANCIENT_BLAZE = create("hostile_mobs/ancient_blaze");
    public static final Identifier BABY_ENDER_DRAGON = create("hostile_mobs/baby_ender_dragon");
    public static final Identifier CRAB = create("neutral_mobs/crab");
    public static final Identifier ELEPHANT = create("neutral_mobs/elephant");
    public static final Identifier FLAMINGO = create("passive_mobs/flamingo");
    public static final Identifier GIRAFFE = create("neutral_mobs/giraffe");
    public static final Identifier KOALA = create("passive_mobs/koala");
    public static final Identifier LION = create("hostile_mobs/lion");
    public static final Identifier MANTARAY = create("passive_mobs/mantaray");
    public static final Identifier MONKEY = create("neutral_mobs/monkey");
    public static final Identifier NETHER_KNIGHT = create("hostile_mobs/nether_knight");
    public static final Identifier OSTRICH = create("passive_mobs/ostrich");
    public static final Identifier OWL = create("passive_mobs/owl");
    public static final Identifier PENGUIN = create("passive_mobs/penguin");
    public static final Identifier RACCOON = create("neutral_mobs/raccoon");
    public static final Identifier SEAHORSE = create("passive_mobs/seahorse");
    public static final Identifier SHARK = create("neutral_mobs/shark");
    public static final Identifier SHROOMIE = create("passive_mobs/shroomie");
    public static final Identifier SNAIL = create("passive_mobs/snail");
    public static final Identifier PEACOCK = create("passive_mobs/peacock");


    private static Identifier create(String path) {
        return Identifier.fromNamespaceAndPath(LivingThings.MOD_ID, path);
    }

}
