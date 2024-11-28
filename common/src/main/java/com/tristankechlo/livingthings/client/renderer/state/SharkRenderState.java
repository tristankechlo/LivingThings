package com.tristankechlo.livingthings.client.renderer.state;

import com.tristankechlo.livingthings.entity.SharkEntity;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class SharkRenderState extends LivingEntityRenderState implements MovingEntityState, StateFromEntity<SharkEntity> {

    public boolean isMoving;

    @Override
    public void fromEntity(SharkEntity entity) {
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
