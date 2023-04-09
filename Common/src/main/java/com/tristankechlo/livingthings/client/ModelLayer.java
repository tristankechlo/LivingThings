package com.tristankechlo.livingthings.client;

import com.tristankechlo.livingthings.LivingThings;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public final class ModelLayer {

    public static final ModelLayerLocation ELEPHANT = createLayerLocation("elephant");
    public static final ModelLayerLocation GIRAFFE = createLayerLocation("giraffe");
    public static final ModelLayerLocation LION = createLayerLocation("lion");
    public static final ModelLayerLocation SHARK = createLayerLocation("shark");
    public static final ModelLayerLocation PENGUIN = createLayerLocation("penguin");
    public static final ModelLayerLocation OSTRICH = createLayerLocation("ostrich");
    public static final ModelLayerLocation FLAMINGO = createLayerLocation("flamingo");
    public static final ModelLayerLocation CRAB = createLayerLocation("crab");
    public static final ModelLayerLocation MANTARAY = createLayerLocation("mantaray");
    public static final ModelLayerLocation RACCOON = createLayerLocation("raccoon");
    public static final ModelLayerLocation OWL = createLayerLocation("owl");
    public static final ModelLayerLocation ANCIENT_BLAZE = createLayerLocation("ancient_blaze");
    public static final ModelLayerLocation KOALA = createLayerLocation("koala");
    public static final ModelLayerLocation SNAIL = createLayerLocation("snail");
    public static final ModelLayerLocation MONKEY = createLayerLocation("monkey");
    public static final ModelLayerLocation MONKEY_SITTING = createLayerLocation("monkey_sitting");
    public static final ModelLayerLocation NETHER_KNIGHT = createLayerLocation("nether_knight");
    public static final ModelLayerLocation SHROOMIE = createLayerLocation("shroomie");
    public static final ModelLayerLocation SEAHORSE = createLayerLocation("seahorse");
    public static final ModelLayerLocation BABY_ENDER_DRAGON = createLayerLocation("baby_ender_dragon");
    public static final ModelLayerLocation BABY_ENDER_DRAGON_SITTING = createLayerLocation("baby_ender_dragon_sitting");
    public static final ModelLayerLocation PEACOCK = createLayerLocation("peacock");
    public static final ModelLayerLocation ANCIENT_ARMOR = createLayerLocation("ancientarmormodel");

    private static ModelLayerLocation createLayerLocation(final String name) {
        return new ModelLayerLocation(new ResourceLocation(LivingThings.MOD_ID, name), "main");
    }

}
