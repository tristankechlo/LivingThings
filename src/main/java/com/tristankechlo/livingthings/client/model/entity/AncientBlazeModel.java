package com.tristankechlo.livingthings.client.model.entity;

import java.util.Arrays;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.tristankechlo.livingthings.client.model.AdvancedEntityModel;
import com.tristankechlo.livingthings.entities.AncientBlazeEntity;

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
public class AncientBlazeModel<T extends AncientBlazeEntity> extends AdvancedEntityModel<T> {

	private static final int MAX_SHIELD_COUNT = 4;
	private static final int MAX_STICK_COUNT = 10;
	private final ModelPart Body;
	private final ModelPart Head;
	private final ModelPart Shields;
	private final ModelPart[] shields; // 4
	private final ModelPart[] sticks; // 10

	public AncientBlazeModel(ModelPart root) {
		this.Body = root.getChild("Body");
		this.Head = root.getChild("Head");
		this.Shields = root.getChild("Shields");
		this.shields = new ModelPart[MAX_SHIELD_COUNT];
		Arrays.setAll(this.shields, (number) -> {
			return root.getChild("shield_" + number);
		});
		this.sticks = new ModelPart[MAX_STICK_COUNT];
		Arrays.setAll(this.sticks, (number) -> {
			return root.getChild("stick_" + number);
		});

		Shields = new ModelPart(this);
		Shields.setPos(0.0F, -22.0F, 0.0F);
		Body.addChild(Shields);
		this.setRotationAngle(Shields, 0.0F, -0.7854F, 0.0F);

		for (int i = 0; i < this.shields.length; i++) {
			this.shields[i] = new ModelPart(this, 0, 43);
			this.shields[i].addBox(-5.0F, 0.0F, -1.0F, 10.0F, 19.0F, 2.0F, 0.0F, false);
			this.shields[i].setPos(i * 7.5F, 0.0F, i * 7.5F);
			this.setRotationAngle(this.shields[i], -0.3491F, ((-1) ^ i) * 1.570796F, 0.0F);
			this.Shields.addChild(this.shields[i]);
		}

		Sticks = new ModelPart(this);
		Sticks.setPos(0.0F, 5.0F, 0.0F);
		Body.addChild(Sticks);

		for (int i = 0; i < this.sticks.length; i++) {
			this.sticks[i] = new ModelPart(this, 0, 18);
			this.sticks[i].addBox(-1.0F, -6.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);
			this.Sticks.addChild(this.sticks[i]);
		}
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(25, 24)
				.addBox(-2.5F, -22.0F, -2.5F, 5.0F, 35.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition Head = Body.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F,
				-4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.5F, 0.0F));

