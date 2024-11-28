package com.tristankechlo.livingthings.client.renderer.state;

import com.tristankechlo.livingthings.entity.SeahorseEntity;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class SeahorseRenderState extends LivingEntityRenderState implements StateFromEntity<SeahorseEntity> {

    public byte variant;

    @Override
    public void fromEntity(SeahorseEntity entity) {
        this.variant = entity.getVariant();
    }

}
