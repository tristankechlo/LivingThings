package com.tristankechlo.livingthings.client.model.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.tristankechlo.livingthings.client.model.AdvancedEntityModel;
import com.tristankechlo.livingthings.entities.ShroomieEntity;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ShroomieModel<T extends ShroomieEntity> extends AdvancedEntityModel<T> {

	private final ModelRenderer Body;
	private final ModelRenderer LegLeft;
	private final ModelRenderer LegRight;
	private final ModelRenderer HeadBrown;
	private final ModelRenderer HeadRed;
	private final ModelRenderer ArmLeft;
	private final ModelRenderer ArmRight;

	public ShroomieModel() {
		texWidth = 48;
		texHeight = 32;

		Body = new ModelRenderer(this);
		Body.setPos(0.0F, 24.0F, 0.0F);
		Body.texOffs(0, 18).addBox(-3.0F, -11.0F, -3.0F, 6.0F, 8.0F, 6.0F, 0.0F, false);

		LegLeft = new ModelRenderer(this);
		LegLeft.setPos(-1.5F, -3.0F, 0.0F);
		Body.addChild(LegLeft);
		LegLeft.texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, true);

		LegRight = new ModelRenderer(this);
		LegRight.setPos(1.5F, -3.0F, 0.0F);
		Body.addChild(LegRight);
		LegRight.texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);

		HeadBrown = new ModelRenderer(this);
		HeadBrown.setPos(0.0F, -11.0F, 0.0F);
		Body.addChild(HeadBrown);
		HeadBrown.texOffs(0, 5).addBox(-5.0F, -3.0F, -5.0F, 10.0F, 3.0F, 10.0F, 0.0F, false);

		HeadRed = new ModelRenderer(this);
		HeadRed.setPos(0.0F, -11.0F, 0.0F);
		Body.addChild(HeadRed);
		HeadRed.texOffs(0, 4).addBox(-5.0F, -4.0F, -5.0F, 10.0F, 4.0F, 10.0F, 0.0F, false);
		HeadRed.texOffs(24, 23).addBox(-3.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, 0.0F, false);

		ArmLeft = new ModelRenderer(this);
		ArmLeft.setPos(3.0F, -9.0F, 0.0F);
		Body.addChild(ArmLeft);
		ArmLeft.texOffs(0, 6).addBox(0.0F, 0.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, false);

		ArmRight = new ModelRenderer(this);
		ArmRight.setPos(-3.0F, -9.0F, 0.0F);
		Body.addChild(ArmRight);
		ArmRight.texOffs(0, 6).addBox(-1.0F, 0.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, true);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {

		if (entity.getVariant() == 0) {
			HeadBrown.visible = true;
			HeadRed.visible = false;
		} else {
			HeadBrown.visible = false;
			HeadRed.visible = true;
		}

		this.walking1(LegLeft, limbSwing, limbSwingAmount);
		this.walking2(LegRight, limbSwing, limbSwingAmount);
		this.walking2(ArmLeft, limbSwing, limbSwingAmount);
		this.walking1(ArmRight, limbSwing, limbSwingAmount);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		Body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

}