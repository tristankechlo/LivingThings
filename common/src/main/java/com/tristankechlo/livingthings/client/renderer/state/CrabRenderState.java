package com.tristankechlo.livingthings.client.renderer.state;

import com.tristankechlo.livingthings.entity.CrabEntity;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class CrabRenderState extends LivingEntityRenderState implements StateFromEntity<CrabEntity> {

    public byte variant;
    public byte scale;

    @Override
    public void fromEntity(CrabEntity crab) {
        this.variant = crab.getVariant();
        this.scale = crab.getScaling();
    }
}
