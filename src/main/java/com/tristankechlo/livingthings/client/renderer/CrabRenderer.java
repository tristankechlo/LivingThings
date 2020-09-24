package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.model.CrabModel;
import com.tristankechlo.livingthings.entities.CrabEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CrabRenderer extends MobRenderer<CrabEntity, CrabModel<CrabEntity>> {

	protected static final ResourceLocation TEXTURE1 = new ResourceLocation(LivingThings.MOD_ID, "textures/entity/crab/crab.png");
	protected static final ResourceLocation TEXTURE2 = new ResourceLocation(LivingThings.MOD_ID, "textures/entity/crab/crab2.png");
	protected static final ResourceLocation TEXTURE_ALBINO = new ResourceLocation(LivingThings.MOD_ID, "textures/entity/crab/crab_albino.png");

	public CrabRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new CrabModel<>(), 0.4F);
	}

	@Override
	public ResourceLocation getEntityTexture(CrabEntity entity) {
		byte variant = entity.getVariant();
		if(variant == 15) {
			return TEXTURE_ALBINO;			
		} else if(variant == 1) {
			return TEXTURE2;
		}
		return TEXTURE1;			
	}

}
