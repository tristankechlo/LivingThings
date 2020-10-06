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
import net.minecraft.entity.player.PlayerEntity;
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

public class BreakOstrichEggGoal extends MoveToBlockGoal {

	private final Block block;
	private final MobEntity entity;
	private int breakingTime;
	private boolean destroyNestComplete;
	private int chance;

	public BreakOstrichEggGoal(CreatureEntity creatureIn, double speed, int yMax, int chance, boolean destroyNestComplete) {
		super(creatureIn, speed, 12, yMax);
		this.block = ModBlocks.OSTRICH_NEST.get();
		this.entity = creatureIn;
		this.destroyNestComplete = destroyNestComplete;
		this.chance = chance;
	}

	@Override
	public boolean shouldExecute() {
		if (!net.minecraftforge.common.ForgeHooks.canEntityDestroy(this.entity.world, this.destinationBlock, this.entity)) {
			return false;
		} else if (this.runDelay > 0) {
			--this.runDelay;
			return false;
		} else if (this.shouldMoveToDestination()) {
			this.runDelay = 20;
			if(this.entity.getRNG().nextInt(this.chance) == 0) {
				return true;
			}
			return false;
		} else {
			this.runDelay = this.getRunDelay(this.creature);
			return false;
		}
	}

	private boolean shouldMoveToDestination() {
		return this.destinationBlock != null && this.shouldMoveTo(this.creature.world, this.destinationBlock) ? true : this.searchForDestination();
	}

	@Override
	public void resetTask() {
		super.resetTask();
		this.entity.fallDistance = 1.0F;
	}

	@Override
	public void startExecuting() {
		super.startExecuting();
		this.breakingTime = 0;
	}

	public void playBreakingSound(IWorld worldIn, BlockPos pos) {
        worldIn.playSound((PlayerEntity)null, pos, ModSounds.OSTRICH_EGG_CRACKS.get(), SoundCategory.HOSTILE, 0.5F, 0.9F + this.entity.getRNG().nextFloat() * 0.2F);
	}

	public void playBrokenSound(World worldIn, BlockPos pos) {
        worldIn.playSound((PlayerEntity)null, pos, ModSounds.OSTRICH_EGG_CRACKS.get(), SoundCategory.BLOCKS, 0.7F, 0.9F + worldIn.rand.nextFloat() * 0.2F);
	}

	@Override
	public void tick() {
		super.tick();
		World world = this.entity.world;
		BlockPos blockpos = this.entity.getPosition();
		BlockPos blockpos1 = this.findTarget(blockpos, world);
		Random random = this.entity.getRNG();
		if (this.getIsAboveDestination() && blockpos1 != null) {
			if (this.breakingTime > 0) {
				Vector3d vector3d = this.entity.getMotion();
				this.entity.setMotion(vector3d.x, 0.3D, vector3d.z);
				if (!world.isRemote) {
					((ServerWorld) world).spawnParticle(new ItemParticleData(ParticleTypes.ITEM, new ItemStack(Items.EGG)),	(double) blockpos1.getX() + 0.5D, (double) blockpos1.getY() + 0.7D, (double) blockpos1.getZ() + 0.5D, 3, ((double) random.nextFloat() - 0.5D) * 0.08D, ((double) random.nextFloat() - 0.5D) * 0.08D, ((double) random.nextFloat() - 0.5D) * 0.08D,	(double) 0.15F);
				}
			}

			if (this.breakingTime % 2 == 0) {
				Vector3d vector3d1 = this.entity.getMotion();
				this.entity.setMotion(vector3d1.x, -0.3D, vector3d1.z);
				if (this.breakingTime % 6 == 0) {
					this.playBreakingSound(world, this.destinationBlock);
				}
			}

			if (this.breakingTime > 60) {
				if(this.destroyNestComplete) {
					//destroy nest complete
					world.removeBlock(blockpos1, false);
				} else {
					//remove only the egg
					world.setBlockState(blockpos1, world.getBlockState(blockpos1).with(OstrichNestBlock.EGG, false));
				}
				if (!world.isRemote) {
					for (int i = 0; i < 20; ++i) {
						double d3 = random.nextGaussian() * 0.02D;
						double d1 = random.nextGaussian() * 0.02D;
						double d2 = random.nextGaussian() * 0.02D;
						((ServerWorld) world).spawnParticle(ParticleTypes.POOF, (double) blockpos1.getX() + 0.5D, (double) blockpos1.getY(), (double) blockpos1.getZ() + 0.5D, 1, d3, d1, d2, (double) 0.15F);
					}

					this.playBrokenSound(world, blockpos1);
				}
			}

			++this.breakingTime;
		}

	}

	@Nullable
	private BlockPos findTarget(BlockPos pos, IBlockReader worldIn) {
		if (worldIn.getBlockState(pos).isIn(this.block)) {
			if(worldIn.getBlockState(pos).get(OstrichNestBlock.EGG)) {
				return pos;
			}
			return null;
		} else {
			BlockPos[] ablockpos = new BlockPos[] { pos.down(), pos.west(), pos.east(), pos.north(), pos.south(), pos.down().down() };

			for (BlockPos blockpos : ablockpos) {
				if (worldIn.getBlockState(blockpos).isIn(this.block)) {
					if(worldIn.getBlockState(blockpos).get(OstrichNestBlock.EGG)) {
						return blockpos;
					}
				}
			}

			return null;
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	protected boolean shouldMoveTo(IWorldReader worldIn, BlockPos pos) {
		IChunk ichunk = worldIn.getChunk(pos.getX() >> 4, pos.getZ() >> 4, ChunkStatus.FULL, false);
		if (ichunk == null) {
			return false;
		} else {
			if(ichunk.getBlockState(pos).isIn(this.block)) {
				return ichunk.getBlockState(pos).get(OstrichNestBlock.EGG) && ichunk.getBlockState(pos.up()).isAir() && ichunk.getBlockState(pos.up(2)).isAir();
			}
			return false;
		}
	}
	
	@Override
	public double getTargetDistanceSq() {
        return 1.25D;
	}

}
