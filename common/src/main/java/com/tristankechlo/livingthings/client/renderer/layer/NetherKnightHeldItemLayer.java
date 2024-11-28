package com.tristankechlo.livingthings.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.tristankechlo.livingthings.client.renderer.state.NetherKnightRenderState;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class NetherKnightHeldItemLayer<M extends EntityModel<NetherKnightRenderState> & ArmedModel> extends RenderLayer<NetherKnightRenderState, M> {

    private final ItemRenderer itemRenderer;

    public NetherKnightHeldItemLayer(RenderLayerParent<NetherKnightRenderState, M> renderer, ItemRenderer itemInHandRenderer) {
        super(renderer);
        this.itemRenderer = itemInHandRenderer;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int i, NetherKnightRenderState state, float f1, float f2) {
        this.renderArmWithItem(state.rightHandItem, state.rightHandItemModel, ItemDisplayContext.THIRD_PERSON_LEFT_HAND, HumanoidArm.LEFT, poseStack, buffer, i);
        this.renderArmWithItem(state.leftHandItem, state.leftHandItemModel, ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, HumanoidArm.RIGHT, poseStack, buffer, i);
    }

    private void renderArmWithItem(ItemStack itemStack, BakedModel model, ItemDisplayContext context, HumanoidArm hand, PoseStack poseStack, MultiBufferSource buffer, int i) {
        if (itemStack.isEmpty()) {
            return;
        }
        poseStack.pushPose();
        this.getParentModel().translateToHand(hand, poseStack);
        poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
        boolean flag = hand == HumanoidArm.LEFT;
        poseStack.translate(0, 0.12D, -0.76D);
        this.itemRenderer.render(itemStack, ItemDisplayContext.GROUND, flag, poseStack, buffer, i, OverlayTexture.NO_OVERLAY, model);
        poseStack.popPose();
    }

}
