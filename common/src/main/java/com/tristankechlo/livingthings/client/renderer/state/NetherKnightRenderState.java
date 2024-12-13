package com.tristankechlo.livingthings.client.renderer.state;

import com.tristankechlo.livingthings.entity.NetherKnightEntity;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;

public class NetherKnightRenderState extends LivingEntityRenderState implements StateFromEntity<NetherKnightEntity> {

    public final ItemStackRenderState mainHandItem = new ItemStackRenderState();
    public final ItemStackRenderState offHandItem = new ItemStackRenderState();
    public float attackTime;
    public HumanoidArm mainArm;

    @Override
    public void fromEntity(NetherKnightEntity entity) {
        this.attackTime = entity.attackAnim;
        this.mainArm = entity.getMainArm();
    }

    public static void extractHoldingEntityRenderState(LivingEntity entity, NetherKnightRenderState reusedState, ItemModelResolver resolver) {
        resolver.updateForLiving(reusedState.mainHandItem, entity.getMainHandItem(), ItemDisplayContext.GROUND, false, entity);
        resolver.updateForLiving(reusedState.offHandItem, entity.getOffhandItem(), ItemDisplayContext.GROUND, true, entity);
    }

}
