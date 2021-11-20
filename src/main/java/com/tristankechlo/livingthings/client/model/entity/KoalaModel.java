package com.tristankechlo.livingthings.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.tristankechlo.livingthings.client.model.AdvancedEntityModel;
import com.tristankechlo.livingthings.entities.KoalaEntity;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class KoalaModel<T extends KoalaEntity> extends AdvancedEntityModel<T> {

	private final ModelPart Body;
	private final ModelPart Head;
	private final ModelPart LegFrontRight;
	private final ModelPart LegFrontLeft;
	private final ModelPart LegBackRight;
	private final ModelPart LegBackLeft;

	public KoalaModel(ModelPart root) {
		this.Body = root.getChild("Body");
		this.Head = Body.getChild("Head");
		this.LegFrontRight = Body.getChild("LegFrontRight");
		this.LegFrontLeft = Body.getChild("LegFrontLeft");
		this.LegBackRight = Body.getChild("LegBackRight");
		this.LegBackLeft = Body.getChild("LegBackLeft");
	}

	@SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Body = partdefinition.addOrReplaceChild("Body",
				CubeListBuilder.create().texOffs(0, 4).addBox(-3.5F, -6.0F, -3.0F, 7.0F, 6.0F, 6.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 19.1F, -2.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition BodyBack = Body.addOrReplaceChild("BodyBack",
				CubeListBuilder.create().texOffs(0, 17).addBox(-4.0F, -3.8486F, -0.0152F, 8.0F, 7.0F, 8.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -2.9128F, 2.0038F, -0.1309F, 0.0F, 0.0F));

		PartDefinition Head = Body.addOrReplaceChild("Head",
				CubeListBuilder.create().texOffs(33, 23).mirror()
						.addBox(-3.0F, -2.4245F, -3.9909F, 6.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(0.0F, -2.8577F, -2.7764F, 0.0873F, 0.0F, 0.0F));

		PartDefinition EarLeft = Head.addOrReplaceChild("EarLeft",
				CubeListBuilder.create().texOffs(54, 28).addBox(-1.5F, -1.5F, -0.5F, 3.0F, 3.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.9F, -2.2245F, -1.4909F, 0.0F, 0.0F, 0.1309F));

		PartDefinition EarRight = Head.addOrReplaceChild("EarRight",
				CubeListBuilder.create().texOffs(54, 28).mirror()
						.addBox(-1.5F, -1.5F, -0.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(-2.9F, -2.2245F, -1.4909F, 0.0F, 0.0F, -0.1309F));

		PartDefinition Nose = Head.addOrReplaceChild("Nose",
				CubeListBuilder.create().texOffs(54, 24).addBox(-1.5F, -1.0174F, -1.1992F, 3.0F, 2.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.8255F, -3.6909F, 0.0873F, 0.0F, 0.0F));

		PartDefinition Nose2 = Nose.addOrReplaceChild("Nose2",
				CubeListBuilder.create().texOffs(38, 19).addBox(-1.0F, -1.0521F, -1.4954F, 2.0F, 2.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.025F, 0.0873F, 0.0F, 0.0F));

		PartDefinition LegFrontLeft = Body.addOrReplaceChild("LegFrontLeft",
				CubeListBuilder.create().texOffs(27, 9).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.5512F, -3.5798F, -0.5496F, 0.0873F, 0.0F, -0.0873F));

		PartDefinition LegFrontLeft2 = LegFrontLeft.addOrReplaceChild("LegFrontLeft2",
				CubeListBuilder.create().texOffs(27, 1)
						.addBox(-1.3257F, -0.0076F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(27, 18)
						.addBox(-1.3257F, 2.9924F, -2.4999F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.1863F, 4.4963F, 0.0016F, 0.0F, 0.0F, 0.0873F));

		PartDefinition LegBackLeft = Body.addOrReplaceChild("LegBackLeft",
				CubeListBuilder.create().texOffs(40, 9).addBox(-2.0057F, 0.0432F, -2.0038F, 4.0F, 5.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.0512F, -4.1899F, 6.4237F, 0.0F, 0.0F, -0.0873F));

		PartDefinition LegBackLeft2 = LegBackLeft.addOrReplaceChild("LegBackLeft2",
				CubeListBuilder.create().texOffs(41, 1)
						.addBox(-1.3257F, -0.0076F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(27, 18)
						.addBox(-1.3257F, 2.9923F, -2.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.1863F, 4.4963F, 0.0016F, 0.0873F, 0.0F, 0.0873F));

		PartDefinition LegFrontRight = Body.addOrReplaceChild("LegFrontRight",
				CubeListBuilder.create().texOffs(27, 9).mirror()
						.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(-2.4488F, -3.5798F, -0.5496F, 0.0873F, 0.0F, 0.0873F));

		PartDefinition LegFrontRight2 = LegFrontRight.addOrReplaceChild("LegFrontRight2",
				CubeListBuilder.create().texOffs(27, 1)
						.addBox(-1.3007F, -0.0076F, -1.4998F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
						.texOffs(27, 18).mirror()
						.addBox(-1.3007F, 2.9924F, -2.4999F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(-0.1863F, 4.4963F, 0.0016F, 0.0F, 0.0F, -0.0873F));

		PartDefinition LegBackRight = Body.addOrReplaceChild("LegBackRight",
				CubeListBuilder.create().texOffs(40, 9).addBox(-2.0057F, 0.0432F, -2.0038F, 4.0F, 5.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.9488F, -4.1899F, 6.4237F, 0.0F, 0.0F, 0.0873F));

		PartDefinition LegBackRight2 = LegBackRight.addOrReplaceChild("LegBackRight2",
				CubeListBuilder.create().texOffs(41, 1)
						.addBox(-1.3257F, -0.0076F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(27, 18)
						.addBox(-1.3257F, 2.9923F, -2.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.1863F, 4.4963F, 0.0016F, 0.0873F, 0.0F, -0.0873F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {

		this.Head.xRot = headPitch * 0.0174532925F;
		this.Head.yRot = (netHeadYaw / 3.75F) * 0.0174532925F;

		this.walk(LegFrontRight, LegFrontLeft, LegBackRight, LegBackLeft, limbSwing, limbSwingAmount);
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
