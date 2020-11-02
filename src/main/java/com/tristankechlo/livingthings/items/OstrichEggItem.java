package com.tristankechlo.livingthings.items;

import com.tristankechlo.livingthings.blocks.OstrichNestBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class OstrichEggItem extends Item {

	public OstrichEggItem(Properties properties) {
		super(properties);
	}

	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		BlockPos pos = context.getPos();
		World world = context.getWorld();
		if (context.getHand() != Hand.MAIN_HAND) {
			return super.onItemUse(context);
		}
		if (!world.isRemote) {
			BlockState state = world.getBlockState(pos);
			Block block = state.getBlock();
			if (!(block instanceof OstrichNestBlock)) {
				return super.onItemUse(context);
			}
			if (!state.get(OstrichNestBlock.EGG)) {
				world.setBlockState(pos, state.with(OstrichNestBlock.EGG, true).with(OstrichNestBlock.HATCH, 0), 2);
				world.playSound(null, pos, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PLACE, SoundCategory.BLOCKS, 0.7F, 0.9F);
				context.getItem().shrink(1);
				return ActionResultType.SUCCESS;
			}
		}
		return super.onItemUse(context);
	}

}
