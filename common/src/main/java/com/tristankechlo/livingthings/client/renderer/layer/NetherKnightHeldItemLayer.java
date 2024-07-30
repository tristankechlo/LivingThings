package com.tristankechlo.livingthings.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import com.tristankechlo.livingthings.entity.NetherKnightEntity;
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

    public NetherKnightHeldItemLayer(RenderLayerParent<NetherKnightEntity, M> renderer, ItemInHandRenderer itemInHandRenderer) {
        super(renderer);
        this.itemRenderer = itemInHandRenderer;
    }

    @Override
    public void render(PoseStack mStack, MultiBufferSource buffer, int p_225628_3_, NetherKnightEntity entity,
                       float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_,
                       float p_225628_10_) {
        boolean flag = entity.getMainArm() == HumanoidArm.RIGHT;
        ItemStack itemstack = flag ? entity.getOffhandItem() : entity.getMainHandItem();
        ItemStack itemstack1 = flag ? entity.getMainHandItem() : entity.getOffhandItem();
        if (!itemstack.isEmpty() || !itemstack1.isEmpty()) {
            mStack.pushPose();
            this.renderArmWithItem(entity, itemstack1, TransformType.THIRD_PERSON_RIGHT_HAND, HumanoidArm.RIGHT, mStack, buffer, p_225628_3_);
            this.renderArmWithItem(entity, itemstack, TransformType.THIRD_PERSON_LEFT_HAND, HumanoidArm.LEFT, mStack, buffer, p_225628_3_);
            mStack.popPose();
        }
    }

    private void renderArmWithItem(NetherKnightEntity entity, ItemStack itemStack, TransformType transformType,
                                   HumanoidArm hand, PoseStack matrixStack, MultiBufferSource buffer, int p_229135_7_) {
        if (!itemStack.isEmpty()) {
            matrixStack.pushPose();
            this.getParentModel().translateToHand(hand, matrixStack);
            matrixStack.mulPose(Vector3f.XP.rotationDegrees(-90.0F));
            matrixStack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
            matrixStack.mulPose(new Quaternion(0.4f, 0, 0, false));
            boolean flag = hand == HumanoidArm.LEFT;
            matrixStack.translate(0, -0.14D, -0.76D);
            itemRenderer.renderItem(entity, itemStack, transformType, flag, matrixStack, buffer, p_229135_7_);
            matrixStack.popPose();
        }
    }
}
