package com.tristankechlo.livingthings.client.model.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.tristankechlo.livingthings.client.model.AdvancedEntityModel;
import com.tristankechlo.livingthings.entities.MonkeyEntity;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonkeyModel<T extends MonkeyEntity> extends AdvancedEntityModel<T> {

	private final ModelRenderer Body;
	private final ModelRenderer BodyFront;
	private final ModelRenderer Head;
	private final ModelRenderer MouthTop;
	private final ModelRenderer MouthBottom;
	private final ModelRenderer EarRight;
	private final ModelRenderer EarLeft;
	private final ModelRenderer FrontRightLegTop;
	private final ModelRenderer FrontRightLegBottom;
	private final ModelRenderer FrontLeftLegTop;
	private final ModelRenderer FrontLeftLegBottom;
	private final ModelRenderer BackRightLegTop;
	private final ModelRenderer BackRightLegBottom;
	private final ModelRenderer BackLeftLegTop;
	private final ModelRenderer BackLeftLegBottom;
	private final ModelRenderer Tail;
	private final ModelRenderer Tail2;
	private final ModelRenderer Tail3;

	public MonkeyModel() {
		texWidth = 32;
		texHeight = 32;

		Body = new ModelRenderer(this);
		Body.setPos(0.0F, 16.8264F, 3.7609F);
		setRotationAngle(Body, -0.0873F, 0.0F, 0.0F);
		Body.texOffs(0, 14).addBox(-3.0F, -2.9948F, -3.2964F, 6.0F, 4.0F, 5.0F, 0.0F, false);

		BodyFront = new ModelRenderer(this);
		BodyFront.setPos(0.0F, -1.174F, -3.1568F);
		Body.addChild(BodyFront);
		setRotationAngle(BodyFront, 0.0873F, 0.0F, 0.0F);
		BodyFront.texOffs(0, 23).addBox(-3.0F, -1.8282F, -5.0129F, 6.0F, 4.0F, 5.0F, -0.008F, false);

		Head = new ModelRenderer(this);
		Head.setPos(0.0F, -0.2655F, -4.271F);
		BodyFront.addChild(Head);
		Head.texOffs(0, 5).addBox(-2.5F, -3.9712F, -4.0871F, 5.0F, 5.0F, 4.0F, 0.0F, false);

		MouthTop = new ModelRenderer(this);
		MouthTop.setPos(0.0F, -1.022F, -3.3425F);
		Head.addChild(MouthTop);
		setRotationAngle(MouthTop, 0.0873F, 0.0F, 0.0F);
		MouthTop.texOffs(0, 0).addBox(-1.5F, -0.975F, -2.0F, 3.0F, 2.0F, 2.0F, 0.0F, false);

		MouthBottom = new ModelRenderer(this);
		MouthBottom.setPos(0.0F, 0.2191F, -3.1611F);
		Head.addChild(MouthBottom);
		setRotationAngle(MouthBottom, 0.3054F, 0.0F, 0.0F);
		MouthBottom.texOffs(19, 8).addBox(-1.5F, -0.475F, -2.0F, 3.0F, 1.0F, 2.0F, 0.0F, false);

		EarRight = new ModelRenderer(this);
		EarRight.setPos(-2.5F, -2.1589F, -2.0536F);
		Head.addChild(EarRight);
		EarRight.texOffs(28, 21).addBox(-0.5F, -0.95F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		EarLeft = new ModelRenderer(this);
		EarLeft.setPos(2.5F, -2.1589F, -2.0536F);
		Head.addChild(EarLeft);
		EarLeft.texOffs(28, 21).addBox(-0.5F, -0.95F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, true);

		FrontRightLegTop = new ModelRenderer(this);
		FrontRightLegTop.setPos(-2.5F, 0.8968F, -3.0129F);
		BodyFront.addChild(FrontRightLegTop);
		setRotationAngle(FrontRightLegTop, 0.1745F, 0.0F, 0.0F);
		FrontRightLegTop.texOffs(15, 0).addBox(-1.0F, -0.9462F, -1.1566F, 2.0F, 4.0F, 2.0F, 0.0F, true);

		FrontRightLegBottom = new ModelRenderer(this);
		FrontRightLegBottom.setPos(0.0F, 2.8619F, -0.1854F);
		FrontRightLegTop.addChild(FrontRightLegBottom);
		setRotationAngle(FrontRightLegBottom, -0.1745F, 0.0F, 0.0F);
		FrontRightLegBottom.texOffs(24, 0).addBox(-1.0F, -0.175F, -1.0F, 2.0F, 5.0F, 2.0F, -0.008F, false);
		FrontRightLegBottom.texOffs(24, 30).addBox(-1.0F, 3.825F, -2.0F, 2.0F, 1.0F, 1.0F, -0.008F, false);

		FrontLeftLegTop = new ModelRenderer(this);
		FrontLeftLegTop.setPos(2.5F, 0.8968F, -3.0129F);
		BodyFront.addChild(FrontLeftLegTop);
		setRotationAngle(FrontLeftLegTop, 0.1745F, 0.0F, 0.0F);
		FrontLeftLegTop.texOffs(15, 0).addBox(-1.0F, -0.9462F, -1.1566F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		FrontLeftLegBottom = new ModelRenderer(this);
		FrontLeftLegBottom.setPos(0.0F, 2.8865F, -0.1898F);
		FrontLeftLegTop.addChild(FrontLeftLegBottom);
		setRotationAngle(FrontLeftLegBottom, -0.1745F, 0.0F, 0.0F);
		FrontLeftLegBottom.texOffs(24, 0).addBox(-1.0F, -0.2F, -1.0F, 2.0F, 5.0F, 2.0F, -0.008F, true);
		FrontLeftLegBottom.texOffs(24, 30).addBox(-1.0F, 3.8F, -2.0F, 2.0F, 1.0F, 1.0F, -0.008F, true);

		BackRightLegTop = new ModelRenderer(this);
		BackRightLegTop.setPos(-2.5F, -0.075F, -0.0033F);
		Body.addChild(BackRightLegTop);
		setRotationAngle(BackRightLegTop, -0.2182F, 0.0F, 0.0F);
		BackRightLegTop.texOffs(15, 0).addBox(-1.0F, -1.2262F, -0.9925F, 2.0F, 4.0F, 2.0F, 0.0F, true);

		BackRightLegBottom = new ModelRenderer(this);
		BackRightLegBottom.setPos(0.0F, 2.6048F, 0.0064F);
		BackRightLegTop.addChild(BackRightLegBottom);
		setRotationAngle(BackRightLegBottom, 0.3054F, 0.0F, 0.0F);
		BackRightLegBottom.texOffs(24, 0).addBox(-1.0F, -0.25F, -0.975F, 2.0F, 5.0F, 2.0F, -0.008F, false);
		BackRightLegBottom.texOffs(24, 30).addBox(-1.0F, 3.75F, -1.975F, 2.0F, 1.0F, 1.0F, -0.008F, false);

		BackLeftLegTop = new ModelRenderer(this);
		BackLeftLegTop.setPos(2.5F, 0.0F, 0.0F);
		Body.addChild(BackLeftLegTop);
		setRotationAngle(BackLeftLegTop, -0.2182F, 0.0F, 0.0F);
		BackLeftLegTop.texOffs(15, 0).addBox(-1.0F, -1.3012F, -0.9925F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		BackLeftLegBottom = new ModelRenderer(this);
		BackLeftLegBottom.setPos(0.0F, 2.6273F, -0.0651F);
		BackLeftLegTop.addChild(BackLeftLegBottom);
		setRotationAngle(BackLeftLegBottom, 0.3054F, 0.0F, 0.0F);
		BackLeftLegBottom.texOffs(24, 0).addBox(-1.0F, -0.325F, -0.875F, 2.0F, 5.0F, 2.0F, -0.008F, true);
		BackLeftLegBottom.texOffs(24, 30).addBox(-1.0F, 3.675F, -1.875F, 2.0F, 1.0F, 1.0F, -0.008F, true);

		Tail = new ModelRenderer(this);
		Tail.setPos(0.0F, -1.7519F, 0.3378F);
		Body.addChild(Tail);
		setRotationAngle(Tail, 0.6545F, 0.0F, 0.0F);
		Tail.texOffs(18, 20).addBox(-1.0F, -0.95F, 0.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		Tail2 = new ModelRenderer(this);
		Tail2.setPos(0.0F, -0.0568F, 4.3282F);
		Tail.addChild(Tail2);
		setRotationAngle(Tail2, 0.576F, 0.0F, 0.0F);
		Tail2.texOffs(22, 14).addBox(-0.5F, -0.575F, 0.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);

		Tail3 = new ModelRenderer(this);
		Tail3.setPos(0.0F, -0.246F, 3.5488F);
		Tail2.addChild(Tail3);
		setRotationAngle(Tail3, -0.9599F, 0.0F, 0.0F);
		Tail3.texOffs(18, 13).addBox(-0.5F, -0.575F, 0.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(MonkeyEntity monkey, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {

		if (monkey.isPartying()) {

			this.setPartyAngles();
			this.defaultWalking1(BackLeftLegTop, 67.5F, limbSwing, limbSwingAmount);
			this.defaultWalking2(BackRightLegTop, 67.5F, limbSwing, limbSwingAmount);
			FrontLeftLegTop.zRot = -0.6108F + MathHelper.cos(ageInTicks * 0.7F) * 0.2617F;
			FrontRightLegTop.zRot = 0.6108F + MathHelper.cos(ageInTicks * 0.7F + (float) Math.PI) * 0.2617F;
			Head.xRot = 1.3963F + MathHelper.cos(ageInTicks * 0.7F + (float) Math.PI) * 0.1745F;

		} else {

			this.setDefaultAngles();
			this.defaultWalking1(FrontRightLegTop, 10F, limbSwing, limbSwingAmount);
			this.defaultWalking2(FrontLeftLegTop, 10F, limbSwing, limbSwingAmount);
			this.defaultWalking1(BackLeftLegTop, -12.5F, limbSwing, limbSwingAmount);
			this.defaultWalking2(BackRightLegTop, -12.5F, limbSwing, limbSwingAmount);
			this.defaultHeadMovement(Head, 2.5F, 0F, headPitch, netHeadYaw);
			Tail.yRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.3F * limbSwingAmount;
			Tail2.yRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.7F * limbSwingAmount;
		}
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn,
			int packedOverlayIn, float red, float green, float blue, float alpha) {

		if (this.young) {
			matrixStackIn.scale(0.5F, 0.5F, 0.5F);
			matrixStackIn.translate(0, 1.5D, 0);
		}
		Body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
	}

	private void setDefaultAngles() {
		Body.setPos(0.0F, 16.8264F, 3.7609F);
		setRotationAngle(Body, -0.0873F, 0.0F, 0.0F);
		Head.setPos(0.0F, -0.2655F, -4.271F);
		setRotationAngle(Head, 0.0F, 0.0F, 0.0F);

		setRotationAngle(FrontRightLegTop, 0.1745F, 0.0F, 0.0F);
		FrontRightLegBottom.setPos(0.0F, 2.8619F, -0.1854F);
		setRotationAngle(FrontRightLegBottom, -0.1745F, 0.0F, 0.0F);
		setRotationAngle(FrontLeftLegTop, 0.1745F, 0.0F, 0.0F);
		FrontLeftLegBottom.setPos(0.0F, 2.8865F, -0.1898F);
		setRotationAngle(FrontLeftLegBottom, -0.1745F, 0.0F, 0.0F);

		setRotationAngle(BackRightLegTop, -0.2182F, 0.0F, 0.0F);
		setRotationAngle(BackLeftLegTop, -0.2182F, 0.0F, 0.0F);

		setRotationAngle(Tail, 0.6545F, 0.0F, 0.0F);
		setRotationAngle(Tail2, 0.576F, 0.0F, 0.0F);
		Tail3.setPos(0.0F, -0.246F, 3.5488F);
		setRotationAngle(Tail3, -0.9599F, 0.0F, 0.0F);
	}

	private void setPartyAngles() {
		Body.setPos(0.0F, 16.8264F, 0.7609F);
		setRotationAngle(Body, -1.4835F, 0.0F, 0.0F);
		Head.setPos(0.0F, -1.9627F, -5.5457F);
		setRotationAngle(Head, 1.3963F, 0.0F, 0.0F);

		setRotationAngle(FrontRightLegTop, 0.1745F, 0.0F, 0.4363F);
		FrontRightLegBottom.setPos(-0.425F, 2.2369F, -0.1854F);
		setRotationAngle(FrontRightLegBottom, 0.0F, 0.0F, -1.1345F);
		setRotationAngle(FrontLeftLegTop, 0.1745F, 0.0F, -0.4363F);
		FrontLeftLegBottom.setPos(0.4F, 2.2365F, -0.1898F);
		setRotationAngle(FrontLeftLegBottom, 0.0F, 0.0F, 1.1345F);

		setRotationAngle(BackRightLegTop, 1.1781F, 0.0F, 0.0F);
		setRotationAngle(BackLeftLegTop, 1.1781F, 0.0F, 0.0F);

		setRotationAngle(Tail, 0.3491F, 0.0F, 0.0F);
		setRotationAngle(Tail2, 0.4451F, 0.0F, 0.0F);
		Tail3.setPos(0.0F, 0.0751F, 3.7487F);
		setRotationAngle(Tail3, 0.6545F, 0.0F, 0.0F);
	}

}
