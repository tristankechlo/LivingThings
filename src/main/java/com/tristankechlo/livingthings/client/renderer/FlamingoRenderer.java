package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.FlamingoModel;
import com.tristankechlo.livingthings.entities.FlamingoEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FlamingoRenderer extends MobRenderer<FlamingoEntity, FlamingoModel<FlamingoEntity>> {

	protected static final ResourceLocation TEXTURE = new ResourceLocation(LivingThings.MOD_ID,
			"textures/entity/flamingo.png");

	public FlamingoRenderer(Context context) {
		super(context, new FlamingoModel<>(context.bakeLayer(ModelLayer.FLAMINGO)), 0.35F);
	}

	@Override
	public ResourceLocation getTextureLocation(FlamingoEntity entity) {
		return TEXTURE;
	}

}
