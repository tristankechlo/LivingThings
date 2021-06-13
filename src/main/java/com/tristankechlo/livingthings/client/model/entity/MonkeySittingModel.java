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
public class MonkeySittingModel<T extends MonkeyEntity> extends AdvancedEntityModel<T> {

	private final ModelRenderer Body;
	private final ModelRenderer BackRightLegTop;
	private final ModelRenderer BackRightLegBottom;
	private final ModelRenderer BackLeftLegTop;
	private final ModelRenderer BackLeftLegBottom;
	private final ModelRenderer Tail;
	private final ModelRenderer Tail2;
	private final ModelRenderer Tail3;
	private final ModelRenderer BodyTop;
	private final ModelRenderer Head;
	private final ModelRenderer MouthTop;
	private final ModelRenderer MouthBottom;
	private final ModelRenderer EarRight;
	private final ModelRenderer EarLeft;
	private final ModelRenderer FrontRightLegTop;
	private final ModelRenderer FrontRightLegBottom;
	private final ModelRenderer FrontLeftLegTop;
	private final ModelRenderer FrontLeftLegBottom;

	public MonkeySittingModel() {
		texWidth = 32;
		texHeight = 32;

		Body = new ModelRenderer(this);
		Body.setPos(0.0F, 19.117F, 0.9453F);
		setRotationAngle(Body, -1.5272F, 0.0F, 0.0F);
		Body.texOffs(0, 14).addBox(-3.0F, -2.1468F, -0.0005F, 6.0F, 4.0F, 5.0F, 0.0F, false);

		BackRightLegTop = new ModelRenderer(this);
		BackRightLegTop.setPos(-2.5F, 1.0209F, 4.0362F);
		Body.addChild(BackRightLegTop);
		setRotationAngle(BackRightLegTop, -0.0436F, 0.0436F, 0.5672F);
		BackRightLegTop.texOffs(15, 0).addBox(-1.0F, -1.4F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		BackRightLegBottom = new ModelRenderer(this);
		BackRightLegBottom.setPos(-0.5388F, 2.1947F, -0.0614F);
		BackRightLegTop.addChild(BackRightLegBottom);
		setRotationAngle(BackRightLegBottom, 0.0F, 0.0F, -1.4835F);
		BackRightLegBottom.texOffs(24, 0).addBox(-1.0F, -0.45F, -0.925F, 2.0F, 5.0F, 2.0F, -0.008F, false);
		BackRightLegBottom.texOffs(24, 30).addBox(-1.0F, 3.55F, -1.925F, 2.0F, 1.0F, 1.0F, -0.008F, false);

		BackLeftLegTop = new ModelRenderer(this);
		BackLeftLegTop.setPos(2.95F, 0.9675F, 3.9196F);
		Body.addChild(BackLeftLegTop);
		setRotationAngle(BackLeftLegTop, -0.0697F, -0.008F, -0.6557F);
		BackLeftLegTop.texOffs(15, 0).addBox(-1.0F, -1.475F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		BackLeftLegBottom = new ModelRenderer(this);
		BackLeftLegBottom.setPos(0.2656F, 1.7932F, 0.0215F);
		BackLeftLegTop.addChild(BackLeftLegBottom);
		setRotationAngle(BackLeftLegBottom, 1.309F, -1.5708F, 0.0F);
		BackLeftLegBottom.texOffs(24, 0).addBox(-1.0F, -0.525F, -0.9F, 2.0F, 5.0F, 2.0F, -0.008F, false);
		BackLeftLegBottom.texOffs(24, 30).addBox(-1.0F, 3.475F, -1.9F, 2.0F, 1.0F, 1.0F, -0.008F, false);

		Tail = new ModelRenderer(this);
		Tail.setPos(0.0F, 0.0106F, 4.1315F);
		Body.addChild(Tail);
		setRotationAngle(Tail, 1.4835F, 0.0F, -0.1745F);
		Tail.texOffs(18, 20).addBox(-1.0F, -1.1F, 0.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		Tail2 = new ModelRenderer(this);
		Tail2.setPos(0.0102F, -0.1211F, 4.3435F);
		Tail.addChild(Tail2);
		setRotationAngle(Tail2, 0.0436F, -0.6981F, 0.0F);
		Tail2.texOffs(22, 14).addBox(-0.4898F, -0.4184F, 0.0203F, 1.0F, 1.0F, 4.0F, 0.0F, false);

		Tail3 = new ModelRenderer(this);
		Tail3.setPos(0.0268F, 0.0709F, 4.0296F);
		Tail2.addChild(Tail3);
		setRotationAngle(Tail3, 0.0F, -0.6109F, 0.0F);
		Tail3.texOffs(18, 13).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);

		BodyTop = new ModelRenderer(this);
		BodyTop.setPos(0.0F, -0.1619F, 0.3208F);
		Body.addChild(BodyTop);
		setRotationAngle(BodyTop, 0.0873F, 0.0F, 0.0F);
		BodyTop.texOffs(0, 23).addBox(-3.0F, -2.0076F, -5.0365F, 6.0F, 4.0F, 5.0F, -0.008F, false);

		Head = new ModelRenderer(this);
		Head.setPos(0.0F, 0.3827F, -4.8802F);
		BodyTop.addChild(Head);
		setRotationAngle(Head, 1.4835F, 0.0F, 0.0F);
		Head.texOffs(0, 5).addBox(-2.5F, -5.0F, -1.9965F, 5.0F, 5.0F, 4.0F, 0.0F, false);

		MouthTop = new ModelRenderer(this);
		MouthTop.setPos(0.0F, -1.9009F, -1.2519F);
		Head.addChild(MouthTop);
		setRotationAngle(MouthTop, 0.0873F, 0.0F, 0.0F);
		MouthTop.texOffs(0, 0).addBox(-1.5F, -1.125F, -2.0F, 3.0F, 2.0F, 2.0F, 0.0F, false);

		MouthBottom = new ModelRenderer(this);
		MouthBottom.setPos(0.0F, -0.6598F, -1.0705F);
		Head.addChild(MouthBottom);
		setRotationAngle(MouthBottom, 0.3927F, 0.0F, 0.0F);
		MouthBottom.texOffs(19, 8).addBox(-1.5F, -0.625F, -2.0F, 3.0F, 1.0F, 2.0F, 0.0F, false);

		EarRight = new ModelRenderer(this);
		EarRight.setPos(-2.5F, -3.0378F, 0.037F);
		Head.addChild(EarRight);
		EarRight.texOffs(28, 21).addBox(-0.5F, -1.1F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		EarLeft = new ModelRenderer(this);
		EarLeft.setPos(2.5F, -3.0378F, 0.037F);
		Head.addChild(EarLeft);
		EarLeft.texOffs(28, 21).addBox(-0.5F, -1.1F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		FrontRightLegTop = new ModelRenderer(this);
		FrontRightLegTop.setPos(-2.675F, 0.2566F, -2.8141F);
		BodyTop.addChild(FrontRightLegTop);
		setRotationAngle(FrontRightLegTop, 0.4038F, -0.8178F, 0.7357F);
		FrontRightLegTop.texOffs(15, 0).addBox(-1.3215F, -1.4728F, -1.059F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		FrontRightLegBottom = new ModelRenderer(this);
		FrontRightLegBottom.setPos(0.136F, 2.4769F, -0.0749F);
		FrontRightLegTop.addChild(FrontRightLegBottom);
		setRotationAngle(FrontRightLegBottom, 0.0F, 0.0F, -1.1781F);
		FrontRightLegBottom.texOffs(24, 0).addBox(-0.5958F, -1.3258F, -0.934F, 2.0F, 5.0F, 2.0F, -0.008F, false);
		FrontRightLegBottom.texOffs(24, 30).addBox(-0.5958F, 2.6742F, -1.934F, 2.0F, 1.0F, 1.0F, -0.008F, false);

		FrontLeftLegTop = new ModelRenderer(this);
		FrontLeftLegTop.setPos(2.55F, 0.3021F, -2.7148F);
		BodyTop.addChild(FrontLeftLegTop);
		setRotationAngle(FrontLeftLegTop, 0.4911F, -1.1232F, -1.3466F);
		FrontLeftLegTop.texOffs(15, 0).addBox(-1.0F, -0.875F, -1.2F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		FrontLeftLegBottom = new ModelRenderer(this);
		FrontLeftLegBottom.setPos(0.0641F, 2.2424F, -0.8784F);
		FrontLeftLegTop.addChild(FrontLeftLegBottom);
		setRotationAngle(FrontLeftLegBottom, 1.5708F, 0.2618F, 1.5708F);
		FrontLeftLegBottom.texOffs(24, 0).addBox(-1.0588F, -0.0608F, -1.0545F, 2.0F, 5.0F, 2.0F, -0.008F, false);
		FrontLeftLegBottom.texOffs(24, 30).addBox(-1.0588F, 3.9392F, -2.0545F, 2.0F, 1.0F, 1.0F, -0.008F, false);
	}

	@Override
	public void setupAnim(MonkeyEntity monkey, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {

		if (monkey.isPartying()) {
			Head.xRot = 1.4835F + MathHelper.cos(ageInTicks * 0.4F) * 0.3F;
			Head.zRot = 0.0F;
			BodyTop.yRot = MathHelper.cos(ageInTicks * 0.2F) * 0.175F;
		} else {
			Head.xRot = 1.4835F + deg2rad(headPitch);
			Head.zRot = deg2rad(netHeadYaw);
			BodyTop.yRot = 0.0F;
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

}
