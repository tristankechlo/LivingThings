package com.tristankechlo.livingthings.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BabyEnderDragonModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart Body;

	public BabyEnderDragonModel(ModelPart root) {
		this.Body = root.getChild("Body");
	}

	@SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Body = partdefinition.addOrReplaceChild("Body",
				CubeListBuilder.create().texOffs(14, 17)
						.addBox(-3.0F, -11.625F, 0.0F, 6.0F, 6.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(0, 27)
						.addBox(-1.0F, -13.625F, 5.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(0, 27)
						.addBox(-1.0F, -13.625F, 0.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition Neck = Body.addOrReplaceChild("Neck",
				CubeListBuilder.create().texOffs(0, 16)
						.addBox(-1.0F, -1.5F, -7.0F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(0, 19)
						.addBox(-0.5F, -2.5F, -3.7F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 19)
						.addBox(-0.5F, -2.5F, -6.3F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, -8.645F, -4.75F));

		PartDefinition Head = Neck.addOrReplaceChild("Head",
				CubeListBuilder.create().texOffs(0, 0)
						.addBox(-3.0F, -3.48F, -4.0F, 6.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(16, 0)
						.addBox(1.0F, -5.48F, -3.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(16, 0)
						.addBox(-2.0F, -5.48F, -3.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 10)
						.addBox(-3.0F, -0.5F, -7.0F, 6.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(0, 1)
						.addBox(-2.0F, -1.25F, -6.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 1)
						.addBox(1.0F, -1.25F, -6.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, -6.75F));

		PartDefinition FrontBody = Body.addOrReplaceChild("FrontBody",
				CubeListBuilder.create().texOffs(0, 27)
						.addBox(-2.0F, -5.0F, -7.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(19, 3)
						.addBox(-4.0F, -3.0F, -8.0F, 6.0F, 6.0F, 7.0F, new CubeDeformation(-0.001F)),
				PartPose.offset(1.0F, -8.625F, 2.0F));

		PartDefinition FrontLeftLeg = Body.addOrReplaceChild("FrontLeftLeg", CubeListBuilder.create().texOffs(46, 0)
				.addBox(-1.75F, 0.0F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(2.5F, -6.625F, -3.0F));

		PartDefinition FrontLeftLegLower = FrontLeftLeg.addOrReplaceChild("FrontLeftLegLower",
				CubeListBuilder.create().texOffs(56, 0).addBox(-1.25F, 1.475F, -1.1782F, 2.0F, 3.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 2.325F, 1.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition FrontLeftFoot = FrontLeftLegLower.addOrReplaceChild("FrontLeftFoot",
				CubeListBuilder.create().texOffs(11, 16).addBox(0.25F, 3.3F, 3.775F, 3.0F, 1.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, 4.0F, -6.9282F, 0.5236F, 0.0F, 0.0F));

		PartDefinition BackLeftLeg = Body.addOrReplaceChild("BackLeftLeg", CubeListBuilder.create().texOffs(46, 0)
				.addBox(-1.75F, 0.0F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(2.5F, -6.625F, 6.5F));

		PartDefinition BackLeftLegLower = BackLeftLeg.addOrReplaceChild("BackLeftLegLower",
				CubeListBuilder.create().texOffs(56, 0).addBox(-1.25F, 1.475F, -1.1782F, 2.0F, 3.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 2.325F, 1.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition BackLeftFoot = BackLeftLegLower.addOrReplaceChild("BackLeftFoot",
				CubeListBuilder.create().texOffs(11, 16).addBox(0.25F, 3.3F, 3.775F, 3.0F, 1.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, 4.0F, -6.9282F, 0.5236F, 0.0F, 0.0F));

		PartDefinition FrontRightLeg = Body.addOrReplaceChild("FrontRightLeg",
				CubeListBuilder.create().texOffs(46, 0).mirror()
						.addBox(-1.25F, 0.0F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offset(-2.5F, -6.625F, -3.0F));

		PartDefinition FrontRightLegLower = FrontRightLeg.addOrReplaceChild("FrontRightLegLower",
				CubeListBuilder.create().texOffs(56, 0).mirror()
						.addBox(-0.75F, 1.475F, -1.1782F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(0.0F, 2.325F, 1.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition FrontRightFoot = FrontRightLegLower.addOrReplaceChild("FrontRightFoot",
				CubeListBuilder.create().texOffs(11, 16).mirror()
						.addBox(-3.25F, 3.3F, 3.775F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(2.0F, 4.0F, -6.9282F, 0.5236F, 0.0F, 0.0F));

		PartDefinition BackRightLeg = Body.addOrReplaceChild("BackRightLeg",
				CubeListBuilder.create().texOffs(46, 0).mirror()
						.addBox(-1.25F, 0.0F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offset(-2.5F, -6.625F, 6.5F));

		PartDefinition BackRightLegLower = BackRightLeg.addOrReplaceChild("BackRightLegLower",
				CubeListBuilder.create().texOffs(56, 0).mirror()
						.addBox(-0.75F, 1.475F, -1.1782F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(0.0F, 2.325F, 1.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition BackRightFoot = BackRightLegLower.addOrReplaceChild("BackRightFoot",
				CubeListBuilder.create().texOffs(11, 16).mirror()
						.addBox(-3.25F, 3.3F, 3.775F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(2.0F, 4.0F, -6.9282F, 0.5236F, 0.0F, 0.0F));

		PartDefinition Tail = Body.addOrReplaceChild("Tail",
				CubeListBuilder.create().texOffs(38, 0)
						.addBox(-2.0F, 0.0F, -0.75F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(22, 0)
						.addBox(-1.5F, 0.5F, 1.25F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, -10.15F, 8.275F, 1.3526F, 0.0F, 0.0F));

		PartDefinition Tail2 = Tail.addOrReplaceChild("Tail2",
				CubeListBuilder.create().texOffs(38, 0)
						.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(22, 0)
						.addBox(-0.5F, 0.5F, 1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 3.75F, 0.25F, -0.3491F, 0.0F, 0.0F));

		PartDefinition Tail3 = Tail2.addOrReplaceChild("Tail3",
				CubeListBuilder.create().texOffs(38, 0)
						.addBox(-1.0F, 0.0F, -1.0216F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(22, 0)
						.addBox(-0.5F, 0.5F, 0.9784F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 3.75F, 0.0216F, 0.1745F, 0.0F, 0.0F));

		PartDefinition Tail4 = Tail3.addOrReplaceChild("Tail4",
				CubeListBuilder.create().texOffs(38, 0)
						.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(22, 0)
						.addBox(-0.5F, 0.5F, 1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 3.75F, -0.0216F, 0.2182F, 0.0F, 0.0F));

		PartDefinition RightWing = Body.addOrReplaceChild("RightWing",
				CubeListBuilder.create().texOffs(48, 22).mirror()
						.addBox(-4.5F, -1.2402F, -1.4429F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
						.texOffs(47, 8).mirror()
						.addBox(-4.058F, -0.0896F, 0.9F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(-2.5F, -10.625F, -3.5F, 0.0F, 0.0F, 0.3491F));

		PartDefinition RightWing2 = RightWing.addOrReplaceChild("RightWing2",
				CubeListBuilder.create().texOffs(44, 28)
						.addBox(-8.7886F, -1.5405F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(41, 15)
						.addBox(-8.7816F, -0.7213F, 0.825F, 8.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0514F, 0.3469F, 0.075F, 0.0F, 0.0F, -0.3491F));

		PartDefinition LeftWing = Body.addOrReplaceChild("LeftWing",
				CubeListBuilder.create().texOffs(48, 22)
						.addBox(-0.5F, -1.2402F, -1.4429F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(47, 8)
						.addBox(-0.942F, -0.0896F, 0.9F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.5F, -10.625F, -3.5F, 0.0F, 0.0F, -0.3491F));

		PartDefinition LeftWing2 = LeftWing.addOrReplaceChild("LeftWing2",
				CubeListBuilder.create().texOffs(44, 28).mirror()
						.addBox(0.7886F, -1.5405F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
						.texOffs(41, 15).mirror()
						.addBox(0.7816F, -0.7213F, 0.825F, 8.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(3.0514F, 0.3469F, 0.075F, 0.0F, 0.0F, 0.3491F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		Body.render(poseStack, buffer, packedLight, packedOverlay);
	}
}
