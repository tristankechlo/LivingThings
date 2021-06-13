package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.model.entity.SharkModel;
import com.tristankechlo.livingthings.entities.SharkEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SharkRenderer extends MobRenderer<SharkEntity, SharkModel<SharkEntity>> {

	protected static final ResourceLocation TEXTURE = new ResourceLocation(LivingThings.MOD_ID,
			"textures/entity/shark.png");

	public SharkRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new SharkModel<>(), 0.8F);
	}

	@Override
	public ResourceLocation getTextureLocation(SharkEntity entity) {
		return TEXTURE;
	}

}
