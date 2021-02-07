package com.tristankechlo.livingthings.client.model.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.tristankechlo.livingthings.client.model.AdvancedEntityModel;
import com.tristankechlo.livingthings.entities.CrabEntity;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CrabModel<T extends CrabEntity> extends AdvancedEntityModel<T> {

	private final ModelRenderer Body;
	private final ModelRenderer Shear1;
	private final ModelRenderer Shear1Arm;
	private final ModelRenderer Shear1UpperBottom;
	private final ModelRenderer Shear1Upper;
	private final ModelRenderer Shear1Bottom;
	private final ModelRenderer Shear2;
	private final ModelRenderer Shear2Arm;
	private final ModelRenderer Shear2UpperBottom;
	private final ModelRenderer Shear2Upper;
	private final ModelRenderer Shear2Bottom;
	private final ModelRenderer Leg1;
	private final ModelRenderer Leg11;
	private final ModelRenderer Leg2;
	private final ModelRenderer Leg22;
	private final ModelRenderer Leg3;
	private final ModelRenderer Leg33;
	private final ModelRenderer Leg4;
	private final ModelRenderer Leg44;
	private final ModelRenderer Leg5;
	private final ModelRenderer Leg55;
	private final ModelRenderer Leg6;
	private final ModelRenderer Leg66;
	private final ModelRenderer Leg7;
	private final ModelRenderer Leg77;
	private final ModelRenderer Leg8;
	private final ModelRenderer Leg88;

	public CrabModel() {
		textureWidth = 32;
		textureHeight = 32;

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 20.4F, 0.0F);
		Body.setTextureOffset(0, 24).addBox(-3.0F, -1.0F, -3.0F, 6.0F, 2.0F, 6.0F, 0.0F, false);
		Body.setTextureOffset(25, 25).addBox(-1.7F, -2.5F, -3.0F, 1.0F, 2.0F, 1.0F, 0.0F, true);
		Body.setTextureOffset(25, 29).addBox(0.9F, -2.5F, -3.0F, 1.0F, 2.0F, 1.0F, 0.0F, true);

		Shear1 = new ModelRenderer(this);
		Shear1.setRotationPoint(2.5F, -0.3F, -2.5F);
		Body.addChild(Shear1);
		this.setRotationAngle(Shear1, -0.0873F, -0.7854F, 0.0F);
		Shear1.setTextureOffset(0, 0).addBox(-0.5F, -0.5F, -2.05F, 1.0F, 1.0F, 2.0F, 0.0F, false);

		Shear1Arm = new ModelRenderer(this);
		Shear1Arm.setRotationPoint(0.0F, 0.0F, -1.9091F);
		Shear1.addChild(Shear1Arm);
		this.setRotationAngle(Shear1Arm, 0.0F, 0.5236F, 0.0F);
		Shear1Arm.setTextureOffset(0, 4).addBox(-0.4805F, -0.5046F, -0.9277F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		Shear1UpperBottom = new ModelRenderer(this);
		Shear1UpperBottom.setRotationPoint(0.102F, -0.0247F, -0.7323F);
		Shear1Arm.addChild(Shear1UpperBottom);
		this.setRotationAngle(Shear1UpperBottom, 0.0F, 0.2618F, 0.0F);

		Shear1Upper = new ModelRenderer(this);
		Shear1Upper.setRotationPoint(0.0031F, 0.0494F, -0.0074F);
		Shear1UpperBottom.addChild(Shear1Upper);
		this.setRotationAngle(Shear1Upper, -0.0873F, 0.0F, 0.0F);
		Shear1Upper.setTextureOffset(0, 7).addBox(-0.552F, -0.9267F, -2.0836F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		Shear1Upper.setTextureOffset(0, 11).addBox(-0.552F, -1.2255F, -2.0836F, 1.0F, 1.0F, 2.0F, 0.0F, false);

		Shear1Bottom = new ModelRenderer(this);
		Shear1Bottom.setRotationPoint(0.0F, 0.0F, 0.0F);
		Shear1UpperBottom.addChild(Shear1Bottom);
		this.setRotationAngle(Shear1Bottom, 0.1309F, 0.0F, 0.0F);
		Shear1Bottom.setTextureOffset(0, 15).addBox(-0.552F, -0.1753F, -1.8686F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Shear1Bottom.setTextureOffset(0, 18).addBox(-0.552F, -0.1753F, -1.07F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		Shear2 = new ModelRenderer(this);
		Shear2.setRotationPoint(-2.5F, -0.3F, -2.5F);
		Body.addChild(Shear2);
		this.setRotationAngle(Shear2, -0.0873F, 0.7854F, 0.0F);
		Shear2.setTextureOffset(7, 0).addBox(-0.5F, -0.5F, -2.05F, 1.0F, 1.0F, 2.0F, 0.0F, false);

		Shear2Arm = new ModelRenderer(this);
		Shear2Arm.setRotationPoint(0.3535F, 0.4994F, -1.6338F);
		Shear2.addChild(Shear2Arm);
		this.setRotationAngle(Shear2Arm, 0.0F, -0.5236F, 0.0F);
		Shear2Arm.setTextureOffset(7, 4).addBox(-0.975F, -1.0F, -0.95F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		Shear2UpperBottom = new ModelRenderer(this);
		Shear2UpperBottom.setRotationPoint(-0.4926F, -0.4745F, -0.6809F);
		Shear2Arm.addChild(Shear2UpperBottom);
		this.setRotationAngle(Shear2UpperBottom, 0.0F, -0.2618F, 0.0F);

		Shear2Upper = new ModelRenderer(this);
		Shear2Upper.setRotationPoint(0.0031F, 0.0537F, -0.0074F);
		Shear2UpperBottom.addChild(Shear2Upper);
		this.setRotationAngle(Shear2Upper, -0.0873F, 0.0F, 0.0F);
		Shear2Upper.setTextureOffset(7, 7).addBox(-0.552F, -1.0017F, -2.2336F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		Shear2Upper.setTextureOffset(7, 11).addBox(-0.552F, -1.3005F, -2.2336F, 1.0F, 1.0F, 2.0F, 0.0F, false);

		Shear2Bottom = new ModelRenderer(this);
		Shear2Bottom.setRotationPoint(0.0F, 0.0044F, 0.0F);
		Shear2UpperBottom.addChild(Shear2Bottom);
		this.setRotationAngle(Shear2Bottom, 0.1309F, 0.0F, 0.0F);
		Shear2Bottom.setTextureOffset(7, 15).addBox(-0.552F, -0.2503F, -2.0186F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Shear2Bottom.setTextureOffset(7, 18).addBox(-0.552F, -0.2503F, -1.22F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		Leg1 = new ModelRenderer(this);
		Leg1.setRotationPoint(2.15F, 0.6F, -1.825F);
		Body.addChild(Leg1);
		this.setRotationAngle(Leg1, 0.0F, 0.1745F, 0.48F);
		Leg1.setTextureOffset(14, 0).addBox(0.0F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);

		Leg11 = new ModelRenderer(this);
		Leg11.setRotationPoint(2.95F, 0.0F, 0.0F);
		Leg1.addChild(Leg11);
		this.setRotationAngle(Leg11, 0.0F, 0.0F, 0.5236F);
		Leg11.setTextureOffset(23, 0).addBox(-0.2F, -0.45F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		Leg2 = new ModelRenderer(this);
		Leg2.setRotationPoint(2.25F, 0.6F, -0.55F);
		Body.addChild(Leg2);
		this.setRotationAngle(Leg2, 0.0F, 0.0436F, 0.48F);
		Leg2.setTextureOffset(14, 3).addBox(0.0F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);

		Leg22 = new ModelRenderer(this);
		Leg22.setRotationPoint(3.0512F, 0.05F, 0.0066F);
		Leg2.addChild(Leg22);
		this.setRotationAngle(Leg22, 0.0F, 0.0F, 0.5236F);
		Leg22.setTextureOffset(23, 3).addBox(-0.3012F, -0.45F, -0.5066F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		Leg3 = new ModelRenderer(this);
		Leg3.setRotationPoint(2.25F, 0.6F, 0.725F);
		Body.addChild(Leg3);
		this.setRotationAngle(Leg3, 0.0F, -0.0436F, 0.48F);
		Leg3.setTextureOffset(14, 6).addBox(0.0F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);

		Leg33 = new ModelRenderer(this);
		Leg33.setRotationPoint(3.0262F, 0.05F, 0.0066F);
		Leg3.addChild(Leg33);
		this.setRotationAngle(Leg33, 0.0F, 0.0F, 0.5236F);
		Leg33.setTextureOffset(23, 6).addBox(-0.3012F, -0.45F, -0.5066F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		Leg4 = new ModelRenderer(this);
		Leg4.setRotationPoint(2.175F, 0.6F, 2.0F);
		Body.addChild(Leg4);
		this.setRotationAngle(Leg4, 0.0F, -0.1745F, 0.48F);
		Leg4.setTextureOffset(14, 9).addBox(0.0F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);

		Leg44 = new ModelRenderer(this);
		Leg44.setRotationPoint(3.0262F, 0.05F, 0.0066F);
		Leg4.addChild(Leg44);
		this.setRotationAngle(Leg44, 0.0F, 0.0F, 0.5236F);
		Leg44.setTextureOffset(23, 9).addBox(-0.3012F, -0.45F, -0.5066F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		Leg5 = new ModelRenderer(this);
		Leg5.setRotationPoint(-2.2F, 0.525F, 2.0F);
		Body.addChild(Leg5);
		this.setRotationAngle(Leg5, 0.0F, -2.9671F, -0.48F);
		Leg5.setTextureOffset(14, 12).addBox(0.0F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);

		Leg55 = new ModelRenderer(this);
		Leg55.setRotationPoint(3.0262F, 0.05F, 0.0066F);
		Leg5.addChild(Leg55);
		this.setRotationAngle(Leg55, 0.0F, 0.0F, 0.5236F);
		Leg55.setTextureOffset(23, 12).addBox(-0.3012F, -0.45F, -0.5066F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		Leg6 = new ModelRenderer(this);
		Leg6.setRotationPoint(-2.325F, 0.6F, 0.75F);
		Body.addChild(Leg6);
		this.setRotationAngle(Leg6, 0.0F, -3.098F, -0.48F);
		Leg6.setTextureOffset(14, 15).addBox(0.0F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);

		Leg66 = new ModelRenderer(this);
		Leg66.setRotationPoint(3.0262F, 0.05F, 0.0066F);
		Leg6.addChild(Leg66);
		this.setRotationAngle(Leg66, 0.0F, 0.0F, 0.5236F);
		Leg66.setTextureOffset(23, 15).addBox(-0.3012F, -0.45F, -0.5066F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		Leg7 = new ModelRenderer(this);
		Leg7.setRotationPoint(-2.275F, 0.6F, -0.675F);
		Body.addChild(Leg7);
		this.setRotationAngle(Leg7, 0.0F, 3.098F, -0.48F);
		Leg7.setTextureOffset(14, 18).addBox(0.0F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);

		Leg77 = new ModelRenderer(this);
		Leg77.setRotationPoint(3.0262F, 0.05F, 0.0066F);
		Leg7.addChild(Leg77);
		this.setRotationAngle(Leg77, 0.0F, 0.0F, 0.5236F);
		Leg77.setTextureOffset(23, 18).addBox(-0.3012F, -0.45F, -0.5066F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		Leg8 = new ModelRenderer(this);
		Leg8.setRotationPoint(-2.175F, 0.6F, -2.025F);
		Body.addChild(Leg8);
		this.setRotationAngle(Leg8, 0.0F, 2.9671F, -0.48F);
		Leg8.setTextureOffset(14, 21).addBox(0.0F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);

		Leg88 = new ModelRenderer(this);
		Leg88.setRotationPoint(3.0262F, 0.05F, 0.0066F);
		Leg8.addChild(Leg88);
		this.setRotationAngle(Leg88, 0.0F, 0.0F, 0.5236F);
		Leg88.setTextureOffset(23, 21).addBox(-0.3012F, -0.45F, -0.5066F, 2.0F, 1.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(CrabEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {

		// set all legs to default {angle * (PI / 180)}
		this.Leg1.rotateAngleY = 0.174532925F;
		this.Leg1.rotateAngleZ = 0.47996554375F;
		this.Leg2.rotateAngleY = 0.04363323125F;
		this.Leg2.rotateAngleZ = 0.47996554375F;
		this.Leg3.rotateAngleY = -0.04363323125F;
		this.Leg3.rotateAngleZ = 0.47996554375F;
		this.Leg4.rotateAngleY = -0.174532925F;
		this.Leg4.rotateAngleZ = 0.47996554375F;
		this.Leg5.rotateAngleY = -2.967059725F;
		this.Leg5.rotateAngleZ = -0.47996554375F;
		this.Leg6.rotateAngleY = -3.09795941875F;
		this.Leg6.rotateAngleZ = -0.47996554375F;
		this.Leg7.rotateAngleY = 3.09795941875F;
		this.Leg7.rotateAngleZ = -0.47996554375F;
		this.Leg8.rotateAngleY = 2.967059725F;
		this.Leg8.rotateAngleZ = -0.47996554375F;

		// walking animation for legs
		float f1 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * limbSwingAmount;
		float f2 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + (float) Math.PI) * 0.4F) * limbSwingAmount;
		float f3 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + ((float) Math.PI / 2F)) * 0.4F) * limbSwingAmount;
		float f4 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + ((float) Math.PI * 1.5F)) * 0.4F) * limbSwingAmount;
		float f5 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 0.0F) * 0.4F) * limbSwingAmount;
		float f6 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + (float) Math.PI) * 0.4F) * limbSwingAmount;
		float f7 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + ((float) Math.PI / 2F)) * 0.4F) * limbSwingAmount;
		float f8 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + ((float) Math.PI * 1.5F)) * 0.4F) * limbSwingAmount;
		this.Leg1.rotateAngleY += f1;
		this.Leg2.rotateAngleY += -f1;
		this.Leg3.rotateAngleY += f2;
		this.Leg4.rotateAngleY += -f2;
		this.Leg5.rotateAngleY += f3;
		this.Leg6.rotateAngleY += -f3;
		this.Leg7.rotateAngleY += f4;
		this.Leg8.rotateAngleY += -f4;
		this.Leg1.rotateAngleZ += f5;
		this.Leg2.rotateAngleZ += -f5;
		this.Leg3.rotateAngleZ += f6;
		this.Leg4.rotateAngleZ += -f6;
		this.Leg5.rotateAngleZ += f7;
		this.Leg6.rotateAngleZ += -f7;
		this.Leg7.rotateAngleZ += f8;
		this.Leg8.rotateAngleZ += -f8;

		// set shears default angles
		this.Shear1.rotateAngleX = -0.08726646259F;
		this.Shear1.rotateAngleY = -0.78539816339F;
		this.Shear2.rotateAngleX = -0.08726646259F;
		this.Shear2.rotateAngleY = 0.78539816339F;

		// shears walking animation
		this.Shear1.rotateAngleX += -(MathHelper.cos(limbSwing * 1.3324F) * 0.75F * limbSwingAmount) / 2;
		this.Shear2.rotateAngleX += (MathHelper.cos(limbSwing * 1.3324F) * 0.75F * limbSwingAmount) / 2;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {

		if (this.isChild) {
			matrixStack.scale(0.6F, 0.6F, 0.6F);
			matrixStack.translate(0, 1, 0);
		}
		Body.render(matrixStack, buffer, packedLight, packedOverlay);
	}
}
