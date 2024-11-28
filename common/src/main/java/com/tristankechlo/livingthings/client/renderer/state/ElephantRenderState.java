package com.tristankechlo.livingthings.client.renderer.state;

import com.tristankechlo.livingthings.entity.ElephantEntity;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class ElephantRenderState extends LivingEntityRenderState implements StateFromEntity<ElephantEntity> {

    public boolean angry;
    public int attackTimer;
    public boolean hasSaddle;
    public boolean hasChest;
    public float partialTicks;

    @Override
    public void fromEntity(ElephantEntity entity) {
        this.angry = entity.isAngry();
        this.attackTimer = entity.getAttackTimer();
        this.hasSaddle = entity.isSaddled();
        this.hasChest = entity.hasChest();
    }

}
