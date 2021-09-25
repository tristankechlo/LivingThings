package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.model.entity.SeahorseModel;
import com.tristankechlo.livingthings.entities.SeahorseEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SeahorseRenderer extends MobRenderer<SeahorseEntity, SeahorseModel<SeahorseEntity>> {

	private static final ResourceLocation GREEN = new ResourceLocation(LivingThings.MOD_ID,
			"textures/entity/seahorse/seahorse_green.png");
	private static final ResourceLocation BLUE = new ResourceLocation(LivingThings.MOD_ID,
			"textures/entity/seahorse/seahorse_blue.png");
	private static final ResourceLocation PURPLE = new ResourceLocation(LivingThings.MOD_ID,
			"textures/entity/seahorse/seahorse_purple.png");
	private static final ResourceLocation RED = new ResourceLocation(LivingThings.MOD_ID,
			"textures/entity/seahorse/seahorse_red.png");
	private static final ResourceLocation YELLOW = new ResourceLocation(LivingThings.MOD_ID,
			"textures/entity/seahorse/seahorse_yellow.png");

	public SeahorseRenderer(EntityRendererManager rendererManager) {
		super(rendererManager, new SeahorseModel<>(), 0.2F);
	}

	@Override
	public ResourceLocation getTextureLocation(SeahorseEntity entity) {
		final byte variant = entity.getVariant();
		if (variant == 1) {
			return GREEN;
		} else if (variant == 2) {
			return PURPLE;
		} else if (variant == 3) {
			return YELLOW;
		} else if (variant == 4) {
			return RED;
		} else {
			return BLUE;
		}
	}

}
