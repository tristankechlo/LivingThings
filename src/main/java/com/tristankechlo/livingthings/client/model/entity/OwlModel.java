package com.tristankechlo.livingthings.client.model.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.tristankechlo.livingthings.client.model.AdvancedEntityModel;
import com.tristankechlo.livingthings.entities.OwlEntity;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OwlModel<T extends OwlEntity> extends AdvancedEntityModel<T> {

	private final ModelRenderer Body;
	private final ModelRenderer LeftWing;
	private final ModelRenderer RightWing;
	private final ModelRenderer LeftLeg;
	private final ModelRenderer LeftFoot;
	private final ModelRenderer RightToeLeftFoot;
	private final ModelRenderer LeftToeLeftFoot;
	private final ModelRenderer RightLeg;
	private final ModelRenderer RightFoot;
	private final ModelRenderer RightToeRightFoot;
	private final ModelRenderer LeftToeRightFoot;
	private final ModelRenderer Tail;
	private final ModelRenderer Head;
	private final ModelRenderer Beak;

	public OwlModel() {
		texWidth = 64;
		texHeight = 32;

		Body = new ModelRenderer(this);
		Body.setPos(0.0F, 21.5F, 1.0F);
		this.setRotationAngle(Body, 0.1745F, 0.0F, 0.0F);
		Body.texOffs(0, 17).addBox(-3.5F, -9.025F, -3.5F, 7.0F, 9.0F, 6.0F, 0.0F, false);

		LeftWing = new ModelRenderer(this);
		LeftWing.setPos(3.5F, -8.5792F, -0.4056F);
		Body.addChild(LeftWing);
		this.setRotationAngle(LeftWing, 0.0F, 0.0F, -0.1309F);
		LeftWing.texOffs(0, 1).addBox(0.0F, 0.0F, -3.0F, 1.0F, 8.0F, 6.0F, 0.0F, false);

		RightWing = new ModelRenderer(this);
		RightWing.setPos(-3.5F, -8.5792F, -0.4056F);
		Body.addChild(RightWing);
		this.setRotationAngle(RightWing, 0.0F, 0.0F, 0.1309F);
		RightWing.texOffs(15, 1).addBox(-1.0F, 0.0F, -3.0F, 1.0F, 8.0F, 6.0F, 0.0F, false);

		LeftLeg = new ModelRenderer(this);
		LeftLeg.setPos(2.0F, -1.9107F, -1.5699F);
		Body.addChild(LeftLeg);
		this.setRotationAngle(LeftLeg, -0.2618F, 0.0F, 0.0F);
		LeftLeg.texOffs(31, 1).addBox(-1.0F, 0.1224F, -0.5671F, 2.0F, 3.0F, 2.0F, 0.0F, false);

		LeftFoot = new ModelRenderer(this);
		LeftFoot.setPos(0.0667F, 3.0978F, 0.5107F);
		LeftLeg.addChild(LeftFoot);
		this.setRotationAngle(LeftFoot, 0.0873F, 0.0F, 0.0F);
		LeftFoot.texOffs(40, 3).addBox(-1.0667F, -0.0681F, -1.058F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		RightToeLeftFoot = new ModelRenderer(this);
		RightToeLeftFoot.setPos(-0.5667F, 0.4082F, -1.0494F);
		LeftFoot.addChild(RightToeLeftFoot);
		this.setRotationAngle(RightToeLeftFoot, 0.0F, 0.1745F, 0.0F);
		RightToeLeftFoot.texOffs(49, 4).addBox(-0.5F, -0.475F, -0.925F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		LeftToeLeftFoot = new ModelRenderer(this);
		LeftToeLeftFoot.setPos(0.4333F, 0.4332F, -1.0494F);
		LeftFoot.addChild(LeftToeLeftFoot);
		this.setRotationAngle(LeftToeLeftFoot, 0.0F, -0.1745F, 0.0F);
		LeftToeLeftFoot.texOffs(54, 4).addBox(-0.5F, -0.5F, -0.925F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		RightLeg = new ModelRenderer(this);
		RightLeg.setPos(-2.0F, -1.9107F, -1.5699F);
		Body.addChild(RightLeg);
		this.setRotationAngle(RightLeg, -0.2618F, 0.0F, 0.0F);
		RightLeg.texOffs(31, 7).addBox(-1.0F, 0.1224F, -0.5671F, 2.0F, 3.0F, 2.0F, 0.0F, false);

		RightFoot = new ModelRenderer(this);
		RightFoot.setPos(0.0667F, 3.0978F, 0.5107F);
		RightLeg.addChild(RightFoot);
		this.setRotationAngle(RightFoot, 0.0873F, 0.0F, 0.0F);
		RightFoot.texOffs(40, 9).addBox(-1.0667F, -0.0681F, -1.058F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		RightToeRightFoot = new ModelRenderer(this);
		RightToeRightFoot.setPos(-0.5667F, 0.4082F, -1.0494F);
		RightFoot.addChild(RightToeRightFoot);
		this.setRotationAngle(RightToeRightFoot, 0.0F, 0.1745F, 0.0F);
		RightToeRightFoot.texOffs(49, 10).addBox(-0.5F, -0.475F, -0.925F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		LeftToeRightFoot = new ModelRenderer(this);
		LeftToeRightFoot.setPos(0.4333F, 0.4332F, -1.0494F);
		RightFoot.addChild(LeftToeRightFoot);
		this.setRotationAngle(LeftToeRightFoot, 0.0F, -0.1745F, 0.0F);
		LeftToeRightFoot.texOffs(54, 10).addBox(-0.5F, -0.5F, -0.925F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		Tail = new ModelRenderer(this);
		Tail.setPos(0.0F, -0.7652F, 1.3264F);
		Body.addChild(Tail);
		this.setRotationAngle(Tail, -0.9599F, 0.0F, 0.0F);
		Tail.texOffs(31, 13).addBox(-1.0F, -0.5186F, -0.2595F, 2.0F, 1.0F, 3.0F, 0.0F, false);

		Head = new ModelRenderer(this);
		Head.setPos(0.0F, -8.5045F, -0.1394F);
		Body.addChild(Head);
		this.setRotationAngle(Head, -0.1745F, 0.0F, 0.0F);
		Head.texOffs(27, 20).addBox(-3.5F, -6.0F, -3.25F, 7.0F, 6.0F, 6.0F, 0.0F, false);

		Beak = new ModelRenderer(this);
		Beak.setPos(0.0F, -2.25F, -2.7F);
		Head.addChild(Beak);
		this.setRotationAngle(Beak, 0.4363F, 0.0F, 0.0F);
		Beak.texOffs(42, 14).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		if (this.young) {
			matrixStack.scale(0.5F, 0.5F, 0.5F);
			matrixStack.translate(0, 1.5D, 0);
		}
		Body.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {
		this.setRotationAngles(getOwlState(entity), limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
	}

	@Override
	public void prepareMobModel(T entity, float limbSwing, float limbSwingAmount, float partialTick) {
		this.setLivingAnimations(getOwlState(entity), entity);
	}

	public void setRotationAngles(OwlState state, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {

		this.Head.xRot = -0.174532F + headPitch * ((float) Math.PI / 180F);
		this.Head.yRot = netHeadYaw * ((float) Math.PI / 180F);
		this.Head.zRot = 0.0F;
		this.Head.x = 0.0F;
		this.Body.x = 0.0F;
		this.Tail.x = 0.0F;
		this.RightWing.x = -3.5F;
		this.LeftWing.x = 3.5F;
		switch (state) {
		case SITTING:
			break;
		case STANDING:
			this.LeftLeg.xRot += MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
			this.RightLeg.xRot += MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
			break;
		case FLYING:
		default:
			this.Tail.xRot = -0.959931F + MathHelper.cos(limbSwing * 0.6662F) * 0.3F * limbSwingAmount;
			this.LeftWing.zRot = -0.0873F - ageInTicks;
			this.RightWing.zRot = 0.0873F + ageInTicks;
			break;
		}

	}

	public void setLivingAnimations(OwlState state, OwlEntity owl) {
		this.Body.xRot = 0.174532F;

		this.LeftWing.xRot = 0F;
		this.LeftWing.yRot = 0F;
		this.LeftWing.zRot = -0.130899F;

		this.RightWing.xRot = 0F;
		this.RightWing.yRot = 0F;
		this.RightWing.zRot = 0.130899F;

		this.LeftLeg.xRot = -0.261799F;
		this.LeftLeg.y = -1.9107F;
		this.LeftLeg.zRot = 0F;

		this.RightLeg.xRot = -0.261799F;
		this.RightLeg.y = -1.9107F;
		this.RightLeg.zRot = 0F;

		switch (state) {
		case SITTING:
			this.Body.xRot = 0F;
			this.LeftLeg.xRot = 0F;
			this.RightLeg.xRot = 0F;
			break;
		case FLYING:
			if (Entity.getHorizontalDistanceSqr(owl.getDeltaMovement()) > 1.0E-7D) {
				this.LeftLeg.xRot += 0.6981317F;
				this.RightLeg.xRot += 0.6981317F;
			}
			break;
		case STANDING:
		default:
			break;
		}
	}

	private static OwlState getOwlState(OwlEntity owl) {
		if (owl.isInSittingPose() || owl.isSleeping()) {
			return OwlState.SITTING;
		} else {
			return owl.isFlying() ? OwlState.FLYING : OwlState.STANDING;
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static enum OwlState {
		FLYING, STANDING, SITTING;
	}
}
