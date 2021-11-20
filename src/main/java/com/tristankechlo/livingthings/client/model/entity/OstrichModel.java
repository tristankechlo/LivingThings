package com.tristankechlo.livingthings.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.tristankechlo.livingthings.client.model.AdvancedEntityModel;
import com.tristankechlo.livingthings.entities.OstrichEntity;

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
public class OstrichModel<T extends OstrichEntity> extends AdvancedEntityModel<T> {

	private boolean isLayingEgg;
	private boolean isBuildingNest;
	private final ModelPart Body;
	private final ModelPart Head;
	private final ModelPart Neck;
	private final ModelPart NeckTop;
	private final ModelPart LeftLegTop;
	private final ModelPart LeftLegBottom;
	private final ModelPart LeftFoot;
	private final ModelPart RightLegTop;
	private final ModelPart RightLegBottom;
	private final ModelPart RightFoot;

	public OstrichModel(ModelPart root) {
		this.Body = root.getChild("Body");
		this.Neck = Body.getChild("Neck");
		this.NeckTop = Neck.getChild("NeckTop");
		this.Head = NeckTop.getChild("Head");
		this.LeftLegTop = Body.getChild("LeftLegTop");
		this.LeftLegBottom = LeftLegTop.getChild("LeftLegBottom");
		this.LeftFoot = LeftLegBottom.getChild("LeftFoot");
		this.RightLegTop = Body.getChild("RightLegTop");
		this.RightLegBottom = RightLegTop.getChild("RightLegBottom");
		this.RightFoot = RightLegBottom.getChild("RightFoot");
	}

	@SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Body = partdefinition.addOrReplaceChild("Body",
				CubeListBuilder.create().texOffs(0, 44)
						.addBox(-4.5F, -5.0F, -4.0F, 9.0F, 9.0F, 11.0F, new CubeDeformation(0.0F)).texOffs(42, 53)
						.addBox(-4.0F, -5.0F, -7.0F, 8.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 8.0F, 0.0F, -0.0436F, 0.0F, 0.0F));

		PartDefinition Neck = Body.addOrReplaceChild("Neck",
				CubeListBuilder.create().texOffs(29, 0).addBox(-1.5F, -5.0F, -0.5F, 3.0F, 7.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -1.0F, -7.5F, 0.3054F, 0.0F, 0.0F));

		PartDefinition NeckTop = Neck.addOrReplaceChild("NeckTop",
				CubeListBuilder.create().texOffs(29, 11).addBox(-1.0F, -7.0F, -1.0F, 2.0F, 8.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -5.0F, 1.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition Head = NeckTop.addOrReplaceChild("Head",
				CubeListBuilder.create().texOffs(29, 22).addBox(-1.5F, -3.0F, -2.75F, 3.0F, 3.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -7.0F, 0.0F, -0.0436F, 0.0F, 0.0F));

		PartDefinition Mouth = Head.addOrReplaceChild("Mouth", CubeListBuilder.create().texOffs(29, 30).addBox(-1.0F,
				-1.3F, -1.25F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -3.0F));

		PartDefinition MouthTop = Mouth.addOrReplaceChild("MouthTop",
				CubeListBuilder.create().texOffs(29, 30).addBox(-1.0F, -0.75F, -1.0F, 2.0F, 1.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -0.8F, -0.25F, 0.2182F, 0.0F, 0.0F));

		PartDefinition LeftWing = Body.addOrReplaceChild("LeftWing",
				CubeListBuilder.create().texOffs(44, 22).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 5.0F, 9.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.5F, -2.0F, -3.0F, 0.0F, 0.1745F, 0.0F));

		PartDefinition RightWing = Body.addOrReplaceChild("RightWing",
				CubeListBuilder.create().texOffs(44, 37).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 5.0F, 9.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.5F, -2.0F, -3.0F, 0.0F, -0.1745F, 0.0F));

		PartDefinition LeftLegTop = Body
				.addOrReplaceChild("LeftLegTop",
						CubeListBuilder.create().texOffs(0, 21).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(4.0F, 2.0F, 0.0F, 0.3927F, 0.0F, 0.0F));

