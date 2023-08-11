package com.tristankechlo.livingthings.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import com.tristankechlo.livingthings.entity.NetherKnightEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms.TransformType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;

public class NetherKnightHeldItemLayer<M extends EntityModel<NetherKnightEntity> & ArmedModel> extends RenderLayer<NetherKnightEntity, M> {

    private final ItemInHandRenderer itemRenderer;

    public NetherKnightHeldItemLayer(RenderLayerParent<NetherKnightEntity, M> renderer) {
        super(renderer);
        this.itemRenderer = Minecraft.getInstance().getItemInHandRenderer();
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int i, NetherKnightEntity entity, float f1, float f2, float f3, float f4, float f5, float f6) {
        boolean flag = entity.getMainArm() == HumanoidArm.RIGHT;
        ItemStack itemstack = flag ? entity.getOffhandItem() : entity.getMainHandItem();
        ItemStack itemstack1 = flag ? entity.getMainHandItem() : entity.getOffhandItem();
        this.renderArmWithItem(entity, itemstack, TransformType.THIRD_PERSON_LEFT_HAND, HumanoidArm.LEFT, poseStack, buffer, i);
        this.renderArmWithItem(entity, itemstack1, TransformType.THIRD_PERSON_RIGHT_HAND, HumanoidArm.RIGHT, poseStack, buffer, i);
    }

    private void renderArmWithItem(NetherKnightEntity entity, ItemStack itemStack, TransformType transformType, HumanoidArm hand, PoseStack poseStack, MultiBufferSource buffer, int i) {
        if (itemStack.isEmpty()) {
            return;
        }
        poseStack.pushPose();
        this.getParentModel().translateToHand(hand, poseStack);
        poseStack.mulPose(Vector3f.XP.rotationDegrees(-90.0F));
        poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
        poseStack.mulPose(new Quaternion(0.4f, 0, 0, false));
        boolean flag = hand == HumanoidArm.LEFT;
        poseStack.translate(0, -0.14D, -0.76D);
        itemRenderer.renderItem(entity, itemStack, transformType, flag, poseStack, buffer, i);
        poseStack.popPose();
    }
}
