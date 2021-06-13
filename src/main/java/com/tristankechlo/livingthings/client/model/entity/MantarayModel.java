package com.tristankechlo.livingthings.client.model.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.tristankechlo.livingthings.client.model.AdvancedEntityModel;
import com.tristankechlo.livingthings.entities.MantarayEntity;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MantarayModel<T extends MantarayEntity> extends AdvancedEntityModel<T> {

	private final ModelRenderer Body;
	private final ModelRenderer Tail;
	private final ModelRenderer Tail2;
	private final ModelRenderer LeftFlipper;
	private final ModelRenderer LeftFlipper2;
	private final ModelRenderer RightFlipper;
	private final ModelRenderer RightFlipper2;
	private final ModelRenderer Head;
	private final ModelRenderer eyes;

	public MantarayModel() {
		texWidth = 64;
		texHeight = 64;

		Body = new ModelRenderer(this);
		Body.setPos(0.0F, 23.5F, 0.0F);
		Body.texOffs(0, 38).addBox(-3.0F, -3.9F, -6.0F, 6.0F, 1.0F, 10.0F, 0.0F, false);
		Body.texOffs(0, 51).addBox(-3.5F, -3.5F, -6.0F, 7.0F, 2.0F, 11.0F, 0.0F, false);
		Body.texOffs(28, 50).addBox(-3.5F, -1.75F, -6.0F, 7.0F, 1.0F, 11.0F, 0.0F, false);

		Tail = new ModelRenderer(this);
		Tail.setPos(0.0F, -1.5F, 5.0F);
		Body.addChild(Tail);
		Tail.texOffs(46, 14).addBox(-1.5F, -2.0F, 0.0F, 3.0F, 2.0F, 6.0F, 0.0F, false);

		Tail2 = new ModelRenderer(this);
		Tail2.setPos(0.0F, -0.25F, 6.0F);
		Tail.addChild(Tail2);
		Tail2.texOffs(33, 41).addBox(-0.5F, -1.5F, 0.0F, 1.0F, 1.0F, 7.0F, 0.0F, false);

		LeftFlipper = new ModelRenderer(this);
		LeftFlipper.setPos(3.5F, -2.5F, -0.25F);
		Body.addChild(LeftFlipper);
		this.setRotationAngle(LeftFlipper, 0.0F, 0.0F, -0.0873F);
		LeftFlipper.texOffs(0, 1).addBox(0.0F, -1.0F, -4.5F, 4.0F, 2.0F, 9.0F, 0.0F, false);

		LeftFlipper2 = new ModelRenderer(this);
		LeftFlipper2.setPos(4.0F, 0.0F, 0.0F);
		LeftFlipper.addChild(LeftFlipper2);
		this.setRotationAngle(LeftFlipper2, 0.0F, 0.0F, -0.0873F);
		LeftFlipper2.texOffs(27, 3).addBox(0.0F, -1.0F, -4.25F, 2.0F, 1.0F, 8.0F, 0.0F, false);
		LeftFlipper2.texOffs(0, 13).addBox(0.0F, -0.5F, -4.25F, 2.0F, 1.0F, 8.0F, 0.0F, false);
		LeftFlipper2.texOffs(0, 23).addBox(2.0F, -1.0F, -4.0F, 3.0F, 1.0F, 7.0F, 0.0F, false);
		LeftFlipper2.texOffs(48, 5).addBox(5.0F, -1.0F, -3.85F, 2.0F, 1.0F, 6.0F, 0.0F, false);
		LeftFlipper2.texOffs(22, 16).addBox(7.0F, -1.0F, -3.7F, 1.0F, 1.0F, 5.0F, 0.0F, false);
		LeftFlipper2.texOffs(22, 26).addBox(8.0F, -1.0F, -3.55F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		LeftFlipper2.texOffs(35, 18).addBox(9.0F, -1.0F, -3.4F, 1.0F, 1.0F, 3.0F, 0.0F, false);

		RightFlipper = new ModelRenderer(this);
		RightFlipper.setPos(-3.5F, -2.5F, -0.25F);
		Body.addChild(RightFlipper);
		this.setRotationAngle(RightFlipper, 0.0F, 0.0F, 0.0873F);
		RightFlipper.texOffs(0, 1).addBox(-4.0F, -1.0F, -4.5F, 4.0F, 2.0F, 9.0F, 0.0F, true);

		RightFlipper2 = new ModelRenderer(this);
		RightFlipper2.setPos(-4.0F, 0.0F, 0.0F);
		RightFlipper.addChild(RightFlipper2);
		this.setRotationAngle(RightFlipper2, 0.0F, 0.0F, 0.0873F);
		RightFlipper2.texOffs(27, 3).addBox(-2.0F, -1.0F, -4.25F, 2.0F, 1.0F, 8.0F, 0.0F, true);
		RightFlipper2.texOffs(0, 13).addBox(-2.0F, -0.5F, -4.25F, 2.0F, 1.0F, 8.0F, 0.0F, true);
		RightFlipper2.texOffs(0, 23).addBox(-5.0F, -1.0F, -4.0F, 3.0F, 1.0F, 7.0F, 0.0F, true);
		RightFlipper2.texOffs(48, 5).addBox(-7.0F, -1.0F, -3.85F, 2.0F, 1.0F, 6.0F, 0.0F, true);
		RightFlipper2.texOffs(22, 16).addBox(-8.0F, -1.0F, -3.7F, 1.0F, 1.0F, 5.0F, 0.0F, true);
		RightFlipper2.texOffs(22, 26).addBox(-9.0F, -1.0F, -3.55F, 1.0F, 1.0F, 4.0F, 0.0F, true);
		RightFlipper2.texOffs(35, 18).addBox(-10.0F, -1.0F, -3.4F, 1.0F, 1.0F, 3.0F, 0.0F, true);

		Head = new ModelRenderer(this);
		Head.setPos(0.0F, -2.25F, -5.75F);
		Body.addChild(Head);
		Head.texOffs(30, 33).addBox(-3.0F, -1.25F, -1.0F, 6.0F, 2.0F, 1.0F, 0.0F, false);
		Head.texOffs(45, 34).addBox(-3.0F, 0.25F, -1.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);

		eyes = new ModelRenderer(this);
		eyes.setPos(0.0F, 0.0131F, -0.8491F);
		Head.addChild(eyes);
		this.setRotationAngle(eyes, 0.1309F, 0.0F, 0.0F);
		eyes.texOffs(22, 33).addBox(-2.6F, -0.25F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		eyes.texOffs(15, 33).addBox(-2.6F, -0.75F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		eyes.texOffs(8, 33).addBox(1.6F, -0.25F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		eyes.texOffs(0, 33).addBox(1.6F, -0.75F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {

		this.Body.xRot = headPitch * 0.0174532925F;
		this.Body.yRot = netHeadYaw * 0.0174532925F;

		this.Tail.xRot = -0.5F * headPitch * 0.0174532925F;
		this.Tail2.xRot = -0.25F * headPitch * 0.0174532925F;

		// needed, so that the patchouli entry is always the same
		this.LeftFlipper.zRot = -0.34906585F;
		this.LeftFlipper2.zRot = -0.34906585F;
		this.RightFlipper.zRot = 0.34906585F;
		this.RightFlipper2.zRot = 0.34906585F;

		if (Entity.getHorizontalDistanceSqr(entity.getDeltaMovement()) > 1.0E-7D) {
			this.Body.xRot += -0.05F + (-0.05F * MathHelper.cos(ageInTicks * 0.2F));

			// wings flapping
			this.LeftFlipper.zRot = -0.6F * MathHelper.cos(ageInTicks * 0.15F);
			this.LeftFlipper2.zRot = -0.6F * MathHelper.cos(ageInTicks * 0.15F);
			this.RightFlipper.zRot = 0.6F * MathHelper.cos(ageInTicks * 0.15F);
			this.RightFlipper2.zRot = 0.6F * MathHelper.cos(ageInTicks * 0.15F);
		}
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		Body.render(matrixStack, buffer, packedLight, packedOverlay);
	}

}
