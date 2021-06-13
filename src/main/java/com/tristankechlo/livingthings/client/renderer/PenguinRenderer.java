package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.model.entity.PenguinModel;
import com.tristankechlo.livingthings.entities.PenguinEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PenguinRenderer extends MobRenderer<PenguinEntity, PenguinModel<PenguinEntity>> {

	protected static final ResourceLocation TEXTURE = new ResourceLocation(LivingThings.MOD_ID,
			"textures/entity/penguin.png");
	protected static final ResourceLocation TEXTURE_CHILD = new ResourceLocation(LivingThings.MOD_ID,
			"textures/entity/penguin_baby.png");

	public PenguinRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new PenguinModel<>(), 0.45F);
	}

	@Override
	public ResourceLocation getTextureLocation(PenguinEntity entity) {
		return (entity.isBaby()) ? TEXTURE_CHILD : TEXTURE;
	}

}
