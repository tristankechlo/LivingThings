package com.tristankechlo.livingthings.init;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.blocks.OstrichNestBlock;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			LivingThings.MOD_ID);

	// BLOCKS
	public static final RegistryObject<Block> OSTRICH_NEST = BLOCKS.register("ostrich_nest",
			() -> new OstrichNestBlock());

	// BLOCK - ITEMS
	public static final RegistryObject<Item> OSTRICH_NEST_ITEM = ModItems.ITEMS.register("ostrich_nest",
			() -> new BlockItem(OSTRICH_NEST.get(), new Properties().tab(ModItemGroups.GENERAL)));

}
