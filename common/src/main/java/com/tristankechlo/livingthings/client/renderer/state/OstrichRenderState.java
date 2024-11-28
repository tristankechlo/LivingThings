package com.tristankechlo.livingthings.client.renderer.state;

import com.tristankechlo.livingthings.entity.OstrichEntity;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class OstrichRenderState extends LivingEntityRenderState implements StateFromEntity<OstrichEntity> {

    public boolean isLayingEgg;
    public boolean isBuildingNest;

    @Override
    public void fromEntity(OstrichEntity entity) {
        this.isLayingEgg = entity.isLayingEgg();
        this.isBuildingNest = entity.isBuildingNest();
    }

}
