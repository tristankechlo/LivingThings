package com.tristankechlo.livingthings.client.renderer.state;

import com.tristankechlo.livingthings.entity.ShroomieEntity;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class ShroomieRenderState extends LivingEntityRenderState implements StateFromEntity<ShroomieEntity> {

    public byte variant;

    @Override
    public void fromEntity(ShroomieEntity entity) {
        this.variant = entity.getVariant();
    }

}
