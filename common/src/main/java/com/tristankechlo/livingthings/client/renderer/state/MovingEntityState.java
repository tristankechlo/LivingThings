package com.tristankechlo.livingthings.client.renderer.state;

import net.minecraft.world.entity.Entity;

public interface MovingEntityState {

    boolean isMoving();

    void setMoving(boolean moving);

    default void setMoving(Entity entity) {
        this.setMoving(entity.getDeltaMovement().horizontalDistanceSqr() > 1.0E-7D);
    }

}
