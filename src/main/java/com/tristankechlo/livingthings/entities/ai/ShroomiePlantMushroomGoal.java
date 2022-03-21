package com.tristankechlo.livingthings.entities.ai;

import com.tristankechlo.livingthings.entities.ShroomieEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraftforge.common.IPlantable;

public class ShroomiePlantMushroomGoal extends MoveToBlockGoal {

	private final ShroomieEntity shroomie;
	private boolean isAboveDestination;
	private final BlockState targetState;

	public ShroomiePlantMushroomGoal(ShroomieEntity shroomie) {
		super(shroomie, 1.0D, 12, 50);
		this.shroomie = shroomie;
		this.targetState = shroomie.getVariant() == 0 ? Blocks.BROWN_MUSHROOM.defaultBlockState()
				: Blocks.RED_MUSHROOM.defaultBlockState();
	}

	@Override
	public boolean canUse() {
		return this.shroomie.canPlantMushroom() && super.canUse();
	}

	@Override
	public boolean canContinueToUse() {
		return this.shroomie.canPlantMushroom() && super.canContinueToUse();
	}

	@Override
	public void tick() {
		BlockPos blockpos = this.getMoveToTarget();
		if (!blockpos.closerToCenterThan(this.mob.position(), this.acceptedDistance())) {
			this.isAboveDestination = false;
			++this.tryTicks;
			if (this.shouldRecalculatePath()) {
				this.moveMobToBlock();
			}
		} else {
			this.isAboveDestination = true;
			--this.tryTicks;
		}
		if (this.shroomie.canPlantMushroom() && this.isReachedTarget()
				&& this.shroomie.level.getBlockState(blockpos).isAir()) {
			this.shroomie.level.playSound(null, blockpos, SoundEvents.LILY_PAD_PLACE, SoundSource.BLOCKS, 0.9F, 0.9F);
			this.shroomie.level.setBlockAndUpdate(blockpos, targetState);
			this.shroomie.plantedMushroom();
		}
	}

	@Override
	protected void moveMobToBlock() {
		BlockPos blockpos = this.getMoveToTarget();
		Path path = this.mob.getNavigation().createPath(blockpos.getX() + 0.5D, blockpos.getY(), blockpos.getZ() + 0.5D,
				0);
		this.mob.getNavigation().moveTo(path, this.speedModifier);
	}

	@Override
	protected boolean isValidTarget(LevelReader worldReader, BlockPos blockpos) {
		BlockState blockstate = worldReader.getBlockState(blockpos);
		if (blockstate.is(BlockTags.MUSHROOM_GROW_BLOCK)) {
			return worldReader.getBlockState(blockpos.above()).isAir();
		} else {
			Block block = blockstate.getBlock();
			if (!(block instanceof IPlantable)) {
				return false;
			}
			return worldReader.getRawBrightness(blockpos, 0) < 13
					&& blockstate.canSustainPlant(worldReader, blockpos, Direction.UP, (IPlantable) block);
		}
	}

	@Override
	protected boolean isReachedTarget() {
		return this.isAboveDestination;
	}

}
