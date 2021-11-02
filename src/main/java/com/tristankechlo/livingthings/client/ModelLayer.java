package com.tristankechlo.livingthings.client;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.misc.Names;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public final class ModelLayer {

	public static final ModelLayerLocation ELEPHANT = createLayerLocation(Names.ELEPHANT);
	public static final ModelLayerLocation GIRAFFE = createLayerLocation(Names.GIRAFFE);
	public static final ModelLayerLocation LION = createLayerLocation(Names.LION);
	public static final ModelLayerLocation SHARK = createLayerLocation(Names.SHARK);
	public static final ModelLayerLocation PENGUIN = createLayerLocation(Names.PENGUIN);
	public static final ModelLayerLocation OSTRICH = createLayerLocation(Names.OSTRICH);
	public static final ModelLayerLocation FLAMINGO = createLayerLocation(Names.FLAMINGO);
	public static final ModelLayerLocation CRAB = createLayerLocation(Names.CRAB);
	public static final ModelLayerLocation MANTARAY = createLayerLocation(Names.MANTARAY);
	public static final ModelLayerLocation RACCOON = createLayerLocation(Names.RACCOON);
	public static final ModelLayerLocation OWL = createLayerLocation(Names.OWL);
	public static final ModelLayerLocation ANCIENT_BLAZE = createLayerLocation(Names.ANCIENT_BLAZE);
	public static final ModelLayerLocation KOALA = createLayerLocation(Names.KOALA);
	public static final ModelLayerLocation SNAIL = createLayerLocation(Names.SNAIL);
	public static final ModelLayerLocation MONKEY = createLayerLocation(Names.MONKEY);
	public static final ModelLayerLocation MONKEY_SITTING = createLayerLocation(Names.MONKEY);
	public static final ModelLayerLocation NETHER_KNIGHT = createLayerLocation(Names.NETHER_KNIGHT);
	public static final ModelLayerLocation SHROOMIE = createLayerLocation(Names.SHROOMIE);
	public static final ModelLayerLocation SEAHORSE = createLayerLocation(Names.SEAHORSE);
	public static final ModelLayerLocation ANCIENT_ARMOR = createLayerLocation("ancientarmormodel");

	private static final ModelLayerLocation createLayerLocation(final String name) {
		return new ModelLayerLocation(new ResourceLocation(LivingThings.MOD_ID, name), "main");
	}
}
