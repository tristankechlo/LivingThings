package com.tristankechlo.livingthings.entities.ai;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.RemoveBlockGoal;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;

public class BreakTurtleEggGoal extends RemoveBlockGoal {

	public BreakTurtleEggGoal(PathfinderMob creatureIn, double speed, int yMax) {
		super(Blocks.TURTLE_EGG, creatureIn, speed, yMax);
	}

	@Override
	public void playDestroyProgressSound(LevelAccessor worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.ZOMBIE_DESTROY_EGG, SoundSource.HOSTILE, 0.5F,
				0.9F + this.mob.getRandom().nextFloat() * 0.2F);
	}

	@Override
	public void playBreakSound(Level worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.TURTLE_EGG_BREAK, SoundSource.BLOCKS, 0.7F,
				0.9F + worldIn.random.nextFloat() * 0.2F);
	}

	@Override
	public double acceptedDistance() {
		return 1.25D;
	}
}
