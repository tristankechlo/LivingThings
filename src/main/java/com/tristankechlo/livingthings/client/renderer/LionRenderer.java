package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.model.entity.LionModel;
import com.tristankechlo.livingthings.entities.LionEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LionRenderer extends MobRenderer<LionEntity, LionModel<LionEntity>> {
	
	protected static final ResourceLocation TEXTURE = new ResourceLocation(LivingThings.MOD_ID,	"textures/entity/lion/lion.png");
	protected static final ResourceLocation TEXTURE_ALBINO = new ResourceLocation(LivingThings.MOD_ID,	"textures/entity/lion/lion_albino.png");

	public LionRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new LionModel<>(), 1F);
	}

	@Override
	public ResourceLocation getEntityTexture(LionEntity entity) {
		if(entity.getVariant() != 0) {
			return TEXTURE_ALBINO;
		}
		return TEXTURE;
	}	
	
}
