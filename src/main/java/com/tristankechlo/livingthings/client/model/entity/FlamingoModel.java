package com.tristankechlo.livingthings.client.model.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.tristankechlo.livingthings.client.model.AdvancedEntityModel;
import com.tristankechlo.livingthings.entities.FlamingoEntity;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FlamingoModel<T extends FlamingoEntity> extends AdvancedEntityModel<T> {

	private final ModelRenderer Body;
	private final ModelRenderer Back1;
	private final ModelRenderer Back2;
	private final ModelRenderer Back3;
	private final ModelRenderer Legs;
	private final ModelRenderer LeftLegTop;
	private final ModelRenderer LeftLegBottom;
	private final ModelRenderer LeftFoot;
	private final ModelRenderer RightLegTop;
	private final ModelRenderer RightLegBottom;
	private final ModelRenderer RightFoot;
	private final ModelRenderer NeckBottom;
	private final ModelRenderer Neck2;
	private final ModelRenderer Neck3;
	private final ModelRenderer Neck4;
	private final ModelRenderer Neck5;
	private final ModelRenderer NeckTop;
	private final ModelRenderer Head;
	private final ModelRenderer Beak;
	private final ModelRenderer Beak2;
	private final ModelRenderer Beak3;
	private final ModelRenderer Wings;
	private final ModelRenderer LeftWing;
	private final ModelRenderer RightWing;

	public FlamingoModel() {
		texWidth = 64;
		texHeight = 64;

		Body = new ModelRenderer(this);
		Body.setPos(0.0F, 20.9F, 0.0F);
		Body.texOffs(36, 49).addBox(-3.0F, -15.9F, -4.0F, 6.0F, 7.0F, 8.0F, 0.0F, false);

		Back1 = new ModelRenderer(this);
		Back1.setPos(0.0F, -12.55F, 7.475F);
		Body.addChild(Back1);
		this.setRotationAngle(Back1, -0.1833F, 0.0F, 0.0F);
		Back1.texOffs(52, 25).addBox(-2.5F, -0.4808F, -0.8778F, 5.0F, 4.0F, 1.0F, 0.0F, false);

		Back2 = new ModelRenderer(this);
		Back2.setPos(0.0F, -15.05F, 3.85F);
		Body.addChild(Back2);
		Back2.texOffs(48, 31).addBox(-2.5F, 1.925F, -0.375F, 5.0F, 4.0F, 3.0F, 0.0F, false);

		Back3 = new ModelRenderer(this);
		Back3.setPos(0.0F, -15.3F, 3.8F);
		Body.addChild(Back3);
		this.setRotationAngle(Back3, -0.6109F, 0.0F, 0.0F);
		Back3.texOffs(44, 39).addBox(-2.5F, -0.3463F, -0.5502F, 5.0F, 4.0F, 5.0F, 0.0F, false);

		Legs = new ModelRenderer(this);
		Legs.setPos(0.0F, -9.9F, 0.0F);
		Body.addChild(Legs);

		LeftLegTop = new ModelRenderer(this);
		LeftLegTop.setPos(2.0F, 1.0F, 0.0F);
		Legs.addChild(LeftLegTop);
		this.setRotationAngle(LeftLegTop, 0.1309F, 0.0F, 0.0F);
		LeftLegTop.texOffs(0, 56).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 7.0F, 1.0F, 0.0F, false);

		LeftLegBottom = new ModelRenderer(this);
		LeftLegBottom.setPos(0.5F, 6.0F, 0.0F);
		LeftLegTop.addChild(LeftLegBottom);
		this.setRotationAngle(LeftLegBottom, -0.1745F, 0.0F, 0.0F);
		LeftLegBottom.texOffs(5, 57).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 6.0F, 1.0F, 0.0F, false);

		LeftFoot = new ModelRenderer(this);
		LeftFoot.setPos(0.5F, 6.0F, 0.0F);
		LeftLegBottom.addChild(LeftFoot);
		this.setRotationAngle(LeftFoot, 0.0436F, 0.0F, 0.0F);
		LeftFoot.texOffs(10, 61).addBox(-1.5F, -1.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);

		RightLegTop = new ModelRenderer(this);
		RightLegTop.setPos(-2.0F, 1.0F, 0.0F);
		Legs.addChild(RightLegTop);
		this.setRotationAngle(RightLegTop, 0.1309F, 0.0F, 0.0F);
		RightLegTop.texOffs(0, 46).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 7.0F, 1.0F, 0.0F, false);

		RightLegBottom = new ModelRenderer(this);
		RightLegBottom.setPos(0.5F, 6.0F, 0.0F);
		RightLegTop.addChild(RightLegBottom);
		this.setRotationAngle(RightLegBottom, -0.1745F, 0.0F, 0.0F);
		RightLegBottom.texOffs(5, 47).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 6.0F, 1.0F, 0.0F, false);

		RightFoot = new ModelRenderer(this);
		RightFoot.setPos(0.5F, 6.0F, 0.0F);
		RightLegBottom.addChild(RightFoot);
		this.setRotationAngle(RightFoot, 0.0436F, 0.0F, 0.0F);
		RightFoot.texOffs(10, 51).addBox(-1.5F, -1.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);

		NeckBottom = new ModelRenderer(this);
		NeckBottom.setPos(0.0F, -11.9F, -4.0F);
		Body.addChild(NeckBottom);
		this.setRotationAngle(NeckBottom, -0.1745F, 0.0F, 0.0F);
		NeckBottom.texOffs(0, 35).addBox(-2.5F, -3.0F, -1.0F, 5.0F, 5.0F, 2.0F, 0.0F, false);

		Neck2 = new ModelRenderer(this);
		Neck2.setPos(0.0F, 0.0F, -1.0F);
		NeckBottom.addChild(Neck2);
		this.setRotationAngle(Neck2, -0.2618F, 0.0F, 0.0F);
		Neck2.texOffs(0, 27).addBox(-2.0F, -2.5F, -1.0F, 4.0F, 4.0F, 3.0F, 0.0F, false);

		Neck3 = new ModelRenderer(this);
		Neck3.setPos(0.0F, 0.0F, -1.0F);
		Neck2.addChild(Neck3);
		this.setRotationAngle(Neck3, -0.5236F, 0.0F, 0.0F);
		Neck3.texOffs(0, 21).addBox(-1.5F, -2.0F, -1.0F, 3.0F, 3.0F, 2.0F, 0.0F, false);

		Neck4 = new ModelRenderer(this);
		Neck4.setPos(0.0F, -0.1109F, -1.2724F);
		Neck3.addChild(Neck4);
		this.setRotationAngle(Neck4, -0.6545F, 0.0F, 0.0F);
		Neck4.texOffs(0, 15).addBox(-1.0F, -1.5F, -2.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);

		Neck5 = new ModelRenderer(this);
		Neck5.setPos(0.0F, -0.5921F, -2.4868F);
		Neck4.addChild(Neck5);
		this.setRotationAngle(Neck5, 1.3963F, 0.0F, 0.0F);
		Neck5.texOffs(0, 10).addBox(-1.0F, -0.825F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		NeckTop = new ModelRenderer(this);
		NeckTop.setPos(0.0F, -1.95F, -0.05F);
		Neck5.addChild(NeckTop);
		this.setRotationAngle(NeckTop, 0.0436F, 0.0F, 0.0F);
		NeckTop.texOffs(0, 3).addBox(-1.0F, -2.5F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		Head = new ModelRenderer(this);
		Head.setPos(0.0F, -2.126F, 0.1477F);
		NeckTop.addChild(Head);
		this.setRotationAngle(Head, 0.2618F, 0.0F, 0.0F);
		Head.texOffs(21, 28).addBox(-1.5F, -3.0F, -4.0F, 3.0F, 3.0F, 5.0F, 0.0F, false);

		Beak = new ModelRenderer(this);
		Beak.setPos(0.0F, -1.5619F, -2.4006F);
		Head.addChild(Beak);
		this.setRotationAngle(Beak, 0.0873F, 0.0F, 0.0F);
		Beak.texOffs(23, 19).addBox(-1.0F, -0.5152F, -3.1737F, 2.0F, 2.0F, 3.0F, 0.0F, false);

		Beak2 = new ModelRenderer(this);
		Beak2.setPos(0.0F, 0.1884F, -3.2262F);
		Beak.addChild(Beak2);
		this.setRotationAngle(Beak2, -0.8727F, 0.0F, 0.0F);
		Beak2.texOffs(35, 21).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);

		Beak3 = new ModelRenderer(this);
		Beak3.setPos(0.0F, 1.1255F, -3.6706F);
		Beak.addChild(Beak3);
		this.setRotationAngle(Beak3, 0.2662F, 0.0F, 0.0F);
		Beak3.texOffs(34, 25).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		Wings = new ModelRenderer(this);
		Wings.setPos(0.0F, 3.1F, 0.0F);
		Body.addChild(Wings);

		LeftWing = new ModelRenderer(this);
		LeftWing.setPos(3.0F, -15.5F, -3.0F);
		Wings.addChild(LeftWing);
		this.setRotationAngle(LeftWing, -0.1745F, 0.0873F, 0.0F);
		LeftWing.texOffs(48, 0).addBox(0.0F, -2.5F, 0.0F, 1.0F, 5.0F, 7.0F, 0.0F, false);

		RightWing = new ModelRenderer(this);
		RightWing.setPos(-3.0F, -15.5F, -3.0F);
		Wings.addChild(RightWing);
		this.setRotationAngle(RightWing, -0.1745F, -0.0873F, 0.0F);
		RightWing.texOffs(31, 0).addBox(-1.0F, -2.5F, 0.0F, 1.0F, 5.0F, 7.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(FlamingoEntity flamingo, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {

		this.Head.xRot = 0.2617993875F + (headPitch * 0.0174532925F);
		this.Head.yRot = (netHeadYaw / 3.75F) * 0.0174532925F;

		if (flamingo.isLeftLegUp()) {
			// values are defined by: ((Math.PI / 180) * AngleIn�)
			this.LeftLegTop.xRot = 1.3089969389F;
			this.LeftLegBottom.xRot = -2.7052603405F;
			this.LeftFoot.xRot = 0.174532925F;

			this.RightLegTop.xRot = 0.1308996938F;
			this.RightLegBottom.xRot = -0.174532925F;
			this.RightFoot.xRot = 0.0436332312F;

		} else if (flamingo.isRightLegUp()) {

			this.LeftLegTop.xRot = 0.1308996938F;
			this.LeftLegBottom.xRot = -0.174532925F;
			this.LeftFoot.xRot = 0.0436332312F;

			this.RightLegTop.xRot = 1.3089969389F;
			this.RightLegBottom.xRot = -2.7052603405F;
			this.RightFoot.xRot = 0.174532925F;

		} else {
			this.LeftLegTop.xRot = 0.1308996937F
					+ (MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount);
			this.LeftLegBottom.xRot = -0.174532925F;
			this.LeftFoot.xRot = 0.0436332312F;

			this.RightLegTop.xRot = 0.1308996937F + (MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmount);
			this.RightLegBottom.xRot = -0.174532925F;
			this.RightFoot.xRot = 0.0436332312F;
		}
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn,
			int packedOverlayIn, float red, float green, float blue, float alpha) {

		if (this.young) {
			matrixStackIn.scale(0.5F, 0.5F, 0.5F);
			matrixStackIn.translate(0, 1.5D, 0);
		}
		Body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
	}

}
