package com.tristankechlo.livingthings.util;

import com.tristankechlo.livingthings.LivingThings;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public final class LivingThingsTags {

    public static final TagKey<Block> DROPS_BANANAS = registerBlockTag("drops_bananas");

    // item tags for entity temptation
    public static final TagKey<Item> BABY_ENDERDRAGON_FOOD = mobFood("baby_ender_dragon");
    public static final TagKey<Item> CRAB_FOOD = mobFood("crab");
    public static final TagKey<Item> ELEPHANT_FOOD = mobFood("elephant");
    public static final TagKey<Item> ELEPHANT_TAMING_FOOD = mobFood("elephant_taming");
    public static final TagKey<Item> FLAMINGO_FOOD = mobFood("flamingo");
    public static final TagKey<Item> GIRAFFE_FOOD = mobFood("giraffe");
    public static final TagKey<Item> KOALA_FOOD = mobFood("koala");
    public static final TagKey<Item> LION_FOOD = mobFood("lion");
    public static final TagKey<Item> MONKEY_FOOD = mobFood("monkey");
    public static final TagKey<Item> OSTRICH_FOOD = mobFood("ostrich");
    public static final TagKey<Item> OWL_FOOD = mobFood("owl");
    public static final TagKey<Item> PEACOCK_FOOD = mobFood("peacock");
    public static final TagKey<Item> PENGUIN_FOOD = mobFood("penguin");
    public static final TagKey<Item> RACCOON_FOOD = mobFood("raccoon");
    public static final TagKey<Item> SHROOMIE_FOOD = mobFood("shroomie");
    public static final TagKey<Item> SNAIL_FOOD = mobFood("snail");

    // block tags to define where entities can spawn on
    public static final TagKey<Block> CRAB_SPAWNABLE_ON = spawnableOn("crab");
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
    public static final TagKey<Block> PEACOCK_SPAWNABLE_ON = spawnableOn("peacock");

    private static TagKey<Block> spawnableOn(String name) {
        return registerBlockTag(name + "_spawnable_on");
    }

    @SuppressWarnings("removal") // ResourceLocation constructor is removed in 1.21+ => stop forge from complaining here
    private static TagKey<Block> registerBlockTag(String name) {
        return TagKey.create(Registries.BLOCK, new ResourceLocation(LivingThings.MOD_ID, name));
    }

    private static TagKey<Item> mobFood(String name) {
        return registerItemTag(name + "_food");
    }

    @SuppressWarnings("removal") // ResourceLocation constructor is removed in 1.21+ => stop forge from complaining here
    private static TagKey<Item> registerItemTag(String name) {
        return TagKey.create(Registries.ITEM, new ResourceLocation(LivingThings.MOD_ID, name));
    }

    @SuppressWarnings("removal") // ResourceLocation constructor is removed in 1.21+ => stop forge from complaining here
    private static TagKey<Fluid> registerFluidTag(String name) {
        return TagKey.create(Registries.FLUID, new ResourceLocation(LivingThings.MOD_ID, name));
    }

}
