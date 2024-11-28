package com.tristankechlo.livingthings.client.renderer.state;

import com.tristankechlo.livingthings.entity.AncientBlazeEntity;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class AncientBlazeRenderState extends LivingEntityRenderState implements StateFromEntity<AncientBlazeEntity> {

    public boolean isPowered;
    public int shoots;

    @Override
    public void fromEntity(AncientBlazeEntity entity) {
        this.isPowered = entity.isPowered();
        this.shoots = entity.getShoots();
    }
}
