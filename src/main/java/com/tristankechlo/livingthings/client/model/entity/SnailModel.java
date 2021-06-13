package com.tristankechlo.livingthings.client.model.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.tristankechlo.livingthings.client.model.AdvancedEntityModel;
import com.tristankechlo.livingthings.entities.SnailEntity;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SnailModel<T extends SnailEntity> extends AdvancedEntityModel<T> {

	private final ModelRenderer Body;
	private final ModelRenderer LeftEyeStick;
	private final ModelRenderer LeftEye;
	private final ModelRenderer RightEyeStick;
	private final ModelRenderer RightEye;
	private final ModelRenderer Shell;
	private final ModelRenderer LeftNose;
	private final ModelRenderer RightNose;

	public SnailModel(float scale) {
		texWidth = 64;
		texHeight = 32;

		Body = new ModelRenderer(this);
		Body.setPos(0.0F, 24.0F, 0.0F);
		Body.texOffs(26, 14).addBox(-3.0F, -5.0F, -6.5F, 6.0F, 5.0F, 13.0F, 0.0F, false);

		LeftEyeStick = new ModelRenderer(this);
		LeftEyeStick.setPos(1.7F, -4.9F, -5.0F);
		Body.addChild(LeftEyeStick);
		setRotationAngle(LeftEyeStick, 0.1309F, -0.1309F, 0.0873F);
		LeftEyeStick.texOffs(42, 1).addBox(-0.5F, -4.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);

		LeftEye = new ModelRenderer(this);
		LeftEye.setPos(0.0F, -3.9672F, 0.0F);
		LeftEyeStick.addChild(LeftEye);
		LeftEye.texOffs(5, 27).addBox(-0.5F, -3.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.2F, true);

		RightEyeStick = new ModelRenderer(this);
		RightEyeStick.setPos(-1.7F, -4.9F, -5.0F);
		Body.addChild(RightEyeStick);
		setRotationAngle(RightEyeStick, 0.1309F, 0.1309F, -0.0873F);
		RightEyeStick.texOffs(37, 1).addBox(-0.5F, -4.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);

		RightEye = new ModelRenderer(this);
		RightEye.setPos(0.0F, -3.9672F, 0.0F);
		RightEyeStick.addChild(RightEye);
		RightEye.texOffs(0, 27).addBox(-0.5F, -3.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.2F, false);

		Shell = new ModelRenderer(this);
		Shell.setPos(0.0F, -2.4508F, -0.3417F);
		Body.addChild(Shell);
		setRotationAngle(Shell, -0.2182F, 0.0F, 0.0F);
		Shell.texOffs(0, 0).addBox(-4.0F, -11.0668F, -2.9662F, 8.0F, 11.0F, 10.0F, 0.0F, false);

		LeftNose = new ModelRenderer(this);
		LeftNose.setPos(1.4F, -2.5F, -6.0F);
		Body.addChild(LeftNose);
		setRotationAngle(LeftNose, 0.0F, -0.1309F, 0.0F);
		LeftNose.texOffs(56, 3).addBox(-0.5F, -0.5F, -2.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);

		RightNose = new ModelRenderer(this);
		RightNose.setPos(-1.4F, -2.5F, -6.0F);
		Body.addChild(RightNose);
		setRotationAngle(RightNose, 0.0F, 0.1309F, 0.0F);
		RightNose.texOffs(47, 3).addBox(-0.5F, -0.5F, -2.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {
		this.LeftEyeStick.zRot = MathHelper.cos(limbSwing * 0.6662F) * 0.4F * limbSwingAmount;
		this.RightEyeStick.zRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.4F * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		Body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

}
