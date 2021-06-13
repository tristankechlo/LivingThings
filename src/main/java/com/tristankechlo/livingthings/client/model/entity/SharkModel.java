package com.tristankechlo.livingthings.client.model.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.tristankechlo.livingthings.client.model.AdvancedEntityModel;
import com.tristankechlo.livingthings.entities.SharkEntity;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SharkModel<T extends SharkEntity> extends AdvancedEntityModel<T> {

	private final ModelRenderer Body;
	private final ModelRenderer TopFin;
	private final ModelRenderer RightFin;
	private final ModelRenderer LeftFin;
	private final ModelRenderer Head;
	private final ModelRenderer HeadTop;
	private final ModelRenderer HeadBottom;
	private final ModelRenderer Tail;
	private final ModelRenderer Tail2;
	private final ModelRenderer BackFinTop;
	private final ModelRenderer BackFinBottom;

	public SharkModel() {
		texWidth = 128;
		texHeight = 64;

		Body = new ModelRenderer(this);
		Body.setPos(0.0F, 17.0F, 0.0F);
		Body.texOffs(48, 22).addBox(-6.0F, -7.0F, -18.0F, 12.0F, 14.0F, 28.0F, 0.0F, false);

		TopFin = new ModelRenderer(this);
		TopFin.setPos(0.0F, -2.0F, 0.0F);
		Body.addChild(TopFin);
		this.setRotationAngle(TopFin, -0.6109F, 0.0F, 0.0F);
		TopFin.texOffs(0, 0).addBox(-1.0F, -8.0F, -8.0F, 2.0F, 8.0F, 4.0F, 0.0F, false);

		RightFin = new ModelRenderer(this);
		RightFin.setPos(-4.0F, 4.0F, -8.0F);
		Body.addChild(RightFin);
		this.setRotationAngle(RightFin, -1.0036F, -0.1745F, 0.6981F);
		RightFin.texOffs(0, 30).addBox(-1.0F, -2.5F, 0.0F, 1.0F, 5.0F, 11.0F, 0.0F, false);

		LeftFin = new ModelRenderer(this);
		LeftFin.setPos(4.0F, 4.0F, -8.0F);
		Body.addChild(LeftFin);
		this.setRotationAngle(LeftFin, -1.0036F, 0.1745F, -0.6981F);
		LeftFin.texOffs(0, 13).addBox(0.0F, -2.5F, 0.0F, 1.0F, 5.0F, 11.0F, 0.0F, false);

		Head = new ModelRenderer(this);
		Head.setPos(0.0F, 7.0F, 0.0F);
		Body.addChild(Head);

		HeadTop = new ModelRenderer(this);
		HeadTop.setPos(0.0F, -9.5F, -24.0F);
		Head.addChild(HeadTop);
		this.setRotationAngle(HeadTop, 0.1309F, 0.0F, 0.0F);
		HeadTop.texOffs(78, 0).addBox(-5.5F, -3.5F, -7.0F, 11.0F, 7.0F, 14.0F, 0.0F, false);

		HeadBottom = new ModelRenderer(this);
		HeadBottom.setPos(0.0F, -1.0F, -9.0F);
		Head.addChild(HeadBottom);
		this.setRotationAngle(HeadBottom, -0.3054F, 0.0F, 0.0F);
		HeadBottom.texOffs(24, 1).addBox(-5.5F, -3.6993F, -19.9537F, 11.0F, 7.0F, 12.0F, 0.0F, false);

		Tail = new ModelRenderer(this);
		Tail.setPos(0.0F, 0.0F, 10.0F);
		Body.addChild(Tail);
		Tail.texOffs(30, 23).addBox(-5.0F, -6.5F, 0.0F, 10.0F, 12.0F, 11.0F, 0.0F, false);

		Tail2 = new ModelRenderer(this);
		Tail2.setPos(0.0F, -0.5F, 11.0F);
		Tail.addChild(Tail2);
		Tail2.texOffs(0, 47).addBox(-4.0F, -5.5F, 0.0F, 8.0F, 10.0F, 7.0F, 0.0F, false);

		BackFinTop = new ModelRenderer(this);
		BackFinTop.setPos(0.0F, -0.5F, 15.0F);
		Tail2.addChild(BackFinTop);
		this.setRotationAngle(BackFinTop, -0.7854F, 0.0F, 0.0F);
		BackFinTop.texOffs(108, 29).addBox(-1.0F, -3.4645F, -10.7071F, 2.0F, 11.0F, 6.0F, 0.0F, false);

		BackFinBottom = new ModelRenderer(this);
		BackFinBottom.setPos(0.0F, -0.5F, 13.0F);
		Tail2.addChild(BackFinBottom);
		this.setRotationAngle(BackFinBottom, 0.7854F, 0.0F, 0.0F);
		BackFinBottom.texOffs(32, 49).addBox(-1.0F, -4.0F, -9.0F, 2.0F, 10.0F, 5.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {

		LeftFin.yRot = -0.2F * MathHelper.cos(ageInTicks * 0.3F);
		RightFin.yRot = -0.2F * MathHelper.cos(ageInTicks * 0.3F);

		this.Body.xRot = headPitch * 0.0174532925F;
		this.Body.yRot = netHeadYaw * 0.0174532925F;

		if (Entity.getHorizontalDistanceSqr(entityIn.getDeltaMovement()) > 1.0E-7D) {
			this.Body.xRot += -0.05F + -0.05F * MathHelper.cos(ageInTicks * 0.3F);
			Tail.yRot = -0.15F * MathHelper.cos(ageInTicks * 0.3F);
			Tail2.yRot = -0.25F * MathHelper.cos(ageInTicks * 0.3F);
		} else {
			Tail.yRot = -0.05F * MathHelper.cos(ageInTicks * 0.3F);
			Tail2.yRot = -0.1F * MathHelper.cos(ageInTicks * 0.3F);
		}
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn,
			int packedOverlayIn, float red, float green, float blue, float alpha) {
		Body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}

}
