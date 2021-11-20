package com.tristankechlo.livingthings.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.tristankechlo.livingthings.client.model.AdvancedEntityModel;
import com.tristankechlo.livingthings.entities.SharkEntity;

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
public class SharkModel<T extends SharkEntity> extends AdvancedEntityModel<T> {

	private final ModelPart Body;
	private final ModelPart LeftFin;
	private final ModelPart RightFin;
	private final ModelPart Tail;
	private final ModelPart Tail2;

	public SharkModel(ModelPart root) {
		this.Body = root.getChild("Body");
		this.LeftFin = Body.getChild("LeftFin");
		this.RightFin = Body.getChild("RightFin");
		this.Tail = Body.getChild("Tail");
		this.Tail2 = Tail.getChild("Tail2");
	}

	@SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(48, 22)
				.addBox(-6.0F, -7.0F, -18.0F, 12.0F, 14.0F, 28.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 17.0F, 0.0F));

		PartDefinition TopFin = Body.addOrReplaceChild("TopFin",
				CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -8.0F, -8.0F, 2.0F, 8.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, -0.6109F, 0.0F, 0.0F));

		PartDefinition RightFin = Body.addOrReplaceChild("RightFin",
				CubeListBuilder.create().texOffs(0, 30).addBox(-1.0F, -2.5F, 0.0F, 1.0F, 5.0F, 11.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, 4.0F, -8.0F, -1.0036F, -0.1745F, 0.6981F));

		PartDefinition LeftFin = Body.addOrReplaceChild("LeftFin",
				CubeListBuilder.create().texOffs(0, 13).addBox(0.0F, -2.5F, 0.0F, 1.0F, 5.0F, 11.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, 4.0F, -8.0F, -1.0036F, 0.1745F, -0.6981F));

		PartDefinition Head = Body.addOrReplaceChild("Head", CubeListBuilder.create(),
				PartPose.offset(0.0F, 7.0F, 0.0F));

		PartDefinition HeadTop = Head.addOrReplaceChild("HeadTop",
				CubeListBuilder.create().texOffs(78, 0).addBox(-5.5F, -3.5F, -7.0F, 11.0F, 7.0F, 14.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -9.5F, -24.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition HeadBottom = Head.addOrReplaceChild("HeadBottom",
				CubeListBuilder.create().texOffs(24, 1).addBox(-5.5F, -3.6993F, -19.9537F, 11.0F, 7.0F, 12.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -1.0F, -9.0F, -0.3054F, 0.0F, 0.0F));

		PartDefinition Tail = Body.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(30, 23).addBox(-5.0F,
				-6.5F, 0.0F, 10.0F, 12.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 10.0F));

		PartDefinition Tail2 = Tail.addOrReplaceChild("Tail2", CubeListBuilder.create().texOffs(0, 47).addBox(-4.0F,
				-5.5F, 0.0F, 8.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 11.0F));

		PartDefinition BackFinTop = Tail2.addOrReplaceChild("BackFinTop",
				CubeListBuilder.create().texOffs(108, 29).addBox(-1.0F, -3.4645F, -10.7071F, 2.0F, 11.0F, 6.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -0.5F, 15.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition BackFinBottom = Tail2.addOrReplaceChild("BackFinBottom",
				CubeListBuilder.create().texOffs(32, 49).addBox(-1.0F, -4.0F, -9.0F, 2.0F, 10.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -0.5F, 13.0F, 0.7854F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {

		LeftFin.yRot = -0.2F * Mth.cos(ageInTicks * 0.3F);
		RightFin.yRot = -0.2F * Mth.cos(ageInTicks * 0.3F);

		this.Body.xRot = headPitch * 0.0174532925F;
		this.Body.yRot = netHeadYaw * 0.0174532925F;

		if (entityIn.getDeltaMovement().horizontalDistanceSqr() > 1.0E-7D) {
			this.Body.xRot += -0.05F + -0.05F * Mth.cos(ageInTicks * 0.3F);
			Tail.yRot = -0.15F * Mth.cos(ageInTicks * 0.3F);
			Tail2.yRot = -0.25F * Mth.cos(ageInTicks * 0.3F);
		} else {
			Tail.yRot = -0.05F * Mth.cos(ageInTicks * 0.3F);
			Tail2.yRot = -0.1F * Mth.cos(ageInTicks * 0.3F);
		}
	}

	@Override
	public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha) {
		Body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}

}
