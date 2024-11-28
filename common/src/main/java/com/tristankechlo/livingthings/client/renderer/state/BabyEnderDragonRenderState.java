package com.tristankechlo.livingthings.client.renderer.state;

import com.tristankechlo.livingthings.entity.BabyEnderDragonEntity;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.item.DyeColor;

public class BabyEnderDragonRenderState extends LivingEntityRenderState implements MovingEntityState, StateFromEntity<BabyEnderDragonEntity> {

    private boolean moving;
    public boolean flying;
    public boolean isSitting;
    public boolean isTame;
    public DyeColor collarColor;

    @Override
    public boolean isMoving() {
        return this.moving;
    }

    @Override
    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    @Override
    public void fromEntity(BabyEnderDragonEntity entity) {
        this.setMoving(entity);
        this.flying = entity.isFlying();
        this.isTame = entity.isTame();
        this.isSitting = entity.isInSittingPose();
        this.collarColor = entity.getCollarColor();
    }
}
