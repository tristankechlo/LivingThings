package com.tristankechlo.livingthings.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.tristankechlo.livingthings.entities.ElephantEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ElephantModel<T extends ElephantEntity> extends EntityModel<T> {
	private final ModelRenderer Body;
	private final ModelRenderer Legs;
	private final ModelRenderer Front;
	private final ModelRenderer RightFrontLeg;
	private final ModelRenderer LeftFrontLeg;
	private final ModelRenderer Back;
	private final ModelRenderer RightBackLeg;
	private final ModelRenderer LeftBackLeg;
	private final ModelRenderer Head;
	private final ModelRenderer Trunk;
	private final ModelRenderer TrunkMiddle;
	private final ModelRenderer TrunkBottom;
	private final ModelRenderer Ears;
	private final ModelRenderer LeftEar;
	private final ModelRenderer RightEar;
	private final ModelRenderer Tusks;
	private final ModelRenderer LeftTusk;
	private final ModelRenderer LeftTuskTop;
	private final ModelRenderer LeftTuskMiddle;
	private final ModelRenderer LeftTuskBottom;
	private final ModelRenderer RightTusk;
	private final ModelRenderer RightTuskTop;
	private final ModelRenderer RightTuskMiddle;
	private final ModelRenderer RightTuskBottom;

	public ElephantModel() {
		textureWidth = 256;
		textureHeight = 256;

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 24.0F, 0.0F);
		Body.setTextureOffset(21, 136).addBox(-11.0F, -42.0F, -20.0F, 22.0F, 24.0F, 40.0F, 0.0F, false);
		Body.setTextureOffset(0, 0).addBox(-8.0F, -43.0F, -17.0F, 16.0F, 1.0F, 34.0F, 0.0F, false);

		Legs = new ModelRenderer(this);
		Legs.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.addChild(Legs);

		Front = new ModelRenderer(this);
		Front.setRotationPoint(0.0F, 0.0F, 0.0F);
		Legs.addChild(Front);

		RightFrontLeg = new ModelRenderer(this);
		RightFrontLeg.setRotationPoint(-8.0F, -19.0F, -16.0F);
		Front.addChild(RightFrontLeg);
		RightFrontLeg.setTextureOffset(16, 73).addBox(-3.0F, 1.0F, -3.0F, 6.0F, 18.0F, 6.0F, 0.0F, false);

		LeftFrontLeg = new ModelRenderer(this);
		LeftFrontLeg.setRotationPoint(8.0F, -19.0F, -16.0F);
		Front.addChild(LeftFrontLeg);
		LeftFrontLeg.setTextureOffset(57, 144).addBox(-3.0F, 1.0F, -3.0F, 6.0F, 18.0F, 6.0F, 0.0F, false);

		Back = new ModelRenderer(this);
		Back.setRotationPoint(0.0F, 0.0F, 0.0F);
		Legs.addChild(Back);

		RightBackLeg = new ModelRenderer(this);
		RightBackLeg.setRotationPoint(-8.0F, -19.0F, 16.0F);
		Back.addChild(RightBackLeg);
		RightBackLeg.setTextureOffset(160, 45).addBox(-3.0F, 1.0F, -3.0F, 6.0F, 18.0F, 6.0F, 0.0F, false);

		LeftBackLeg = new ModelRenderer(this);
		LeftBackLeg.setRotationPoint(8.0F, -19.0F, 16.0F);
		Back.addChild(LeftBackLeg);
		LeftBackLeg.setTextureOffset(108, 115).addBox(-3.0F, 1.0F, -3.0F, 6.0F, 18.0F, 6.0F, 0.0F, false);

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, -36.0F, -19.0F);
		Body.addChild(Head);
		this.setRotationAngleForModel(Head, -0.0436F, 0.0F, 0.0F);
		Head.setTextureOffset(116, 144).addBox(-9.0F, -9.0F, -12.0F, 18.0F, 16.0F, 13.0F, 0.0F, false);

		Trunk = new ModelRenderer(this);
		Trunk.setRotationPoint(0.0F, 7.0F, -8.0F);
		Head.addChild(Trunk);
		this.setRotationAngleForModel(Trunk, 0.0436F, 0.0F, 0.0F);
		Trunk.setTextureOffset(0, 0).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 10.0F, 8.0F, 0.0F, false);

		TrunkMiddle = new ModelRenderer(this);
		TrunkMiddle.setRotationPoint(0.0F, 10.0F, 0.0F);
		Trunk.addChild(TrunkMiddle);
		this.setRotationAngleForModel(TrunkMiddle, 0.0436F, 0.0F, 0.0F);
		TrunkMiddle.setTextureOffset(0, 0).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 8.0F, 6.0F, 0.0F, false);

		TrunkBottom = new ModelRenderer(this);
		TrunkBottom.setRotationPoint(0.0F, 7.0F, 0.0F);
		TrunkMiddle.addChild(TrunkBottom);
		this.setRotationAngleForModel(TrunkBottom, 0.0873F, 0.0F, 0.0F);
		TrunkBottom.setTextureOffset(0, 0).addBox(-2.0F, 1.0F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, false);

		Ears = new ModelRenderer(this);
		Ears.setRotationPoint(0.0F, 0.0F, 0.0F);
		Head.addChild(Ears);

		LeftEar = new ModelRenderer(this);
		LeftEar.setRotationPoint(9.0F, -1.0F, -6.0F);
		Ears.addChild(LeftEar);
		this.setRotationAngleForModel(LeftEar, 0.1309F, 0.48F, 0.0F);
		LeftEar.setTextureOffset(144, 124).addBox(0.0F, -8.0F, 0.0F, 1.0F, 12.0F, 8.0F, 0.0F, false);

		RightEar = new ModelRenderer(this);
		RightEar.setRotationPoint(-9.0F, -1.0F, -6.0F);
		Ears.addChild(RightEar);
		this.setRotationAngleForModel(RightEar, 0.1309F, -0.48F, 0.0F);
		RightEar.setTextureOffset(244, 62).addBox(-2.0F, -8.0F, 0.0F, 1.0F, 12.0F, 8.0F, 0.0F, false);

		Tusks = new ModelRenderer(this);
		Tusks.setRotationPoint(0.0F, 0.0F, 0.0F);
		Head.addChild(Tusks);

		LeftTusk = new ModelRenderer(this);
		LeftTusk.setRotationPoint(7.0F, 7.0F, -10.0F);
		Tusks.addChild(LeftTusk);

		LeftTuskTop = new ModelRenderer(this);
		LeftTuskTop.setRotationPoint(0.0F, 0.0F, 0.0F);
		LeftTusk.addChild(LeftTuskTop);
		LeftTuskTop.setTextureOffset(225, 230).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);

		LeftTuskMiddle = new ModelRenderer(this);
		LeftTuskMiddle.setRotationPoint(0.0F, 10.0F, 0.0F);
		LeftTuskTop.addChild(LeftTuskMiddle);
		this.setRotationAngleForModel(LeftTuskMiddle, -0.3054F, 0.0F, 0.0F);
		LeftTuskMiddle.setTextureOffset(240, 230).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		LeftTuskBottom = new ModelRenderer(this);
		LeftTuskBottom.setRotationPoint(0.0F, 6.0F, 0.0F);
		LeftTuskMiddle.addChild(LeftTuskBottom);
		this.setRotationAngleForModel(LeftTuskBottom, -0.6545F, 0.0F, 0.0F);
		LeftTuskBottom.setTextureOffset(235, 228).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

		RightTusk = new ModelRenderer(this);
		RightTusk.setRotationPoint(-7.0F, 7.0F, -10.0F);
		Tusks.addChild(RightTusk);

		RightTuskTop = new ModelRenderer(this);
		RightTuskTop.setRotationPoint(14.0F, 0.0F, 0.0F);
		RightTusk.addChild(RightTuskTop);
		RightTuskTop.setTextureOffset(225, 230).addBox(-15.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);

		RightTuskMiddle = new ModelRenderer(this);
		RightTuskMiddle.setRotationPoint(0.0F, 10.0F, 0.0F);
		RightTuskTop.addChild(RightTuskMiddle);
		this.setRotationAngleForModel(RightTuskMiddle, -0.3054F, 0.0F, 0.0F);
		RightTuskMiddle.setTextureOffset(240, 230).addBox(-15.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		RightTuskBottom = new ModelRenderer(this);
		RightTuskBottom.setRotationPoint(0.0F, 6.0F, 0.0F);
		RightTuskMiddle.addChild(RightTuskBottom);
		this.setRotationAngleForModel(RightTuskBottom, -0.6545F, 0.0F, 0.0F);
		RightTuskBottom.setTextureOffset(235, 228).addBox(-15.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Body.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,	float netHeadYaw, float headPitch) {
		this.Head.rotateAngleX = headPitch * ((float) Math.PI / 180F);
		this.Head.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
		this.RightFrontLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F	* limbSwingAmount;
		this.RightBackLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.LeftFrontLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.LeftBackLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
	}

	@Override
	public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		
	}

	private void setRotationAngleForModel(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}


}
