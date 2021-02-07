package com.tristankechlo.livingthings.client.model;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AdvancedEntityModel<T extends Entity> extends EntityModel<T> {

	protected void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	protected void defaultHeadMovement(ModelRenderer head, float defaultDegreeX, float defaultDegreeY, float headPitch,
			float netHeadYaw) {
		head.rotateAngleX = this.deg2rad(defaultDegreeX) + this.deg2rad(headPitch);
		head.rotateAngleY = this.deg2rad(defaultDegreeY) + this.deg2rad(netHeadYaw);
	}

	protected void walk(ModelRenderer frontRight, ModelRenderer frontLeft, ModelRenderer backRight,
			ModelRenderer backLeft, float limbSwing, float limbSwingAmount) {

		this.walking2(backRight, limbSwing, limbSwingAmount);
		this.walking1(backLeft, limbSwing, limbSwingAmount);
		this.walking1(frontRight, limbSwing, limbSwingAmount);
		this.walking2(frontLeft, limbSwing, limbSwingAmount);
	}

	protected void walking1(ModelRenderer model, float limbSwing, float limbSwingAmount) {
		model.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
	}

	protected void walking2(ModelRenderer model, float limbSwing, float limbSwingAmount) {
		model.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}

	protected void defaultWalking1(ModelRenderer model, float defaultDegree, float limbSwing, float limbSwingAmount) {
		model.rotateAngleX = this.deg2rad(defaultDegree)
				+ MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
	}

	protected void defaultWalking2(ModelRenderer model, float defaultDegree, float limbSwing, float limbSwingAmount) {
		model.rotateAngleX = this.deg2rad(defaultDegree) + MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}

	protected float deg2rad(double degree) {
		return (float) Math.toRadians(degree);
	}

}
