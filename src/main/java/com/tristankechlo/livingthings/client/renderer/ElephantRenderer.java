package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.model.entity.ElephantModel;
import com.tristankechlo.livingthings.entities.ElephantEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ElephantRenderer extends MobRenderer<ElephantEntity, ElephantModel<ElephantEntity>> {

	protected static final ResourceLocation TEXTURE = new ResourceLocation(LivingThings.MOD_ID,
			"textures/entity/elephant/elephant.png");
	protected static final ResourceLocation TEXTURE_SADDLED = new ResourceLocation(LivingThings.MOD_ID,
			"textures/entity/elephant/elephant_saddled.png");

	public ElephantRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new ElephantModel<>(), 1.2F);
	}

	@Override
	public ResourceLocation getTextureLocation(ElephantEntity entity) {
		if (entity.isTame() && !entity.isBaby()) {
			return TEXTURE_SADDLED;
		}
		return TEXTURE;
	}
}