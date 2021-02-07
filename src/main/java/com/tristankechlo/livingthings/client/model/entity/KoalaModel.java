package com.tristankechlo.livingthings.client.model.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.tristankechlo.livingthings.client.model.AdvancedEntityModel;
import com.tristankechlo.livingthings.entities.KoalaEntity;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class KoalaModel<T extends KoalaEntity> extends AdvancedEntityModel<T> {

	private final ModelRenderer Body;
	private final ModelRenderer BodyBack;
	private final ModelRenderer Head;
	private final ModelRenderer EarLeft;
	private final ModelRenderer EarRight;
	private final ModelRenderer Nose;
	private final ModelRenderer Nose2;
	private final ModelRenderer LegFrontLeft;
	private final ModelRenderer LegFrontLeft2;
	private final ModelRenderer LegBackLeft;
	private final ModelRenderer LegBackLeft2;
	private final ModelRenderer LegFrontRight;
	private final ModelRenderer LegFrontRight2;
	private final ModelRenderer LegBackRight;
	private final ModelRenderer LegBackRight2;

	public KoalaModel() {
		textureWidth = 64;
		textureHeight = 32;

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 19.1F, -2.0F);
		this.setRotationAngle(Body, -0.0873F, 0.0F, 0.0F);
		Body.setTextureOffset(0, 4).addBox(-3.5F, -6.0F, -3.0F, 7.0F, 6.0F, 6.0F, 0.0F, false);

		BodyBack = new ModelRenderer(this);
		BodyBack.setRotationPoint(0.0F, -2.9128F, 2.0038F);
		Body.addChild(BodyBack);
		this.setRotationAngle(BodyBack, -0.1309F, 0.0F, 0.0F);
		BodyBack.setTextureOffset(0, 17).addBox(-4.0F, -3.8486F, -0.0152F, 8.0F, 7.0F, 8.0F, 0.0F, false);

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, -2.8577F, -2.7764F);
		Body.addChild(Head);
		this.setRotationAngle(Head, 0.0873F, 0.0F, 0.0F);
		Head.setTextureOffset(33, 23).addBox(-3.0F, -2.4245F, -3.9909F, 6.0F, 5.0F, 4.0F, 0.0F, true);

		EarLeft = new ModelRenderer(this);
		EarLeft.setRotationPoint(2.9F, -2.2245F, -1.4909F);
		Head.addChild(EarLeft);
		this.setRotationAngle(EarLeft, 0.0F, 0.0F, 0.1309F);
		EarLeft.setTextureOffset(54, 28).addBox(-1.5F, -1.5F, -0.5F, 3.0F, 3.0F, 1.0F, 0.0F, false);

		EarRight = new ModelRenderer(this);
		EarRight.setRotationPoint(-2.9F, -2.2245F, -1.4909F);
		Head.addChild(EarRight);
		this.setRotationAngle(EarRight, 0.0F, 0.0F, -0.1309F);
		EarRight.setTextureOffset(54, 28).addBox(-1.5F, -1.5F, -0.5F, 3.0F, 3.0F, 1.0F, 0.0F, true);

		Nose = new ModelRenderer(this);
		Nose.setRotationPoint(0.0F, 0.8255F, -3.6909F);
		Head.addChild(Nose);
		this.setRotationAngle(Nose, 0.0873F, 0.0F, 0.0F);
		Nose.setTextureOffset(54, 24).addBox(-1.5F, -1.0174F, -1.1992F, 3.0F, 2.0F, 1.0F, 0.0F, false);

		Nose2 = new ModelRenderer(this);
		Nose2.setRotationPoint(0.0F, 0.0F, 0.025F);
		Nose.addChild(Nose2);
		this.setRotationAngle(Nose2, 0.0873F, 0.0F, 0.0F);
		Nose2.setTextureOffset(38, 19).addBox(-1.0F, -1.0521F, -1.4954F, 2.0F, 2.0F, 1.0F, 0.0F, false);

		LegFrontLeft = new ModelRenderer(this);
		LegFrontLeft.setRotationPoint(2.5512F, -3.5798F, -0.5496F);
		Body.addChild(LegFrontLeft);
		this.setRotationAngle(LegFrontLeft, 0.0873F, 0.0F, -0.0873F);
		LegFrontLeft.setTextureOffset(27, 9).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F, false);

		LegFrontLeft2 = new ModelRenderer(this);
		LegFrontLeft2.setRotationPoint(-0.1863F, 4.4963F, 0.0016F);
		LegFrontLeft.addChild(LegFrontLeft2);
		this.setRotationAngle(LegFrontLeft2, 0.0F, 0.0F, 0.0873F);
		LegFrontLeft2.setTextureOffset(27, 1).addBox(-1.3257F, -0.0076F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, false);
		LegFrontLeft2.setTextureOffset(27, 18).addBox(-1.3257F, 2.9924F, -2.4999F, 3.0F, 1.0F, 1.0F, 0.0F, false);

		LegBackLeft = new ModelRenderer(this);
		LegBackLeft.setRotationPoint(3.0512F, -4.1899F, 6.4237F);
		Body.addChild(LegBackLeft);
		this.setRotationAngle(LegBackLeft, 0.0F, 0.0F, -0.0873F);
		LegBackLeft.setTextureOffset(40, 9).addBox(-2.0057F, 0.0432F, -2.0038F, 4.0F, 5.0F, 4.0F, 0.0F, false);

		LegBackLeft2 = new ModelRenderer(this);
		LegBackLeft2.setRotationPoint(-0.1863F, 4.4963F, 0.0016F);
		LegBackLeft.addChild(LegBackLeft2);
		this.setRotationAngle(LegBackLeft2, 0.0873F, 0.0F, 0.0873F);
		LegBackLeft2.setTextureOffset(41, 1).addBox(-1.3257F, -0.0076F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, false);
		LegBackLeft2.setTextureOffset(27, 18).addBox(-1.3257F, 2.9923F, -2.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);

		LegFrontRight = new ModelRenderer(this);
		LegFrontRight.setRotationPoint(-2.4488F, -3.5798F, -0.5496F);
		Body.addChild(LegFrontRight);
		this.setRotationAngle(LegFrontRight, 0.0873F, 0.0F, 0.0873F);
		LegFrontRight.setTextureOffset(27, 9).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F, true);

		LegFrontRight2 = new ModelRenderer(this);
		LegFrontRight2.setRotationPoint(-0.1863F, 4.4963F, 0.0016F);
		LegFrontRight.addChild(LegFrontRight2);
		this.setRotationAngle(LegFrontRight2, 0.0F, 0.0F, -0.0873F);
		LegFrontRight2.setTextureOffset(27, 1).addBox(-1.3007F, -0.0076F, -1.4998F, 3.0F, 4.0F, 3.0F, 0.0F, false);
		LegFrontRight2.setTextureOffset(27, 18).addBox(-1.3007F, 2.9924F, -2.4999F, 3.0F, 1.0F, 1.0F, 0.0F, true);

		LegBackRight = new ModelRenderer(this);
		LegBackRight.setRotationPoint(-2.9488F, -4.1899F, 6.4237F);
		Body.addChild(LegBackRight);
		this.setRotationAngle(LegBackRight, 0.0F, 0.0F, 0.0873F);
		LegBackRight.setTextureOffset(40, 9).addBox(-2.0057F, 0.0432F, -2.0038F, 4.0F, 5.0F, 4.0F, 0.0F, false);

		LegBackRight2 = new ModelRenderer(this);
		LegBackRight2.setRotationPoint(-0.1863F, 4.4963F, 0.0016F);
		LegBackRight.addChild(LegBackRight2);
		this.setRotationAngle(LegBackRight2, 0.0873F, 0.0F, -0.0873F);
		LegBackRight2.setTextureOffset(41, 1).addBox(-1.3257F, -0.0076F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, false);
		LegBackRight2.setTextureOffset(27, 18).addBox(-1.3257F, 2.9923F, -2.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {

		this.Head.rotateAngleX = headPitch * 0.0174532925F;
		this.Head.rotateAngleY = (netHeadYaw / 3.75F) * 0.0174532925F;

		this.walk(LegFrontRight, LegFrontLeft, LegBackRight, LegBackLeft, limbSwing, limbSwingAmount);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {

		if (this.isChild) {
			matrixStack.scale(0.5F, 0.5F, 0.5F);
			matrixStack.translate(0, 1.5D, 0);
		}
		Body.render(matrixStack, buffer, packedLight, packedOverlay);
	}

}
