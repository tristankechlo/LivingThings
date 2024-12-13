package com.tristankechlo.livingthings.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.tristankechlo.livingthings.client.renderer.state.NetherKnightRenderState;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.HumanoidArm;

public class NetherKnightHeldItemLayer<M extends EntityModel<NetherKnightRenderState> & ArmedModel> extends RenderLayer<NetherKnightRenderState, M> {

    public NetherKnightHeldItemLayer(RenderLayerParent<NetherKnightRenderState, M> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int i, NetherKnightRenderState state, float f1, float f2) {
        this.renderArmWithItem(state.rightHandItem, HumanoidArm.RIGHT, poseStack, buffer, i);
        this.renderArmWithItem(state.leftHandItem, HumanoidArm.LEFT, poseStack, buffer, i);
    }

    private void renderArmWithItem(ItemStackRenderState heldItem, HumanoidArm hand, PoseStack poseStack, MultiBufferSource buffer, int i) {
        if (heldItem.isEmpty()) {
            return;
        }
        poseStack.pushPose();
        this.getParentModel().translateToHand(hand, poseStack);
        poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
        poseStack.translate(0, 0.12D, -0.76D);
        heldItem.render(poseStack, buffer, i, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
    }

}
