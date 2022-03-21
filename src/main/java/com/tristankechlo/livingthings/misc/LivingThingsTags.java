package com.tristankechlo.livingthings.misc;

import com.tristankechlo.livingthings.LivingThings;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public final class LivingThingsTags {

	public static final TagKey<Block> DROPS_BANANAS = registerBlockTag("drops_bananas");
	public static final TagKey<Item> BANANAS = registerItemTag("bananas");

	public static final TagKey<Block> CRAP_SPAWNABLE_ON = spawnableOn(Names.CRAB);
	public static final TagKey<Block> ELEPHANT_SPAWNABLE_ON = spawnableOn(Names.ELEPHANT);
	public static final TagKey<Block> FLAMINGO_SPAWNABLE_ON = spawnableOn(Names.FLAMINGO);
	public static final TagKey<Block> GIRAFFE_SPAWNABLE_ON = spawnableOn(Names.GIRAFFE);
	public static final TagKey<Block> KOALA_SPAWNABLE_ON = spawnableOn(Names.KOALA);
	public static final TagKey<Block> LION_SPAWNABLE_ON = spawnableOn(Names.LION);
	public static final TagKey<Fluid> MANTARAY_SPAWNABLE_ON = registerFluidTag(Names.MANTARAY + "_spawnable_on");
	public static final TagKey<Block> MONKEY_SPAWNABLE_ON = spawnableOn(Names.MONKEY);
	public static final TagKey<Block> OSTRICH_SPAWNABLE_ON = spawnableOn(Names.OSTRICH);
	public static final TagKey<Block> OWL_SPAWNABLE_ON = spawnableOn(Names.OWL);
	public static final TagKey<Block> PENGUIN_SPAWNABLE_ON = spawnableOn(Names.PENGUIN);
	public static final TagKey<Block> RACCOON_SPAWNABLE_ON = spawnableOn(Names.RACCOON);
	public static final TagKey<Fluid> SEAHORSE_SPAWNABLE_ON = registerFluidTag(Names.SEAHORSE + "_spawnable_on");
	public static final TagKey<Fluid> SHARK_SPAWNABLE_ON = registerFluidTag(Names.SHARK + "_spawnable_on");
	public static final TagKey<Block> SHROOMIE_SPAWNABLE_ON = spawnableOn(Names.SHROOMIE);
	public static final TagKey<Block> SNAIL_SPAWNABLE_ON = spawnableOn(Names.SNAIL);
	public static final TagKey<Block> BABY_ENDER_DRAGON_SPAWNABLE_ON = spawnableOn(Names.BABY_ENDER_DRAGON);

	private static TagKey<Block> spawnableOn(String name) {
		return registerBlockTag(name + "_spawnable_on");
	}

	private static TagKey<Block> registerBlockTag(String name) {
		return BlockTags.create(new ResourceLocation(LivingThings.MOD_ID, name));
	}

	private static TagKey<Item> registerItemTag(String name) {
		return ItemTags.create(new ResourceLocation(LivingThings.MOD_ID, name));
	}

	private static TagKey<Fluid> registerFluidTag(String name) {
		return FluidTags.create(new ResourceLocation(LivingThings.MOD_ID, name));
	}

}
