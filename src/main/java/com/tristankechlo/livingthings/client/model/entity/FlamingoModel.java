package com.tristankechlo.livingthings.client.model.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.tristankechlo.livingthings.entities.FlamingoEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FlamingoModel <T extends FlamingoEntity> extends EntityModel<T> {

	private final ModelRenderer Body;
	private final ModelRenderer back1;
	private final ModelRenderer bone9;
	private final ModelRenderer bone10;
	private final ModelRenderer Legs;
	private final ModelRenderer LeftLegTop;
	private final ModelRenderer LeftLegBottom;
	private final ModelRenderer LeftFoot;
	private final ModelRenderer RightLegTop;
	private final ModelRenderer RightLegBottom;
	private final ModelRenderer RightFoot;
	private final ModelRenderer NeckBottom;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone6;
	private final ModelRenderer bone7;
	private final ModelRenderer Head;
	private final ModelRenderer bone8;
	private final ModelRenderer Wings;
	private final ModelRenderer LeftWing;
	private final ModelRenderer RightWing;
	
	public FlamingoModel() {
		textureWidth = 128;
		textureHeight = 128;

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 20.9F, 0.0F);
		Body.setTextureOffset(0, 0).addBox(-3.0F, -15.9F, -4.0F, 6.0F, 7.0F, 8.0F, 0.0F, false);

		back1 = new ModelRenderer(this);
		back1.setRotationPoint(0.0F, -12.4F, 2.5F);
		Body.addChild(back1);
		setRotationAngle(back1, -0.1309F, 0.0F, 0.0F);
		back1.setTextureOffset(0, 0).addBox(-2.5F, -3.0F, 1.0F, 5.0F, 6.0F, 2.0F, 0.0F, false);

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(-0.5F, -0.5F, 3.0F);
		back1.addChild(bone9);
		setRotationAngle(bone9, -0.1309F, 0.0F, 0.0F);
		bone9.setTextureOffset(0, 0).addBox(-1.5F, -1.5F, -1.0F, 4.0F, 5.0F, 2.0F, 0.0F, false);

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(-0.5F, -0.4418F, 0.7827F);
		bone9.addChild(bone10);
		bone10.setTextureOffset(0, 0).addBox(-0.5F, 0.0F, -0.9F, 3.0F, 4.0F, 2.0F, 0.0F, false);

		Legs = new ModelRenderer(this);
		Legs.setRotationPoint(0.0F, -9.9F, 0.0F);
		Body.addChild(Legs);
		

		LeftLegTop = new ModelRenderer(this);
		LeftLegTop.setRotationPoint(2.0F, 1.0F, 0.0F);
		Legs.addChild(LeftLegTop);
		setRotationAngle(LeftLegTop, 0.1309F, 0.0F, 0.0F);
		LeftLegTop.setTextureOffset(0, 0).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 7.0F, 1.0F, 0.0F, false);

		LeftLegBottom = new ModelRenderer(this);
		LeftLegBottom.setRotationPoint(0.5F, 6.0F, 0.0F);
		LeftLegTop.addChild(LeftLegBottom);
		setRotationAngle(LeftLegBottom, -0.1745F, 0.0F, 0.0F);
		LeftLegBottom.setTextureOffset(0, 0).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 6.0F, 1.0F, 0.0F, false);

		LeftFoot = new ModelRenderer(this);
		LeftFoot.setRotationPoint(0.5F, 6.0F, 0.0F);
		LeftLegBottom.addChild(LeftFoot);
		setRotationAngle(LeftFoot, 0.0436F, 0.0F, 0.0F);
		LeftFoot.setTextureOffset(0, 0).addBox(-1.5F, -1.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);

		RightLegTop = new ModelRenderer(this);
		RightLegTop.setRotationPoint(-2.0F, 1.0F, 0.0F);
		Legs.addChild(RightLegTop);
		setRotationAngle(RightLegTop, 0.1309F, 0.0F, 0.0F);
		RightLegTop.setTextureOffset(0, 0).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 7.0F, 1.0F, 0.0F, false);

		RightLegBottom = new ModelRenderer(this);
		RightLegBottom.setRotationPoint(0.5F, 6.0F, 0.0F);
		RightLegTop.addChild(RightLegBottom);
		setRotationAngle(RightLegBottom, -0.1745F, 0.0F, 0.0F);
		RightLegBottom.setTextureOffset(0, 0).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 6.0F, 1.0F, 0.0F, false);

		RightFoot = new ModelRenderer(this);
		RightFoot.setRotationPoint(0.5F, 6.0F, 0.0F);
		RightLegBottom.addChild(RightFoot);
		setRotationAngle(RightFoot, 0.0436F, 0.0F, 0.0F);
		RightFoot.setTextureOffset(0, 0).addBox(-1.5F, -1.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);

		NeckBottom = new ModelRenderer(this);
		NeckBottom.setRotationPoint(0.0F, -11.9F, -4.0F);
		Body.addChild(NeckBottom);
		setRotationAngle(NeckBottom, -0.1745F, 0.0F, 0.0F);
		NeckBottom.setTextureOffset(0, 0).addBox(-2.5F, -3.0F, -1.0F, 5.0F, 5.0F, 2.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, 0.0F, -1.0F);
		NeckBottom.addChild(bone4);
		setRotationAngle(bone4, -0.2618F, 0.0F, 0.0F);
		bone4.setTextureOffset(0, 0).addBox(-2.0F, -2.5F, -1.0F, 4.0F, 4.0F, 3.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(0.0F, 0.0F, -1.0F);
		bone4.addChild(bone5);
		setRotationAngle(bone5, -0.5236F, 0.0F, 0.0F);
		bone5.setTextureOffset(0, 0).addBox(-1.5F, -2.0F, -1.0F, 3.0F, 3.0F, 2.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, -0.1109F, -1.2724F);
		bone5.addChild(bone);
		setRotationAngle(bone, -0.6545F, 0.0F, 0.0F);
		bone.setTextureOffset(0, 0).addBox(-1.0F, -1.5F, -2.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, -0.5921F, -2.4868F);
		bone.addChild(bone2);
		setRotationAngle(bone2, 1.3963F, 0.0F, 0.0F);
		bone2.setTextureOffset(0, 0).addBox(-1.0F, -0.825F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, -0.725F, -0.025F);
		bone2.addChild(bone3);
		setRotationAngle(bone3, -0.0785F, 0.0F, 0.0F);
		bone3.setTextureOffset(0, 0).addBox(-1.0F, -0.85F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(0.0F, -1.225F, -0.025F);
		bone3.addChild(bone6);
		setRotationAngle(bone6, 0.0436F, 0.0F, 0.0F);
		bone6.setTextureOffset(0, 0).addBox(-1.0F, -2.5F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(0.0F, -2.5F, 0.0F);
		bone6.addChild(bone7);
		setRotationAngle(bone7, 0.2182F, 0.0F, 0.0F);
		

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, 0.4F, 0.0F);
		bone7.addChild(Head);
		setRotationAngle(Head, 0.0F, 0.0F, 0.0F);
		Head.setTextureOffset(100, 102).addBox(-1.5F, -3.0F, -4.0F, 3.0F, 3.0F, 5.0F, 0.0F, false);

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(0.0F, -1.575F, -2.55F);
		Head.addChild(bone8);
		setRotationAngle(bone8, 0.0873F, 0.0F, 0.0F);
		bone8.setTextureOffset(0, 0).addBox(-1.0F, -0.5152F, -3.1737F, 2.0F, 2.0F, 3.0F, 0.0F, false);

		Wings = new ModelRenderer(this);
		Wings.setRotationPoint(0.0F, 3.1F, 0.0F);
		Body.addChild(Wings);
		

		LeftWing = new ModelRenderer(this);
		LeftWing.setRotationPoint(3.0F, -15.5F, -3.0F);
		Wings.addChild(LeftWing);
		setRotationAngle(LeftWing, -0.1745F, 0.0873F, 0.0F);
		LeftWing.setTextureOffset(0, 0).addBox(0.0F, -2.5F, 0.0F, 1.0F, 5.0F, 7.0F, 0.0F, false);

		RightWing = new ModelRenderer(this);
		RightWing.setRotationPoint(-3.0F, -15.5F, -3.0F);
		Wings.addChild(RightWing);
		setRotationAngle(RightWing, -0.1745F, -0.0873F, 0.0F);
		RightWing.setTextureOffset(0, 0).addBox(-1.0F, -2.5F, 0.0F, 1.0F, 5.0F, 7.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T flamingo, float limbSwing, float limbSwingAmount, float ageInTicks,	float netHeadYaw, float headPitch) {
		if(flamingo.rightLegUp) {
			
		} else if (flamingo.leftLegUp) {
			
		} else {
			
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
