package com.tristankechlo.livingthings.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.tristankechlo.livingthings.entity.NetherKnightEntity;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class NetherKnightHeldItemLayer<M extends EntityModel<NetherKnightEntity> & ArmedModel> extends RenderLayer<NetherKnightEntity, M> {

    private final ItemInHandRenderer itemRenderer;

    public NetherKnightHeldItemLayer(RenderLayerParent<NetherKnightEntity, M> renderer, ItemInHandRenderer itemInHandRenderer) {
        super(renderer);
        this.itemRenderer = itemInHandRenderer;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int i, NetherKnightEntity entity, float f1, float f2, float f3, float f4, float f5, float f6) {
        boolean flag = entity.getMainArm() == HumanoidArm.RIGHT;
        ItemStack itemstack = flag ? entity.getOffhandItem() : entity.getMainHandItem();
        ItemStack itemstack1 = flag ? entity.getMainHandItem() : entity.getOffhandItem();
        this.renderArmWithItem(entity, itemstack, ItemDisplayContext.THIRD_PERSON_LEFT_HAND, HumanoidArm.LEFT, poseStack, buffer, i);
        this.renderArmWithItem(entity, itemstack1, ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, HumanoidArm.RIGHT, poseStack, buffer, i);
    }

    private void renderArmWithItem(NetherKnightEntity entity, ItemStack itemStack, ItemDisplayContext context, HumanoidArm hand, PoseStack poseStack, MultiBufferSource buffer, int i) {
        if (itemStack.isEmpty()) {
            return;
        }
        poseStack.pushPose();
        this.getParentModel().translateToHand(hand, poseStack);
        poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
        boolean flag = hand == HumanoidArm.LEFT;
        poseStack.translate(0, 0.12D, -0.76D);
        itemRenderer.renderItem(entity, itemStack, context, flag, poseStack, buffer, i);
        poseStack.popPose();
    }
}
