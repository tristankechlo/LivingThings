package com.tristankechlo.livingthings.client.model.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.tristankechlo.livingthings.entities.FlamingoEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FlamingoModel <T extends FlamingoEntity> extends EntityModel<T> {

	private final ModelRenderer Body;
	private final ModelRenderer Back1;
	private final ModelRenderer Back2;
	private final ModelRenderer Back3;
	private final ModelRenderer Legs;
	private final ModelRenderer LeftLegTop;
	private final ModelRenderer LeftLegBottom;
	private final ModelRenderer LeftFoot;
	private final ModelRenderer RightLegTop;
	private final ModelRenderer RightLegBottom;
	private final ModelRenderer RightFoot;
	private final ModelRenderer NeckBottom;
	private final ModelRenderer Neck2;
	private final ModelRenderer Neck3;
	private final ModelRenderer Neck4;
	private final ModelRenderer Neck5;
	private final ModelRenderer NeckTop;
	private final ModelRenderer Head;
	private final ModelRenderer Beak;
	private final ModelRenderer Beak2;
	private final ModelRenderer Beak3;
	private final ModelRenderer Wings;
	private final ModelRenderer LeftWing;
	private final ModelRenderer RightWing;
	
	public FlamingoModel() {
		textureWidth = 64;
		textureHeight = 64;

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 20.9F, 0.0F);
		Body.setTextureOffset(36, 49).addBox(-3.0F, -15.9F, -4.0F, 6.0F, 7.0F, 8.0F, 0.0F, false);

		Back1 = new ModelRenderer(this);
		Back1.setRotationPoint(0.0F, -12.55F, 7.475F);
		Body.addChild(Back1);
		setRotationAngle(Back1, -0.1833F, 0.0F, 0.0F);
		Back1.setTextureOffset(52, 25).addBox(-2.5F, -0.4808F, -0.8778F, 5.0F, 4.0F, 1.0F, 0.0F, false);

		Back2 = new ModelRenderer(this);
		Back2.setRotationPoint(0.0F, -15.05F, 3.85F);
		Body.addChild(Back2);
		Back2.setTextureOffset(48, 31).addBox(-2.5F, 1.925F, -0.375F, 5.0F, 4.0F, 3.0F, 0.0F, false);

		Back3 = new ModelRenderer(this);
		Back3.setRotationPoint(0.0F, -15.3F, 3.8F);
		Body.addChild(Back3);
		setRotationAngle(Back3, -0.6109F, 0.0F, 0.0F);
		Back3.setTextureOffset(44, 39).addBox(-2.5F, -0.3463F, -0.5502F, 5.0F, 4.0F, 5.0F, 0.0F, false);

		Legs = new ModelRenderer(this);
		Legs.setRotationPoint(0.0F, -9.9F, 0.0F);
		Body.addChild(Legs);
		

		LeftLegTop = new ModelRenderer(this);
		LeftLegTop.setRotationPoint(2.0F, 1.0F, 0.0F);
		Legs.addChild(LeftLegTop);
		setRotationAngle(LeftLegTop, 0.1309F, 0.0F, 0.0F);
		LeftLegTop.setTextureOffset(0, 56).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 7.0F, 1.0F, 0.0F, false);

		LeftLegBottom = new ModelRenderer(this);
		LeftLegBottom.setRotationPoint(0.5F, 6.0F, 0.0F);
		LeftLegTop.addChild(LeftLegBottom);
		setRotationAngle(LeftLegBottom, -0.1745F, 0.0F, 0.0F);
		LeftLegBottom.setTextureOffset(5, 57).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 6.0F, 1.0F, 0.0F, false);

		LeftFoot = new ModelRenderer(this);
		LeftFoot.setRotationPoint(0.5F, 6.0F, 0.0F);
		LeftLegBottom.addChild(LeftFoot);
		setRotationAngle(LeftFoot, 0.0436F, 0.0F, 0.0F);
		LeftFoot.setTextureOffset(10, 61).addBox(-1.5F, -1.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);

		RightLegTop = new ModelRenderer(this);
		RightLegTop.setRotationPoint(-2.0F, 1.0F, 0.0F);
		Legs.addChild(RightLegTop);
		setRotationAngle(RightLegTop, 0.1309F, 0.0F, 0.0F);
		RightLegTop.setTextureOffset(0, 46).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 7.0F, 1.0F, 0.0F, false);

		RightLegBottom = new ModelRenderer(this);
		RightLegBottom.setRotationPoint(0.5F, 6.0F, 0.0F);
		RightLegTop.addChild(RightLegBottom);
		setRotationAngle(RightLegBottom, -0.1745F, 0.0F, 0.0F);
		RightLegBottom.setTextureOffset(5, 47).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 6.0F, 1.0F, 0.0F, false);

		RightFoot = new ModelRenderer(this);
		RightFoot.setRotationPoint(0.5F, 6.0F, 0.0F);
		RightLegBottom.addChild(RightFoot);
		setRotationAngle(RightFoot, 0.0436F, 0.0F, 0.0F);
		RightFoot.setTextureOffset(10, 51).addBox(-1.5F, -1.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);

		NeckBottom = new ModelRenderer(this);
		NeckBottom.setRotationPoint(0.0F, -11.9F, -4.0F);
		Body.addChild(NeckBottom);
		setRotationAngle(NeckBottom, -0.1745F, 0.0F, 0.0F);
		NeckBottom.setTextureOffset(0, 35).addBox(-2.5F, -3.0F, -1.0F, 5.0F, 5.0F, 2.0F, 0.0F, false);

		Neck2 = new ModelRenderer(this);
		Neck2.setRotationPoint(0.0F, 0.0F, -1.0F);
		NeckBottom.addChild(Neck2);
		setRotationAngle(Neck2, -0.2618F, 0.0F, 0.0F);
		Neck2.setTextureOffset(0, 27).addBox(-2.0F, -2.5F, -1.0F, 4.0F, 4.0F, 3.0F, 0.0F, false);

		Neck3 = new ModelRenderer(this);
		Neck3.setRotationPoint(0.0F, 0.0F, -1.0F);
		Neck2.addChild(Neck3);
		setRotationAngle(Neck3, -0.5236F, 0.0F, 0.0F);
		Neck3.setTextureOffset(0, 21).addBox(-1.5F, -2.0F, -1.0F, 3.0F, 3.0F, 2.0F, 0.0F, false);

		Neck4 = new ModelRenderer(this);
		Neck4.setRotationPoint(0.0F, -0.1109F, -1.2724F);
		Neck3.addChild(Neck4);
		setRotationAngle(Neck4, -0.6545F, 0.0F, 0.0F);
		Neck4.setTextureOffset(0, 15).addBox(-1.0F, -1.5F, -2.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);

		Neck5 = new ModelRenderer(this);
		Neck5.setRotationPoint(0.0F, -0.5921F, -2.4868F);
		Neck4.addChild(Neck5);
		setRotationAngle(Neck5, 1.3963F, 0.0F, 0.0F);
		Neck5.setTextureOffset(0, 10).addBox(-1.0F, -0.825F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		NeckTop = new ModelRenderer(this);
		NeckTop.setRotationPoint(0.0F, -1.95F, -0.05F);
		Neck5.addChild(NeckTop);
		setRotationAngle(NeckTop, 0.0436F, 0.0F, 0.0F);
		NeckTop.setTextureOffset(0, 3).addBox(-1.0F, -2.5F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, -2.126F, 0.1477F);
		NeckTop.addChild(Head);
		setRotationAngle(Head, 0.2618F, 0.0F, 0.0F);
		Head.setTextureOffset(21, 28).addBox(-1.5F, -3.0F, -4.0F, 3.0F, 3.0F, 5.0F, 0.0F, false);

		Beak = new ModelRenderer(this);
		Beak.setRotationPoint(0.0F, -1.5619F, -2.4006F);
		Head.addChild(Beak);
		setRotationAngle(Beak, 0.0873F, 0.0F, 0.0F);
		Beak.setTextureOffset(23, 19).addBox(-1.0F, -0.5152F, -3.1737F, 2.0F, 2.0F, 3.0F, 0.0F, false);

		Beak2 = new ModelRenderer(this);
		Beak2.setRotationPoint(0.0F, 0.1884F, -3.2262F);
		Beak.addChild(Beak2);
		setRotationAngle(Beak2, -0.8727F, 0.0F, 0.0F);
		Beak2.setTextureOffset(35, 21).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);

		Beak3 = new ModelRenderer(this);
		Beak3.setRotationPoint(0.0F, 1.1255F, -3.6706F);
		Beak.addChild(Beak3);
		setRotationAngle(Beak3, 0.2662F, 0.0F, 0.0F);
		Beak3.setTextureOffset(34, 25).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		Wings = new ModelRenderer(this);
		Wings.setRotationPoint(0.0F, 3.1F, 0.0F);
		Body.addChild(Wings);
		

		LeftWing = new ModelRenderer(this);
		LeftWing.setRotationPoint(3.0F, -15.5F, -3.0F);
		Wings.addChild(LeftWing);
		setRotationAngle(LeftWing, -0.1745F, 0.0873F, 0.0F);
		LeftWing.setTextureOffset(48, 0).addBox(0.0F, -2.5F, 0.0F, 1.0F, 5.0F, 7.0F, 0.0F, false);

		RightWing = new ModelRenderer(this);
		RightWing.setRotationPoint(-3.0F, -15.5F, -3.0F);
		Wings.addChild(RightWing);
		setRotationAngle(RightWing, -0.1745F, -0.0873F, 0.0F);
		RightWing.setTextureOffset(31, 0).addBox(-1.0F, -2.5F, 0.0F, 1.0F, 5.0F, 7.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T flamingo, float limbSwing, float limbSwingAmount, float ageInTicks,	float netHeadYaw, float headPitch) {

		this.Head.rotateAngleX = 0.2617993875F + (headPitch * 0.0174532925F);
	    this.Head.rotateAngleY = (netHeadYaw / 3.75F) * 0.0174532925F;
	    
		if(flamingo.isLeftLegUp()) {
			//values are defined by: ((Math.PI / 180) * AngleIn°)
			this.LeftLegTop.rotateAngleX = 1.3089969389F;
			this.LeftLegBottom.rotateAngleX = -2.7052603405F;
			this.LeftFoot.rotateAngleX = 0.174532925F;
			
			this.RightLegTop.rotateAngleX = 0.1308996938F;
			this.RightLegBottom.rotateAngleX = -0.174532925F;
			this.RightFoot.rotateAngleX = 0.0436332312F;
			
		} else if (flamingo.isRightLegUp()) {

			this.LeftLegTop.rotateAngleX = 0.1308996938F;
			this.LeftLegBottom.rotateAngleX = -0.174532925F;
			this.LeftFoot.rotateAngleX = 0.0436332312F;
			
			this.RightLegTop.rotateAngleX = 1.3089969389F;
			this.RightLegBottom.rotateAngleX = -2.7052603405F;
			this.RightFoot.rotateAngleX = 0.174532925F;
			
		} else {
			this.LeftLegTop.rotateAngleX = 0.1308996937F + (MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount);
			this.LeftLegBottom.rotateAngleX = -0.174532925F;
			this.LeftFoot.rotateAngleX = 0.0436332312F;
			
			this.RightLegTop.rotateAngleX = 0.1308996937F + (MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmount);
			this.RightLegBottom.rotateAngleX = -0.174532925F;
			this.RightFoot.rotateAngleX = 0.0436332312F;
		}
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		if(this.isChild) {
			matrixStackIn.scale(0.5F, 0.5F, 0.5F);
			matrixStackIn.translate(0, 1.5D, 0);
		}
		Body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
	}
	

	private void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

}
