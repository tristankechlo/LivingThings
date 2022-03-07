package com.tristankechlo.livingthings.misc;

import com.tristankechlo.livingthings.LivingThings;

import net.minecraft.resources.ResourceLocation;

public final class LivingThingsTags {

	public static final ResourceLocation DROPS_BANANAS = create("drops_bananas");
	public static final ResourceLocation BANANAS = create("bananas");

	public static final ResourceLocation CRAP_SPAWNABLE_ON = spawnableOn(Names.CRAB);
	public static final ResourceLocation ELEPHANT_SPAWNABLE_ON = spawnableOn(Names.ELEPHANT);
	public static final ResourceLocation FLAMINGO_SPAWNABLE_ON = spawnableOn(Names.FLAMINGO);
	public static final ResourceLocation GIRAFFE_SPAWNABLE_ON = spawnableOn(Names.GIRAFFE);
	public static final ResourceLocation KOALA_SPAWNABLE_ON = spawnableOn(Names.KOALA);
	public static final ResourceLocation LION_SPAWNABLE_ON = spawnableOn(Names.LION);
	public static final ResourceLocation MANTARAY_SPAWNABLE_ON = spawnableOn(Names.MANTARAY);
	public static final ResourceLocation MONKEY_SPAWNABLE_ON = spawnableOn(Names.MONKEY);
	public static final ResourceLocation OSTRICH_SPAWNABLE_ON = spawnableOn(Names.OSTRICH);
	public static final ResourceLocation OWL_SPAWNABLE_ON = spawnableOn(Names.OWL);
	public static final ResourceLocation PENGUIN_SPAWNABLE_ON = spawnableOn(Names.PENGUIN);
	public static final ResourceLocation RACCOON_SPAWNABLE_ON = spawnableOn(Names.RACCOON);
	public static final ResourceLocation SEAHORSE_SPAWNABLE_ON = spawnableOn(Names.SEAHORSE);
	public static final ResourceLocation SHARK_SPAWNABLE_ON = spawnableOn(Names.SHARK);
	public static final ResourceLocation SHROOMIE_SPAWNABLE_ON = spawnableOn(Names.SHROOMIE);
	public static final ResourceLocation SNAIL_SPAWNABLE_ON = spawnableOn(Names.SNAIL);
	public static final ResourceLocation BABY_ENDER_DRAGON_SPAWNABLE_ON = spawnableOn(Names.BABY_ENDER_DRAGON);

	private static ResourceLocation spawnableOn(String name) {
		return create(name + "_spawnable_on");
	}

	private static ResourceLocation create(String name) {
		return new ResourceLocation(LivingThings.MOD_ID, name);
	}

}
