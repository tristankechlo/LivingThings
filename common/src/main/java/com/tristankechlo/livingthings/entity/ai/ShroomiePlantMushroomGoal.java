package com.tristankechlo.livingthings.entity.ai;

import com.tristankechlo.livingthings.entity.ShroomieEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.Path;

public class ShroomiePlantMushroomGoal extends MoveToBlockGoal {

    private final ShroomieEntity shroomie;
    private boolean isAboveDestination;

    public ShroomiePlantMushroomGoal(ShroomieEntity shroomie) {
        super(shroomie, 1.0D, 12, 50);
        this.shroomie = shroomie;
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
        if (this.shroomie.canPlantMushroom() && this.isReachedTarget() && this.shroomie.level.getBlockState(blockpos).isAir()) {
            Block block = this.shroomie.getVariant() == 0 ? Blocks.BROWN_MUSHROOM : Blocks.RED_MUSHROOM;
            this.shroomie.level.playSound(null, blockpos, SoundEvents.LILY_PAD_PLACE, SoundSource.BLOCKS, 0.9F, 0.9F);
            this.shroomie.level.setBlockAndUpdate(blockpos, block.defaultBlockState());
            this.shroomie.plantedMushroom();
        }
    }

    @Override
    protected void moveMobToBlock() {
        BlockPos blockpos = this.getMoveToTarget();
        Path path = this.mob.getNavigation().createPath(blockpos.getX() + 0.5D, blockpos.getY(), blockpos.getZ() + 0.5D, 0);
        this.mob.getNavigation().moveTo(path, this.speedModifier);
    }

    @Override
    protected boolean isValidTarget(LevelReader world, BlockPos pos) {
        BlockState blockstate = world.getBlockState(pos);
        if (blockstate.is(BlockTags.MUSHROOM_GROW_BLOCK)) {
            return world.getBlockState(pos.above()).isAir();
        } else {
            BlockState mushroom = Blocks.BROWN_MUSHROOM.defaultBlockState();
            return world.getRawBrightness(pos, 0) < 13 && mushroom.canSurvive(world, pos.above());
        }
    }

    @Override
    protected boolean isReachedTarget() {
        return this.isAboveDestination;
    }

}
