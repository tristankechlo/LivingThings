package com.tristankechlo.livingthings.items;

import com.tristankechlo.livingthings.blocks.OstrichNestBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class OstrichEggItem extends Item {

	public OstrichEggItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		BlockPos pos = context.getClickedPos();
		Level world = context.getLevel();
		if (context.getHand() != InteractionHand.MAIN_HAND) {
			return super.useOn(context);
		}
		if (!world.isClientSide()) {
			BlockState state = world.getBlockState(pos);
			Block block = state.getBlock();
			if (!(block instanceof OstrichNestBlock)) {
				return super.useOn(context);
			}
			if (!state.getValue(OstrichNestBlock.EGG)) {
				world.setBlock(pos, state.setValue(OstrichNestBlock.EGG, true).setValue(OstrichNestBlock.HATCH, 0), 2);
				world.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PLACE, SoundSource.BLOCKS, 0.7F, 0.9F);
				context.getItemInHand().shrink(1);
				return InteractionResult.SUCCESS;
			}
		}
		return super.useOn(context);
	}

}
