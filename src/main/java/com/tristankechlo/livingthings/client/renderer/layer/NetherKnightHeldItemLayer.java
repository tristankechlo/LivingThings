package com.tristankechlo.livingthings.client.renderer.layer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.tristankechlo.livingthings.entities.NetherKnightEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NetherKnightHeldItemLayer<M extends EntityModel<NetherKnightEntity> & IHasArm>
		extends LayerRenderer<NetherKnightEntity, M> {

	public NetherKnightHeldItemLayer(IEntityRenderer<NetherKnightEntity, M> renderer) {
		super(renderer);
	}

	@Override
	public void render(MatrixStack mStack, IRenderTypeBuffer buffer, int p_225628_3_, NetherKnightEntity entity,
			float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_,
			float p_225628_10_) {
		boolean flag = entity.getMainArm() == HandSide.RIGHT;
		ItemStack itemstack = flag ? entity.getOffhandItem() : entity.getMainHandItem();
		ItemStack itemstack1 = flag ? entity.getMainHandItem() : entity.getOffhandItem();
		if (!itemstack.isEmpty() || !itemstack1.isEmpty()) {
			mStack.pushPose();
			this.renderArmWithItem(entity, itemstack1, TransformType.THIRD_PERSON_RIGHT_HAND, HandSide.RIGHT, mStack,
					buffer, p_225628_3_);
			this.renderArmWithItem(entity, itemstack, TransformType.THIRD_PERSON_LEFT_HAND, HandSide.LEFT, mStack,
					buffer, p_225628_3_);
			mStack.popPose();
		}
	}

	private void renderArmWithItem(NetherKnightEntity entity, ItemStack itemStack, TransformType transformType,
			HandSide hand, MatrixStack matrixStack, IRenderTypeBuffer buffer, int p_229135_7_) {
		if (!itemStack.isEmpty()) {
			matrixStack.pushPose();
			this.getParentModel().translateToHand(hand, matrixStack);
			matrixStack.mulPose(Vector3f.XP.rotationDegrees(-90.0F));
			matrixStack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
			matrixStack.mulPose(new Quaternion(0.4f, 0, 0, false));
			boolean flag = hand == HandSide.LEFT;
			matrixStack.translate(0, -0.14D, -0.76D);
			Minecraft.getInstance().getItemInHandRenderer().renderItem(entity, itemStack, transformType, flag,
					matrixStack, buffer, p_229135_7_);
			matrixStack.popPose();
		}
	}
}
