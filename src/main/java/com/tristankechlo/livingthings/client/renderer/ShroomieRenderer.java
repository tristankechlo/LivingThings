package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.ShroomieModel;
import com.tristankechlo.livingthings.entities.ShroomieEntity;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ShroomieRenderer extends MobRenderer<ShroomieEntity, EntityModel<ShroomieEntity>> {

	private static final ResourceLocation RED = new ResourceLocation(LivingThings.MOD_ID,
			"textures/entity/shroomie/shroomie_red.png");
	private static final ResourceLocation BROWN = new ResourceLocation(LivingThings.MOD_ID,
			"textures/entity/shroomie/shroomie_brown.png");

	public ShroomieRenderer(Context context) {
		super(context, new ShroomieModel<>(context.bakeLayer(ModelLayer.SHROOMIE)), 0.4F);
	}

	@Override
	public ResourceLocation getTextureLocation(ShroomieEntity shroomie) {
		if (shroomie.getVariant() == 1) {
			return RED;
		}
		return BROWN;
	}

}