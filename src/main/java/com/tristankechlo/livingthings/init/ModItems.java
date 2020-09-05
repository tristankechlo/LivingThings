package com.tristankechlo.livingthings.init;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.items.OstrichEggItem;

import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
	
	public static final Properties standard_properties = new Properties().group(ModItemGroups.General).maxStackSize(64);

	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LivingThings.MOD_ID);
	
	
	public static final RegistryObject<Item> SHARK_TOOTH = ITEMS.register("shark_tooth",() -> new Item(standard_properties));
	public static final RegistryObject<Item> OSTRICH_EGG = ITEMS.register("ostrich_egg",() -> new OstrichEggItem());

}
