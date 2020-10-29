package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.model.entity.RaccoonModel;
import com.tristankechlo.livingthings.entities.RaccoonEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RaccoonRenderer extends MobRenderer<RaccoonEntity, RaccoonModel<RaccoonEntity>> {
	
	protected static final ResourceLocation TEXTURE = new ResourceLocation(LivingThings.MOD_ID, "textures/entity/raccoon.png");

	public RaccoonRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new RaccoonModel<>(), 0.4F);
	}

	@Override
	public ResourceLocation getEntityTexture(RaccoonEntity entity) {
		return TEXTURE;
	}

}
