package com.tristankechlo.livingthings.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.tristankechlo.livingthings.client.model.AdvancedEntityModel;
import com.tristankechlo.livingthings.entities.SnailEntity;

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
public class SnailModel<T extends SnailEntity> extends AdvancedEntityModel<T> {

	private final ModelPart Body;
	private final ModelPart LeftEyeStick;
	private final ModelPart RightEyeStick;

	public SnailModel(ModelPart root, float scale) {
		this.Body = root.getChild("Body");
		this.LeftEyeStick = root.getChild("LeftEyeStick");
		this.RightEyeStick = root.getChild("RightEyeStick");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(26, 14).addBox(
				-3.0F, -5.0F, -6.5F, 6.0F, 5.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition LeftEyeStick = Body.addOrReplaceChild("LeftEyeStick",
				CubeListBuilder.create().texOffs(42, 1).addBox(-0.5F, -4.0F, -0.5F, 1.0F, 4.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.7F, -4.9F, -5.0F, 0.1309F, -0.1309F, 0.0873F));

		PartDefinition LeftEye = LeftEyeStick.addOrReplaceChild("LeftEye",
				CubeListBuilder.create().texOffs(5, 27).mirror()
						.addBox(-0.5F, -3.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offset(0.0F, -3.9672F, 0.0F));

		PartDefinition RightEyeStick = Body.addOrReplaceChild("RightEyeStick",
				CubeListBuilder.create().texOffs(37, 1).addBox(-0.5F, -4.0F, -0.5F, 1.0F, 4.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.7F, -4.9F, -5.0F, 0.1309F, 0.1309F, -0.0873F));

		PartDefinition RightEye = RightEyeStick.addOrReplaceChild("RightEye", CubeListBuilder.create().texOffs(0, 27)
				.addBox(-0.5F, -3.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, -3.9672F, 0.0F));

		PartDefinition Shell = Body.addOrReplaceChild("Shell",
				CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -11.0668F, -2.9662F, 8.0F, 11.0F, 10.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -2.4508F, -0.3417F, -0.2182F, 0.0F, 0.0F));

		PartDefinition LeftNose = Body.addOrReplaceChild("LeftNose",
				CubeListBuilder.create().texOffs(56, 3).addBox(-0.5F, -0.5F, -2.5F, 1.0F, 1.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.4F, -2.5F, -6.0F, 0.0F, -0.1309F, 0.0F));

		PartDefinition RightNose = Body.addOrReplaceChild("RightNose",
				CubeListBuilder.create().texOffs(47, 3).addBox(-0.5F, -0.5F, -2.5F, 1.0F, 1.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.4F, -2.5F, -6.0F, 0.0F, 0.1309F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {
		this.LeftEyeStick.zRot = Mth.cos(limbSwing * 0.6662F) * 0.4F * limbSwingAmount;
		this.RightEyeStick.zRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.4F * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		Body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

}
