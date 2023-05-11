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
import org.joml.Quaternionf;

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
            this.renderArmWithItem(entity, itemstack1, ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, HumanoidArm.RIGHT, mStack, buffer, p_225628_3_);
            this.renderArmWithItem(entity, itemstack, ItemDisplayContext.THIRD_PERSON_LEFT_HAND, HumanoidArm.LEFT, mStack, buffer, p_225628_3_);
            mStack.popPose();
        }
    }

    private void renderArmWithItem(NetherKnightEntity entity, ItemStack itemStack, ItemDisplayContext context,
                                   HumanoidArm hand, PoseStack matrixStack, MultiBufferSource buffer, int p_229135_7_) {
        if (!itemStack.isEmpty()) {
            matrixStack.pushPose();
            this.getParentModel().translateToHand(hand, matrixStack);
            matrixStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
            matrixStack.mulPose(Axis.YP.rotationDegrees(180.0F));
            matrixStack.mulPose(new Quaternionf(0.4f, 0f, 0f, 0f));
            boolean flag = hand == HumanoidArm.LEFT;
            matrixStack.translate(0, -0.14D, -0.76D);
            itemRenderer.renderItem(entity, itemStack, context, flag, matrixStack, buffer, p_229135_7_);
            matrixStack.popPose();
        }
    }
}
