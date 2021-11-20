package com.tristankechlo.livingthings.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.tristankechlo.livingthings.client.model.AdvancedEntityModel;
import com.tristankechlo.livingthings.entities.ShroomieEntity;

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
public class ShroomieModel<T extends ShroomieEntity> extends AdvancedEntityModel<T> {

	private final ModelPart Body;
	private final ModelPart LegLeft;
	private final ModelPart LegRight;
	private final ModelPart HeadBrown;
	private final ModelPart HeadRed;
	private final ModelPart ArmLeft;
	private final ModelPart ArmRight;

	public ShroomieModel(ModelPart root) {
		this.Body = root.getChild("Body");
		this.LegLeft = Body.getChild("LegLeft");
		this.LegRight = Body.getChild("LegRight");
		this.HeadBrown = Body.getChild("HeadBrown");
		this.HeadRed = Body.getChild("HeadRed");
		this.ArmLeft = Body.getChild("ArmLeft");
		this.ArmRight = Body.getChild("ArmRight");
	}

	@SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 18).addBox(
				-3.0F, -11.0F, -3.0F, 6.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition LegLeft = Body.addOrReplaceChild("LegLeft",
				CubeListBuilder.create().texOffs(0, 0).mirror()
						.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offset(-1.5F, -3.0F, 0.0F));

		PartDefinition LegRight = Body.addOrReplaceChild("LegRight", CubeListBuilder.create().texOffs(0, 0).addBox(
				-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, -3.0F, 0.0F));

		PartDefinition HeadBrown = Body.addOrReplaceChild("HeadBrown", CubeListBuilder.create().texOffs(0, 5)
				.addBox(-5.0F, -3.0F, -5.0F, 10.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, -11.0F, 0.0F));

		PartDefinition HeadRed = Body.addOrReplaceChild("HeadRed",
				CubeListBuilder.create().texOffs(0, 4)
						.addBox(-5.0F, -4.0F, -5.0F, 10.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)).texOffs(24, 23)
						.addBox(-3.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, -11.0F, 0.0F));

		PartDefinition ArmLeft = Body.addOrReplaceChild("ArmLeft", CubeListBuilder.create().texOffs(0, 6).addBox(0.0F,
				0.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -9.0F, 0.0F));

		PartDefinition ArmRight = Body.addOrReplaceChild("ArmRight",
				CubeListBuilder.create().texOffs(0, 6).mirror()
						.addBox(-1.0F, 0.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offset(-3.0F, -9.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 48, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {

		if (entity.getVariant() == 0) {
			HeadBrown.visible = true;
			HeadRed.visible = false;
		} else {
			HeadBrown.visible = false;
			HeadRed.visible = true;
		}

		this.walking1(LegLeft, limbSwing, limbSwingAmount);
		this.walking2(LegRight, limbSwing, limbSwingAmount);
		this.walking2(ArmLeft, limbSwing, limbSwingAmount);
		this.walking1(ArmRight, limbSwing, limbSwingAmount);
	}

	@Override
	public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		Body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

}