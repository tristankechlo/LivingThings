package com.tristankechlo.livingthings.client.renderer.state;

import com.tristankechlo.livingthings.entity.NetherKnightEntity;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class NetherKnightRenderState extends LivingEntityRenderState implements StateFromEntity<NetherKnightEntity> {

    public float attackTime;

    @Override
    public void fromEntity(NetherKnightEntity entity) {
        this.attackTime = entity.attackAnim;
    }

}
