package com.tristankechlo.livingthings.client.renderer.state;

import com.tristankechlo.livingthings.entity.LionEntity;
import com.tristankechlo.livingthings.entity.misc.IGenderedMob;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class LionRenderState extends LivingEntityRenderState implements StateFromEntity<LionEntity> {

    public byte variant;
    public IGenderedMob.Gender gender;

    @Override
    public void fromEntity(LionEntity entity) {
        this.variant = entity.getVariant();
        this.gender = entity.getGender();
    }

}
