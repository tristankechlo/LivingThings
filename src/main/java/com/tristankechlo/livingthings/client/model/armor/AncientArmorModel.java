package com.tristankechlo.livingthings.client.model.armor;

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
		Helmet.setPos(0.0F, 0.0F, 0.0F);

		ModelRenderer HelmetForm = new ModelRenderer(this);
		HelmetForm.setPos(0.0F, 0.0F, 0.0F);
		Helmet.addChild(HelmetForm);
		HelmetForm.texOffs(0, 32).addBox(3.5F, -8.9F, -4.5F, 1.0F, 9.0F, 2.0F, 0.0F, false);
		HelmetForm.texOffs(32, 32).addBox(-3.5F, -7.9F, -4.5F, 7.0F, 1.0F, 1.0F, 0.0F, false);
		HelmetForm.texOffs(32, 36).addBox(-2.5F, -8.9F, -4.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		HelmetForm.texOffs(38, 36).addBox(0.5F, -8.9F, -4.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		HelmetForm.texOffs(32, 38).addBox(-3.5F, -9.9F, -4.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		HelmetForm.texOffs(38, 38).addBox(1.5F, -9.9F, -4.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		HelmetForm.texOffs(32, 40).addBox(0.5F, -10.9F, -4.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		HelmetForm.texOffs(38, 40).addBox(-2.5F, -10.9F, -4.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		HelmetForm.texOffs(48, 32).addBox(-1.5F, -11.9F, -4.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		HelmetForm.texOffs(56, 32).addBox(1.5F, -1.9F, -4.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		HelmetForm.texOffs(56, 37).addBox(-3.5F, -1.9F, -4.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		HelmetForm.texOffs(6, 32).addBox(-4.5F, -8.9F, -4.5F, 1.0F, 9.0F, 2.0F, 0.0F, false);
		HelmetForm.texOffs(44, 36).addBox(-2.5F, -8.9F, 3.5F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		HelmetForm.texOffs(44, 38).addBox(-2.5F, -0.9F, 3.5F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		HelmetForm.texOffs(24, 32).addBox(2.5F, -9.9F, 3.5F, 1.0F, 10.0F, 1.0F, 0.0F, false);
		HelmetForm.texOffs(28, 32).addBox(-3.5F, -9.9F, 3.5F, 1.0F, 10.0F, 1.0F, 0.0F, false);
		HelmetForm.texOffs(44, 40).addBox(-2.5F, -4.9F, 3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		HelmetForm.texOffs(32, 42).addBox(-0.5F, -7.9F, 3.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		HelmetForm.texOffs(36, 42).addBox(-0.5F, -2.9F, 3.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		HelmetForm.texOffs(0, 44).addBox(-1.5F, -6.9F, 3.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
		HelmetForm.texOffs(4, 44).addBox(0.5F, -6.9F, 3.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
		HelmetForm.texOffs(48, 40).addBox(1.5F, -4.9F, 3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		HelmetForm.texOffs(0, 50).addBox(3.5F, -1.9F, -2.5F, 1.0F, 2.0F, 5.0F, 0.0F, false);
		HelmetForm.texOffs(12, 50).addBox(3.5F, -8.9F, -2.5F, 1.0F, 2.0F, 5.0F, 0.0F, false);
		HelmetForm.texOffs(24, 50).addBox(-4.5F, -8.9F, -2.5F, 1.0F, 2.0F, 5.0F, 0.0F, false);
		HelmetForm.texOffs(8, 44).addBox(3.5F, -3.9F, -0.5F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		HelmetForm.texOffs(14, 44).addBox(-4.5F, -3.9F, -0.5F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		HelmetForm.texOffs(12, 32).addBox(3.5F, -9.9F, 2.5F, 1.0F, 10.0F, 2.0F, 0.0F, false);
		HelmetForm.texOffs(18, 32).addBox(-4.5F, -9.9F, 2.5F, 1.0F, 10.0F, 2.0F, 0.0F, false);
		HelmetForm.texOffs(52, 40).addBox(-4.5F, -10.9F, 3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		HelmetForm.texOffs(40, 42).addBox(3.5F, -10.9F, 3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		HelmetForm.texOffs(50, 43).addBox(3.5F, -9.9F, -3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		HelmetForm.texOffs(54, 43).addBox(-4.5F, -9.9F, -3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		HelmetForm.texOffs(20, 44).addBox(3.5F, -5.9F, -1.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		HelmetForm.texOffs(24, 44).addBox(-4.5F, -5.9F, -1.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		HelmetForm.texOffs(8, 50).addBox(3.5F, -5.9F, -0.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		HelmetForm.texOffs(20, 50).addBox(-4.5F, -5.9F, -0.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		HelmetForm.texOffs(36, 50).addBox(-4.5F, -1.9F, -2.5F, 1.0F, 2.0F, 5.0F, 0.0F, false);
		HelmetForm.texOffs(32, 46).addBox(3.5F, -12.9F, -4.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		HelmetForm.texOffs(36, 46).addBox(-4.5F, -12.9F, -4.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		HelmetForm.texOffs(40, 45).addBox(2.5F, -14.9F, -4.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		HelmetForm.texOffs(45, 43).addBox(-3.5F, -14.9F, -4.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		ModelRenderer Crystal = new ModelRenderer(this);
		Crystal.setPos(0.0F, 0.0F, 0.0F);
		Helmet.addChild(Crystal);
		Crystal.texOffs(50, 54).addBox(-1.5F, -10.9F, -4.0F, 3.0F, 3.0F, 0.0F, 0.0F, false);
		Crystal.texOffs(56, 54).addBox(-3.5F, -8.9F, -4.0F, 1.0F, 1.0F, 0.0F, 0.0F, false);
		Crystal.texOffs(56, 55).addBox(2.5F, -8.9F, -4.0F, 1.0F, 1.0F, 0.0F, 0.0F, false);

		this.head.addChild(Helmet);
	}

}
