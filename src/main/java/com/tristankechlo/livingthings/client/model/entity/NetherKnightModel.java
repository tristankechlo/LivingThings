package com.tristankechlo.livingthings.client.model.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.tristankechlo.livingthings.client.model.AdvancedEntityModel;
import com.tristankechlo.livingthings.entities.NetherKnightEntity;

import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NetherKnightModel<T extends NetherKnightEntity> extends AdvancedEntityModel<T> implements IHasArm {

	private final ModelRenderer Body;
	private final ModelRenderer Head;
	private final ModelRenderer Horn1;
	private final ModelRenderer Horn2;
	private final ModelRenderer RightArm;
	private final ModelRenderer RightShoulder;
	private final ModelRenderer LeftArm;
	private final ModelRenderer LeftShoulder;
	private final ModelRenderer RightLeg;
	private final ModelRenderer LeftLeg;

	public NetherKnightModel() {
		texWidth = 64;
		texHeight = 64;

		Body = new ModelRenderer(this);
		Body.setPos(0.0F, 0.0F, 0.0F);
		Body.texOffs(0, 53).addBox(-6.0F, -3.8F, -2.0F, 12.0F, 7.0F, 4.0F, 0.25F, false);
		Body.texOffs(0, 41).addBox(-4.5F, 3.0F, -2.0F, 9.0F, 8.0F, 4.0F, 0.0F, false);

		Head = new ModelRenderer(this);
		Head.setPos(0.0F, -4.0F, 0.0F);
		Body.addChild(Head);
		Head.texOffs(0, 0).addBox(-4.0F, -9.0F, -4.0F, 8.0F, 9.0F, 8.0F, 0.0F, false);
		Head.texOffs(20, 39).addBox(-3.0F, -5.75F, -3.975F, 6.0F, 2.0F, 0.0F, 0.0F, false);
		Head.texOffs(0, 0).addBox(-1.0F, -3.5F, -5.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);

		Horn1 = new ModelRenderer(this);
		Horn1.setPos(0.0F, 0.0F, 0.0F);
		Head.addChild(Horn1);
		setRotationAngle(Horn1, 0.0F, 0.3927F, 0.0F);
		Horn1.texOffs(0, 19).addBox(-7.0F, -14.0F, 0.0F, 14.0F, 9.0F, 0.0F, 0.0F, false);

		Horn2 = new ModelRenderer(this);
		Horn2.setPos(0.0F, 0.0F, 0.0F);
		Head.addChild(Horn2);
		setRotationAngle(Horn2, 0.0F, -0.3927F, 0.0F);
		Horn2.texOffs(0, 19).addBox(-7.0F, -14.0F, 0.0F, 14.0F, 9.0F, 0.0F, 0.0F, true);

		RightArm = new ModelRenderer(this);
		RightArm.setPos(-8.0F, -2.0F, 0.0F);
		Body.addChild(RightArm);
		RightArm.texOffs(48, 46).addBox(-2.15F, -1.0F, -2.0F, 4.0F, 14.0F, 4.0F, -0.25F, true);
		RightArm.texOffs(48, 11).addBox(-2.15F, -1.0F, -2.0F, 4.0F, 14.0F, 4.0F, -0.5F, false);
		RightArm.texOffs(48, 37).addBox(-2.15F, 8.9F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F, true);

		RightShoulder = new ModelRenderer(this);
		RightShoulder.setPos(-0.25F, 0.0F, 0.0F);
		RightArm.addChild(RightShoulder);
		setRotationAngle(RightShoulder, 0.0F, 0.0F, -0.1745F);
		RightShoulder.texOffs(48, 29).addBox(-1.9F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.6F, true);

		LeftArm = new ModelRenderer(this);
		LeftArm.setPos(8.0F, -2.0F, 0.0F);
		Body.addChild(LeftArm);
		LeftArm.texOffs(48, 46).addBox(-1.85F, -1.0F, -2.0F, 4.0F, 14.0F, 4.0F, -0.25F, false);
		LeftArm.texOffs(48, 11).addBox(-1.85F, -1.0F, -2.0F, 4.0F, 14.0F, 4.0F, -0.5F, false);
		LeftArm.texOffs(48, 37).addBox(-1.85F, 8.9F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F, false);

		LeftShoulder = new ModelRenderer(this);
		LeftShoulder.setPos(-0.25F, 0.0F, 0.0F);
		LeftArm.addChild(LeftShoulder);
		setRotationAngle(LeftShoulder, 0.0F, 0.0F, 0.1745F);
		LeftShoulder.texOffs(48, 29).addBox(-1.6F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.6F, false);

		RightLeg = new ModelRenderer(this);
		RightLeg.setPos(-1.9F, 11.0F, 0.0F);
		Body.addChild(RightLeg);
		RightLeg.texOffs(32, 47).addBox(-2.3F, -2.1F, -2.0F, 4.0F, 13.0F, 4.0F, -0.1F, false);
		RightLeg.texOffs(32, 30).addBox(-2.3F, -2.1F, -2.0F, 4.0F, 13.0F, 4.0F, -0.2F, false);
		RightLeg.texOffs(0, 33).addBox(-2.3F, 8.9F, -2.0F, 4.0F, 4.0F, 4.0F, 0.1F, false);

		LeftLeg = new ModelRenderer(this);
		LeftLeg.setPos(1.9F, 12.0F, 0.0F);
		Body.addChild(LeftLeg);
		LeftLeg.texOffs(32, 47).addBox(-1.7F, -3.1F, -2.0F, 4.0F, 13.0F, 4.0F, -0.1F, true);
		LeftLeg.texOffs(32, 30).addBox(-1.7F, -3.1F, -2.0F, 4.0F, 13.0F, 4.0F, -0.2F, true);
		LeftLeg.texOffs(0, 33).addBox(-1.7F, 7.9F, -2.0F, 4.0F, 4.0F, 4.0F, 0.1F, true);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {
		this.LeftArm.xRot = 0;
		this.LeftArm.zRot = 0;
		this.RightArm.xRot = 0;
		this.RightArm.zRot = 0;
		this.walking1(LeftLeg, limbSwing, limbSwingAmount);
		this.walking2(RightLeg, limbSwing, limbSwingAmount);
		this.walking2(LeftArm, limbSwing, limbSwingAmount);
		this.walking1(RightArm, limbSwing, limbSwingAmount);
		this.setupAttackAnimation(entity, ageInTicks);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		Body.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	@Override
	public void translateToHand(HandSide handSide, MatrixStack stack) {
		this.getArm(handSide).translateAndRotate(stack);
	}

	private ModelRenderer getArm(HandSide handSide) {
		return handSide == HandSide.LEFT ? this.LeftArm : this.RightArm;
	}

	private void setupAttackAnimation(T entity, float ageInTicks) {
		if (this.attackTime > 0.0F) {
			float f = MathHelper.sin(attackTime * (float) Math.PI);
			float f1 = MathHelper.sin((1.0F - (1.0F - attackTime) * (1.0F - attackTime)) * (float) Math.PI);
			LeftArm.zRot = 0.0F;
			RightArm.zRot = 0.0F;
			LeftArm.yRot = 0.15707964F;
			RightArm.yRot = -0.15707964F;
			if (entity.getMainArm() == HandSide.RIGHT) {
				RightArm.xRot = -MathHelper.cos(ageInTicks * 0.1F) * 0.5F;
				RightArm.xRot -= f * 1.2F + f1 * 0.4F;
			} else {
				LeftArm.xRot = -MathHelper.cos(ageInTicks * 0.1F) * 0.5F;
				LeftArm.xRot -= f * 1.2F + f1 * 0.4F;
			}
		}
	}

}