package com.tristankechlo.livingthings.client.renderer.state;

import com.tristankechlo.livingthings.entity.GiraffeEntity;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class GiraffeRenderState extends LivingEntityRenderState implements StateFromEntity<GiraffeEntity> {

    public byte variant;

    @Override
    public void fromEntity(GiraffeEntity entity) {
        this.variant = entity.getVariant();
    }

}
