package com.tristankechlo.livingthings.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.tristankechlo.livingthings.client.model.AdvancedEntityModel;
import com.tristankechlo.livingthings.entities.FlamingoEntity;

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
public class FlamingoModel<T extends FlamingoEntity> extends AdvancedEntityModel<T> {

	private final ModelPart Body;
	private final ModelPart NeckBottom;
	private final ModelPart Neck2;
	private final ModelPart Neck3;
	private final ModelPart Neck4;
	private final ModelPart Neck5;
	private final ModelPart NeckTop;
	private final ModelPart Head;
	private final ModelPart Legs;
	private final ModelPart LeftLegTop;
	private final ModelPart LeftLegBottom;
	private final ModelPart LeftFoot;
	private final ModelPart RightLegTop;
	private final ModelPart RightLegBottom;
	private final ModelPart RightFoot;

	public FlamingoModel(ModelPart root) {
		this.Body = root.getChild("Body");
		this.NeckBottom = Body.getChild("NeckBottom");
		this.Neck2 = NeckBottom.getChild("Neck2");
		this.Neck3 = Neck2.getChild("Neck3");
		this.Neck4 = Neck3.getChild("Neck4");
		this.Neck5 = Neck4.getChild("Neck5");
		this.NeckTop = Neck5.getChild("NeckTop");
		this.Head = NeckTop.getChild("Head");
		this.Legs = Body.getChild("Legs");
		this.LeftLegTop = Legs.getChild("LeftLegTop");
		this.LeftLegBottom = LeftLegTop.getChild("LeftLegBottom");
		this.LeftFoot = LeftLegBottom.getChild("LeftFoot");
		this.RightLegTop = Legs.getChild("RightLegTop");
		this.RightLegBottom = RightLegTop.getChild("RightLegBottom");
		this.RightFoot = RightLegBottom.getChild("RightFoot");
	}