		PartDefinition Helmet = Head.addOrReplaceChild("Helmet",
				CubeListBuilder.create().texOffs(33, 0)
						.addBox(3.5F, -9.5F, -4.5F, 1.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(9, 17)
						.addBox(-3.5F, -8.5F, -4.5F, 7.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(9, 22)
						.addBox(-2.5F, -9.5F, -4.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(16, 22)
						.addBox(0.5F, -9.5F, -4.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(23, 22)
						.addBox(-3.5F, -10.5F, -4.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(9, 25)
						.addBox(1.5F, -10.5F, -4.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(16, 25)
						.addBox(0.5F, -11.5F, -4.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(23, 25)
						.addBox(-2.5F, -11.5F, -4.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(9, 28)
						.addBox(-1.5F, -12.5F, -4.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(33, 13)
						.addBox(1.5F, -3.5F, -4.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(40, 13)
						.addBox(-3.5F, -3.5F, -4.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(40, 0)
						.addBox(-4.5F, -9.5F, -4.5F, 1.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 33)
						.addBox(-2.5F, -8.5F, 3.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 36)
						.addBox(-2.5F, -0.5F, 3.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(47, 13)
						.addBox(2.5F, -9.5F, 3.5F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(47, 25)
						.addBox(-3.5F, -9.5F, 3.5F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(18, 28)
						.addBox(-2.5F, -4.5F, 3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(33, 19)
						.addBox(-0.5F, -7.5F, 3.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(38, 19)
						.addBox(-0.5F, -2.5F, 3.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(47, 37)
						.addBox(-1.5F, -6.5F, 3.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(47, 44)
						.addBox(0.5F, -6.5F, 3.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(41, 24)
						.addBox(1.5F, -4.5F, 3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(52, 29)
						.addBox(3.5F, -1.5F, -2.5F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(52, 21)
						.addBox(3.5F, -8.5F, -2.5F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(52, 13)
						.addBox(-4.5F, -8.5F, -2.5F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(46, 51)
						.addBox(3.5F, -3.5F, -0.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 39)
						.addBox(-4.5F, -3.5F, -0.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(47, 0)
						.addBox(3.5F, -9.5F, 2.5F, 1.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(54, 0)
						.addBox(-4.5F, -9.5F, 2.5F, 1.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(7, 40)
						.addBox(-4.5F, -10.5F, 3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(13, 31)
						.addBox(3.5F, -10.5F, 3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(13, 34)
						.addBox(3.5F, -5.5F, -1.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(18, 34)
						.addBox(-4.5F, -5.5F, -1.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(46, 55)
						.addBox(3.5F, -5.5F, -0.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(46, 60)
						.addBox(-4.5F, -5.5F, -0.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(52, 37)
						.addBox(-4.5F, -1.5F, -2.5F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(52, 45)
						.addBox(3.5F, -13.5F, -4.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(57, 45)
						.addBox(-4.5F, -13.5F, -4.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(53, 51)
						.addBox(2.5F, -15.5F, -4.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(58, 51)
						.addBox(-3.5F, -15.5F, -4.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Crystal = Head.addOrReplaceChild("Crystal",
				CubeListBuilder.create().texOffs(25, 2)
						.addBox(-1.5F, -11.5F, -4.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(27, 6)
						.addBox(-3.5F, -9.5F, -4.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(27, 6)
						.addBox(2.5F, -9.5F, -4.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Shields = Body.addOrReplaceChild("Shields", CubeListBuilder.create(),
				PartPose.offsetAndRotation(0.0F, -22.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition ShieldOne = Shields.addOrReplaceChild("ShieldOne",
				CubeListBuilder.create().texOffs(0, 43).addBox(-5.0F, 0.0F, -1.0F, 10.0F, 19.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, -7.5F, -0.3491F, 0.0F, 0.0F));

		PartDefinition ShieldTwo = Shields.addOrReplaceChild("ShieldTwo",
				CubeListBuilder.create().texOffs(0, 43).addBox(-5.0F, 0.0F, -1.0F, 10.0F, 19.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-7.5F, 0.0F, 0.0F, -0.3491F, 1.5708F, 0.0F));

		PartDefinition ShieldThree = Shields.addOrReplaceChild("ShieldThree",
				CubeListBuilder.create().texOffs(0, 43).addBox(-5.0F, 0.0F, -1.0F, 10.0F, 19.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 7.5F, -0.3491F, 3.1416F, 0.0F));

		PartDefinition ShieldFour = Shields.addOrReplaceChild("ShieldFour",
				CubeListBuilder.create().texOffs(0, 43).addBox(-5.0F, 0.0F, -1.0F, 10.0F, 19.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(7.5F, 0.0F, 0.0F, -0.3491F, -1.5708F, 0.0F));

		PartDefinition Sticks = Body.addOrReplaceChild("Sticks", CubeListBuilder.create(),
				PartPose.offset(0.0F, 5.0F, 0.0F));

		PartDefinition StickOne = Sticks.addOrReplaceChild("StickOne", CubeListBuilder.create().texOffs(0, 18).addBox(
				-1.0F, -6.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, 0.0F, 0.0F));

		PartDefinition StickTwo = Sticks.addOrReplaceChild("StickTwo", CubeListBuilder.create().texOffs(0, 18)
				.addBox(-1.0F, -6.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-4.0F, 0.0F, 6.928F));

		PartDefinition StickThree = Sticks.addOrReplaceChild("StickThree", CubeListBuilder.create().texOffs(0, 18)
				.addBox(-1.0F, -6.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(4.0F, 0.0F, 6.928F));

		PartDefinition StickFour = Sticks.addOrReplaceChild("StickFour", CubeListBuilder.create().texOffs(0, 18).addBox(
				-1.0F, -6.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 0.0F, 0.0F));

		PartDefinition StickFive = Sticks.addOrReplaceChild("StickFive", CubeListBuilder.create().texOffs(0, 18)
				.addBox(-1.0F, -6.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(4.0F, 0.0F, -6.928F));

		PartDefinition StickSix = Sticks.addOrReplaceChild("StickSix", CubeListBuilder.create().texOffs(0, 18)
				.addBox(-1.0F, -6.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-4.0F, 0.0F, -6.928F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(AncientBlazeEntity blaze, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {

		this.Head.yRot = netHeadYaw * 0.017453F;
		this.Head.xRot = headPitch * 0.017453F;

		float f = 0.785398F + ageInTicks * -0.094247F;
		for (int i = 0; i < this.sticks.length; i++) {
			this.sticks[i].visible = (blaze.getShoots() > i);
			this.sticks[i].y = Mth.cos((i * 3F + ageInTicks) * 0.3F) - 1.0F;
			this.sticks[i].x = Mth.cos(f) * 9.0F;
			this.sticks[i].z = Mth.sin(f) * 9.0F;
			f++;
		}

		if (blaze.isPowered()) {
			this.Shields.yRot = 0.785398F;
			this.Head.y = -22.5F;

			for (int i = 0; i < this.shields.length; i++) {
				this.shields[i].x = Mth.cos(i * 1.570796F) * 6.0F;
				this.shields[i].z = Mth.sin(i * 1.570796F) * 6.0F;
				this.shields[i].xRot = 0F;
			}

		} else {
			this.Shields.yRot = -ageInTicks / 50;
			this.Head.y = -24.5F;

			for (int i = 0; i < this.shields.length; i++) {
				this.shields[i].x = Mth.cos(i * 1.570796F) * 7.5F;
				this.shields[i].z = Mth.sin(i * 1.570796F) * 7.5F;
				this.shields[i].xRot = -0.349065F;
			}

		}
	}

	@Override
	public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		Body.render(matrixStack, buffer, packedLight, packedOverlay);
	}

}
