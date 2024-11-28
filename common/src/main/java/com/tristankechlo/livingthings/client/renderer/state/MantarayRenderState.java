package com.tristankechlo.livingthings.client.renderer.state;

import com.tristankechlo.livingthings.entity.MantarayEntity;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class MantarayRenderState extends LivingEntityRenderState implements MovingEntityState, StateFromEntity<MantarayEntity> {

    public byte variant;
    public byte scale;
    public boolean isMoving;

    @Override
    public void fromEntity(MantarayEntity entity) {
        this.variant = entity.getVariant();
        this.scale = entity.getScaling();
        this.setMoving(entity);
    }

    @Override
    public boolean isMoving() {
        return this.isMoving;
    }

    @Override
    public void setMoving(boolean moving) {
        this.isMoving = moving;
    }

}
