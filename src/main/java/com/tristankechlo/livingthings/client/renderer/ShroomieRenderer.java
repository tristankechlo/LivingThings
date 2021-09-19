package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.model.entity.ShroomieModel;
import com.tristankechlo.livingthings.entities.ShroomieEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ShroomieRenderer extends MobRenderer<ShroomieEntity, EntityModel<ShroomieEntity>> {

	private static final ResourceLocation RED = new ResourceLocation(LivingThings.MOD_ID,
			"textures/entity/shroomie/shroomie_red.png");
	private static final ResourceLocation BROWN = new ResourceLocation(LivingThings.MOD_ID,
			"textures/entity/shroomie/shroomie_brown.png");

	public ShroomieRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new ShroomieModel<>(), 0.4F);
	}

	@Override
	public ResourceLocation getTextureLocation(ShroomieEntity shroomie) {
		if (shroomie.getVariant() == 1) {
			return RED;
		}
		return BROWN;
	}

}