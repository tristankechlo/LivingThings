package com.tristankechlo.livingthings.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroups {
	
    public static final ItemGroup General = new ItemGroup("LivingThings") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(ModItems.SHARK_TOOTH.get());
		}
	};

}
