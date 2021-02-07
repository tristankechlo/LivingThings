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
		textureWidth = 64;
		textureHeight = 64;

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 23.5F, 0.0F);
		Body.setTextureOffset(0, 38).addBox(-3.0F, -3.9F, -6.0F, 6.0F, 1.0F, 10.0F, 0.0F, false);
		Body.setTextureOffset(0, 51).addBox(-3.5F, -3.5F, -6.0F, 7.0F, 2.0F, 11.0F, 0.0F, false);
		Body.setTextureOffset(28, 50).addBox(-3.5F, -1.75F, -6.0F, 7.0F, 1.0F, 11.0F, 0.0F, false);

		Tail = new ModelRenderer(this);
		Tail.setRotationPoint(0.0F, -1.5F, 5.0F);
		Body.addChild(Tail);
		Tail.setTextureOffset(46, 14).addBox(-1.5F, -2.0F, 0.0F, 3.0F, 2.0F, 6.0F, 0.0F, false);

		Tail2 = new ModelRenderer(this);
		Tail2.setRotationPoint(0.0F, -0.25F, 6.0F);
		Tail.addChild(Tail2);
		Tail2.setTextureOffset(33, 41).addBox(-0.5F, -1.5F, 0.0F, 1.0F, 1.0F, 7.0F, 0.0F, false);

		LeftFlipper = new ModelRenderer(this);
		LeftFlipper.setRotationPoint(3.5F, -2.5F, -0.25F);
		Body.addChild(LeftFlipper);
		this.setRotationAngle(LeftFlipper, 0.0F, 0.0F, -0.0873F);
		LeftFlipper.setTextureOffset(0, 1).addBox(0.0F, -1.0F, -4.5F, 4.0F, 2.0F, 9.0F, 0.0F, false);

		LeftFlipper2 = new ModelRenderer(this);
		LeftFlipper2.setRotationPoint(4.0F, 0.0F, 0.0F);
		LeftFlipper.addChild(LeftFlipper2);
		this.setRotationAngle(LeftFlipper2, 0.0F, 0.0F, -0.0873F);
		LeftFlipper2.setTextureOffset(27, 3).addBox(0.0F, -1.0F, -4.25F, 2.0F, 1.0F, 8.0F, 0.0F, false);
		LeftFlipper2.setTextureOffset(0, 13).addBox(0.0F, -0.5F, -4.25F, 2.0F, 1.0F, 8.0F, 0.0F, false);
		LeftFlipper2.setTextureOffset(0, 23).addBox(2.0F, -1.0F, -4.0F, 3.0F, 1.0F, 7.0F, 0.0F, false);
		LeftFlipper2.setTextureOffset(48, 5).addBox(5.0F, -1.0F, -3.85F, 2.0F, 1.0F, 6.0F, 0.0F, false);
		LeftFlipper2.setTextureOffset(22, 16).addBox(7.0F, -1.0F, -3.7F, 1.0F, 1.0F, 5.0F, 0.0F, false);
		LeftFlipper2.setTextureOffset(22, 26).addBox(8.0F, -1.0F, -3.55F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		LeftFlipper2.setTextureOffset(35, 18).addBox(9.0F, -1.0F, -3.4F, 1.0F, 1.0F, 3.0F, 0.0F, false);

		RightFlipper = new ModelRenderer(this);
		RightFlipper.setRotationPoint(-3.5F, -2.5F, -0.25F);
		Body.addChild(RightFlipper);
		this.setRotationAngle(RightFlipper, 0.0F, 0.0F, 0.0873F);
		RightFlipper.setTextureOffset(0, 1).addBox(-4.0F, -1.0F, -4.5F, 4.0F, 2.0F, 9.0F, 0.0F, true);

		RightFlipper2 = new ModelRenderer(this);
		RightFlipper2.setRotationPoint(-4.0F, 0.0F, 0.0F);
		RightFlipper.addChild(RightFlipper2);
		this.setRotationAngle(RightFlipper2, 0.0F, 0.0F, 0.0873F);
		RightFlipper2.setTextureOffset(27, 3).addBox(-2.0F, -1.0F, -4.25F, 2.0F, 1.0F, 8.0F, 0.0F, true);
		RightFlipper2.setTextureOffset(0, 13).addBox(-2.0F, -0.5F, -4.25F, 2.0F, 1.0F, 8.0F, 0.0F, true);
		RightFlipper2.setTextureOffset(0, 23).addBox(-5.0F, -1.0F, -4.0F, 3.0F, 1.0F, 7.0F, 0.0F, true);
		RightFlipper2.setTextureOffset(48, 5).addBox(-7.0F, -1.0F, -3.85F, 2.0F, 1.0F, 6.0F, 0.0F, true);
		RightFlipper2.setTextureOffset(22, 16).addBox(-8.0F, -1.0F, -3.7F, 1.0F, 1.0F, 5.0F, 0.0F, true);
		RightFlipper2.setTextureOffset(22, 26).addBox(-9.0F, -1.0F, -3.55F, 1.0F, 1.0F, 4.0F, 0.0F, true);
		RightFlipper2.setTextureOffset(35, 18).addBox(-10.0F, -1.0F, -3.4F, 1.0F, 1.0F, 3.0F, 0.0F, true);

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, -2.25F, -5.75F);
		Body.addChild(Head);
		Head.setTextureOffset(30, 33).addBox(-3.0F, -1.25F, -1.0F, 6.0F, 2.0F, 1.0F, 0.0F, false);
		Head.setTextureOffset(45, 34).addBox(-3.0F, 0.25F, -1.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);

		eyes = new ModelRenderer(this);
		eyes.setRotationPoint(0.0F, 0.0131F, -0.8491F);
		Head.addChild(eyes);
		this.setRotationAngle(eyes, 0.1309F, 0.0F, 0.0F);
		eyes.setTextureOffset(22, 33).addBox(-2.6F, -0.25F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		eyes.setTextureOffset(15, 33).addBox(-2.6F, -0.75F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		eyes.setTextureOffset(8, 33).addBox(1.6F, -0.25F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		eyes.setTextureOffset(0, 33).addBox(1.6F, -0.75F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {

		this.Body.rotateAngleX = headPitch * 0.0174532925F;
		this.Body.rotateAngleY = netHeadYaw * 0.0174532925F;

		this.Tail.rotateAngleX = -0.5F * headPitch * 0.0174532925F;
		this.Tail2.rotateAngleX = -0.25F * headPitch * 0.0174532925F;

		// needed, so that the patchouli entry is always the same
		this.LeftFlipper.rotateAngleZ = -0.34906585F;
		this.LeftFlipper2.rotateAngleZ = -0.34906585F;
		this.RightFlipper.rotateAngleZ = 0.34906585F;
		this.RightFlipper2.rotateAngleZ = 0.34906585F;

		if (Entity.horizontalMag(entity.getMotion()) > 1.0E-7D) {
			this.Body.rotateAngleX += -0.05F + (-0.05F * MathHelper.cos(ageInTicks * 0.2F));

			// wings flapping
			this.LeftFlipper.rotateAngleZ = -0.6F * MathHelper.cos(ageInTicks * 0.15F);
			this.LeftFlipper2.rotateAngleZ = -0.6F * MathHelper.cos(ageInTicks * 0.15F);
			this.RightFlipper.rotateAngleZ = 0.6F * MathHelper.cos(ageInTicks * 0.15F);
			this.RightFlipper2.rotateAngleZ = 0.6F * MathHelper.cos(ageInTicks * 0.15F);
		}
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		Body.render(matrixStack, buffer, packedLight, packedOverlay);
	}

}
