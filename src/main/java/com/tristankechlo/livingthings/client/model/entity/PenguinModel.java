package com.tristankechlo.livingthings.client.model.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.tristankechlo.livingthings.client.model.AdvancedEntityModel;
import com.tristankechlo.livingthings.entities.PenguinEntity;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PenguinModel<T extends PenguinEntity> extends AdvancedEntityModel<T> {

	private final ModelRenderer Body;
	private final ModelRenderer RightFeet;
	private final ModelRenderer LeftFeet;
	private final ModelRenderer LeftWing;
	private final ModelRenderer RightWing;
	private final ModelRenderer Head;
	private final ModelRenderer Beak;

	public PenguinModel() {
		textureWidth = 64;
		textureHeight = 42;

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 24.0F, -1.0833F);
		Body.setTextureOffset(0, 0).addBox(-5.0F, -16.0F, -3.9167F, 10.0F, 16.0F, 9.0F, 0.0F, false);

		RightFeet = new ModelRenderer(this);
		RightFeet.setRotationPoint(-4.0F, -0.5F, -2.9167F);
		Body.addChild(RightFeet);
		setRotationAngle(RightFeet, 0.0F, 0.1745F, 0.0F);
		RightFeet.setTextureOffset(54, 10).addBox(-1.0F, -0.5F, -3.0F, 2.0F, 1.0F, 3.0F, 0.0F, false);

		LeftFeet = new ModelRenderer(this);
		LeftFeet.setRotationPoint(4.0F, -0.5F, -2.9167F);
		Body.addChild(LeftFeet);
		setRotationAngle(LeftFeet, 0.0F, -0.1745F, 0.0F);
		LeftFeet.setTextureOffset(54, 10).addBox(-1.0F, -0.5F, -3.0F, 2.0F, 1.0F, 3.0F, 0.0F, true);

		LeftWing = new ModelRenderer(this);
		LeftWing.setRotationPoint(5.0F, -14.0F, 1.0833F);
		Body.addChild(LeftWing);
		setRotationAngle(LeftWing, 0.0F, 0.0F, -0.0873F);
		LeftWing.setTextureOffset(46, 23).addBox(0.0F, 0.0F, -4.5F, 1.0F, 11.0F, 8.0F, 0.0F, false);

		RightWing = new ModelRenderer(this);
		RightWing.setRotationPoint(-5.0F, -14.0F, 1.0833F);
		Body.addChild(RightWing);
		setRotationAngle(RightWing, 0.0F, 0.0F, 0.0873F);
		RightWing.setTextureOffset(46, 23).addBox(-1.0F, 0.0F, -4.5F, 1.0F, 11.0F, 8.0F, 0.0F, true);

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, 8.0F, -1.0F);
		Head.setTextureOffset(0, 27).addBox(-4.0F, -7.0F, -3.75F, 8.0F, 7.0F, 8.0F, 0.0F, false);

		Beak = new ModelRenderer(this);
		Beak.setRotationPoint(0.0F, -1.75F, -3.75F);
		Head.addChild(Beak);
		Beak.setTextureOffset(54, 0).addBox(-1.5F, -1.0F, -2.0F, 3.0F, 2.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {

		this.defaultHeadMovement(Head, 0, 0, headPitch, netHeadYaw);

		// wobbling effect while walking
		this.Body.rotateAngleZ = (MathHelper.cos(limbSwing * 1.3324F) * 0.75F * limbSwingAmount) / 7;

		// animate penguin swinging wings
		this.RightWing.rotateAngleZ = (0.1308996938F)
				+ ((0.7872664625F + MathHelper.cos(limbSwing * 0.6662F) * 1.0F) * limbSwingAmount);
		this.LeftWing.rotateAngleZ = (-0.1308996938F)
				+ ((-0.7872664625F + MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.0F) * limbSwingAmount);
		this.RightFeet.rotateAngleX = -((MathHelper.cos(limbSwing * 1.3324F) * 0.75F * limbSwingAmount) / 2);
		this.LeftFeet.rotateAngleX = (MathHelper.cos(limbSwing * 1.3324F) * 0.75F * limbSwingAmount) / 2;

	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha) {

		if (this.isChild) {
			matrixStackIn.push();
			this.Beak.setRotationPoint(0.0F, -2.25F, -3.75F);
			float f = 0.55F;
			matrixStackIn.scale(f, f, f);
			matrixStackIn.translate(0.0D, 1.32D, 0.02D);
			this.Head.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
			matrixStackIn.pop();

			matrixStackIn.push();
			float f1 = 0.5F;
			matrixStackIn.scale(f1, f1, f1);
			matrixStackIn.translate(0.0D, 1.5D, 0.0D);
			this.Body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
			matrixStackIn.pop();
		} else {
			this.Beak.setRotationPoint(0.0F, -1.75F, -3.75F);
			this.Body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
			this.Head.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		}
	}

}
