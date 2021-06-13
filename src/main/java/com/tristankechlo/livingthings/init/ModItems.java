package com.tristankechlo.livingthings.init;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.items.LexiconItem;
import com.tristankechlo.livingthings.items.ModArmorItem;
import com.tristankechlo.livingthings.items.OstrichEggItem;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LivingThings.MOD_ID);

	public static final RegistryObject<Item> SHARK_TOOTH = ITEMS.register("shark_tooth", () -> new Item(new Properties().tab(ModItemGroups.General)));
	public static final RegistryObject<Item> OSTRICH_EGG = ITEMS.register("ostrich_egg", () -> new OstrichEggItem(new Properties().tab(ModItemGroups.General).stacksTo(16)));
	public static final RegistryObject<Item> CRAB = ITEMS.register("crab", () -> new Item(new Properties().tab(ModItemGroups.General).food(ModFoods.CRAB)));
	public static final RegistryObject<Item> COOKED_CRAB = ITEMS.register("cooked_crab", () -> new Item(new Properties().tab(ModItemGroups.General).food(ModFoods.COOKED_CRAB)));
	public static final RegistryObject<Item> CRAB_SHELL = ITEMS.register("crab_shell", () -> new Item(new Properties().tab(ModItemGroups.General)));
	public static final RegistryObject<Item> LEXICON = ITEMS.register("lexicon", () -> new LexiconItem(new Properties().tab(ModItemGroups.General).stacksTo(1)));
	public static final RegistryObject<Item> ANCIENT_HELMET = ITEMS.register("ancient_helmet", () -> new ModArmorItem(ModArmorMaterial.ANCIENT, EquipmentSlotType.HEAD, new Properties().tab(ModItemGroups.General).stacksTo(1)));
	public static final RegistryObject<Item> OSTRICH = ITEMS.register("ostrich", () -> new Item(new Properties().tab(ModItemGroups.General).food(ModFoods.OSTRICH)));
	public static final RegistryObject<Item> COOKED_OSTRICH = ITEMS.register("cooked_ostrich", () -> new Item(new Properties().tab(ModItemGroups.General).food(ModFoods.COOKED_OSTRICH)));
	public static final RegistryObject<Item> BANANA = ITEMS.register("banana", () -> new Item(new Properties().tab(ModItemGroups.General).food(ModFoods.BANANA)));
	public static final RegistryObject<Item> ELEPHANT = ITEMS.register("elephant", () -> new Item(new Properties().tab(ModItemGroups.General).food(ModFoods.ELEPHANT)));
	public static final RegistryObject<Item> COOKED_ELEPHANT = ITEMS.register("cooked_elephant", () -> new Item(new Properties().tab(ModItemGroups.General).food(ModFoods.COOKED_ELEPHANT)));

}
