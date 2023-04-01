package com.tristankechlo.livingthings.init;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.block.OstrichNestBlock;
import com.tristankechlo.livingthings.platform.RegistrationProvider;
import com.tristankechlo.livingthings.platform.RegistryObject;
import net.minecraft.core.Registry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public final class ModBlocks {

    public static void init() {}

    private static final RegistrationProvider<Block> BLOCKS = RegistrationProvider.get(Registry.BLOCK, LivingThings.MOD_ID);

    //BLOCKS
    public static final RegistryObject<Block> OSTRICH_NEST = BLOCKS.register("ostrich_nest", OstrichNestBlock::new);

    //BLOCK ITEMS
    public static final RegistryObject<BlockItem> OSTRICH_NEST_ITEM = ModItems.register("ostrich_nest", new BlockItem(OSTRICH_NEST.get(), ModItems.normalProps()));

}
