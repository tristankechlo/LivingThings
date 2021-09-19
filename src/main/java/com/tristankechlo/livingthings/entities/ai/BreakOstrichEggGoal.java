package com.tristankechlo.livingthings.entities.ai;

import java.util.Random;

import javax.annotation.Nullable;

import com.tristankechlo.livingthings.blocks.OstrichNestBlock;
import com.tristankechlo.livingthings.init.ModBlocks;
import com.tristankechlo.livingthings.init.ModSounds;

import net.minecraft.block.Block;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;

public class BreakOstrichEggGoal extends MoveToBlockGoal {

	private final Block block;
	private final MobEntity entity;
	private int breakingTime;
	private boolean destroyNestComplete;
	private int chance;

	public BreakOstrichEggGoal(CreatureEntity creatureIn, double speed, int yMax, int chance,
			boolean destroyNestComplete) {
		super(creatureIn, speed, 12, yMax);
		this.block = ModBlocks.OSTRICH_NEST.get();
		this.entity = creatureIn;
		this.destroyNestComplete = destroyNestComplete;
		this.chance = chance;
	}

	@Override
	public boolean canUse() {
		if (!ForgeHooks.canEntityDestroy(this.entity.level, this.blockPos, this.entity)) {
			return false;
		} else if (this.nextStartTick > 0) {
			--this.nextStartTick;
			return false;
		} else if (this.shouldMoveToDestination()) {
			this.nextStartTick = 20;
			return this.entity.getRandom().nextInt(this.chance) == 0;
		} else {
			this.nextStartTick = this.nextStartTick(this.mob);
			return false;
		}
	}

	private boolean shouldMoveToDestination() {
		return this.blockPos != null && this.isValidTarget(this.mob.level, this.blockPos) ? true
				: this.findNearestBlock();
	}

	@Override
	public void stop() {
		super.stop();
		this.entity.fallDistance = 1.0F;
	}

	@Override
	public void start() {
		super.start();
		this.breakingTime = 0;
	}

	public void playBreakingSound(IWorld worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, ModSounds.OSTRICH_EGG_CRACKS.get(), SoundCategory.HOSTILE, 0.5F,
				0.9F + this.entity.getRandom().nextFloat() * 0.2F);
	}

	public void playBrokenSound(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, ModSounds.OSTRICH_EGG_CRACKS.get(), SoundCategory.BLOCKS, 0.7F,
				0.9F + worldIn.random.nextFloat() * 0.2F);
	}

	@Override
	public void tick() {
		super.tick();
		World world = this.entity.level;
		BlockPos blockpos = this.entity.blockPosition();
		BlockPos blockpos1 = this.findTarget(blockpos, world);
		Random random = this.entity.getRandom();
		if (this.isReachedTarget() && blockpos1 != null) {
			if (this.breakingTime > 0) {
				Vector3d vector3d = this.entity.getDeltaMovement();
				this.entity.setDeltaMovement(vector3d.x, 0.3D, vector3d.z);
				if (!world.isClientSide()) {
					((ServerWorld) world).sendParticles(
							new ItemParticleData(ParticleTypes.ITEM, new ItemStack(Items.EGG)), blockpos1.getX() + 0.5D,
							blockpos1.getY() + 0.7D, blockpos1.getZ() + 0.5D, 3, (random.nextFloat() - 0.5D) * 0.08D,
							(random.nextFloat() - 0.5D) * 0.08D, (random.nextFloat() - 0.5D) * 0.08D, 0.15F);
				}
			}

			if (this.breakingTime % 2 == 0) {
				Vector3d vector3d1 = this.entity.getDeltaMovement();
				this.entity.setDeltaMovement(vector3d1.x, -0.3D, vector3d1.z);
				if (this.breakingTime % 6 == 0) {
					this.playBreakingSound(world, this.blockPos);
				}
			}

			if (this.breakingTime > 60) {
				if (this.destroyNestComplete) {
					// destroy nest complete
					world.removeBlock(blockpos1, false);
				} else {
					// remove only the egg
					world.setBlockAndUpdate(blockpos1,
							world.getBlockState(blockpos1).setValue(OstrichNestBlock.EGG, false));
				}
				if (!world.isClientSide()) {
					for (int i = 0; i < 20; ++i) {
						double d3 = random.nextGaussian() * 0.02D;
						double d1 = random.nextGaussian() * 0.02D;
						double d2 = random.nextGaussian() * 0.02D;
						((ServerWorld) world).sendParticles(ParticleTypes.POOF, blockpos1.getX() + 0.5D,
								blockpos1.getY(), blockpos1.getZ() + 0.5D, 1, d3, d1, d2, (double) 0.15F);
					}

					this.playBrokenSound(world, blockpos1);
				}
			}

			++this.breakingTime;
		}

	}

	@Nullable
	private BlockPos findTarget(BlockPos pos, IBlockReader worldIn) {
		if (worldIn.getBlockState(pos).is(this.block)) {
			if (worldIn.getBlockState(pos).getValue(OstrichNestBlock.EGG)) {
				return pos;
			}
			return null;
		} else {
			BlockPos[] ablockpos = new BlockPos[] { pos.below(), pos.west(), pos.east(), pos.north(), pos.south(),
					pos.below().below() };

			for (BlockPos blockpos : ablockpos) {
				if (worldIn.getBlockState(blockpos).is(this.block)) {
					if (worldIn.getBlockState(blockpos).getValue(OstrichNestBlock.EGG)) {
						return blockpos;
					}
				}
			}

			return null;
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	protected boolean isValidTarget(IWorldReader worldIn, BlockPos pos) {
		IChunk ichunk = worldIn.getChunk(pos.getX() >> 4, pos.getZ() >> 4, ChunkStatus.FULL, false);
		if (ichunk == null) {
			return false;
		} else {
			if (ichunk.getBlockState(pos).is(this.block)) {
				return ichunk.getBlockState(pos).getValue(OstrichNestBlock.EGG)
						&& ichunk.getBlockState(pos.above()).isAir() && ichunk.getBlockState(pos.above(2)).isAir();
			}
			return false;
		}
	}

	@Override
	public double acceptedDistance() {
		return 1.25D;
	}

}
