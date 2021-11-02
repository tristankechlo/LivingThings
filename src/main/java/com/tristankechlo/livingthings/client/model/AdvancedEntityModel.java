package com.tristankechlo.livingthings.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AdvancedEntityModel<T extends Entity> extends EntityModel<T> {

	protected void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}

	protected void defaultHeadMovement(ModelPart head, float defaultDegreeX, float defaultDegreeY, float headPitch,
			float netHeadYaw) {
		head.xRot = this.deg2rad(defaultDegreeX) + this.deg2rad(headPitch);
		head.yRot = this.deg2rad(defaultDegreeY) + this.deg2rad(netHeadYaw);
	}

	protected void walk(ModelPart frontRight, ModelPart frontLeft, ModelPart backRight,
			ModelPart backLeft, float limbSwing, float limbSwingAmount) {

		this.walking2(backRight, limbSwing, limbSwingAmount);
		this.walking1(backLeft, limbSwing, limbSwingAmount);
		this.walking1(frontRight, limbSwing, limbSwingAmount);
		this.walking2(frontLeft, limbSwing, limbSwingAmount);
	}

	protected void walking1(ModelPart model, float limbSwing, float limbSwingAmount) {
		model.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
	}

	protected void walking2(ModelPart model, float limbSwing, float limbSwingAmount) {
		model.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}

	protected void defaultWalking1(ModelPart model, float defaultDegree, float limbSwing, float limbSwingAmount) {
		model.xRot = this.deg2rad(defaultDegree)
				+ Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
	}

	protected void defaultWalking2(ModelPart model, float defaultDegree, float limbSwing, float limbSwingAmount) {
		model.xRot = this.deg2rad(defaultDegree) + Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}

	protected float deg2rad(double degree) {
		return (float) Math.toRadians(degree);
	}

}
