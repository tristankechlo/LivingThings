package com.tristankechlo.livingthings.client.model.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.tristankechlo.livingthings.entities.GiraffeEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GiraffeModel <T extends GiraffeEntity> extends EntityModel<T>{
	
	private final ModelRenderer Body;
	private final ModelRenderer Legs;
	private final ModelRenderer FrontRightLeg;
	private final ModelRenderer FrontLeftLeg;
	private final ModelRenderer BackLeftLeg;
	private final ModelRenderer BackRightLeg;
	private final ModelRenderer NeckBottom;
	private final ModelRenderer NeckMiddle;
	private final ModelRenderer NeckTop;
	private final ModelRenderer Head;
	private final ModelRenderer FrontHead;
	private final ModelRenderer Horns;
	private final ModelRenderer LeftHorn;
	private final ModelRenderer RightHorn;
	private final ModelRenderer Ears;
	private final ModelRenderer LeftEar;
	private final ModelRenderer RightEar;
	private final ModelRenderer TailTop;
	private final ModelRenderer TailBottom;
	
	public GiraffeModel() {
		textureWidth = 128;
		textureHeight = 64;

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 14.5F, 0.6F);
		Body.setTextureOffset(72, 35).addBox(-6.0F, -21.5F, -13.6F, 12.0F, 13.0F, 16.0F, 0.0F, false);
		Body.setTextureOffset(81, 14).addBox(-5.5F, -19.5F, 2.4F, 11.0F, 11.0F, 10.0F, 0.0F, false);

		Legs = new ModelRenderer(this);
		Legs.setRotationPoint(0.0F, -7.5F, -0.6F);
		Body.addChild(Legs);
		

		FrontRightLeg = new ModelRenderer(this);
		FrontRightLeg.setRotationPoint(-4.0F, -1.0F, -10.0F);
		Legs.addChild(FrontRightLeg);
		FrontRightLeg.setTextureOffset(98, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
		FrontRightLeg.setTextureOffset(116, 0).addBox(-1.5F, 8.0F, -1.5F, 3.0F, 10.0F, 3.0F, 0.0F, false);

		FrontLeftLeg = new ModelRenderer(this);
		FrontLeftLeg.setRotationPoint(4.0F, -1.0F, -10.0F);
		Legs.addChild(FrontLeftLeg);
		FrontLeftLeg.setTextureOffset(98, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, true);
		FrontLeftLeg.setTextureOffset(116, 0).addBox(-1.5F, 8.0F, -1.5F, 3.0F, 10.0F, 3.0F, 0.0F, false);

		BackLeftLeg = new ModelRenderer(this);
		BackLeftLeg.setRotationPoint(4.0F, -3.0F, 10.0F);
		Legs.addChild(BackLeftLeg);
		BackLeftLeg.setTextureOffset(70, 0).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);
		BackLeftLeg.setTextureOffset(116, 0).addBox(-1.5F, 10.0F, -1.5F, 3.0F, 10.0F, 3.0F, 0.0F, false);

		BackRightLeg = new ModelRenderer(this);
		BackRightLeg.setRotationPoint(-4.0F, -3.0F, 10.0F);
		Legs.addChild(BackRightLeg);
		BackRightLeg.setTextureOffset(70, 0).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
		BackRightLeg.setTextureOffset(116, 0).addBox(-1.5F, 10.0F, -1.5F, 3.0F, 10.0F, 3.0F, 0.0F, false);

		NeckBottom = new ModelRenderer(this);
		NeckBottom.setRotationPoint(0.0F, -21.5F, -13.6F);
		Body.addChild(NeckBottom);
		setRotationAngle(NeckBottom, 0.5236F, 0.0F, 0.0F);
		NeckBottom.setTextureOffset(0, 46).addBox(-4.5F, -2.0F, -3.5F, 9.0F, 9.0F, 9.0F, 0.0F, false);

		NeckMiddle = new ModelRenderer(this);
		NeckMiddle.setRotationPoint(0.0F, -2.0F, 1.0F);
		NeckBottom.addChild(NeckMiddle);
		setRotationAngle(NeckMiddle, -0.1309F, 0.0F, 0.0F);
		NeckMiddle.setTextureOffset(0, 22).addBox(-3.5F, -10.0F, -3.5F, 7.0F, 11.0F, 7.0F, 0.0F, false);

		NeckTop = new ModelRenderer(this);
		NeckTop.setRotationPoint(0.0F, -10.0F, 0.0F);
		NeckMiddle.addChild(NeckTop);
		setRotationAngle(NeckTop, -0.1309F, 0.0F, 0.0F);
		NeckTop.setTextureOffset(0, 0).addBox(-2.5F, -10.0F, -2.5F, 5.0F, 11.0F, 5.0F, 0.0F, false);

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, -8.0F, 0.0F);
		NeckTop.addChild(Head);
		setRotationAngle(Head, -0.1309F, 0.0F, 0.0F);
		Head.setTextureOffset(41, 52).addBox(-3.0F, -6.0F, -4.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);

		FrontHead = new ModelRenderer(this);
		FrontHead.setRotationPoint(0.0F, -2.0F, -4.0F);
		Head.addChild(FrontHead);
		FrontHead.setTextureOffset(46, 41).addBox(-2.0F, -2.0F, -4.0F, 4.0F, 3.0F, 4.0F, 0.0F, false);

		Horns = new ModelRenderer(this);
		Horns.setRotationPoint(0.0F, 0.0F, 0.0F);
		Head.addChild(Horns);
		

		LeftHorn = new ModelRenderer(this);
		LeftHorn.setRotationPoint(1.0F, -6.0F, 0.0F);
		Horns.addChild(LeftHorn);
		setRotationAngle(LeftHorn, -0.0873F, 0.0F, 0.0F);
		LeftHorn.setTextureOffset(30, 15).addBox(0.5F, -2.5F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		RightHorn = new ModelRenderer(this);
		RightHorn.setRotationPoint(-2.0F, -6.0F, 0.0F);
		Horns.addChild(RightHorn);
		setRotationAngle(RightHorn, -0.0873F, 0.0F, 0.0F);
		RightHorn.setTextureOffset(30, 15).addBox(-0.5F, -2.5F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		Ears = new ModelRenderer(this);
		Ears.setRotationPoint(0.0F, 0.0F, 0.0F);
		Head.addChild(Ears);
		

		LeftEar = new ModelRenderer(this);
		LeftEar.setRotationPoint(3.0F, -4.0F, 0.0F);
		Ears.addChild(LeftEar);
		LeftEar.setTextureOffset(36, 16).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		RightEar = new ModelRenderer(this);
		RightEar.setRotationPoint(-3.0F, -4.0F, 0.0F);
		Ears.addChild(RightEar);
		RightEar.setTextureOffset(36, 16).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		TailTop = new ModelRenderer(this);
		TailTop.setRotationPoint(0.0F, -16.5F, 12.4F);
		Body.addChild(TailTop);
		setRotationAngle(TailTop, 0.1745F, 0.0F, 0.0F);
		TailTop.setTextureOffset(48, 4).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 9.0F, 1.0F, 0.0F, false);

		TailBottom = new ModelRenderer(this);
		TailBottom.setRotationPoint(0.0F, 9.0F, 0.0F);
		TailTop.addChild(TailBottom);
		setRotationAngle(TailBottom, -0.1309F, 0.0F, 0.0F);
		TailBottom.setTextureOffset(53, 4).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 7.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		if(this.isChild) {
			matrixStackIn.scale(0.6F, 0.6F, 0.6F);
			matrixStackIn.translate(0, 1, 0);
		}
		Body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
		
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,	float netHeadYaw, float headPitch) {
		this.Head.rotateAngleX = headPitch * ((float) Math.PI / 180F);
		this.NeckTop.rotateAngleX = (float) (this.NeckMiddle.rotateAngleX / 1.5);
		this.NeckMiddle.rotateAngleX = (float) (this.Head.rotateAngleX / 3);

	    this.Head.rotateAngleY = (netHeadYaw / 3.75F) * ((float) Math.PI / 180F);
		this.NeckTop.rotateAngleY = (netHeadYaw / 3.75F) * ((float) Math.PI / 180F);
		this.NeckMiddle.rotateAngleY = (netHeadYaw / 3.75F) * ((float) Math.PI / 180F);
		this.NeckBottom.rotateAngleY = (netHeadYaw / 5F) * ((float) Math.PI / 180F);
		
		this.FrontRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F	* limbSwingAmount;
		this.BackRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.FrontLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.BackLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		
		this.TailTop.rotateAngleZ = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.75F	* limbSwingAmount;
		this.TailBottom.rotateAngleZ = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.75F	* limbSwingAmount;		
	}
		
	private void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

}
