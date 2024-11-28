package com.tristankechlo.livingthings.client.renderer.state;

import com.tristankechlo.livingthings.entity.MonkeyEntity;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class MonkeyRenderState extends LivingEntityRenderState implements StateFromEntity<MonkeyEntity> {

    public boolean isSitting;
    public boolean isPartying;

    @Override
    public void fromEntity(MonkeyEntity entity) {
        this.isSitting = entity.isInSittingPose();
        this.isPartying = entity.isPartying();
    }

}
