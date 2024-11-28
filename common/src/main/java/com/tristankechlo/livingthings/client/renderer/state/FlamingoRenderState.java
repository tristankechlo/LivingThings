package com.tristankechlo.livingthings.client.renderer.state;

import com.tristankechlo.livingthings.entity.FlamingoEntity;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class FlamingoRenderState extends LivingEntityRenderState implements StateFromEntity<FlamingoEntity> {

    public boolean leftLegUp;
    public boolean rightLegUp;

    @Override
    public void fromEntity(FlamingoEntity entity) {
        this.leftLegUp = entity.isLeftLegUp();
        this.rightLegUp = entity.isRightLegUp();
    }

}
