package com.tristankechlo.livingthings.client.model;

import java.util.Random;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.tristankechlo.livingthings.entities.ElephantEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ElephantModel<T extends ElephantEntity> extends EntityModel<T> {
	
	private float[][] currentTrunkAngle= { {0F,0F,0F}, {0F,0F,0F}, {0F,0F,0F} };
	private float[][] targetTrunkAngle= { {0.17F,0.17F,0.17F}, {0.17F,0.17F,0.17F}, {0.17F,0.17F,0.17F} };
	private boolean[][] countUpwards = { {true,true,true}, {true,true,true}, {true,true,true} };

	private final ModelRenderer Tusks;
	private final ModelRenderer LeftTusk;
	private final ModelRenderer LeftTuskTop;
	private final ModelRenderer LeftTuskMiddle;
	private final ModelRenderer LeftTuskBottom;
	private final ModelRenderer RightTusk;
	private final ModelRenderer RightTuskTop;
	private final ModelRenderer RightTuskMiddle;
	private final ModelRenderer RightTuskBottom;
	private final ModelRenderer Body;
	private final ModelRenderer Legs;
	private final ModelRenderer Front;
	private final ModelRenderer RightFrontLeg;
	private final ModelRenderer LeftFrontLeg;
	private final ModelRenderer Back;
	private final ModelRenderer RightBackLeg;
	private final ModelRenderer LeftBackLeg;
	private final ModelRenderer Head;
	private final ModelRenderer TrunkTop;
	private final ModelRenderer TrunkMiddle;
	private final ModelRenderer TrunkBottom;
	private final ModelRenderer Ears;
	private final ModelRenderer LeftEar;
	private final ModelRenderer RightEar;
	private final ModelRenderer Chests;
	private final ModelRenderer HolderLeft;
	private final ModelRenderer HolderRight;
	private final ModelRenderer Saddle;
	private final ModelRenderer SaddleRight;
	private final ModelRenderer SaddleLeft;

	public ElephantModel() {
		textureWidth = 256;
		textureHeight = 128;

		Tusks = new ModelRenderer(this);
		Tusks.setRotationPoint(0.0F, -12.0F, -19.0F);
		

		LeftTusk = new ModelRenderer(this);
		LeftTusk.setRotationPoint(7.0F, 6.0F, -10.0F);
		Tusks.addChild(LeftTusk);
		

		LeftTuskTop = new ModelRenderer(this);
		LeftTuskTop.setRotationPoint(0.0F, 0.0F, 0.0F);
		LeftTusk.addChild(LeftTuskTop);
		LeftTuskTop.setTextureOffset(192, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);

		LeftTuskMiddle = new ModelRenderer(this);
		LeftTuskMiddle.setRotationPoint(0.0F, 10.0F, 0.0F);
		LeftTuskTop.addChild(LeftTuskMiddle);
		setRotationAngle(LeftTuskMiddle, -0.3054F, 0.0F, 0.0F);
		LeftTuskMiddle.setTextureOffset(204, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		LeftTuskBottom = new ModelRenderer(this);
		LeftTuskBottom.setRotationPoint(0.0F, 6.0F, 0.0F);
		LeftTuskMiddle.addChild(LeftTuskBottom);
		setRotationAngle(LeftTuskBottom, -0.6545F, 0.0F, 0.0F);
		LeftTuskBottom.setTextureOffset(215, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

		RightTusk = new ModelRenderer(this);
		RightTusk.setRotationPoint(-7.0F, 6.0F, -10.0F);
		Tusks.addChild(RightTusk);
		

		RightTuskTop = new ModelRenderer(this);
		RightTuskTop.setRotationPoint(14.0F, 0.0F, 0.0F);
		RightTusk.addChild(RightTuskTop);
		RightTuskTop.setTextureOffset(228, 0).addBox(-15.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);

		RightTuskMiddle = new ModelRenderer(this);
		RightTuskMiddle.setRotationPoint(0.0F, 10.0F, 0.0F);
		RightTuskTop.addChild(RightTuskMiddle);
		setRotationAngle(RightTuskMiddle, -0.3054F, 0.0F, 0.0F);
		RightTuskMiddle.setTextureOffset(238, 0).addBox(-15.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		RightTuskBottom = new ModelRenderer(this);
		RightTuskBottom.setRotationPoint(0.0F, 6.0F, 0.0F);
		RightTuskMiddle.addChild(RightTuskBottom);
		setRotationAngle(RightTuskBottom, -0.6545F, 0.0F, 0.0F);
		RightTuskBottom.setTextureOffset(248, 0).addBox(-15.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 24.0F, 0.0F);
		Body.setTextureOffset(0, 64).addBox(-11.0F, -42.0F, -20.0F, 22.0F, 24.0F, 40.0F, 0.0F, false);
		Body.setTextureOffset(156, 93).addBox(-8.0F, -43.0F, -17.0F, 16.0F, 1.0F, 34.0F, 0.0F, false);

		Legs = new ModelRenderer(this);
		Legs.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.addChild(Legs);
		

		Front = new ModelRenderer(this);
		Front.setRotationPoint(0.0F, 0.0F, 0.0F);
		Legs.addChild(Front);
		

		RightFrontLeg = new ModelRenderer(this);
		RightFrontLeg.setRotationPoint(-8.0F, -19.0F, -16.0F);
		Front.addChild(RightFrontLeg);
		RightFrontLeg.setTextureOffset(68, 0).addBox(-3.0F, 1.0F, -3.0F, 6.0F, 18.0F, 6.0F, 0.0F, false);

		LeftFrontLeg = new ModelRenderer(this);
		LeftFrontLeg.setRotationPoint(8.0F, -19.0F, -16.0F);
		Front.addChild(LeftFrontLeg);
		LeftFrontLeg.setTextureOffset(95, 0).addBox(-3.0F, 1.0F, -3.0F, 6.0F, 18.0F, 6.0F, 0.0F, false);

		Back = new ModelRenderer(this);
		Back.setRotationPoint(0.0F, 0.0F, 0.0F);
		Legs.addChild(Back);
		

		RightBackLeg = new ModelRenderer(this);
		RightBackLeg.setRotationPoint(-8.0F, -19.0F, 16.0F);
		Back.addChild(RightBackLeg);
		RightBackLeg.setTextureOffset(122, 0).addBox(-3.0F, 1.0F, -3.0F, 6.0F, 18.0F, 6.0F, 0.0F, false);

		LeftBackLeg = new ModelRenderer(this);
		LeftBackLeg.setRotationPoint(8.0F, -19.0F, 16.0F);
		Back.addChild(LeftBackLeg);
		LeftBackLeg.setTextureOffset(149, 0).addBox(-3.0F, 1.0F, -3.0F, 6.0F, 18.0F, 6.0F, 0.0F, false);

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, -36.0F, -20.0F);
		Body.addChild(Head);
		setRotationAngle(Head, -0.0436F, 0.0F, 0.0F);
		Head.setTextureOffset(123, 45).addBox(-9.0F, -9.0F, -12.0F, 18.0F, 16.0F, 13.0F, 0.0F, false);

		TrunkTop = new ModelRenderer(this);
		TrunkTop.setRotationPoint(0.0F, 7.0F, -8.0F);
		Head.addChild(TrunkTop);
		setRotationAngle(TrunkTop, 0.0436F, 0.0F, 0.0F);
		TrunkTop.setTextureOffset(0, 0).addBox(-4.0F, -0.1F, -4.0F, 8.0F, 10.0F, 8.0F, 0.0F, false);

		TrunkMiddle = new ModelRenderer(this);
		TrunkMiddle.setRotationPoint(0.0F, 10.0F, 0.0F);
		TrunkTop.addChild(TrunkMiddle);
		setRotationAngle(TrunkMiddle, 0.0436F, 0.0F, 0.0F);
		TrunkMiddle.setTextureOffset(0, 26).addBox(-3.0F, -0.2F, -3.0F, 6.0F, 8.0F, 6.0F, 0.0F, false);

		TrunkBottom = new ModelRenderer(this);
		TrunkBottom.setRotationPoint(0.0F, 7.0F, 0.0F);
		TrunkMiddle.addChild(TrunkBottom);
		setRotationAngle(TrunkBottom, 0.0873F, 0.0F, 0.0F);
		TrunkBottom.setTextureOffset(0, 44).addBox(-2.0F, 0.7F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, false);

		Ears = new ModelRenderer(this);
		Ears.setRotationPoint(0.0F, 0.0F, 0.0F);
		Head.addChild(Ears);
		

		LeftEar = new ModelRenderer(this);
		LeftEar.setRotationPoint(9.0F, -1.0F, -6.0F);
		Ears.addChild(LeftEar);
		setRotationAngle(LeftEar, 0.1309F, 0.48F, 0.0F);
		LeftEar.setTextureOffset(200, 37).addBox(0.0F, -8.0F, 0.0F, 1.0F, 12.0F, 8.0F, 0.0F, false);

		RightEar = new ModelRenderer(this);
		RightEar.setRotationPoint(-9.0F, -1.0F, -6.0F);
		Ears.addChild(RightEar);
		setRotationAngle(RightEar, 0.1309F, -0.48F, 0.0F);
		RightEar.setTextureOffset(200, 37).addBox(-1.0F, -8.0F, 0.0F, 1.0F, 12.0F, 8.0F, 0.0F, false);

		Chests = new ModelRenderer(this);
		Chests.setRotationPoint(0.0F, 24.0F, 0.0F);
		Chests.setTextureOffset(54, 38).addBox(11.0F, -37.0F, 9.5F, 2.0F, 8.0F, 8.0F, 0.0F, false);
		Chests.setTextureOffset(54, 38).addBox(-13.0F, -37.0F, 9.5F, 2.0F, 8.0F, 8.0F, 0.0F, true);
		Chests.setTextureOffset(136, 85).addBox(10.2F, -42.475F, 11.5F, 1.0F, 6.0F, 4.0F, 0.0F, false);
		Chests.setTextureOffset(136, 85).addBox(-11.2F, -42.475F, 11.5F, 1.0F, 6.0F, 4.0F, 0.0F, false);
		Chests.setTextureOffset(125, 92).addBox(-8.5F, -43.475F, 11.5F, 17.0F, 1.0F, 4.0F, 0.0F, false);

		HolderLeft = new ModelRenderer(this);
		HolderLeft.setRotationPoint(9.6F, -42.5F, 13.5F);
		Chests.addChild(HolderLeft);
		setRotationAngle(HolderLeft, 0.0F, 0.0F, 0.3491F);
		HolderLeft.setTextureOffset(134, 89).addBox(-1.5F, -0.5F, -2.0F, 3.0F, 2.0F, 4.0F, 0.0F, false);

		HolderRight = new ModelRenderer(this);
		HolderRight.setRotationPoint(-9.6F, -42.5F, 13.5F);
		Chests.addChild(HolderRight);
		setRotationAngle(HolderRight, 0.0F, 0.0F, -0.3491F);
		HolderRight.setTextureOffset(134, 89).addBox(-1.5F, -0.5F, -2.0F, 3.0F, 2.0F, 4.0F, 0.0F, false);

		Saddle = new ModelRenderer(this);
		Saddle.setRotationPoint(0.0F, 24.0F, 0.0F);
		Saddle.setTextureOffset(114, 84).addBox(-6.5F, -44.0F, -7.0F, 13.0F, 1.0F, 12.0F, 0.0F, false);
		Saddle.setTextureOffset(124, 96).addBox(-6.0F, -45.0F, 3.0F, 12.0F, 1.0F, 2.0F, 0.0F, false);
		Saddle.setTextureOffset(136, 85).addBox(-11.2F, -42.475F, -2.0F, 1.0F, 10.0F, 2.0F, 0.0F, false);
		Saddle.setTextureOffset(136, 85).addBox(10.2F, -42.475F, -2.0F, 1.0F, 10.0F, 2.0F, 0.0F, false);
		Saddle.setTextureOffset(144, 90).addBox(-1.5F, -46.0F, -7.0F, 3.0F, 2.0F, 2.0F, 0.0F, false);
		Saddle.setTextureOffset(245, 43).addBox(10.4F, -32.5F, -2.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		Saddle.setTextureOffset(245, 43).addBox(-11.4F, -32.5F, -2.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		Saddle.setTextureOffset(245, 43).addBox(10.4F, -32.0F, -2.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		Saddle.setTextureOffset(245, 43).addBox(-11.4F, -32.0F, -2.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);

		SaddleRight = new ModelRenderer(this);
		SaddleRight.setRotationPoint(-8.625F, -43.175F, -1.5F);
		Saddle.addChild(SaddleRight);
		setRotationAngle(SaddleRight, 0.0F, 0.0F, -0.3054F);
		SaddleRight.setTextureOffset(130, 84).addBox(-2.5492F, -0.0726F, -0.5F, 5.0F, 1.0F, 2.0F, 0.0F, false);

		SaddleLeft = new ModelRenderer(this);
		SaddleLeft.setRotationPoint(8.725F, -43.15F, -1.5F);
		Saddle.addChild(SaddleLeft);
		setRotationAngle(SaddleLeft, 0.0F, 0.0F, 0.3054F);
		SaddleLeft.setTextureOffset(130, 84).addBox(-2.5492F, -0.0726F, -0.5F, 5.0F, 1.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		if(this.isChild) {
			matrixStack.scale(0.6F, 0.6F, 0.6F);
			matrixStack.translate(0, 1, 0);
		} else {
			Tusks.render(matrixStack, buffer, packedLight, packedOverlay);
		}
		Body.render(matrixStack, buffer, packedLight, packedOverlay);
		Chests.render(matrixStack, buffer, packedLight, packedOverlay);
		Saddle.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	@Override
	public void setRotationAngles(T elephant, float limbSwing, float limbSwingAmount, float ageInTicks,	float netHeadYaw, float headPitch) {
	    int i = elephant.getAttackTimer();
	    if (i == 0) {
			this.Head.rotateAngleX = headPitch * ((float) Math.PI / 180F);
			this.Tusks.rotateAngleX = headPitch * ((float) Math.PI / 180F);
	    }
		this.Head.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
		this.Tusks.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
		
		this.RightFrontLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F	* limbSwingAmount;
		this.RightBackLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.LeftFrontLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.LeftBackLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		
		this.TrunkTop.rotateAngleX = this.currentTrunkAngle[0][0];
		this.TrunkTop.rotateAngleY = this.currentTrunkAngle[0][1];
		this.TrunkTop.rotateAngleZ = this.currentTrunkAngle[0][2];
		
		this.TrunkMiddle.rotateAngleX = this.currentTrunkAngle[1][0];
		this.TrunkMiddle.rotateAngleY = this.currentTrunkAngle[1][1];
		this.TrunkMiddle.rotateAngleZ = this.currentTrunkAngle[1][2];
		
		this.TrunkBottom.rotateAngleX = this.currentTrunkAngle[2][0];
		this.TrunkBottom.rotateAngleY = this.currentTrunkAngle[2][1];
		this.TrunkBottom.rotateAngleZ = this.currentTrunkAngle[2][2];

	}

	@Override
	public void setLivingAnimations(T elephant, float limbSwing, float limbSwingAmount, float partialTick) {
		if(partialTick > 0.5F) {
			setTrunkAngle();
		}
	    int i = elephant.getAttackTimer();
	    if (i > 0) {
	       this.Head.rotateAngleX = 1.7F * MathHelper.func_233021_e_((float)i - partialTick, 10.0F);
	       this.Tusks.rotateAngleX = 1.7F * MathHelper.func_233021_e_((float)i - partialTick, 10.0F);
	    }
	    
	    this.Chests.showModel = elephant.hasChest();
	    this.Saddle.showModel = elephant.isSaddled();

	}

	private void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
	private void setTrunkAngle() {
		float[] offset = { 0.0025F, 0.001F, 0.0025F};
		
	    for (int part = 0; part < this.currentTrunkAngle.length; part++) {
	        for (int axis = 0; axis < this.currentTrunkAngle[part].length; axis++) {
	        	
	        	if(this.countUpwards[part][axis] == true) {
	        		
	        		this.currentTrunkAngle[part][axis] += offset[axis];
	        		
	        		if(this.currentTrunkAngle[part][axis] > this.targetTrunkAngle[part][axis]) {
	        			this.targetTrunkAngle[part][axis] = (float)(0 - ((new Random().nextFloat() * 0.1F) + 0.1F));
	        			this.countUpwards[part][axis] = false;
	        		}
	        		
	        		continue;
	        	} else if(this.countUpwards[part][axis] == false) {

	        		this.currentTrunkAngle[part][axis] -= offset[axis];
	        		
	        		if(this.currentTrunkAngle[part][axis] < this.targetTrunkAngle[part][axis]) {
	        			this.targetTrunkAngle[part][axis] = (float)((new Random().nextFloat() * 0.1F) + 0.1F);
	        			this.countUpwards[part][axis] = true;
	        		}
	        		
	        		continue;	        		
	        	}
	        }
	    }
	}
	
}
