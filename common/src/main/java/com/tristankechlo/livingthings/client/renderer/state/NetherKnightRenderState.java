package com.tristankechlo.livingthings.client.renderer.state;

import com.tristankechlo.livingthings.entity.NetherKnightEntity;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;

public class NetherKnightRenderState extends LivingEntityRenderState implements StateFromEntity<NetherKnightEntity> {

    public final ItemStackRenderState rightHandItem = new ItemStackRenderState();
    public final ItemStackRenderState leftHandItem = new ItemStackRenderState();
    public float attackTime;
    public HumanoidArm mainArm;

    @Override
    public void fromEntity(NetherKnightEntity entity) {
        this.attackTime = entity.attackAnim;
        this.mainArm = entity.getMainArm();
    }

    public static void extractHoldingEntityRenderState(LivingEntity entity, NetherKnightRenderState state, ItemModelResolver resolver) {
        resolver.updateForLiving(state.rightHandItem, entity.getItemHeldByArm(HumanoidArm.RIGHT), ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, entity);
        resolver.updateForLiving(state.leftHandItem, entity.getItemHeldByArm(HumanoidArm.LEFT), ItemDisplayContext.THIRD_PERSON_LEFT_HAND, entity);
    }

}
