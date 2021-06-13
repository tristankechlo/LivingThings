package com.tristankechlo.livingthings.client.model.entity;

import java.util.Random;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.tristankechlo.livingthings.client.model.AdvancedEntityModel;
import com.tristankechlo.livingthings.entities.ElephantEntity;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ElephantModel<T extends ElephantEntity> extends AdvancedEntityModel<T> {

	private float[][] currentTrunkAngle = { { 0F, 0F, 0F }, { 0F, 0F, 0F }, { 0F, 0F, 0F } };
	private float[][] targetTrunkAngle = { { 0.17F, 0.17F, 0.17F }, { 0.17F, 0.17F, 0.17F }, { 0.17F, 0.17F, 0.17F } };
	private boolean[][] countUpwards = { { true, true, true }, { true, true, true }, { true, true, true } };

	private final ModelRenderer Tusks;
	private final ModelRenderer LeftTusk;
	private final ModelRenderer LeftTuskTop;
	private final ModelRenderer LeftTuskMiddle;
	private final ModelRenderer LeftTuskBottom;
	private final ModelRenderer RightTusk;
	private final ModelRenderer RightTuskTop;
	private final ModelRenderer RightTuskMiddle;
	private final ModelRenderer RightTuskBottom;
	private final ModelRenderer Body;
	private final ModelRenderer Legs;
	private final ModelRenderer Front;
	private final ModelRenderer RightFrontLeg;
	private final ModelRenderer LeftFrontLeg;
	private final ModelRenderer Back;
	private final ModelRenderer RightBackLeg;
	private final ModelRenderer LeftBackLeg;
	private final ModelRenderer Head;
	private final ModelRenderer TrunkTop;
	private final ModelRenderer TrunkMiddle;
	private final ModelRenderer TrunkBottom;
	private final ModelRenderer Ears;
	private final ModelRenderer LeftEar;
	private final ModelRenderer RightEar;
	private final ModelRenderer Chests;
	private final ModelRenderer HolderLeft;
	private final ModelRenderer HolderRight;
	private final ModelRenderer Saddle;
	private final ModelRenderer SaddleRight;
	private final ModelRenderer SaddleLeft;

	public ElephantModel() {
		texWidth = 256;
		texHeight = 128;

		Tusks = new ModelRenderer(this);
		Tusks.setPos(0.0F, -12.0F, -19.0F);

		LeftTusk = new ModelRenderer(this);
		LeftTusk.setPos(7.0F, 6.0F, -10.0F);
		Tusks.addChild(LeftTusk);

		LeftTuskTop = new ModelRenderer(this);
		LeftTuskTop.setPos(0.0F, 0.0F, 0.0F);
		LeftTusk.addChild(LeftTuskTop);
		LeftTuskTop.texOffs(192, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);

		LeftTuskMiddle = new ModelRenderer(this);
		LeftTuskMiddle.setPos(0.0F, 10.0F, 0.0F);
		LeftTuskTop.addChild(LeftTuskMiddle);
		this.setRotationAngle(LeftTuskMiddle, -0.3054F, 0.0F, 0.0F);
		LeftTuskMiddle.texOffs(204, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		LeftTuskBottom = new ModelRenderer(this);
		LeftTuskBottom.setPos(0.0F, 6.0F, 0.0F);
		LeftTuskMiddle.addChild(LeftTuskBottom);
		this.setRotationAngle(LeftTuskBottom, -0.6545F, 0.0F, 0.0F);
		LeftTuskBottom.texOffs(215, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

		RightTusk = new ModelRenderer(this);
		RightTusk.setPos(-7.0F, 6.0F, -10.0F);
		Tusks.addChild(RightTusk);

		RightTuskTop = new ModelRenderer(this);
		RightTuskTop.setPos(14.0F, 0.0F, 0.0F);
		RightTusk.addChild(RightTuskTop);
		RightTuskTop.texOffs(228, 0).addBox(-15.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);

		RightTuskMiddle = new ModelRenderer(this);
		RightTuskMiddle.setPos(0.0F, 10.0F, 0.0F);
		RightTuskTop.addChild(RightTuskMiddle);
		this.setRotationAngle(RightTuskMiddle, -0.3054F, 0.0F, 0.0F);
		RightTuskMiddle.texOffs(238, 0).addBox(-15.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		RightTuskBottom = new ModelRenderer(this);
		RightTuskBottom.setPos(0.0F, 6.0F, 0.0F);
		RightTuskMiddle.addChild(RightTuskBottom);
		this.setRotationAngle(RightTuskBottom, -0.6545F, 0.0F, 0.0F);
		RightTuskBottom.texOffs(248, 0).addBox(-15.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

		Body = new ModelRenderer(this);
		Body.setPos(0.0F, 24.0F, 0.0F);
		Body.texOffs(0, 64).addBox(-11.0F, -42.0F, -20.0F, 22.0F, 24.0F, 40.0F, 0.0F, false);
		Body.texOffs(156, 93).addBox(-8.0F, -43.0F, -17.0F, 16.0F, 1.0F, 34.0F, 0.0F, false);

		Legs = new ModelRenderer(this);
		Legs.setPos(0.0F, 0.0F, 0.0F);
		Body.addChild(Legs);

		Front = new ModelRenderer(this);
		Front.setPos(0.0F, 0.0F, 0.0F);
		Legs.addChild(Front);

		RightFrontLeg = new ModelRenderer(this);
		RightFrontLeg.setPos(-8.0F, -19.0F, -16.0F);
		Front.addChild(RightFrontLeg);
		RightFrontLeg.texOffs(68, 0).addBox(-3.0F, 1.0F, -3.0F, 6.0F, 18.0F, 6.0F, 0.0F, false);

		LeftFrontLeg = new ModelRenderer(this);
		LeftFrontLeg.setPos(8.0F, -19.0F, -16.0F);
		Front.addChild(LeftFrontLeg);
		LeftFrontLeg.texOffs(95, 0).addBox(-3.0F, 1.0F, -3.0F, 6.0F, 18.0F, 6.0F, 0.0F, false);

		Back = new ModelRenderer(this);
		Back.setPos(0.0F, 0.0F, 0.0F);
		Legs.addChild(Back);

		RightBackLeg = new ModelRenderer(this);
		RightBackLeg.setPos(-8.0F, -19.0F, 16.0F);
		Back.addChild(RightBackLeg);
		RightBackLeg.texOffs(122, 0).addBox(-3.0F, 1.0F, -3.0F, 6.0F, 18.0F, 6.0F, 0.0F, false);

		LeftBackLeg = new ModelRenderer(this);
		LeftBackLeg.setPos(8.0F, -19.0F, 16.0F);
		Back.addChild(LeftBackLeg);
		LeftBackLeg.texOffs(149, 0).addBox(-3.0F, 1.0F, -3.0F, 6.0F, 18.0F, 6.0F, 0.0F, false);

		Head = new ModelRenderer(this);
		Head.setPos(0.0F, -36.0F, -20.0F);
		Body.addChild(Head);
		this.setRotationAngle(Head, -0.0436F, 0.0F, 0.0F);
		Head.texOffs(123, 45).addBox(-9.0F, -9.0F, -12.0F, 18.0F, 16.0F, 13.0F, 0.0F, false);

		TrunkTop = new ModelRenderer(this);
		TrunkTop.setPos(0.0F, 7.0F, -8.0F);
		Head.addChild(TrunkTop);
		this.setRotationAngle(TrunkTop, 0.0436F, 0.0F, 0.0F);
		TrunkTop.texOffs(0, 0).addBox(-4.0F, -0.1F, -4.0F, 8.0F, 10.0F, 8.0F, 0.0F, false);

		TrunkMiddle = new ModelRenderer(this);
		TrunkMiddle.setPos(0.0F, 10.0F, 0.0F);
		TrunkTop.addChild(TrunkMiddle);
		this.setRotationAngle(TrunkMiddle, 0.0436F, 0.0F, 0.0F);
		TrunkMiddle.texOffs(0, 26).addBox(-3.0F, -0.2F, -3.0F, 6.0F, 8.0F, 6.0F, 0.0F, false);

		TrunkBottom = new ModelRenderer(this);
		TrunkBottom.setPos(0.0F, 7.0F, 0.0F);
		TrunkMiddle.addChild(TrunkBottom);
		this.setRotationAngle(TrunkBottom, 0.0873F, 0.0F, 0.0F);
		TrunkBottom.texOffs(0, 44).addBox(-2.0F, 0.7F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, false);

		Ears = new ModelRenderer(this);
		Ears.setPos(0.0F, 0.0F, 0.0F);
		Head.addChild(Ears);

		LeftEar = new ModelRenderer(this);
		LeftEar.setPos(9.0F, -1.0F, -6.0F);
		Ears.addChild(LeftEar);
		this.setRotationAngle(LeftEar, 0.1309F, 0.48F, 0.0F);
		LeftEar.texOffs(200, 37).addBox(0.0F, -8.0F, 0.0F, 1.0F, 12.0F, 8.0F, 0.0F, false);

		RightEar = new ModelRenderer(this);
		RightEar.setPos(-9.0F, -1.0F, -6.0F);
		Ears.addChild(RightEar);
		this.setRotationAngle(RightEar, 0.1309F, -0.48F, 0.0F);
		RightEar.texOffs(200, 37).addBox(-1.0F, -8.0F, 0.0F, 1.0F, 12.0F, 8.0F, 0.0F, false);

		Chests = new ModelRenderer(this);
		Chests.setPos(0.0F, 24.0F, 0.0F);
		Chests.texOffs(54, 38).addBox(11.0F, -37.0F, 9.5F, 2.0F, 8.0F, 8.0F, 0.0F, false);
		Chests.texOffs(54, 38).addBox(-13.0F, -37.0F, 9.5F, 2.0F, 8.0F, 8.0F, 0.0F, true);
		Chests.texOffs(136, 85).addBox(10.2F, -42.475F, 11.5F, 1.0F, 6.0F, 4.0F, 0.0F, false);
		Chests.texOffs(136, 85).addBox(-11.2F, -42.475F, 11.5F, 1.0F, 6.0F, 4.0F, 0.0F, false);
		Chests.texOffs(125, 92).addBox(-8.5F, -43.475F, 11.5F, 17.0F, 1.0F, 4.0F, 0.0F, false);

		HolderLeft = new ModelRenderer(this);
		HolderLeft.setPos(9.6F, -42.5F, 13.5F);
		Chests.addChild(HolderLeft);
		this.setRotationAngle(HolderLeft, 0.0F, 0.0F, 0.3491F);
		HolderLeft.texOffs(134, 89).addBox(-1.5F, -0.5F, -2.0F, 3.0F, 2.0F, 4.0F, 0.0F, false);

		HolderRight = new ModelRenderer(this);
		HolderRight.setPos(-9.6F, -42.5F, 13.5F);
		Chests.addChild(HolderRight);
		this.setRotationAngle(HolderRight, 0.0F, 0.0F, -0.3491F);
		HolderRight.texOffs(134, 89).addBox(-1.5F, -0.5F, -2.0F, 3.0F, 2.0F, 4.0F, 0.0F, false);

		Saddle = new ModelRenderer(this);
		Saddle.setPos(0.0F, 24.0F, 0.0F);
		Saddle.texOffs(114, 84).addBox(-6.5F, -44.0F, -7.0F, 13.0F, 1.0F, 12.0F, 0.0F, false);
		Saddle.texOffs(124, 96).addBox(-6.0F, -45.0F, 3.0F, 12.0F, 1.0F, 2.0F, 0.0F, false);
		Saddle.texOffs(136, 85).addBox(-11.2F, -42.475F, -2.0F, 1.0F, 10.0F, 2.0F, 0.0F, false);
		Saddle.texOffs(136, 85).addBox(10.2F, -42.475F, -2.0F, 1.0F, 10.0F, 2.0F, 0.0F, false);
		Saddle.texOffs(144, 90).addBox(-1.5F, -46.0F, -7.0F, 3.0F, 2.0F, 2.0F, 0.0F, false);
		Saddle.texOffs(245, 43).addBox(10.4F, -32.5F, -2.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		Saddle.texOffs(245, 43).addBox(-11.4F, -32.5F, -2.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		Saddle.texOffs(245, 43).addBox(10.4F, -32.0F, -2.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		Saddle.texOffs(245, 43).addBox(-11.4F, -32.0F, -2.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);

		SaddleRight = new ModelRenderer(this);
		SaddleRight.setPos(-8.625F, -43.175F, -1.5F);
		Saddle.addChild(SaddleRight);
		this.setRotationAngle(SaddleRight, 0.0F, 0.0F, -0.3054F);
		SaddleRight.texOffs(130, 84).addBox(-2.5492F, -0.0726F, -0.5F, 5.0F, 1.0F, 2.0F, 0.0F, false);

		SaddleLeft = new ModelRenderer(this);
		SaddleLeft.setPos(8.725F, -43.15F, -1.5F);
		Saddle.addChild(SaddleLeft);
		this.setRotationAngle(SaddleLeft, 0.0F, 0.0F, 0.3054F);
		SaddleLeft.texOffs(130, 84).addBox(-2.5492F, -0.0726F, -0.5F, 5.0F, 1.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {

		if (this.young) {
			matrixStack.scale(0.6F, 0.6F, 0.6F);
			matrixStack.translate(0, 1, 0);
		} else {
			Tusks.render(matrixStack, buffer, packedLight, packedOverlay);
		}
		Body.render(matrixStack, buffer, packedLight, packedOverlay);
		Chests.render(matrixStack, buffer, packedLight, packedOverlay);
		Saddle.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	@Override
	public void setupAnim(ElephantEntity elephant, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {

		this.Head.xRot = headPitch * 0.0174532925F;
		this.Tusks.xRot = headPitch * 0.0174532925F;
		this.Head.yRot = netHeadYaw * 0.0174532925F;
		this.Tusks.yRot = netHeadYaw * 0.0174532925F;

		this.walk(RightFrontLeg, LeftFrontLeg, RightBackLeg, LeftBackLeg, limbSwing, limbSwingAmount);

		this.TrunkTop.xRot = this.currentTrunkAngle[0][0];
		this.TrunkTop.yRot = this.currentTrunkAngle[0][1];
		this.TrunkTop.zRot = this.currentTrunkAngle[0][2];

		this.TrunkMiddle.xRot = this.currentTrunkAngle[1][0];
		this.TrunkMiddle.yRot = this.currentTrunkAngle[1][1];
		this.TrunkMiddle.zRot = this.currentTrunkAngle[1][2];

		this.TrunkBottom.xRot = this.currentTrunkAngle[2][0];
		this.TrunkBottom.yRot = this.currentTrunkAngle[2][1];
		this.TrunkBottom.zRot = this.currentTrunkAngle[2][2];

	}

	@Override
	public void prepareMobModel(ElephantEntity elephant, float limbSwing, float limbSwingAmount, float partialTick) {
		if (partialTick > 0.5F) {
			setTrunkAngle();
		}
		int i = elephant.getAttackTimer();
		if (i > 0) {
			this.Head.xRot = 1.7F * MathHelper.triangleWave((float) i - partialTick, 10.0F);
			this.Tusks.xRot = 1.7F * MathHelper.triangleWave((float) i - partialTick, 10.0F);
		}
		this.Chests.visible = elephant.hasChest();
		this.Saddle.visible = elephant.isSaddled();

	}

	private void setTrunkAngle() {
		float[] offset = { 0.0025F, 0.001F, 0.0025F };

		for (int part = 0; part < this.currentTrunkAngle.length; part++) {
			for (int axis = 0; axis < this.currentTrunkAngle[part].length; axis++) {

				if (this.countUpwards[part][axis] == true) {

					this.currentTrunkAngle[part][axis] += offset[axis];

					if (this.currentTrunkAngle[part][axis] > this.targetTrunkAngle[part][axis]) {
						this.targetTrunkAngle[part][axis] = (float) (0 - ((new Random().nextFloat() * 0.1F) + 0.1F));
						this.countUpwards[part][axis] = false;
					}

					continue;
				} else if (this.countUpwards[part][axis] == false) {

					this.currentTrunkAngle[part][axis] -= offset[axis];

					if (this.currentTrunkAngle[part][axis] < this.targetTrunkAngle[part][axis]) {
						this.targetTrunkAngle[part][axis] = (float) ((new Random().nextFloat() * 0.1F) + 0.1F);
						this.countUpwards[part][axis] = true;
					}

					continue;
				}
			}
		}
	}

}
