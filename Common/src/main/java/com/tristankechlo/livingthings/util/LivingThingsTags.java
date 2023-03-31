package com.tristankechlo.livingthings.util;

import com.tristankechlo.livingthings.LivingThings;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public final class LivingThingsTags {

    public static final TagKey<Block> DROPS_BANANAS = registerBlockTag("drops_bananas");
    public static final TagKey<Item> BANANAS = registerItemTag("bananas");

    public static final TagKey<Block> CRAP_SPAWNABLE_ON = spawnableOn("crap");
    public static final TagKey<Block> ELEPHANT_SPAWNABLE_ON = spawnableOn("elephant");
    public static final TagKey<Block> FLAMINGO_SPAWNABLE_ON = spawnableOn("flamingo");
    public static final TagKey<Block> GIRAFFE_SPAWNABLE_ON = spawnableOn("giraffe");
    public static final TagKey<Block> KOALA_SPAWNABLE_ON = spawnableOn("koala");
    public static final TagKey<Block> LION_SPAWNABLE_ON = spawnableOn("lion");
    public static final TagKey<Fluid> MANTARAY_SPAWNABLE_ON = registerFluidTag("mantaray_spawnable_on");
    public static final TagKey<Block> MONKEY_SPAWNABLE_ON = spawnableOn("monkey");
    public static final TagKey<Block> OSTRICH_SPAWNABLE_ON = spawnableOn("ostrich");
    public static final TagKey<Block> OWL_SPAWNABLE_ON = spawnableOn("owl");
    public static final TagKey<Block> PENGUIN_SPAWNABLE_ON = spawnableOn("penguin");
    public static final TagKey<Block> RACCOON_SPAWNABLE_ON = spawnableOn("raccoon");
    public static final TagKey<Fluid> SEAHORSE_SPAWNABLE_ON = registerFluidTag("seahorse_spawnable_on");
    public static final TagKey<Fluid> SHARK_SPAWNABLE_ON = registerFluidTag("shark_spawnable_on");
    public static final TagKey<Block> SHROOMIE_SPAWNABLE_ON = spawnableOn("shroomie");
    public static final TagKey<Block> SNAIL_SPAWNABLE_ON = spawnableOn("snail");
    public static final TagKey<Block> BABY_ENDER_DRAGON_SPAWNABLE_ON = spawnableOn("baby_ender_dragon");

    private static TagKey<Block> spawnableOn(String name) {
        return registerBlockTag(name + "_spawnable_on");
    }

    private static TagKey<Block> registerBlockTag(String name) {
        return TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(LivingThings.MOD_ID, name));
    }

    private static TagKey<Item> registerItemTag(String name) {
        return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(LivingThings.MOD_ID, name));
    }

    private static TagKey<Fluid> registerFluidTag(String name) {
        return TagKey.create(Registry.FLUID_REGISTRY, new ResourceLocation(LivingThings.MOD_ID, name));
    }

}
