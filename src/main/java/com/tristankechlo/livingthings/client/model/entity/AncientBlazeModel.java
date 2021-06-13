package com.tristankechlo.livingthings.client.model.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.tristankechlo.livingthings.client.model.AdvancedEntityModel;
import com.tristankechlo.livingthings.entities.AncientBlazeEntity;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AncientBlazeModel<T extends AncientBlazeEntity> extends AdvancedEntityModel<T> {

	private final ModelRenderer Body;
	private final ModelRenderer Head;
	private final ModelRenderer Helmet;
	private final ModelRenderer Crystal;
	private final ModelRenderer Shields;
	private final ModelRenderer[] shields = new ModelRenderer[4];
	private final ModelRenderer Sticks;
	private final ModelRenderer[] sticks = new ModelRenderer[10];

	public AncientBlazeModel() {
		texWidth = 64;
		texHeight = 64;

		Body = new ModelRenderer(this);
		Body.setPos(0.0F, 11.0F, 0.0F);
		Body.texOffs(25, 24).addBox(-2.5F, -22.0F, -2.5F, 5.0F, 35.0F, 5.0F, 0.0F, false);

		Head = new ModelRenderer(this);
		Head.setPos(0.0F, -24.5F, 0.0F);
		Body.addChild(Head);
		Head.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		Helmet = new ModelRenderer(this);
		Helmet.setPos(0.0F, 0.0F, 0.0F);
		Head.addChild(Helmet);
		Helmet.texOffs(33, 0).addBox(3.5F, -9.5F, -4.5F, 1.0F, 10.0F, 2.0F, 0.0F, false);
		Helmet.texOffs(9, 17).addBox(-3.5F, -8.5F, -4.5F, 7.0F, 3.0F, 1.0F, 0.0F, false);
		Helmet.texOffs(9, 22).addBox(-2.5F, -9.5F, -4.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		Helmet.texOffs(16, 22).addBox(0.5F, -9.5F, -4.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		Helmet.texOffs(23, 22).addBox(-3.5F, -10.5F, -4.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		Helmet.texOffs(9, 25).addBox(1.5F, -10.5F, -4.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		Helmet.texOffs(16, 25).addBox(0.5F, -11.5F, -4.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		Helmet.texOffs(23, 25).addBox(-2.5F, -11.5F, -4.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		Helmet.texOffs(9, 28).addBox(-1.5F, -12.5F, -4.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		Helmet.texOffs(33, 13).addBox(1.5F, -3.5F, -4.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);
		Helmet.texOffs(40, 13).addBox(-3.5F, -3.5F, -4.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);
		Helmet.texOffs(40, 0).addBox(-4.5F, -9.5F, -4.5F, 1.0F, 10.0F, 2.0F, 0.0F, false);
		Helmet.texOffs(0, 33).addBox(-2.5F, -8.5F, 3.5F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		Helmet.texOffs(0, 36).addBox(-2.5F, -0.5F, 3.5F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		Helmet.texOffs(47, 13).addBox(2.5F, -9.5F, 3.5F, 1.0F, 10.0F, 1.0F, 0.0F, false);
		Helmet.texOffs(47, 25).addBox(-3.5F, -9.5F, 3.5F, 1.0F, 10.0F, 1.0F, 0.0F, false);
		Helmet.texOffs(18, 28).addBox(-2.5F, -4.5F, 3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Helmet.texOffs(33, 19).addBox(-0.5F, -7.5F, 3.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		Helmet.texOffs(38, 19).addBox(-0.5F, -2.5F, 3.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		Helmet.texOffs(47, 37).addBox(-1.5F, -6.5F, 3.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
		Helmet.texOffs(47, 44).addBox(0.5F, -6.5F, 3.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
		Helmet.texOffs(41, 24).addBox(1.5F, -4.5F, 3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Helmet.texOffs(52, 29).addBox(3.5F, -1.5F, -2.5F, 1.0F, 2.0F, 5.0F, 0.0F, false);
		Helmet.texOffs(52, 21).addBox(3.5F, -8.5F, -2.5F, 1.0F, 2.0F, 5.0F, 0.0F, false);
		Helmet.texOffs(52, 13).addBox(-4.5F, -8.5F, -2.5F, 1.0F, 2.0F, 5.0F, 0.0F, false);
		Helmet.texOffs(46, 51).addBox(3.5F, -3.5F, -0.5F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		Helmet.texOffs(0, 39).addBox(-4.5F, -3.5F, -0.5F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		Helmet.texOffs(47, 0).addBox(3.5F, -9.5F, 2.5F, 1.0F, 10.0F, 2.0F, 0.0F, false);
		Helmet.texOffs(54, 0).addBox(-4.5F, -9.5F, 2.5F, 1.0F, 10.0F, 2.0F, 0.0F, false);
		Helmet.texOffs(7, 40).addBox(-4.5F, -10.5F, 3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Helmet.texOffs(13, 31).addBox(3.5F, -10.5F, 3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Helmet.texOffs(13, 34).addBox(3.5F, -5.5F, -1.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		Helmet.texOffs(18, 34).addBox(-4.5F, -5.5F, -1.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		Helmet.texOffs(46, 55).addBox(3.5F, -5.5F, -0.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		Helmet.texOffs(46, 60).addBox(-4.5F, -5.5F, -0.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		Helmet.texOffs(52, 37).addBox(-4.5F, -1.5F, -2.5F, 1.0F, 2.0F, 5.0F, 0.0F, false);
		Helmet.texOffs(52, 45).addBox(3.5F, -13.5F, -4.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		Helmet.texOffs(57, 45).addBox(-4.5F, -13.5F, -4.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		Helmet.texOffs(53, 51).addBox(2.5F, -15.5F, -4.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		Helmet.texOffs(58, 51).addBox(-3.5F, -15.5F, -4.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		Crystal = new ModelRenderer(this);
		Crystal.setPos(0.0F, 0.0F, 0.0F);
		Head.addChild(Crystal);
		Crystal.texOffs(25, 2).addBox(-1.5F, -11.5F, -4.0F, 3.0F, 3.0F, 0.0F, 0.0F, false);
		Crystal.texOffs(27, 6).addBox(-3.5F, -9.5F, -4.0F, 1.0F, 1.0F, 0.0F, 0.0F, false);
		Crystal.texOffs(27, 6).addBox(2.5F, -9.5F, -4.0F, 1.0F, 1.0F, 0.0F, 0.0F, false);

		Shields = new ModelRenderer(this);
		Shields.setPos(0.0F, -22.0F, 0.0F);
		Body.addChild(Shields);
		this.setRotationAngle(Shields, 0.0F, -0.7854F, 0.0F);

		for (int i = 0; i < this.shields.length; i++) {
			this.shields[i] = new ModelRenderer(this, 0, 43);
			this.shields[i].addBox(-5.0F, 0.0F, -1.0F, 10.0F, 19.0F, 2.0F, 0.0F, false);
			this.shields[i].setPos(i * 7.5F, 0.0F, i * 7.5F);
			this.setRotationAngle(this.shields[i], -0.3491F, ((-1) ^ i) * 1.570796F, 0.0F);
			this.Shields.addChild(this.shields[i]);
		}

		Sticks = new ModelRenderer(this);
		Sticks.setPos(0.0F, 5.0F, 0.0F);
		Body.addChild(Sticks);

		for (int i = 0; i < this.sticks.length; i++) {
			this.sticks[i] = new ModelRenderer(this, 0, 18);
			this.sticks[i].addBox(-1.0F, -6.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);
			this.Sticks.addChild(this.sticks[i]);
		}
	}

	@Override
	public void setupAnim(AncientBlazeEntity blaze, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {

		this.Head.yRot = netHeadYaw * 0.017453F;
		this.Head.xRot = headPitch * 0.017453F;

		float f = 0.785398F + ageInTicks * -0.094247F;
		for (int i = 0; i < this.sticks.length; i++) {
			this.sticks[i].visible = (blaze.getShoots() > i);
			this.sticks[i].y = MathHelper.cos((i * 3F + ageInTicks) * 0.3F) - 1.0F;
			this.sticks[i].x = MathHelper.cos(f) * 9.0F;
			this.sticks[i].z = MathHelper.sin(f) * 9.0F;
			f++;
		}

		if (blaze.isPowered()) {
			this.Shields.yRot = 0.785398F;
			this.Head.y = -22.5F;

			for (int i = 0; i < this.shields.length; i++) {
				this.shields[i].x = MathHelper.cos(i * 1.570796F) * 6.0F;
				this.shields[i].z = MathHelper.sin(i * 1.570796F) * 6.0F;
				this.shields[i].xRot = 0F;
			}

		} else {
			this.Shields.yRot = -ageInTicks / 50;
			this.Head.y = -24.5F;

			for (int i = 0; i < this.shields.length; i++) {
				this.shields[i].x = MathHelper.cos(i * 1.570796F) * 7.5F;
				this.shields[i].z = MathHelper.sin(i * 1.570796F) * 7.5F;
				this.shields[i].xRot = -0.349065F;
			}

		}
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		Body.render(matrixStack, buffer, packedLight, packedOverlay);
	}

}
