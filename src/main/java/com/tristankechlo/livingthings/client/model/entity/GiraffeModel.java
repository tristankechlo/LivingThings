package com.tristankechlo.livingthings.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.tristankechlo.livingthings.client.model.AdvancedEntityModel;
import com.tristankechlo.livingthings.entities.GiraffeEntity;

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
public class GiraffeModel<T extends GiraffeEntity> extends AdvancedEntityModel<T> {

	private final ModelPart Body;
	private final ModelPart Head;
	private final ModelPart Legs;
	private final ModelPart NeckTop;
	private final ModelPart NeckMiddle;
	private final ModelPart NeckBottom;
	private final ModelPart FrontRightLeg;
	private final ModelPart FrontLeftLeg;
	private final ModelPart BackRightLeg;
	private final ModelPart BackLeftLeg;
	private final ModelPart TailTop;
	private final ModelPart TailBottom;

	public GiraffeModel(ModelPart root) {
		this.Body = root.getChild("Body");
		this.Legs = Body.getChild("Legs");
		this.NeckBottom = Body.getChild("NeckBottom");
		this.NeckMiddle = NeckBottom.getChild("NeckMiddle");
		this.NeckTop = NeckMiddle.getChild("NeckTop");
		this.Head = NeckTop.getChild("Head");
		this.FrontRightLeg = Legs.getChild("FrontRightLeg");
		this.FrontLeftLeg = Legs.getChild("FrontLeftLeg");
		this.BackRightLeg = Legs.getChild("BackRightLeg");
		this.BackLeftLeg = Legs.getChild("BackLeftLeg");
		this.TailTop = Body.getChild("TailTop");
		this.TailBottom = TailTop.getChild("TailBottom");
	}

	@SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Body = partdefinition.addOrReplaceChild("Body",
				CubeListBuilder.create().texOffs(72, 35)
						.addBox(-6.0F, -21.5F, -13.6F, 12.0F, 13.0F, 16.0F, new CubeDeformation(0.0F)).texOffs(81, 14)
						.addBox(-5.5F, -19.5F, 2.4F, 11.0F, 11.0F, 10.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 14.5F, 0.6F));

		PartDefinition Legs = Body.addOrReplaceChild("Legs", CubeListBuilder.create(),
				PartPose.offset(0.0F, -7.5F, -0.6F));

		PartDefinition FrontRightLeg = Legs.addOrReplaceChild("FrontRightLeg",
				CubeListBuilder.create().texOffs(98, 0)
						.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(116, 0)
						.addBox(-1.5F, 8.0F, -1.5F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-4.0F, -1.0F, -10.0F));

		PartDefinition FrontLeftLeg = Legs.addOrReplaceChild("FrontLeftLeg",
				CubeListBuilder.create().texOffs(98, 0).mirror()
						.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
						.texOffs(116, 0).addBox(-1.5F, 8.0F, -1.5F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offset(4.0F, -1.0F, -10.0F));

		PartDefinition BackLeftLeg = Legs.addOrReplaceChild("BackLeftLeg",
				CubeListBuilder.create().texOffs(70, 0).mirror()
						.addBox(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
						.texOffs(116, 0).addBox(-1.5F, 10.0F, -1.5F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offset(4.0F, -3.0F, 10.0F));

		PartDefinition BackRightLeg = Legs.addOrReplaceChild("BackRightLeg",
				CubeListBuilder.create().texOffs(70, 0)
						.addBox(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(116, 0)
						.addBox(-1.5F, 10.0F, -1.5F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-4.0F, -3.0F, 10.0F));

		PartDefinition NeckBottom = Body.addOrReplaceChild("NeckBottom",
				CubeListBuilder.create().texOffs(0, 46).addBox(-4.5F, -2.0F, -3.5F, 9.0F, 9.0F, 9.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -21.5F, -13.6F, 0.5236F, 0.0F, 0.0F));

		PartDefinition NeckMiddle = NeckBottom.addOrReplaceChild("NeckMiddle",
				CubeListBuilder.create().texOffs(0, 22).addBox(-3.5F, -10.0F, -3.5F, 7.0F, 11.0F, 7.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -2.0F, 1.0F, -0.1309F, 0.0F, 0.0F));

		PartDefinition NeckTop = NeckMiddle.addOrReplaceChild("NeckTop",
				CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -10.0F, -2.5F, 5.0F, 11.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -10.0F, 0.0F, -0.1309F, 0.0F, 0.0F));

		PartDefinition Head = NeckTop.addOrReplaceChild("Head",
				CubeListBuilder.create().texOffs(41, 52).addBox(-3.0F, -6.0F, -4.0F, 6.0F, 6.0F, 6.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -8.0F, 0.0F, -0.1309F, 0.0F, 0.0F));

		PartDefinition FrontHead = Head.addOrReplaceChild("FrontHead", CubeListBuilder.create().texOffs(46, 41).addBox(
				-2.0F, -2.0F, -4.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -4.0F));

		PartDefinition Horns = Head.addOrReplaceChild("Horns", CubeListBuilder.create(),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LeftHorn = Horns.addOrReplaceChild("LeftHorn",
				CubeListBuilder.create().texOffs(30, 15).addBox(0.5F, -2.5F, 0.0F, 1.0F, 3.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, -6.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition RightHorn = Horns.addOrReplaceChild("RightHorn",
				CubeListBuilder.create().texOffs(30, 15).addBox(-0.5F, -2.5F, 0.0F, 1.0F, 3.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -6.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition Ears = Head.addOrReplaceChild("Ears", CubeListBuilder.create(),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LeftEar = Ears.addOrReplaceChild("LeftEar", CubeListBuilder.create().texOffs(36, 16).addBox(0.0F,
				-1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -4.0F, 0.0F));

		PartDefinition RightEar = Ears.addOrReplaceChild("RightEar", CubeListBuilder.create().texOffs(36, 16).addBox(
				-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -4.0F, 0.0F));

		PartDefinition TailTop = Body.addOrReplaceChild("TailTop",
				CubeListBuilder.create().texOffs(48, 4).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 9.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -16.5F, 12.4F, 0.1745F, 0.0F, 0.0F));

		PartDefinition TailBottom = TailTop
				.addOrReplaceChild("TailBottom",
						CubeListBuilder.create().texOffs(53, 4).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 7.0F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 9.0F, 0.0F, -0.1309F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha) {

		if (this.young) {
			matrixStackIn.scale(0.6F, 0.6F, 0.6F);
			matrixStackIn.translate(0, 1, 0);
		}
		Body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);

	}

	@Override
	public void setupAnim(GiraffeEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {

		this.Head.xRot = headPitch * 0.0174532925F;
		this.NeckTop.xRot = (float) (this.NeckMiddle.xRot / 1);
		this.NeckMiddle.xRot = (float) (this.Head.xRot / 2);

		this.Head.yRot = (netHeadYaw / 3.75F) * 0.0174532925F;
		this.NeckTop.yRot = (netHeadYaw / 3.75F) * 0.0174532925F;
		this.NeckMiddle.yRot = (netHeadYaw / 3.75F) * 0.0174532925F;
		this.NeckBottom.yRot = (netHeadYaw / 5F) * 0.0174532925F;

		this.walk(FrontRightLeg, FrontLeftLeg, BackRightLeg, BackLeftLeg, limbSwing, limbSwingAmount);

		this.TailTop.zRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.75F * limbSwingAmount;
		this.TailBottom.zRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.75F * limbSwingAmount;
	}

}