	@SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(36, 49).addBox(
				-3.0F, -15.9F, -4.0F, 6.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.9F, 0.0F));

		PartDefinition Back1 = Body.addOrReplaceChild("Back1",
				CubeListBuilder.create().texOffs(52, 25).addBox(-2.5F, -0.4808F, -0.8778F, 5.0F, 4.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -12.55F, 7.475F, -0.1833F, 0.0F, 0.0F));

		PartDefinition Back2 = Body.addOrReplaceChild("Back2", CubeListBuilder.create().texOffs(48, 31).addBox(-2.5F,
				1.925F, -0.375F, 5.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -15.05F, 3.85F));

		PartDefinition Back3 = Body.addOrReplaceChild("Back3",
				CubeListBuilder.create().texOffs(44, 39).addBox(-2.5F, -0.3463F, -0.5502F, 5.0F, 4.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -15.3F, 3.8F, -0.6109F, 0.0F, 0.0F));

		PartDefinition Legs = Body.addOrReplaceChild("Legs", CubeListBuilder.create(),
				PartPose.offset(0.0F, -9.9F, 0.0F));

		PartDefinition LeftLegTop = Legs
				.addOrReplaceChild("LeftLegTop",
						CubeListBuilder.create().texOffs(0, 56).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 7.0F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(2.0F, 1.0F, 0.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition LeftLegBottom = LeftLegTop
				.addOrReplaceChild("LeftLegBottom",
						CubeListBuilder.create().texOffs(5, 57).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 6.0F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.5F, 6.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition LeftFoot = LeftLegBottom
				.addOrReplaceChild("LeftFoot",
						CubeListBuilder.create().texOffs(10, 61).addBox(-1.5F, -1.0F, -2.0F, 1.0F, 1.0F, 2.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.5F, 6.0F, 0.0F, 0.0436F, 0.0F, 0.0F));

		PartDefinition RightLegTop = Legs
				.addOrReplaceChild("RightLegTop",
						CubeListBuilder.create().texOffs(0, 46).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 7.0F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(-2.0F, 1.0F, 0.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition RightLegBottom = RightLegTop
				.addOrReplaceChild("RightLegBottom",
						CubeListBuilder.create().texOffs(5, 47).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 6.0F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.5F, 6.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition RightFoot = RightLegBottom
				.addOrReplaceChild("RightFoot",
						CubeListBuilder.create().texOffs(10, 51).addBox(-1.5F, -1.0F, -2.0F, 1.0F, 1.0F, 2.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.5F, 6.0F, 0.0F, 0.0436F, 0.0F, 0.0F));

		PartDefinition NeckBottom = Body.addOrReplaceChild("NeckBottom",
				CubeListBuilder.create().texOffs(0, 35).addBox(-2.5F, -3.0F, -1.0F, 5.0F, 5.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -11.9F, -4.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition Neck2 = NeckBottom.addOrReplaceChild("Neck2",
				CubeListBuilder.create().texOffs(0, 27).addBox(-2.0F, -2.5F, -1.0F, 4.0F, 4.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition Neck3 = Neck2.addOrReplaceChild("Neck3",
				CubeListBuilder.create().texOffs(0, 21).addBox(-1.5F, -2.0F, -1.0F, 3.0F, 3.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition Neck4 = Neck3.addOrReplaceChild("Neck4",
				CubeListBuilder.create().texOffs(0, 15).addBox(-1.0F, -1.5F, -2.0F, 2.0F, 2.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -0.1109F, -1.2724F, -0.6545F, 0.0F, 0.0F));

		PartDefinition Neck5 = Neck4.addOrReplaceChild("Neck5",
				CubeListBuilder.create().texOffs(0, 10).addBox(-1.0F, -0.825F, -1.0F, 2.0F, 2.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -0.5921F, -2.4868F, 1.3963F, 0.0F, 0.0F));

		PartDefinition NeckTop = Neck5.addOrReplaceChild("NeckTop",
				CubeListBuilder.create().texOffs(0, 3).addBox(-1.0F, -2.5F, -1.0F, 2.0F, 4.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -1.95F, -0.05F, 0.0436F, 0.0F, 0.0F));

		PartDefinition Head = NeckTop.addOrReplaceChild("Head",
				CubeListBuilder.create().texOffs(21, 28).addBox(-1.5F, -3.0F, -4.0F, 3.0F, 3.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -2.126F, 0.1477F, 0.2618F, 0.0F, 0.0F));

		PartDefinition Beak = Head.addOrReplaceChild("Beak",
				CubeListBuilder.create().texOffs(23, 19).addBox(-1.0F, -0.5152F, -3.1737F, 2.0F, 2.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -1.5619F, -2.4006F, 0.0873F, 0.0F, 0.0F));

		PartDefinition Beak2 = Beak.addOrReplaceChild("Beak2",
				CubeListBuilder.create().texOffs(35, 21).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 2.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.1884F, -3.2262F, -0.8727F, 0.0F, 0.0F));

		PartDefinition Beak3 = Beak.addOrReplaceChild("Beak3",
				CubeListBuilder.create().texOffs(34, 25).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 1.1255F, -3.6706F, 0.2662F, 0.0F, 0.0F));

		PartDefinition Wings = Body.addOrReplaceChild("Wings", CubeListBuilder.create(),
				PartPose.offset(0.0F, 3.1F, 0.0F));

		PartDefinition LeftWing = Wings.addOrReplaceChild("LeftWing",
				CubeListBuilder.create().texOffs(48, 0).addBox(0.0F, -2.5F, 0.0F, 1.0F, 5.0F, 7.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.0F, -15.5F, -3.0F, -0.1745F, 0.0873F, 0.0F));

		PartDefinition RightWing = Wings.addOrReplaceChild("RightWing",
				CubeListBuilder.create().texOffs(31, 0).addBox(-1.0F, -2.5F, 0.0F, 1.0F, 5.0F, 7.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0F, -15.5F, -3.0F, -0.1745F, -0.0873F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(FlamingoEntity flamingo, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {

		this.Head.xRot = 0.2617993875F + (headPitch * 0.0174532925F);
		this.Head.yRot = (netHeadYaw / 3.75F) * 0.0174532925F;

		if (flamingo.isLeftLegUp()) {
			// values are defined by: ((Math.PI / 180) * AngleIn�)
			this.LeftLegTop.xRot = 1.3089969389F;
			this.LeftLegBottom.xRot = -2.7052603405F;
			this.LeftFoot.xRot = 0.174532925F;

			this.RightLegTop.xRot = 0.1308996938F;
			this.RightLegBottom.xRot = -0.174532925F;
			this.RightFoot.xRot = 0.0436332312F;

		} else if (flamingo.isRightLegUp()) {

			this.LeftLegTop.xRot = 0.1308996938F;
			this.LeftLegBottom.xRot = -0.174532925F;
			this.LeftFoot.xRot = 0.0436332312F;

			this.RightLegTop.xRot = 1.3089969389F;
			this.RightLegBottom.xRot = -2.7052603405F;
			this.RightFoot.xRot = 0.174532925F;

		} else {
			this.LeftLegTop.xRot = 0.1308996937F + (Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount);
			this.LeftLegBottom.xRot = -0.174532925F;
			this.LeftFoot.xRot = 0.0436332312F;

			this.RightLegTop.xRot = 0.1308996937F + (Mth.cos(limbSwing * 0.6662F) * limbSwingAmount);
			this.RightLegBottom.xRot = -0.174532925F;
			this.RightFoot.xRot = 0.0436332312F;
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

}
