package com.tristankechlo.livingthings.client.model.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.tristankechlo.livingthings.entities.OstrichEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OstrichModel<T extends OstrichEntity> extends EntityModel<T> {

	private final ModelRenderer Body;
	private final ModelRenderer Neck;
	private final ModelRenderer NeckTop;
	private final ModelRenderer Head;
	private final ModelRenderer Mouth;
	private final ModelRenderer MouthTop;
	private final ModelRenderer LeftWing;
	private final ModelRenderer RightWing;
	private final ModelRenderer LeftLegTop;
	private final ModelRenderer LeftLegBottom;
	private final ModelRenderer LeftFoot;
	private final ModelRenderer RightLegTop;
	private final ModelRenderer RightLegBottom;
	private final ModelRenderer RightFoot;
	private final ModelRenderer Back;
	private final ModelRenderer add1;
	private final ModelRenderer add1Left;
	private final ModelRenderer add1Right;
	private final ModelRenderer add2;
	private final ModelRenderer add2Left;
	private final ModelRenderer add2Right;
	private final ModelRenderer add3;
	private final ModelRenderer add3Left;
	private final ModelRenderer add3Right;

	private boolean islayingEgg;
	private boolean isbuildingNest;

	public OstrichModel() {
		textureWidth = 64;
		textureHeight = 64;

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 8.0F, 0.0F);
		setRotationAngle(Body, -0.0436F, 0.0F, 0.0F);
		Body.setTextureOffset(0, 44).addBox(-4.5F, -5.0F, -4.0F, 9.0F, 9.0F, 11.0F, 0.0F, false);
		Body.setTextureOffset(42, 53).addBox(-4.0F, -5.0F, -7.0F, 8.0F, 8.0F, 3.0F, 0.0F, false);

		Neck = new ModelRenderer(this);
		Neck.setRotationPoint(0.0F, -1.0F, -7.5F);
		Body.addChild(Neck);
		setRotationAngle(Neck, 0.3054F, 0.0F, 0.0F);
		Neck.setTextureOffset(29, 0).addBox(-1.5F, -5.0F, -0.5F, 3.0F, 7.0F, 3.0F, 0.0F, false);

		NeckTop = new ModelRenderer(this);
		NeckTop.setRotationPoint(0.0F, -5.0F, 1.0F);
		Neck.addChild(NeckTop);
		setRotationAngle(NeckTop, -0.2182F, 0.0F, 0.0F);
		NeckTop.setTextureOffset(29, 11).addBox(-1.0F, -7.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, -7.0F, 0.0F);
		NeckTop.addChild(Head);
		setRotationAngle(Head, -0.0436F, 0.0F, 0.0F);
		Head.setTextureOffset(29, 22).addBox(-1.5F, -3.0F, -2.75F, 3.0F, 3.0F, 4.0F, 0.0F, false);

		Mouth = new ModelRenderer(this);
		Mouth.setRotationPoint(0.0F, 0.0F, -3.0F);
		Head.addChild(Mouth);
		Mouth.setTextureOffset(29, 30).addBox(-1.0F, -1.3F, -1.25F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		MouthTop = new ModelRenderer(this);
		MouthTop.setRotationPoint(0.0F, -0.8F, -0.25F);
		Mouth.addChild(MouthTop);
		setRotationAngle(MouthTop, 0.2182F, 0.0F, 0.0F);
		MouthTop.setTextureOffset(29, 30).addBox(-1.0F, -0.75F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		LeftWing = new ModelRenderer(this);
		LeftWing.setRotationPoint(4.5F, -2.0F, -3.0F);
		Body.addChild(LeftWing);
		setRotationAngle(LeftWing, 0.0F, 0.1745F, 0.0F);
		LeftWing.setTextureOffset(44, 22).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 5.0F, 9.0F, 0.0F, false);

		RightWing = new ModelRenderer(this);
		RightWing.setRotationPoint(-4.5F, -2.0F, -3.0F);
		Body.addChild(RightWing);
		setRotationAngle(RightWing, 0.0F, -0.1745F, 0.0F);
		RightWing.setTextureOffset(44, 37).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 5.0F, 9.0F, 0.0F, false);

		LeftLegTop = new ModelRenderer(this);
		LeftLegTop.setRotationPoint(4.0F, 2.0F, 0.0F);
		Body.addChild(LeftLegTop);
		setRotationAngle(LeftLegTop, 0.3927F, 0.0F, 0.0F);
		LeftLegTop.setTextureOffset(0, 21).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		LeftLegBottom = new ModelRenderer(this);
		LeftLegBottom.setRotationPoint(0.0F, 7.0F, 0.0F);
		LeftLegTop.addChild(LeftLegBottom);
		setRotationAngle(LeftLegBottom, -0.5236F, 0.0F, 0.0F);
		LeftLegBottom.setTextureOffset(9, 21).addBox(-1.0F, -0.6F, -1.15F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		LeftFoot = new ModelRenderer(this);
		LeftFoot.setRotationPoint(0.0F, 7.0F, 0.0F);
		LeftLegBottom.addChild(LeftFoot);
		setRotationAngle(LeftFoot, 0.1745F, 0.0F, 0.0F);
		LeftFoot.setTextureOffset(18, 27).addBox(-1.0F, -0.5F, -2.1F, 2.0F, 1.0F, 3.0F, 0.0F, false);

		RightLegTop = new ModelRenderer(this);
		RightLegTop.setRotationPoint(-4.0F, 2.0F, 0.0F);
		Body.addChild(RightLegTop);
		setRotationAngle(RightLegTop, 0.3927F, 0.0F, 0.0F);
		RightLegTop.setTextureOffset(0, 32).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		RightLegBottom = new ModelRenderer(this);
		RightLegBottom.setRotationPoint(0.0F, 7.0F, 0.0F);
		RightLegTop.addChild(RightLegBottom);
		setRotationAngle(RightLegBottom, -0.5236F, 0.0F, 0.0F);
		RightLegBottom.setTextureOffset(9, 32).addBox(-1.0F, -0.6F, -1.15F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		RightFoot = new ModelRenderer(this);
		RightFoot.setRotationPoint(0.0F, 7.0F, 0.0F);
		RightLegBottom.addChild(RightFoot);
		setRotationAngle(RightFoot, 0.1745F, 0.0F, 0.0F);
		RightFoot.setTextureOffset(18, 38).addBox(-1.0F, -0.5F, -2.1F, 2.0F, 1.0F, 3.0F, 0.0F, false);

		Back = new ModelRenderer(this);
		Back.setRotationPoint(0.0F, -4.0F, 7.5F);
		Body.addChild(Back);
		Back.setTextureOffset(46, 10).addBox(-4.0F, -1.0F, -0.5F, 8.0F, 8.0F, 1.0F, 0.0F, false);

		add1 = new ModelRenderer(this);
		add1.setRotationPoint(0.0F, 0.0F, 0.0F);
		Back.addChild(add1);
		setRotationAngle(add1, -0.6545F, 0.0F, 0.0F);
		add1.setTextureOffset(0, 0).addBox(-1.5F, -5.0F, -0.5F, 3.0F, 5.0F, 1.0F, 0.0F, false);

		add1Left = new ModelRenderer(this);
		add1Left.setRotationPoint(1.0F, 0.0F, 2.0F);
		add1.addChild(add1Left);
		setRotationAngle(add1Left, 0.0F, 0.0F, 0.6109F);
		add1Left.setTextureOffset(9, 0).addBox(-1.0F, -5.0F, -2.5F, 2.0F, 5.0F, 1.0F, 0.0F, false);

		add1Right = new ModelRenderer(this);
		add1Right.setRotationPoint(-1.0F, 0.0F, 2.0F);
		add1.addChild(add1Right);
		setRotationAngle(add1Right, 0.0F, 0.0F, -0.6109F);
		add1Right.setTextureOffset(16, 0).addBox(-1.0F, -5.0F, -2.5F, 2.0F, 5.0F, 1.0F, 0.0F, false);

		add2 = new ModelRenderer(this);
		add2.setRotationPoint(0.0F, 0.5F, 0.0F);
		Back.addChild(add2);
		setRotationAngle(add2, -1.1781F, 0.0F, 0.0F);
		add2.setTextureOffset(0, 7).addBox(-1.5F, -5.0F, -0.5F, 3.0F, 5.0F, 1.0F, 0.0F, false);

		add2Left = new ModelRenderer(this);
		add2Left.setRotationPoint(1.0F, 0.0F, 2.0F);
		add2.addChild(add2Left);
		setRotationAngle(add2Left, 0.0F, 0.0F, 0.6545F);
		add2Left.setTextureOffset(9, 7).addBox(-1.0F, -5.0F, -2.5F, 2.0F, 5.0F, 1.0F, 0.0F, false);

		add2Right = new ModelRenderer(this);
		add2Right.setRotationPoint(-1.0F, 0.0F, 2.0F);
		add2.addChild(add2Right);
		setRotationAngle(add2Right, 0.0F, 0.0F, -0.7418F);
		add2Right.setTextureOffset(9, 14).addBox(-1.0F, -5.0F, -2.5F, 2.0F, 5.0F, 1.0F, 0.0F, false);

		add3 = new ModelRenderer(this);
		add3.setRotationPoint(0.0F, 1.0F, 0.0F);
		Back.addChild(add3);
		setRotationAngle(add3, -1.6581F, 0.0F, 0.0F);
		add3.setTextureOffset(0, 14).addBox(-1.5F, -5.0F, -0.5F, 3.0F, 5.0F, 1.0F, 0.0F, false);

		add3Left = new ModelRenderer(this);
		add3Left.setRotationPoint(1.0F, 0.0F, 2.0F);
		add3.addChild(add3Left);
		setRotationAngle(add3Left, 0.0F, 0.0F, 0.6981F);
		add3Left.setTextureOffset(16, 7).addBox(-1.0F, -5.0F, -2.5F, 2.0F, 5.0F, 1.0F, 0.0F, false);

		add3Right = new ModelRenderer(this);
		add3Right.setRotationPoint(-1.0F, 0.0F, 2.0F);
		add3.addChild(add3Right);
		setRotationAngle(add3Right, 0.0F, 0.0F, -0.6981F);
		add3Right.setTextureOffset(16, 14).addBox(-1.0F, -5.0F, -2.5F, 2.0F, 5.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.islayingEgg = entity.isLayingEgg();
		this.isbuildingNest = entity.isBuildingNest();

		this.Head.rotateAngleX = headPitch * 0.0174532925F;
		this.Head.rotateAngleY = (netHeadYaw / 3.75F) * 0.0174532925F;

		this.NeckTop.rotateAngleX = (float) (this.Head.rotateAngleX / 1.75F);
		this.Neck.rotateAngleY = (float) (this.Head.rotateAngleY / 1.75F);

		if (this.islayingEgg) {

			this.LeftLegTop.rotateAngleX = 1.17809724375F;
			this.LeftLegBottom.rotateAngleX = -2.5743606466F;
			this.LeftFoot.rotateAngleX = 0.6108652381F;

			this.RightLegTop.rotateAngleX = 1.17809724375F;
			this.RightLegBottom.rotateAngleX = -2.5743606466F;
			this.RightFoot.rotateAngleX = 0.6108652381F;

		} else if (this.isbuildingNest) {

			this.LeftLegTop.rotateAngleX = 0.3926990812F;
			this.LeftLegBottom.rotateAngleX = -0.523598775F;
			this.LeftFoot.rotateAngleX = 0.174532925F;

			this.RightLegTop.rotateAngleX = 0.3926990812F + (MathHelper.cos(ageInTicks * 0.45F));
			this.RightLegBottom.rotateAngleX = -0.523598775F;
			this.RightFoot.rotateAngleX = 0.174532925F;

		} else {

			this.LeftLegTop.rotateAngleX = 0.3926990812F
					+ (MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount);
			this.LeftLegBottom.rotateAngleX = -0.523598775F;
			this.LeftFoot.rotateAngleX = 0.174532925F;

			this.RightLegTop.rotateAngleX = 0.3926990812F + (MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmount);
			this.RightLegBottom.rotateAngleX = -0.523598775F;
			this.RightFoot.rotateAngleX = 0.174532925F;

		}
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		if (this.isChild) {
			matrixStack.scale(0.6F, 0.6F, 0.6F);
			matrixStack.translate(0, 1, 0);
		} else if (this.islayingEgg) {
			matrixStack.translate(0, 0.65, 0);
		}
		Body.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

}
