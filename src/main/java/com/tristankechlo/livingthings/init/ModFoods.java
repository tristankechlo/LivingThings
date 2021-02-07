package com.tristankechlo.livingthings.init;

import net.minecraft.item.Food;

public class ModFoods {

	public static final Food CRAB = (new Food.Builder()).hunger(2).saturation(0.2F).build();
	public static final Food COOKED_CRAB = (new Food.Builder()).hunger(5).saturation(0.5F).build();
	public static final Food OSTRICH = (new Food.Builder()).hunger(3).saturation(0.4F).build();
	public static final Food COOKED_OSTRICH = (new Food.Builder()).hunger(6).saturation(0.7F).build();
	public static final Food BANANA = (new Food.Builder()).hunger(4).saturation(0.3F).build();

}
