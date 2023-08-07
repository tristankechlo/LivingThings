package com.tristankechlo.livingthings.entity.ai;

import com.tristankechlo.livingthings.entity.PeacockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.Path;

public class PeacockDestroyCropBlocks extends MoveToBlockGoal {

    private final PeacockEntity peacock;
    private boolean reachedTarget = false;

    public PeacockDestroyCropBlocks(PeacockEntity entity) {
        super(entity, 1.0D, 16);
        this.peacock = entity;
    }

    @Override
    public void tick() {
        BlockPos targetPos = this.getMoveToTarget();
        if (!targetPos.closerToCenterThan(this.mob.position(), this.acceptedDistance())) {
            this.reachedTarget = false;
            ++this.tryTicks;
            if (this.shouldRecalculatePath()) {
                this.moveMobToBlock();
            }
        } else {
            this.reachedTarget = true;
            --this.tryTicks;
        }

        if (!this.isReachedTarget()) {
            return;
        }
        //animate peacock picking the plant
        this.peacock.setDestroyingCrops(true);
        //destroy the plant and start fluffing
        if (this.peacock.getRandom().nextInt(100) == 0) {
            this.peacock.level().destroyBlock(targetPos, false);
            this.peacock.startFluffing();
        }
    }

    @Override
    protected BlockPos getMoveToTarget() {
        return this.blockPos;
    }

    @Override
    protected void moveMobToBlock() {
        //move exactly to the block, not one block away
        BlockPos blockpos = this.getMoveToTarget();
        Path path = this.mob.getNavigation().createPath(blockpos.getX() + 0.5D, blockpos.getY() + 0.5D, blockpos.getZ() + 0.5D, 0);
        this.mob.getNavigation().moveTo(path, this.speedModifier);
    }

    @Override
    public double acceptedDistance() {
        return 0.75D;
    }

    @Override
    protected boolean isValidTarget(LevelReader level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        if (state.getBlock() instanceof CropBlock) {
            CropBlock block = (CropBlock) state.getBlock();
            return block.isMaxAge(state);
        }
        return false;
    }

    @Override
    public boolean isReachedTarget() {
        return this.reachedTarget;
    }

    @Override
    public void stop() {
        super.stop();
        this.peacock.setDestroyingCrops(false);
    }

}
