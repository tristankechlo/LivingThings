package com.tristankechlo.livingthings.misc;

import com.tristankechlo.livingthings.LivingThings;

import net.minecraft.resources.ResourceLocation;

public final class LivingThingsTags {

	public static final ResourceLocation DROPS_BANANAS = new ResourceLocation(LivingThings.MOD_ID, "drops_bananas");
	public static final ResourceLocation BANANAS = new ResourceLocation(LivingThings.MOD_ID, "bananas");

	public static final ResourceLocation CRAP_SPAWNABLE_ON = get(Names.CRAB + "_spawnable_on");
	public static final ResourceLocation ELEPHANT_SPAWNABLE_ON = get(Names.ELEPHANT + "_spawnable_on");
	public static final ResourceLocation FLAMINGO_SPAWNABLE_ON = get(Names.FLAMINGO + "_spawnable_on");
	public static final ResourceLocation GIRAFFE_SPAWNABLE_ON = get(Names.GIRAFFE + "_spawnable_on");
	public static final ResourceLocation KOALA_SPAWNABLE_ON = get(Names.KOALA + "_spawnable_on");
	public static final ResourceLocation LION_SPAWNABLE_ON = get(Names.LION + "_spawnable_on");
	public static final ResourceLocation MANTARAY_SPAWNABLE_ON = get(Names.MANTARAY + "_spawnable_on");
	public static final ResourceLocation MONKEY_SPAWNABLE_ON = get(Names.MONKEY + "_spawnable_on");
	public static final ResourceLocation OSTRICH_SPAWNABLE_ON = get(Names.OSTRICH + "_spawnable_on");
	public static final ResourceLocation OWL_SPAWNABLE_ON = get(Names.OWL + "_spawnable_on");
	public static final ResourceLocation PENGUIN_SPAWNABLE_ON = get(Names.PENGUIN + "_spawnable_on");
	public static final ResourceLocation RACCOON_SPAWNABLE_ON = get(Names.RACCOON + "_spawnable_on");
	public static final ResourceLocation SEAHORSE_SPAWNABLE_ON = get(Names.SEAHORSE + "_spawnable_on");
	public static final ResourceLocation SHARK_SPAWNABLE_ON = get(Names.SHARK + "_spawnable_on");
	public static final ResourceLocation SHROOMIE_SPAWNABLE_ON = get(Names.SHROOMIE + "_spawnable_on");
	public static final ResourceLocation SNAIL_SPAWNABLE_ON = get(Names.SNAIL + "_spawnable_on");
	public static final ResourceLocation BABY_ENDER_DRAGON_SPAWNABLE_ON = get(Names.BABY_ENDER_DRAGON + "_spawnable_on");

	public static ResourceLocation get(String name) {
		return new ResourceLocation(LivingThings.MOD_ID, name);
	}

}
