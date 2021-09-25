package com.tristankechlo.livingthings.client.model.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.tristankechlo.livingthings.client.model.AdvancedEntityModel;
import com.tristankechlo.livingthings.entities.SeahorseEntity;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SeahorseModel<T extends SeahorseEntity> extends AdvancedEntityModel<T> {

	private final ModelRenderer Body;
	private final ModelRenderer Head;
	private final ModelRenderer mouth;
	private final ModelRenderer mouth_bottom;
	private final ModelRenderer Ear_Right;
	private final ModelRenderer Ear_Left;
	private final ModelRenderer throat_front;
	private final ModelRenderer tail_front;
	private final ModelRenderer tail;
	private final ModelRenderer tail1;
	private final ModelRenderer tail2;
	private final ModelRenderer tail3;
	private final ModelRenderer tail4;
	private final ModelRenderer tail5;
	private final ModelRenderer tail6;
	private final ModelRenderer tail7;

	public SeahorseModel() {
		texWidth = 16;
		texHeight = 16;

		Body = new ModelRenderer(this);
		Body.setPos(0.0F, 18.05F, 0.0F);
		setRotationAngle(Body, 0.1745F, 0.0F, 0.0F);
		Body.texOffs(8, 11).addBox(-1.0F, -1.2F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
		Body.texOffs(4, 13).addBox(-0.5F, -2.85F, -0.05F, 1.0F, 2.0F, 1.0F, 0.025F, false);
		Body.texOffs(4, 13).addBox(-0.5F, 1.3F, -0.05F, 1.0F, 2.0F, 1.0F, 0.025F, true);
		Body.texOffs(12, 3).addBox(0.0F, -2.45F, 0.3F, 0.0F, 6.0F, 2.0F, 0.0F, false);

		Head = new ModelRenderer(this);
		Head.setPos(0.0F, -2.8153F, 0.197F);
		Body.addChild(Head);
		setRotationAngle(Head, -0.0873F, 0.0F, 0.0F);
		Head.texOffs(8, 0).addBox(-1.0F, -1.8707F, -1.0981F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		mouth = new ModelRenderer(this);
		mouth.setPos(0.0F, -0.4207F, -0.8981F);
		Head.addChild(mouth);
		setRotationAngle(mouth, 0.1309F, 0.0F, 0.0F);
		mouth.texOffs(4, 6).addBox(-0.5F, -0.825F, -1.825F, 1.0F, 1.0F, 2.0F, 0.0F, false);

		mouth_bottom = new ModelRenderer(this);
		mouth_bottom.setPos(0.0F, 0.15F, -0.1F);
		mouth.addChild(mouth_bottom);
		setRotationAngle(mouth_bottom, 0.1745F, 0.0F, 0.0F);
		mouth_bottom.texOffs(4, 9).addBox(-0.5F, -0.5834F, -1.5624F, 1.0F, 1.0F, 2.0F, -0.025F, false);

		Ear_Right = new ModelRenderer(this);
		Ear_Right.setPos(-1.0F, -1.3457F, 0.4019F);
		Head.addChild(Ear_Right);
		setRotationAngle(Ear_Right, 0.1309F, -0.4363F, 0.0F);
		Ear_Right.texOffs(9, 4).addBox(0.1F, -0.675F, -0.025F, 0.0F, 2.0F, 1.0F, 0.0F, false);

		Ear_Left = new ModelRenderer(this);
		Ear_Left.setPos(1.0F, -1.3457F, 0.4019F);
		Head.addChild(Ear_Left);
		setRotationAngle(Ear_Left, 0.1309F, 0.4363F, 0.0F);
		Ear_Left.texOffs(9, 4).addBox(-0.1F, -0.675F, -0.025F, 0.0F, 2.0F, 1.0F, 0.0F, false);

		throat_front = new ModelRenderer(this);
		throat_front.setPos(0.0F, -1.875F, -0.2F);
		Body.addChild(throat_front);
		setRotationAngle(throat_front, -0.3054F, 0.0F, 0.0F);
		throat_front.texOffs(4, 13).addBox(-0.5F, -0.9925F, -0.5238F, 1.0F, 2.0F, 1.0F, -0.025F, true);

		tail_front = new ModelRenderer(this);
		tail_front.setPos(0.0F, 2.65F, -0.075F);
		Body.addChild(tail_front);
		setRotationAngle(tail_front, 0.48F, 0.0F, 0.0F);
		tail_front.texOffs(0, 13).addBox(-0.5F, -1.175F, -0.4F, 1.0F, 2.0F, 1.0F, -0.025F, false);

		tail = new ModelRenderer(this);
		tail.setPos(0.0F, 3.25F, 0.475F);
		Body.addChild(tail);
		setRotationAngle(tail, -0.0436F, 0.0F, 0.0F);
		tail.texOffs(0, 0).addBox(-0.5F, 0.0F, -0.525F, 1.0F, 1.0F, 1.0F, -0.001F, false);

		tail1 = new ModelRenderer(this);
		tail1.setPos(0.0F, 0.875F, -0.025F);
		tail.addChild(tail1);
		setRotationAngle(tail1, -0.3927F, 0.0F, 0.0F);
		tail1.texOffs(0, 2).addBox(-0.5F, -0.106F, -0.4908F, 1.0F, 1.0F, 1.0F, -0.01F, false);

		tail2 = new ModelRenderer(this);
		tail2.setPos(0.0F, 0.819F, 0.0342F);
		tail1.addChild(tail2);
		setRotationAngle(tail2, -0.5236F, 0.0F, 0.0F);
		tail2.texOffs(0, 4).addBox(-0.5F, -0.1799F, -0.55F, 1.0F, 1.0F, 1.0F, -0.02F, false);

		tail3 = new ModelRenderer(this);
		tail3.setPos(0.0F, 0.674F, -0.0366F);
		tail2.addChild(tail3);
		setRotationAngle(tail3, -0.5672F, 0.0F, 0.0F);
		tail3.texOffs(0, 6).addBox(-0.5F, -0.175F, -0.5F, 1.0F, 1.0F, 1.0F, -0.03F, false);

		tail4 = new ModelRenderer(this);
		tail4.setPos(0.0F, 0.7502F, 0.0533F);
		tail3.addChild(tail4);
		setRotationAngle(tail4, -0.5672F, 0.0F, 0.0F);
		tail4.texOffs(0, 8).addBox(-0.5F, -0.2476F, -0.594F, 1.0F, 1.0F, 1.0F, -0.04F, false);

		tail5 = new ModelRenderer(this);
		tail5.setPos(0.0F, 0.7024F, -0.119F);
		tail4.addChild(tail5);
		setRotationAngle(tail5, -0.5236F, 0.0F, 0.0F);
		tail5.texOffs(4, 0).addBox(-0.5F, -0.2725F, -0.5492F, 1.0F, 1.0F, 1.0F, -0.05F, false);

		tail6 = new ModelRenderer(this);
		tail6.setPos(0.0F, 0.6261F, -0.0859F);
		tail5.addChild(tail6);
		setRotationAngle(tail6, -0.5672F, 0.0F, 0.0F);
		tail6.texOffs(4, 2).addBox(-0.5F, -0.2747F, -0.5139F, 1.0F, 1.0F, 1.0F, -0.06F, false);

		tail7 = new ModelRenderer(this);
		tail7.setPos(0.0F, 0.623F, -0.0488F);
		tail6.addChild(tail7);
		setRotationAngle(tail7, -0.48F, 0.0F, 0.0F);
		tail7.texOffs(4, 4).addBox(-0.5F, -0.2693F, -0.5188F, 1.0F, 1.0F, 1.0F, -0.07F, false);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {
		this.defaultHeadMovement(Head, -5, 0, headPitch, netHeadYaw);

		Ear_Right.yRot = -0.2F * MathHelper.cos(ageInTicks * 0.25F) - 0.55F;
		Ear_Left.yRot = 0.2F * MathHelper.cos(ageInTicks * 0.25F) + 0.55F;

		tail.xRot = -0.1F * MathHelper.cos(ageInTicks * 0.1F) - 0.0436F;
		tail5.xRot = -0.2F * MathHelper.cos(ageInTicks * 0.1F) - 0.5236F;
		tail7.xRot = -0.1F * MathHelper.cos(ageInTicks * 0.15F) - 0.48F;
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		Body.render(matrixStack, buffer, packedLight, packedOverlay);
	}

}
