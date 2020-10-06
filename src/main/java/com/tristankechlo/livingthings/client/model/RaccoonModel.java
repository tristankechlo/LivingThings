package com.tristankechlo.livingthings.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.tristankechlo.livingthings.entities.RaccoonEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RaccoonModel <T extends RaccoonEntity> extends EntityModel<T> {
	
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
		textureWidth = 64;
		textureHeight = 32;

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 24.0F, 0.0F);
		Body.setTextureOffset(0, 1).addBox(-3.0F, -11.0F, -5.5F, 6.0F, 6.0F, 11.0F, 0.0F, false);

		LegFrontLeft = new ModelRenderer(this);
		LegFrontLeft.setRotationPoint(1.75F, -5.0F, -3.5F);
		Body.addChild(LegFrontLeft);
		LegFrontLeft.setTextureOffset(0, 25).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

		LegBackLeft = new ModelRenderer(this);
		LegBackLeft.setRotationPoint(1.75F, -5.0F, 3.5F);
		Body.addChild(LegBackLeft);
		LegBackLeft.setTextureOffset(9, 25).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

		LegFrontRight = new ModelRenderer(this);
		LegFrontRight.setRotationPoint(-1.75F, -5.0F, -3.5F);
		Body.addChild(LegFrontRight);
		LegFrontRight.setTextureOffset(18, 25).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

		LegBackRight = new ModelRenderer(this);
		LegBackRight.setRotationPoint(-1.75F, -5.0F, 3.5F);
		Body.addChild(LegBackRight);
		LegBackRight.setTextureOffset(27, 25).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

		Tail = new ModelRenderer(this);
		Tail.setRotationPoint(0.0F, -8.95F, 4.65F);
		Body.addChild(Tail);
		setRotationAngle(Tail, -0.4363F, 0.0F, 0.0F);
		Tail.setTextureOffset(36, 20).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 8.0F, 0.0F, false);

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, -9.0F, -5.5F);
		Body.addChild(Head);
		Head.setTextureOffset(36, 7).addBox(-4.0F, -3.0F, -5.0F, 8.0F, 6.0F, 5.0F, 0.0F, false);

		LeftEar = new ModelRenderer(this);
		LeftEar.setRotationPoint(2.5F, -3.0F, -2.0F);
		Head.addChild(LeftEar);
		LeftEar.setTextureOffset(0, 20).addBox(-1.0F, -1.75F, -0.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);

		RightEar = new ModelRenderer(this);
		RightEar.setRotationPoint(-2.5F, -3.0F, -1.75F);
		Head.addChild(RightEar);
		RightEar.setTextureOffset(7, 20).addBox(-1.0F, -1.75F, -0.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);

		Mouth = new ModelRenderer(this);
		Mouth.setRotationPoint(0.0F, 1.25F, -5.0F);
		Head.addChild(Mouth);
		Mouth.setTextureOffset(25, 4).addBox(-2.0F, -1.5F, -3.0F, 4.0F, 3.0F, 3.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.Head.rotateAngleX = headPitch * ((float) Math.PI / 180F);
	    this.Head.rotateAngleY = (netHeadYaw / 3.75F) * ((float) Math.PI / 180F);
		
		this.LegBackRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F	* limbSwingAmount;
		this.LegBackLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.LegFrontRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.LegFrontLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;

		this.Tail.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.75F	* limbSwingAmount;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		if(this.isChild) {
			matrixStack.scale(0.5F, 0.5F, 0.5F);
			matrixStack.translate(0, 1.5D, 0);
		}
		Body.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}