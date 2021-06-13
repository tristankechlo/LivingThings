package com.tristankechlo.livingthings.client.model.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.tristankechlo.livingthings.client.model.AdvancedEntityModel;
import com.tristankechlo.livingthings.entities.RaccoonEntity;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RaccoonModel<T extends RaccoonEntity> extends AdvancedEntityModel<T> {

	private final ModelRenderer Body;
	private final ModelRenderer LegFrontLeft;
	private final ModelRenderer LegBackLeft;
	private final ModelRenderer LegFrontRight;
	private final ModelRenderer LegBackRight;
	private final ModelRenderer Tail;
	private final ModelRenderer Head;
	private final ModelRenderer LeftEar;
	private final ModelRenderer RightEar;
	private final ModelRenderer Mouth;

	public RaccoonModel() {
		texWidth = 64;
		texHeight = 32;

		Body = new ModelRenderer(this);
		Body.setPos(0.0F, 24.0F, 0.0F);
		Body.texOffs(0, 1).addBox(-3.0F, -11.0F, -5.5F, 6.0F, 6.0F, 11.0F, 0.0F, false);

		LegFrontLeft = new ModelRenderer(this);
		LegFrontLeft.setPos(1.75F, -5.0F, -3.5F);
		Body.addChild(LegFrontLeft);
		LegFrontLeft.texOffs(0, 25).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

		LegBackLeft = new ModelRenderer(this);
		LegBackLeft.setPos(1.75F, -5.0F, 3.5F);
		Body.addChild(LegBackLeft);
		LegBackLeft.texOffs(9, 25).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

		LegFrontRight = new ModelRenderer(this);
		LegFrontRight.setPos(-1.75F, -5.0F, -3.5F);
		Body.addChild(LegFrontRight);
		LegFrontRight.texOffs(18, 25).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

		LegBackRight = new ModelRenderer(this);
		LegBackRight.setPos(-1.75F, -5.0F, 3.5F);
		Body.addChild(LegBackRight);
		LegBackRight.texOffs(27, 25).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

		Tail = new ModelRenderer(this);
		Tail.setPos(0.0F, -8.95F, 4.65F);
		Body.addChild(Tail);
		this.setRotationAngle(Tail, -0.4363F, 0.0F, 0.0F);
		Tail.texOffs(36, 20).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 8.0F, 0.0F, false);

		Head = new ModelRenderer(this);
		Head.setPos(0.0F, -9.0F, -5.5F);
		Body.addChild(Head);
		Head.texOffs(36, 7).addBox(-4.0F, -3.0F, -5.0F, 8.0F, 6.0F, 5.0F, 0.0F, false);

		LeftEar = new ModelRenderer(this);
		LeftEar.setPos(2.5F, -3.0F, -2.0F);
		Head.addChild(LeftEar);
		LeftEar.texOffs(0, 20).addBox(-1.0F, -1.75F, -0.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);

		RightEar = new ModelRenderer(this);
		RightEar.setPos(-2.5F, -3.0F, -1.75F);
		Head.addChild(RightEar);
		RightEar.texOffs(7, 20).addBox(-1.0F, -1.75F, -0.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);

		Mouth = new ModelRenderer(this);
		Mouth.setPos(0.0F, 1.25F, -5.0F);
		Head.addChild(Mouth);
		Mouth.texOffs(25, 4).addBox(-2.0F, -1.5F, -3.0F, 4.0F, 3.0F, 3.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {

		this.Head.xRot = headPitch * 0.0174532925F;
		this.Head.yRot = (netHeadYaw / 3.75F) * 0.0174532925F;
		this.walk(LegFrontRight, LegFrontLeft, LegBackRight, LegBackLeft, limbSwing, limbSwingAmount);
		this.Tail.yRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.75F * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {

		if (this.young) {
			matrixStack.scale(0.5F, 0.5F, 0.5F);
			matrixStack.translate(0, 1.5D, 0);
		}
		Body.render(matrixStack, buffer, packedLight, packedOverlay);
	}
}