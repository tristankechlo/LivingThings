package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.model.entity.GiraffeModel;
import com.tristankechlo.livingthings.entities.GiraffeEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GiraffeRenderer  extends MobRenderer<GiraffeEntity, GiraffeModel<GiraffeEntity>>{
	
	protected static final ResourceLocation TEXTURE_1 = new ResourceLocation(LivingThings.MOD_ID,	"textures/entity/giraffe/giraffe.png");
	protected static final ResourceLocation TEXTURE_2 = new ResourceLocation(LivingThings.MOD_ID,	"textures/entity/giraffe/giraffe_2.png");
	protected static final ResourceLocation TEXTURE_ALBINO = new ResourceLocation(LivingThings.MOD_ID,	"textures/entity/giraffe/giraffe_albino.png");

	public GiraffeRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new GiraffeModel<>(), 0.8F);
	}

	@Override
	public ResourceLocation getEntityTexture(GiraffeEntity entity) {
		byte variant = entity.getVariant();
		if(variant == 15) {
			return TEXTURE_ALBINO;
		} else if (variant == 1) {
			return TEXTURE_2;
		}
		return TEXTURE_1;
	}

}
