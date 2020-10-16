package com.tristankechlo.livingthings.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.tristankechlo.livingthings.entities.AncientBlazeEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AncientBlazeModel <T extends AncientBlazeEntity> extends EntityModel<T> {
	
	private final ModelRenderer Body;
	private final ModelRenderer Head;
	private final ModelRenderer Helmet;
	private final ModelRenderer Shields;
	private final ModelRenderer one;
	private final ModelRenderer two;
	private final ModelRenderer three;
	private final ModelRenderer four;

	public AncientBlazeModel() {
		textureWidth = 128;
		textureHeight = 64;

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 24.0F, 0.0F);
		Body.setTextureOffset(54, 4).addBox(-2.0F, -22.0F, -2.0F, 4.0F, 22.0F, 4.0F, 0.0F, false);

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, -24.5F, 0.0F);
		Body.addChild(Head);
		Head.setTextureOffset(92, 1).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		Helmet = new ModelRenderer(this);
		Helmet.setRotationPoint(0.0F, 0.0F, 0.0F);
		Head.addChild(Helmet);
		Helmet.setTextureOffset(76, 54).addBox(3.5F, -8.5F, -4.5F, 1.0F, 9.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(88, 57).addBox(-3.5F, -8.3F, -4.5F, 7.0F, 2.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(108, 62).addBox(-3.5F, -8.2F, -3.5F, 1.0F, 1.0F, 8.0F, 0.0F, false);
		Helmet.setTextureOffset(68, 45).addBox(2.5F, -8.2F, -3.5F, 1.0F, 1.0F, 8.0F, 0.0F, false);
		Helmet.setTextureOffset(68, 45).addBox(3.5F, -8.2F, -2.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(68, 45).addBox(-4.5F, -8.2F, -2.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(68, 45).addBox(-4.5F, -8.2F, 2.5F, 9.0F, 1.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(86, 52).addBox(-2.5F, -9.3F, -4.5F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(85, 62).addBox(-1.5F, -10.3F, -4.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(110, 54).addBox(1.5F, -3.5F, -4.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(112, 28).addBox(-3.5F, -3.5F, -4.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(121, 23).addBox(-4.5F, -8.5F, -4.5F, 1.0F, 9.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(87, 22).addBox(-4.5F, -7.5F, 3.5F, 9.0F, 8.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(112, 35).addBox(3.5F, -7.5F, -3.5F, 1.0F, 8.0F, 7.0F, 0.0F, false);
		Helmet.setTextureOffset(93, 35).addBox(-4.5F, -7.5F, -3.5F, 1.0F, 8.0F, 7.0F, 0.0F, false);

		Shields = new ModelRenderer(this);
		Shields.setRotationPoint(0.0F, -22.0F, 0.0F);
		Body.addChild(Shields);
		setRotationAngle(Shields, 0.0F, -0.7854F, 0.0F);
		

		one = new ModelRenderer(this);
		one.setRotationPoint(-7.75F, 0.0F, 0.0F);
		Shields.addChild(one);
		setRotationAngle(one, 0.0F, 0.0F, 0.2182F);
		one.setTextureOffset(27, 1).addBox(-1.0F, 0.0F, -5.0F, 2.0F, 19.0F, 10.0F, 0.0F, false);

		two = new ModelRenderer(this);
		two.setRotationPoint(0.0F, 0.0F, 7.75F);
		Shields.addChild(two);
		setRotationAngle(two, 0.2182F, 0.0F, 0.0F);
		two.setTextureOffset(0, 42).addBox(-5.0F, 0.0F, -1.0F, 10.0F, 19.0F, 2.0F, 0.0F, false);

		three = new ModelRenderer(this);
		three.setRotationPoint(7.75F, 0.0F, 0.0F);
		Shields.addChild(three);
		setRotationAngle(three, 0.0F, 0.0F, -0.2182F);
		three.setTextureOffset(0, 1).addBox(-1.0F, 0.0F, -5.0F, 2.0F, 19.0F, 10.0F, 0.0F, false);

		four = new ModelRenderer(this);
		four.setRotationPoint(0.0F, 0.0F, -7.75F);
		Shields.addChild(four);
		setRotationAngle(four, -0.2182F, 0.0F, 0.0F);
		four.setTextureOffset(27, 42).addBox(-5.0F, 0.0F, -1.0F, 10.0F, 19.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,	float netHeadYaw, float headPitch) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Body.render(matrixStack, buffer, packedLight, packedOverlay);
		
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

}
