package com.tristankechlo.livingthings.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.tristankechlo.livingthings.client.renderer.state.NetherKnightRenderState;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
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
    public void submit(PoseStack poseStack, SubmitNodeCollector collector, int i, NetherKnightRenderState state, float yRot, float xRot) {
        this.renderArmWithItem(state, state.rightHandItem, HumanoidArm.RIGHT, poseStack, collector, i);
        this.renderArmWithItem(state, state.leftHandItem, HumanoidArm.LEFT, poseStack, collector, i);
    }

    private void renderArmWithItem(NetherKnightRenderState state, ItemStackRenderState heldItem, HumanoidArm hand, PoseStack poseStack, SubmitNodeCollector collector, int i) {
        if (heldItem.isEmpty()) {
            return;
        }
        poseStack.pushPose();
        this.getParentModel().translateToHand(state, hand, poseStack);
        poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
        poseStack.translate(0, 0.12D, -0.76D);
        heldItem.submit(poseStack, collector, i, OverlayTexture.NO_OVERLAY, state.outlineColor);
        poseStack.popPose();
    }

}
