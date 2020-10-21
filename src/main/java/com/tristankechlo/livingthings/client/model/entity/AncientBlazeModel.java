package com.tristankechlo.livingthings.client.model.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.entities.AncientBlazeEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AncientBlazeModel <T extends AncientBlazeEntity> extends EntityModel<T> {

	private final ModelRenderer Body;
	private final ModelRenderer Head;
	private final ModelRenderer Helmet;
	private final ModelRenderer Crystal;
	private final ModelRenderer Shields;
	private final ModelRenderer[] shields = new ModelRenderer[4];
	private final ModelRenderer Sticks;
	private final ModelRenderer[] sticks = new ModelRenderer[LivingThingsConfig.ANCIENT_BLAZE.largeFireballAmount.get()];

	public AncientBlazeModel() {
		textureWidth = 64;
		textureHeight = 64;

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 11.0F, 0.0F);
		Body.setTextureOffset(25, 24).addBox(-2.5F, -22.0F, -2.5F, 5.0F, 35.0F, 5.0F, 0.0F, false);

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, -24.5F, 0.0F);
		Body.addChild(Head);
		Head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		Helmet = new ModelRenderer(this);
		Helmet.setRotationPoint(0.0F, 0.0F, 0.0F);
		Head.addChild(Helmet);
		Helmet.setTextureOffset(33, 0).addBox(3.5F, -9.5F, -4.5F, 1.0F, 10.0F, 2.0F, 0.0F, false);
		Helmet.setTextureOffset(9, 17).addBox(-3.5F, -8.5F, -4.5F, 7.0F, 3.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(9, 22).addBox(-2.5F, -9.5F, -4.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(16, 22).addBox(0.5F, -9.5F, -4.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(23, 22).addBox(-3.5F, -10.5F, -4.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(9, 25).addBox(1.5F, -10.5F, -4.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(16, 25).addBox(0.5F, -11.5F, -4.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(23, 25).addBox(-2.5F, -11.5F, -4.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(9, 28).addBox(-1.5F, -12.5F, -4.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(33, 13).addBox(1.5F, -3.5F, -4.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(40, 13).addBox(-3.5F, -3.5F, -4.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(40, 0).addBox(-4.5F, -9.5F, -4.5F, 1.0F, 10.0F, 2.0F, 0.0F, false);
		Helmet.setTextureOffset(0, 33).addBox(-2.5F, -8.5F, 3.5F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(0, 36).addBox(-2.5F, -0.5F, 3.5F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(47, 13).addBox(2.5F, -9.5F, 3.5F, 1.0F, 10.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(47, 25).addBox(-3.5F, -9.5F, 3.5F, 1.0F, 10.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(18, 28).addBox(-2.5F, -4.5F, 3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(33, 19).addBox(-0.5F, -7.5F, 3.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(38, 19).addBox(-0.5F, -2.5F, 3.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(47, 37).addBox(-1.5F, -6.5F, 3.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(47, 44).addBox(0.5F, -6.5F, 3.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(41, 24).addBox(1.5F, -4.5F, 3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(52, 29).addBox(3.5F, -1.5F, -2.5F, 1.0F, 2.0F, 5.0F, 0.0F, false);
		Helmet.setTextureOffset(52, 21).addBox(3.5F, -8.5F, -2.5F, 1.0F, 2.0F, 5.0F, 0.0F, false);
		Helmet.setTextureOffset(52, 13).addBox(-4.5F, -8.5F, -2.5F, 1.0F, 2.0F, 5.0F, 0.0F, false);
		Helmet.setTextureOffset(46, 51).addBox(3.5F, -3.5F, -0.5F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		Helmet.setTextureOffset(0, 39).addBox(-4.5F, -3.5F, -0.5F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		Helmet.setTextureOffset(47, 0).addBox(3.5F, -9.5F, 2.5F, 1.0F, 10.0F, 2.0F, 0.0F, false);
		Helmet.setTextureOffset(54, 0).addBox(-4.5F, -9.5F, 2.5F, 1.0F, 10.0F, 2.0F, 0.0F, false);
		Helmet.setTextureOffset(7, 40).addBox(-4.5F, -10.5F, 3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(13, 31).addBox(3.5F, -10.5F, 3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(13, 34).addBox(3.5F, -5.5F, -1.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(18, 34).addBox(-4.5F, -5.5F, -1.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(46, 55).addBox(3.5F, -5.5F, -0.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		Helmet.setTextureOffset(46, 60).addBox(-4.5F, -5.5F, -0.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		Helmet.setTextureOffset(52, 37).addBox(-4.5F, -1.5F, -2.5F, 1.0F, 2.0F, 5.0F, 0.0F, false);
		Helmet.setTextureOffset(52, 45).addBox(3.5F, -13.5F, -4.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(57, 45).addBox(-4.5F, -13.5F, -4.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(53, 51).addBox(2.5F, -15.5F, -4.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(58, 51).addBox(-3.5F, -15.5F, -4.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		Crystal = new ModelRenderer(this);
		Crystal.setRotationPoint(0.0F, 0.0F, 0.0F);
		Head.addChild(Crystal);
		Crystal.setTextureOffset(25, 2).addBox(-1.5F, -11.5F, -4.0F, 3.0F, 3.0F, 0.0F, 0.0F, false);
		Crystal.setTextureOffset(27, 6).addBox(-3.5F, -9.5F, -4.0F, 1.0F, 1.0F, 0.0F, 0.0F, false);
		Crystal.setTextureOffset(27, 6).addBox(2.5F, -9.5F, -4.0F, 1.0F, 1.0F, 0.0F, 0.0F, false);

		Shields = new ModelRenderer(this);
		Shields.setRotationPoint(0.0F, -22.0F, 0.0F);
		Body.addChild(Shields);
		setRotationAngle(Shields, 0.0F, -0.7854F, 0.0F);
		
		for (int i = 0; i < this.shields.length; i++) {
			this.shields[i] = new ModelRenderer(this, 0, 43);
			this.shields[i].addBox(-5.0F, 0.0F, -1.0F, 10.0F, 19.0F, 2.0F, 0.0F, false);
			this.shields[i].setRotationPoint(i * 7.5F, 0.0F, i * 7.5F);
			setRotationAngle(this.shields[i], -0.3491F, ((-1)^i) * 1.570796F, 0.0F);
			this.Shields.addChild(this.shields[i]);
		}

		Sticks = new ModelRenderer(this);
		Sticks.setRotationPoint(0.0F, 5.0F, 0.0F);
		Body.addChild(Sticks);
		
		for (int i = 0; i < this.sticks.length; i++) {
			this.sticks[i] = new ModelRenderer(this, 0, 18);
			this.sticks[i].addBox(-1.0F, -6.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);
			this.Sticks.addChild(this.sticks[i]);
		}
	}

	@Override
	public void setRotationAngles(T blaze, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.Head.rotateAngleY = netHeadYaw * 0.017453F;
	    this.Head.rotateAngleX = headPitch * 0.017453F;

	    float f = 0.785398F + ageInTicks * -0.094247F;
	    for(int i = 0; i < this.sticks.length; i++) {
	    	this.sticks[i].showModel = (blaze.getShoots() > i);
	    	this.sticks[i].rotationPointY = MathHelper.cos((i * 3F + ageInTicks) * 0.3F) - 1.0F;
	        this.sticks[i].rotationPointX = MathHelper.cos(f) * 9.0F;
	        this.sticks[i].rotationPointZ = MathHelper.sin(f) * 9.0F;
	        f++;
	    }
	    	    
	    if(blaze.isCharged()) {
		    this.Shields.rotateAngleY = 0.785398F;
		    this.Head.rotationPointY = -22.5F;

		    for(int i = 0; i < this.shields.length; i++) {
		    	this.shields[i].rotationPointX = MathHelper.cos(i * 1.570796F) * 6.0F;
		    	this.shields[i].rotationPointZ = MathHelper.sin(i * 1.570796F) * 6.0F;
		    	this.shields[i].rotateAngleX = 0F;
		    }
	         
	    } else {
		    this.Shields.rotateAngleY = -ageInTicks / 50;
		    this.Head.rotationPointY = -24.5F;

		    for(int i = 0; i < this.shields.length; i++) {
		    	this.shields[i].rotationPointX = MathHelper.cos(i * 1.570796F) * 7.5F;
		    	this.shields[i].rotationPointZ = MathHelper.sin(i * 1.570796F) * 7.5F;
		    	this.shields[i].rotateAngleX = -0.349065F;
		    }
		    
	    }
	}
	
	@Override
	public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
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
