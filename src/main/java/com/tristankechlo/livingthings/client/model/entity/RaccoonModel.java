package com.tristankechlo.livingthings.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.tristankechlo.livingthings.client.model.AdvancedEntityModel;
import com.tristankechlo.livingthings.entities.RaccoonEntity;

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
public class RaccoonModel<T extends RaccoonEntity> extends AdvancedEntityModel<T> {

	private final ModelPart Body;
	private final ModelPart Head;
	private final ModelPart Tail;
	private final ModelPart LegFrontRight;
	private final ModelPart LegFrontLeft;
	private final ModelPart LegBackRight;
	private final ModelPart LegBackLeft;

	public RaccoonModel(ModelPart root) {
		this.Body = root.getChild("Body");
		this.Head = Body.getChild("Head");
		this.Tail = Body.getChild("Tail");
		this.LegFrontRight = Body.getChild("LegFrontRight");
		this.LegFrontLeft = Body.getChild("LegFrontLeft");
		this.LegBackRight = Body.getChild("LegBackRight");
		this.LegBackLeft = Body.getChild("LegBackLeft");
	}

	@SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 1)
				.addBox(-3.0F, -11.0F, -5.5F, 6.0F, 6.0F, 11.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition LegFrontLeft = Body.addOrReplaceChild("LegFrontLeft", CubeListBuilder.create().texOffs(0, 25)
				.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(1.75F, -5.0F, -3.5F));

		PartDefinition LegBackLeft = Body.addOrReplaceChild("LegBackLeft", CubeListBuilder.create().texOffs(9, 25)
				.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(1.75F, -5.0F, 3.5F));

		PartDefinition LegFrontRight = Body.addOrReplaceChild("LegFrontRight", CubeListBuilder.create().texOffs(18, 25)
				.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-1.75F, -5.0F, -3.5F));

		PartDefinition LegBackRight = Body.addOrReplaceChild("LegBackRight", CubeListBuilder.create().texOffs(27, 25)
				.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-1.75F, -5.0F, 3.5F));

		PartDefinition Tail = Body.addOrReplaceChild("Tail",
				CubeListBuilder.create().texOffs(36, 20).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 8.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -8.95F, 4.65F, -0.4363F, 0.0F, 0.0F));

		PartDefinition Head = Body.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(36, 7).addBox(-4.0F,
				-3.0F, -5.0F, 8.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, -5.5F));

		PartDefinition LeftEar = Head.addOrReplaceChild("LeftEar", CubeListBuilder.create().texOffs(0, 20).addBox(-1.0F,
				-1.75F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, -3.0F, -2.0F));

		PartDefinition RightEar = Head.addOrReplaceChild("RightEar", CubeListBuilder.create().texOffs(7, 20)
				.addBox(-1.0F, -1.75F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-2.5F, -3.0F, -1.75F));

		PartDefinition Mouth = Head.addOrReplaceChild("Mouth", CubeListBuilder.create().texOffs(25, 4).addBox(-2.0F,
				-1.5F, -3.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.25F, -5.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {

		this.Head.xRot = headPitch * 0.0174532925F;
		this.Head.yRot = (netHeadYaw / 3.75F) * 0.0174532925F;
		this.walk(LegFrontRight, LegFrontLeft, LegBackRight, LegBackLeft, limbSwing, limbSwingAmount);
		this.Tail.yRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.75F * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {

		if (this.young) {
			matrixStack.scale(0.5F, 0.5F, 0.5F);
			matrixStack.translate(0, 1.5D, 0);
		}
		Body.render(matrixStack, buffer, packedLight, packedOverlay);
	}
}