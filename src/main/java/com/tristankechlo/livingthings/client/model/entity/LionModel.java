package com.tristankechlo.livingthings.client.model.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.tristankechlo.livingthings.client.model.AdvancedEntityModel;
import com.tristankechlo.livingthings.entities.LionEntity;
import com.tristankechlo.livingthings.entities.misc.IGenderedMob.Gender;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LionModel<T extends LionEntity> extends AdvancedEntityModel<T> {

	private final ModelRenderer Body;
	private final ModelRenderer Legs;
	private final ModelRenderer FrontLeftLeg;
	private final ModelRenderer FrontRightLeg;
	private final ModelRenderer BackLeftLeg;
	private final ModelRenderer BackRightLeg;
	private final ModelRenderer Tail;
	private final ModelRenderer Head;
	private final ModelRenderer Ears;
	private final ModelRenderer LeftEar;
	private final ModelRenderer RightEar;
	private final ModelRenderer Mane;

	public LionModel() {
		textureWidth = 128;
		textureHeight = 64;

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 6.0F, 0.0F);
		Body.setTextureOffset(0, 23).addBox(-6.0F, -4.0F, -14.0F, 12.0F, 12.0F, 29.0F, 0.0F, false);

		Legs = new ModelRenderer(this);
		Legs.setRotationPoint(0.0F, 18.0F, 0.0F);
		Body.addChild(Legs);

		FrontLeftLeg = new ModelRenderer(this);
		FrontLeftLeg.setRotationPoint(4.0F, -10.0F, -12.0F);
		Legs.addChild(FrontLeftLeg);
		FrontLeftLeg.setTextureOffset(0, 17).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, 0.0F, false);

		FrontRightLeg = new ModelRenderer(this);
		FrontRightLeg.setRotationPoint(-4.0F, -10.0F, -12.0F);
		Legs.addChild(FrontRightLeg);
		FrontRightLeg.setTextureOffset(0, 17).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, 0.0F, false);

		BackLeftLeg = new ModelRenderer(this);
		BackLeftLeg.setRotationPoint(4.0F, -10.0F, 12.0F);
		Legs.addChild(BackLeftLeg);
		BackLeftLeg.setTextureOffset(0, 17).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, 0.0F, false);

		BackRightLeg = new ModelRenderer(this);
		BackRightLeg.setRotationPoint(-4.0F, -10.0F, 12.0F);
		Legs.addChild(BackRightLeg);
		BackRightLeg.setTextureOffset(0, 17).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, 0.0F, false);

		Tail = new ModelRenderer(this);
		Tail.setRotationPoint(0.0F, -4.0F, 14.5F);
		Body.addChild(Tail);
		this.setRotationAngle(Tail, -2.7489F, 0.0F, 0.0F);
		Tail.setTextureOffset(61, 21).addBox(-1.0F, -12.5F, 0.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, 0.0F, -14.0F);
		Body.addChild(Head);
		Head.setTextureOffset(88, 44).addBox(-6.0F, -7.0F, -8.0F, 12.0F, 12.0F, 8.0F, 0.0F, false);
		Head.setTextureOffset(30, 1).addBox(-3.0F, -2.0F, -14.0F, 6.0F, 7.0F, 6.0F, 0.0F, false);

		Ears = new ModelRenderer(this);
		Ears.setRotationPoint(0.0F, -7.0F, -1.0F);
		Head.addChild(Ears);

		LeftEar = new ModelRenderer(this);
		LeftEar.setRotationPoint(5.0F, 0.0F, -1.0F);
		Ears.addChild(LeftEar);
		this.setRotationAngle(LeftEar, 0.0F, 0.0F, 0.1745F);
		LeftEar.setTextureOffset(11, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);

		RightEar = new ModelRenderer(this);
		RightEar.setRotationPoint(-5.0F, 0.0F, -1.0F);
		Ears.addChild(RightEar);
		this.setRotationAngle(RightEar, 0.0F, 0.0F, -0.1745F);
		RightEar.setTextureOffset(0, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);

		Mane = new ModelRenderer(this);
		Mane.setRotationPoint(0.0F, 6.0F, -14.0F);
		Mane.setTextureOffset(82, 0).addBox(-7.0F, -9.0F, -2.0F, 14.0F, 15.0F, 9.0F, 0.0F, true);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha) {

		if (this.isChild) {
			matrixStackIn.scale(0.6F, 0.6F, 0.6F);
			matrixStackIn.translate(0, 1, 0);
		} else {
			Mane.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
		}
		Body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {

		this.Head.rotateAngleX = headPitch * 0.0174532925F;
		this.Head.rotateAngleY = (netHeadYaw / 3.75F) * 0.0174532925F;

		this.walk(FrontRightLeg, FrontLeftLeg, BackRightLeg, BackLeftLeg, limbSwing, limbSwingAmount);

		this.Tail.rotateAngleZ = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.75F * limbSwingAmount;
	}

	@Override
	public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
		if (entityIn.getGender() == Gender.MALE) {
			this.Mane.showModel = true;
		} else {
			this.Mane.showModel = false;
		}
	}

}
