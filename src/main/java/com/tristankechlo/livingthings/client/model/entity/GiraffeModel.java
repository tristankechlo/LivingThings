package com.tristankechlo.livingthings.client.model.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.tristankechlo.livingthings.client.model.AdvancedEntityModel;
import com.tristankechlo.livingthings.entities.GiraffeEntity;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GiraffeModel<T extends GiraffeEntity> extends AdvancedEntityModel<T> {

	private final ModelRenderer Body;
	private final ModelRenderer Legs;
	private final ModelRenderer FrontRightLeg;
	private final ModelRenderer FrontLeftLeg;
	private final ModelRenderer BackLeftLeg;
	private final ModelRenderer BackRightLeg;
	private final ModelRenderer NeckBottom;
	private final ModelRenderer NeckMiddle;
	private final ModelRenderer NeckTop;
	private final ModelRenderer Head;
	private final ModelRenderer FrontHead;
	private final ModelRenderer Horns;
	private final ModelRenderer LeftHorn;
	private final ModelRenderer RightHorn;
	private final ModelRenderer Ears;
	private final ModelRenderer LeftEar;
	private final ModelRenderer RightEar;
	private final ModelRenderer TailTop;
	private final ModelRenderer TailBottom;

	public GiraffeModel() {
		texWidth = 128;
		texHeight = 64;

		Body = new ModelRenderer(this);
		Body.setPos(0.0F, 14.5F, 0.6F);
		Body.texOffs(72, 35).addBox(-6.0F, -21.5F, -13.6F, 12.0F, 13.0F, 16.0F, 0.0F, false);
		Body.texOffs(81, 14).addBox(-5.5F, -19.5F, 2.4F, 11.0F, 11.0F, 10.0F, 0.0F, false);

		Legs = new ModelRenderer(this);
		Legs.setPos(0.0F, -7.5F, -0.6F);
		Body.addChild(Legs);

		FrontRightLeg = new ModelRenderer(this);
		FrontRightLeg.setPos(-4.0F, -1.0F, -10.0F);
		Legs.addChild(FrontRightLeg);
		FrontRightLeg.texOffs(98, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
		FrontRightLeg.texOffs(116, 0).addBox(-1.5F, 8.0F, -1.5F, 3.0F, 10.0F, 3.0F, 0.0F, false);

		FrontLeftLeg = new ModelRenderer(this);
		FrontLeftLeg.setPos(4.0F, -1.0F, -10.0F);
		Legs.addChild(FrontLeftLeg);
		FrontLeftLeg.texOffs(98, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, true);
		FrontLeftLeg.texOffs(116, 0).addBox(-1.5F, 8.0F, -1.5F, 3.0F, 10.0F, 3.0F, 0.0F, false);

		BackLeftLeg = new ModelRenderer(this);
		BackLeftLeg.setPos(4.0F, -3.0F, 10.0F);
		Legs.addChild(BackLeftLeg);
		BackLeftLeg.texOffs(70, 0).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);
		BackLeftLeg.texOffs(116, 0).addBox(-1.5F, 10.0F, -1.5F, 3.0F, 10.0F, 3.0F, 0.0F, false);

		BackRightLeg = new ModelRenderer(this);
		BackRightLeg.setPos(-4.0F, -3.0F, 10.0F);
		Legs.addChild(BackRightLeg);
		BackRightLeg.texOffs(70, 0).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
		BackRightLeg.texOffs(116, 0).addBox(-1.5F, 10.0F, -1.5F, 3.0F, 10.0F, 3.0F, 0.0F, false);

		NeckBottom = new ModelRenderer(this);
		NeckBottom.setPos(0.0F, -21.5F, -13.6F);
		Body.addChild(NeckBottom);
		this.setRotationAngle(NeckBottom, 0.5236F, 0.0F, 0.0F);
		NeckBottom.texOffs(0, 46).addBox(-4.5F, -2.0F, -3.5F, 9.0F, 9.0F, 9.0F, 0.0F, false);

		NeckMiddle = new ModelRenderer(this);
		NeckMiddle.setPos(0.0F, -2.0F, 1.0F);
		NeckBottom.addChild(NeckMiddle);
		this.setRotationAngle(NeckMiddle, -0.1309F, 0.0F, 0.0F);
		NeckMiddle.texOffs(0, 22).addBox(-3.5F, -10.0F, -3.5F, 7.0F, 11.0F, 7.0F, 0.0F, false);

		NeckTop = new ModelRenderer(this);
		NeckTop.setPos(0.0F, -10.0F, 0.0F);
		NeckMiddle.addChild(NeckTop);
		this.setRotationAngle(NeckTop, -0.1309F, 0.0F, 0.0F);
		NeckTop.texOffs(0, 0).addBox(-2.5F, -10.0F, -2.5F, 5.0F, 11.0F, 5.0F, 0.0F, false);

		Head = new ModelRenderer(this);
		Head.setPos(0.0F, -8.0F, 0.0F);
		NeckTop.addChild(Head);
		this.setRotationAngle(Head, -0.1309F, 0.0F, 0.0F);
		Head.texOffs(41, 52).addBox(-3.0F, -6.0F, -4.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);

		FrontHead = new ModelRenderer(this);
		FrontHead.setPos(0.0F, -2.0F, -4.0F);
		Head.addChild(FrontHead);
		FrontHead.texOffs(46, 41).addBox(-2.0F, -2.0F, -4.0F, 4.0F, 3.0F, 4.0F, 0.0F, false);

		Horns = new ModelRenderer(this);
		Horns.setPos(0.0F, 0.0F, 0.0F);
		Head.addChild(Horns);

		LeftHorn = new ModelRenderer(this);
		LeftHorn.setPos(1.0F, -6.0F, 0.0F);
		Horns.addChild(LeftHorn);
		this.setRotationAngle(LeftHorn, -0.0873F, 0.0F, 0.0F);
		LeftHorn.texOffs(30, 15).addBox(0.5F, -2.5F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		RightHorn = new ModelRenderer(this);
		RightHorn.setPos(-2.0F, -6.0F, 0.0F);
		Horns.addChild(RightHorn);
		this.setRotationAngle(RightHorn, -0.0873F, 0.0F, 0.0F);
		RightHorn.texOffs(30, 15).addBox(-0.5F, -2.5F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		Ears = new ModelRenderer(this);
		Ears.setPos(0.0F, 0.0F, 0.0F);
		Head.addChild(Ears);

		LeftEar = new ModelRenderer(this);
		LeftEar.setPos(3.0F, -4.0F, 0.0F);
		Ears.addChild(LeftEar);
		LeftEar.texOffs(36, 16).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		RightEar = new ModelRenderer(this);
		RightEar.setPos(-3.0F, -4.0F, 0.0F);
		Ears.addChild(RightEar);
		RightEar.texOffs(36, 16).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		TailTop = new ModelRenderer(this);
		TailTop.setPos(0.0F, -16.5F, 12.4F);
		Body.addChild(TailTop);
		this.setRotationAngle(TailTop, 0.1745F, 0.0F, 0.0F);
		TailTop.texOffs(48, 4).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 9.0F, 1.0F, 0.0F, false);

		TailBottom = new ModelRenderer(this);
		TailBottom.setPos(0.0F, 9.0F, 0.0F);
		TailTop.addChild(TailBottom);
		this.setRotationAngle(TailBottom, -0.1309F, 0.0F, 0.0F);
		TailBottom.texOffs(53, 4).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 7.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn,
			int packedOverlayIn, float red, float green, float blue, float alpha) {

		if (this.young) {
			matrixStackIn.scale(0.6F, 0.6F, 0.6F);
			matrixStackIn.translate(0, 1, 0);
		}
		Body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);

	}

	@Override
	public void setupAnim(GiraffeEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {

		this.Head.xRot = headPitch * 0.0174532925F;
		this.NeckTop.xRot = (float) (this.NeckMiddle.xRot / 1);
		this.NeckMiddle.xRot = (float) (this.Head.xRot / 2);

		this.Head.yRot = (netHeadYaw / 3.75F) * 0.0174532925F;
		this.NeckTop.yRot = (netHeadYaw / 3.75F) * 0.0174532925F;
		this.NeckMiddle.yRot = (netHeadYaw / 3.75F) * 0.0174532925F;
		this.NeckBottom.yRot = (netHeadYaw / 5F) * 0.0174532925F;

		this.walk(FrontRightLeg, FrontLeftLeg, BackRightLeg, BackLeftLeg, limbSwing, limbSwingAmount);

		this.TailTop.zRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.75F * limbSwingAmount;
		this.TailBottom.zRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.75F * limbSwingAmount;
	}

}
