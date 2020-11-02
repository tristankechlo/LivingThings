package com.tristankechlo.livingthings.init;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.NonNullList;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItemGroups {

	public static final ItemGroup General = new ItemGroup("LivingThings") {

		@Override
		public ItemStack createIcon() {
			return new ItemStack(ModItems.SHARK_TOOTH.get());
		}

		@Override
		@OnlyIn(Dist.CLIENT)
		public void fill(NonNullList<ItemStack> items) {
			NonNullList<ItemStack> spawneggsItems = NonNullList.create();
			for (Item item : ForgeRegistries.ITEMS) {
				if (!(item instanceof SpawnEggItem)) {
					item.fillItemGroup(this, items);
				} else {
					if (item.getGroup() == this) {
						spawneggsItems.add(new ItemStack(item));
					}
				}
			}
			items.addAll(spawneggsItems);
		}
	};

}
