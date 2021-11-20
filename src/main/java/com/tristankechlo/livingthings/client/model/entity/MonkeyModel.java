package com.tristankechlo.livingthings.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.tristankechlo.livingthings.client.model.AdvancedEntityModel;
import com.tristankechlo.livingthings.entities.MonkeyEntity;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonkeyModel<T extends MonkeyEntity> extends AdvancedEntityModel<T> {

	private final ModelPart Body;
	private final ModelPart BodyFront;
	private final ModelPart Head;
	private final ModelPart Tail;
	private final ModelPart Tail2;
	private final ModelPart Tail3;
	private final ModelPart FrontRightLegTop;
	private final ModelPart FrontRightLegBottom;
	private final ModelPart FrontLeftLegTop;
	private final ModelPart FrontLeftLegBottom;
	private final ModelPart BackRightLegTop;
	private final ModelPart BackLeftLegTop;
	//private final ModelPart BackRightLegBottom;
	//private final ModelPart BackLeftLegBottom;

	public MonkeyModel(ModelPart root) {
		this.Body = root.getChild("Body");
		this.BodyFront = Body.getChild("BodyFront");
		this.Head = BodyFront.getChild("Head");
		this.Tail = Body.getChild("Tail");
		this.Tail2 = Tail.getChild("Tail2");
		this.Tail3 = Tail2.getChild("Tail3");
		this.FrontRightLegTop = BodyFront.getChild("FrontRightLegTop");
		this.FrontRightLegBottom = FrontRightLegTop.getChild("FrontRightLegBottom");
		this.FrontLeftLegTop = BodyFront.getChild("FrontLeftLegTop");
		this.FrontLeftLegBottom = FrontLeftLegTop.getChild("FrontLeftLegBottom");
		this.BackRightLegTop = Body.getChild("BackRightLegTop");
		this.BackLeftLegTop = Body.getChild("BackLeftLegTop");
//		this.BackRightLegBottom = BackRightLegTop.getChild("BackRightLegBottom");
//		this.BackLeftLegBottom = BackLeftLegTop.getChild("BackLeftLegBottom");
	}

	@SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Body = partdefinition.addOrReplaceChild("Body",
				CubeListBuilder.create().texOffs(0, 14).addBox(-3.0F, -2.9948F, -3.2964F, 6.0F, 4.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 16.8264F, 3.7609F, -0.0873F, 0.0F, 0.0F));

		PartDefinition BodyFront = Body.addOrReplaceChild("BodyFront",
				CubeListBuilder.create().texOffs(0, 23).addBox(-3.0F, -1.8282F, -5.0129F, 6.0F, 4.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -1.174F, -3.1568F, 0.0873F, 0.0F, 0.0F));

		PartDefinition Head = BodyFront.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 5).addBox(-2.5F,
				-3.9712F, -4.0871F, 5.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, -0.2655F, -4.271F));

		PartDefinition MouthTop = Head.addOrReplaceChild("MouthTop",
				CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -0.975F, -2.0F, 3.0F, 2.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -1.022F, -3.3425F, 0.0873F, 0.0F, 0.0F));

		PartDefinition MouthBottom = Head.addOrReplaceChild("MouthBottom",
				CubeListBuilder.create().texOffs(19, 8).addBox(-1.5F, -0.475F, -2.0F, 3.0F, 1.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.2191F, -3.1611F, 0.3054F, 0.0F, 0.0F));

		PartDefinition EarRight = Head.addOrReplaceChild("EarRight", CubeListBuilder.create().texOffs(28, 21)
				.addBox(-0.5F, -0.95F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-2.5F, -2.1589F, -2.0536F));

		PartDefinition EarLeft = Head.addOrReplaceChild("EarLeft",
				CubeListBuilder.create().texOffs(28, 21).mirror()
						.addBox(-0.5F, -0.95F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offset(2.5F, -2.1589F, -2.0536F));

		PartDefinition FrontRightLegTop = BodyFront.addOrReplaceChild("FrontRightLegTop",
				CubeListBuilder.create().texOffs(15, 0).mirror()
						.addBox(-1.0F, -0.9462F, -1.1566F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(-2.5F, 0.8968F, -3.0129F, 0.1745F, 0.0F, 0.0F));

		PartDefinition FrontRightLegBottom = FrontRightLegTop.addOrReplaceChild("FrontRightLegBottom",
				CubeListBuilder.create().texOffs(24, 0)
						.addBox(-1.0F, -0.175F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(24, 30)
						.addBox(-1.0F, 3.825F, -2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 2.8619F, -0.1854F, -0.1745F, 0.0F, 0.0F));

		PartDefinition FrontLeftLegTop = BodyFront.addOrReplaceChild("FrontLeftLegTop",
				CubeListBuilder.create().texOffs(15, 0).addBox(-1.0F, -0.9462F, -1.1566F, 2.0F, 4.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.5F, 0.8968F, -3.0129F, 0.1745F, 0.0F, 0.0F));

		PartDefinition FrontLeftLegBottom = FrontLeftLegTop.addOrReplaceChild("FrontLeftLegBottom",
				CubeListBuilder.create().texOffs(24, 0).mirror()
						.addBox(-1.0F, -0.2F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
						.texOffs(24, 30).mirror()
						.addBox(-1.0F, 3.8F, -2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(0.0F, 2.8865F, -0.1898F, -0.1745F, 0.0F, 0.0F));

		PartDefinition BackRightLegTop = Body.addOrReplaceChild("BackRightLegTop",
				CubeListBuilder.create().texOffs(15, 0).mirror()
						.addBox(-1.0F, -1.2262F, -0.9925F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(-2.5F, -0.075F, -0.0033F, -0.2182F, 0.0F, 0.0F));

		PartDefinition BackRightLegBottom = BackRightLegTop.addOrReplaceChild("BackRightLegBottom",
				CubeListBuilder.create().texOffs(24, 0)
						.addBox(-1.0F, -0.25F, -0.975F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(24, 30)
						.addBox(-1.0F, 3.75F, -1.975F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 2.6048F, 0.0064F, 0.3054F, 0.0F, 0.0F));

		PartDefinition BackLeftLegTop = Body.addOrReplaceChild(
				"BackLeftLegTop", CubeListBuilder.create().texOffs(15, 0).addBox(-1.0F, -1.3012F, -0.9925F, 2.0F, 4.0F,
						2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.5F, 0.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition BackLeftLegBottom = BackLeftLegTop.addOrReplaceChild("BackLeftLegBottom",
				CubeListBuilder.create().texOffs(24, 0).mirror()
						.addBox(-1.0F, -0.325F, -0.875F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
						.texOffs(24, 30).mirror()
						.addBox(-1.0F, 3.675F, -1.875F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(0.0F, 2.6273F, -0.0651F, 0.3054F, 0.0F, 0.0F));

		PartDefinition Tail = Body.addOrReplaceChild("Tail",
				CubeListBuilder.create().texOffs(18, 20).addBox(-1.0F, -0.95F, 0.0F, 2.0F, 2.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -1.7519F, 0.3378F, 0.6545F, 0.0F, 0.0F));

		PartDefinition Tail2 = Tail.addOrReplaceChild("Tail2",
				CubeListBuilder.create().texOffs(22, 14).addBox(-0.5F, -0.575F, 0.0F, 1.0F, 1.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -0.0568F, 4.3282F, 0.576F, 0.0F, 0.0F));

		PartDefinition Tail3 = Tail2.addOrReplaceChild("Tail3",
				CubeListBuilder.create().texOffs(18, 13).addBox(-0.5F, -0.575F, 0.0F, 1.0F, 1.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -0.246F, 3.5488F, -0.9599F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(MonkeyEntity monkey, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {

		if (monkey.isPartying()) {

			this.setPartyAngles();
			this.defaultWalking1(BackLeftLegTop, 67.5F, limbSwing, limbSwingAmount);
			this.defaultWalking2(BackRightLegTop, 67.5F, limbSwing, limbSwingAmount);
			FrontLeftLegTop.zRot = -0.6108F + Mth.cos(ageInTicks * 0.7F) * 0.2617F;
			FrontRightLegTop.zRot = 0.6108F + Mth.cos(ageInTicks * 0.7F + (float) Math.PI) * 0.2617F;
			Head.xRot = 1.3963F + Mth.cos(ageInTicks * 0.7F + (float) Math.PI) * 0.1745F;

		} else {

			this.setDefaultAngles();
			this.defaultWalking1(FrontRightLegTop, 10F, limbSwing, limbSwingAmount);
			this.defaultWalking2(FrontLeftLegTop, 10F, limbSwing, limbSwingAmount);
			this.defaultWalking1(BackLeftLegTop, -12.5F, limbSwing, limbSwingAmount);
			this.defaultWalking2(BackRightLegTop, -12.5F, limbSwing, limbSwingAmount);
			this.defaultHeadMovement(Head, 2.5F, 0F, headPitch, netHeadYaw);
			Tail.yRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.3F * limbSwingAmount;
			Tail2.yRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.7F * limbSwingAmount;
		}
	}

	@Override
	public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha) {

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
