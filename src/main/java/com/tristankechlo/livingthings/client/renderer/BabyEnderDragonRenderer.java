package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.BabyEnderDragonModel;
import com.tristankechlo.livingthings.entities.BabyEnderDragonEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BabyEnderDragonRenderer
		extends MobRenderer<BabyEnderDragonEntity, BabyEnderDragonModel<BabyEnderDragonEntity>> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(LivingThings.MOD_ID,
			"textures/entity/baby_ender_dragon/baby_ender_dragon.png");
	private static final ResourceLocation COLLAR = new ResourceLocation(LivingThings.MOD_ID,
			"textures/entity/baby_ender_dragon/baby_ender_dragon_collar");
	private static final ResourceLocation BELL = new ResourceLocation(LivingThings.MOD_ID,
			"textures/entity/baby_ender_dragon/baby_ender_dragon_bell");

	public BabyEnderDragonRenderer(Context context) {
		super(context, new BabyEnderDragonModel<>(context.bakeLayer(ModelLayer.BABY_ENDER_DRAGON)), 0.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(BabyEnderDragonEntity entity) {
		return TEXTURE;
	}

}
