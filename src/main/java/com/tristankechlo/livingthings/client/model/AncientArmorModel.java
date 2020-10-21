package com.tristankechlo.livingthings.client.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AncientArmorModel extends BipedModel<LivingEntity> {

	public AncientArmorModel(float modelSize) {
		super(modelSize, 0, 64, 64);

		ModelRenderer Helmet = new ModelRenderer(this);
		Helmet.setRotationPoint(0.0F, 0.0F, 0.0F);

		ModelRenderer HelmetForm = new ModelRenderer(this);
		HelmetForm.setRotationPoint(0.0F, 0.0F, 0.0F);
		Helmet.addChild(HelmetForm);
		HelmetForm.setTextureOffset(0, 32).addBox(3.5F, -8.9F, -4.5F, 1.0F, 9.0F, 2.0F, 0.0F, false);
		HelmetForm.setTextureOffset(32, 32).addBox(-3.5F, -7.9F, -4.5F, 7.0F, 3.0F, 1.0F, 0.0F, false);
		HelmetForm.setTextureOffset(32, 36).addBox(-2.5F, -8.9F, -4.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		HelmetForm.setTextureOffset(38, 36).addBox(0.5F, -8.9F, -4.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		HelmetForm.setTextureOffset(32, 38).addBox(-3.5F, -9.9F, -4.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		HelmetForm.setTextureOffset(38, 38).addBox(1.5F, -9.9F, -4.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		HelmetForm.setTextureOffset(32, 40).addBox(0.5F, -10.9F, -4.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		HelmetForm.setTextureOffset(38, 40).addBox(-2.5F, -10.9F, -4.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		HelmetForm.setTextureOffset(48, 32).addBox(-1.5F, -11.9F, -4.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		HelmetForm.setTextureOffset(56, 32).addBox(1.5F, -2.9F, -4.5F, 2.0F, 3.0F, 1.0F, 0.0F, false);
		HelmetForm.setTextureOffset(56, 37).addBox(-3.5F, -2.9F, -4.5F, 2.0F, 3.0F, 1.0F, 0.0F, false);
		HelmetForm.setTextureOffset(6, 32).addBox(-4.5F, -8.9F, -4.5F, 1.0F, 9.0F, 2.0F, 0.0F, false);
		HelmetForm.setTextureOffset(44, 36).addBox(-2.5F, -8.9F, 3.5F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		HelmetForm.setTextureOffset(44, 38).addBox(-2.5F, -0.9F, 3.5F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		HelmetForm.setTextureOffset(24, 32).addBox(2.5F, -9.9F, 3.5F, 1.0F, 10.0F, 1.0F, 0.0F, false);
		HelmetForm.setTextureOffset(28, 32).addBox(-3.5F, -9.9F, 3.5F, 1.0F, 10.0F, 1.0F, 0.0F, false);
		HelmetForm.setTextureOffset(44, 40).addBox(-2.5F, -4.9F, 3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		HelmetForm.setTextureOffset(32, 42).addBox(-0.5F, -7.9F, 3.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		HelmetForm.setTextureOffset(36, 42).addBox(-0.5F, -2.9F, 3.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		HelmetForm.setTextureOffset(0, 44).addBox(-1.5F, -6.9F, 3.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
		HelmetForm.setTextureOffset(4, 44).addBox(0.5F, -6.9F, 3.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
		HelmetForm.setTextureOffset(48, 40).addBox(1.5F, -4.9F, 3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		HelmetForm.setTextureOffset(0, 50).addBox(3.5F, -1.9F, -2.5F, 1.0F, 2.0F, 5.0F, 0.0F, false);
		HelmetForm.setTextureOffset(12, 50).addBox(3.5F, -8.9F, -2.5F, 1.0F, 2.0F, 5.0F, 0.0F, false);
		HelmetForm.setTextureOffset(24, 50).addBox(-4.5F, -8.9F, -2.5F, 1.0F, 2.0F, 5.0F, 0.0F, false);
		HelmetForm.setTextureOffset(8, 44).addBox(3.5F, -3.9F, -0.5F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		HelmetForm.setTextureOffset(14, 44).addBox(-4.5F, -3.9F, -0.5F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		HelmetForm.setTextureOffset(12, 32).addBox(3.5F, -9.9F, 2.5F, 1.0F, 10.0F, 2.0F, 0.0F, false);
		HelmetForm.setTextureOffset(18, 32).addBox(-4.5F, -9.9F, 2.5F, 1.0F, 10.0F, 2.0F, 0.0F, false);
		HelmetForm.setTextureOffset(52, 40).addBox(-4.5F, -10.9F, 3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		HelmetForm.setTextureOffset(40, 42).addBox(3.5F, -10.9F, 3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		HelmetForm.setTextureOffset(50, 43).addBox(3.5F, -9.9F, -3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		HelmetForm.setTextureOffset(54, 43).addBox(-4.5F, -9.9F, -3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		HelmetForm.setTextureOffset(20, 44).addBox(3.5F, -5.9F, -1.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		HelmetForm.setTextureOffset(24, 44).addBox(-4.5F, -5.9F, -1.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		HelmetForm.setTextureOffset(8, 50).addBox(3.5F, -5.9F, -0.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		HelmetForm.setTextureOffset(20, 50).addBox(-4.5F, -5.9F, -0.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		HelmetForm.setTextureOffset(36, 50).addBox(-4.5F, -1.9F, -2.5F, 1.0F, 2.0F, 5.0F, 0.0F, false);
		HelmetForm.setTextureOffset(32, 46).addBox(3.5F, -12.9F, -4.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		HelmetForm.setTextureOffset(36, 46).addBox(-4.5F, -12.9F, -4.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		HelmetForm.setTextureOffset(40, 45).addBox(2.5F, -14.9F, -4.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		HelmetForm.setTextureOffset(45, 43).addBox(-3.5F, -14.9F, -4.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		HelmetForm.setTextureOffset(0, 62).addBox(-3.5F, -5.1F, -4.5F, 7.0F, 1.0F, 1.0F, 0.0F, false);

		ModelRenderer Crystal = new ModelRenderer(this);
		Crystal.setRotationPoint(0.0F, 0.0F, 0.0F);
		Helmet.addChild(Crystal);
		Crystal.setTextureOffset(50, 54).addBox(-1.5F, -10.9F, -4.0F, 3.0F, 3.0F, 0.0F, 0.0F, false);
		Crystal.setTextureOffset(56, 54).addBox(-3.5F, -8.9F, -4.0F, 1.0F, 1.0F, 0.0F, 0.0F, false);
		Crystal.setTextureOffset(56, 55).addBox(2.5F, -8.9F, -4.0F, 1.0F, 1.0F, 0.0F, 0.0F, false);

		this.bipedHead.addChild(Helmet);
	}

}
