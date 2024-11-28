package com.tristankechlo.livingthings.client.renderer.state;

import com.tristankechlo.livingthings.entity.PeacockEntity;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class PeacockRenderState extends LivingEntityRenderState implements StateFromEntity<PeacockEntity> {

    public boolean inPanic;
    public boolean isDestroyingCrops;
    public boolean isTailFluffed;

    @Override
    public void fromEntity(PeacockEntity entity) {
        this.inPanic = entity.isInPanic();
        this.isDestroyingCrops = entity.isDestroyingCrops();
        this.isTailFluffed = entity.isTailFluffed();
    }

}