		PartDefinition LeftLegBottom = LeftLegTop
				.addOrReplaceChild("LeftLegBottom",
						CubeListBuilder.create().texOffs(9, 21).addBox(-1.0F, -0.6F, -1.15F, 2.0F, 8.0F, 2.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 7.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition LeftFoot = LeftLegBottom
				.addOrReplaceChild("LeftFoot",
						CubeListBuilder.create().texOffs(18, 27).addBox(-1.0F, -0.5F, -2.1F, 2.0F, 1.0F, 3.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 7.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition RightLegTop = Body
				.addOrReplaceChild("RightLegTop",
						CubeListBuilder.create().texOffs(0, 32).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(-4.0F, 2.0F, 0.0F, 0.3927F, 0.0F, 0.0F));

		PartDefinition RightLegBottom = RightLegTop
				.addOrReplaceChild("RightLegBottom",
						CubeListBuilder.create().texOffs(9, 32).addBox(-1.0F, -0.6F, -1.15F, 2.0F, 8.0F, 2.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 7.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition RightFoot = RightLegBottom
				.addOrReplaceChild("RightFoot",
						CubeListBuilder.create().texOffs(18, 38).addBox(-1.0F, -0.5F, -2.1F, 2.0F, 1.0F, 3.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 7.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition Back = Body.addOrReplaceChild("Back", CubeListBuilder.create().texOffs(46, 10).addBox(-4.0F,
				-1.0F, -0.5F, 8.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 7.5F));

		PartDefinition add1 = Back
				.addOrReplaceChild("add1",
						CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -5.0F, -0.5F, 3.0F, 5.0F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6545F, 0.0F, 0.0F));

		PartDefinition add1Left = add1
				.addOrReplaceChild("add1Left",
						CubeListBuilder.create().texOffs(9, 0).addBox(-1.0F, -5.0F, -2.5F, 2.0F, 5.0F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(1.0F, 0.0F, 2.0F, 0.0F, 0.0F, 0.6109F));

		PartDefinition add1Right = add1.addOrReplaceChild("add1Right",
				CubeListBuilder.create().texOffs(16, 0).addBox(-1.0F, -5.0F, -2.5F, 2.0F, 5.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 0.0F, 2.0F, 0.0F, 0.0F, -0.6109F));

		PartDefinition add2 = Back
				.addOrReplaceChild("add2",
						CubeListBuilder.create().texOffs(0, 7).addBox(-1.5F, -5.0F, -0.5F, 3.0F, 5.0F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.5F, 0.0F, -1.1781F, 0.0F, 0.0F));

		PartDefinition add2Left = add2
				.addOrReplaceChild("add2Left",
						CubeListBuilder.create().texOffs(9, 7).addBox(-1.0F, -5.0F, -2.5F, 2.0F, 5.0F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(1.0F, 0.0F, 2.0F, 0.0F, 0.0F, 0.6545F));

		PartDefinition add2Right = add2.addOrReplaceChild("add2Right",
				CubeListBuilder.create().texOffs(9, 14).addBox(-1.0F, -5.0F, -2.5F, 2.0F, 5.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 0.0F, 2.0F, 0.0F, 0.0F, -0.7418F));

		PartDefinition add3 = Back
				.addOrReplaceChild("add3",
						CubeListBuilder.create().texOffs(0, 14).addBox(-1.5F, -5.0F, -0.5F, 3.0F, 5.0F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, -1.6581F, 0.0F, 0.0F));

		PartDefinition add3Left = add3
				.addOrReplaceChild("add3Left",
						CubeListBuilder.create().texOffs(16, 7).addBox(-1.0F, -5.0F, -2.5F, 2.0F, 5.0F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(1.0F, 0.0F, 2.0F, 0.0F, 0.0F, 0.6981F));

		PartDefinition add3Right = add3.addOrReplaceChild("add3Right",
				CubeListBuilder.create().texOffs(16, 14).addBox(-1.0F, -5.0F, -2.5F, 2.0F, 5.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 0.0F, 2.0F, 0.0F, 0.0F, -0.6981F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {
		this.isLayingEgg = entity.isLayingEgg();
		this.isBuildingNest = entity.isBuildingNest();

		this.Head.xRot = headPitch * 0.0174532925F;
		this.Head.yRot = (netHeadYaw / 3.75F) * 0.0174532925F;

		this.NeckTop.xRot = (float) (this.Head.xRot / 1.75F);
		this.Neck.yRot = (float) (this.Head.yRot / 1.75F);

		if (this.isLayingEgg) {

			this.LeftLegTop.xRot = 1.17809724375F;
			this.LeftLegBottom.xRot = -2.5743606466F;
			this.LeftFoot.xRot = 0.6108652381F;

			this.RightLegTop.xRot = 1.17809724375F;
			this.RightLegBottom.xRot = -2.5743606466F;
			this.RightFoot.xRot = 0.6108652381F;

		} else if (this.isBuildingNest) {

			this.LeftLegTop.xRot = 0.3926990812F;
			this.LeftLegBottom.xRot = -0.523598775F;
			this.LeftFoot.xRot = 0.174532925F;

			this.RightLegTop.xRot = 0.3926990812F + (Mth.cos(ageInTicks * 0.45F));
			this.RightLegBottom.xRot = -0.523598775F;
			this.RightFoot.xRot = 0.174532925F;

		} else {

			this.LeftLegTop.xRot = 0.3926990812F + (Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount);
			this.LeftLegBottom.xRot = -0.523598775F;
			this.LeftFoot.xRot = 0.174532925F;

			this.RightLegTop.xRot = 0.3926990812F + (Mth.cos(limbSwing * 0.6662F) * limbSwingAmount);
			this.RightLegBottom.xRot = -0.523598775F;
			this.RightFoot.xRot = 0.174532925F;

		}
	}

	@Override
	public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {

		if (this.young) {
			matrixStack.scale(0.6F, 0.6F, 0.6F);
			matrixStack.translate(0, 1, 0);
		} else if (this.isLayingEgg) {
			matrixStack.translate(0, 0.65, 0);
		}
		Body.render(matrixStack, buffer, packedLight, packedOverlay);
	}

}
