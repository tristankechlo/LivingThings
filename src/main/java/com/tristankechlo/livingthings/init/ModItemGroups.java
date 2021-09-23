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

	public static final ItemGroup GENERAL = new ItemGroup("LivingThings") {

		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ModItems.SHARK_TOOTH.get());
		}

		@Override
		@OnlyIn(Dist.CLIENT)
		public void fillItemList(NonNullList<ItemStack> items) {
			NonNullList<ItemStack> spawneggsItems = NonNullList.create();
			for (Item item : ForgeRegistries.ITEMS) {
				if (!(item instanceof SpawnEggItem)) {
					item.fillItemCategory(this, items);
				} else {
					if (item.getItemCategory() == this) {
						spawneggsItems.add(new ItemStack(item));
					}
				}
			}
			items.addAll(spawneggsItems);
		}

	};

}
