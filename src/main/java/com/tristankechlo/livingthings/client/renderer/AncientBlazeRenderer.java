package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.model.entity.AncientBlazeModel;
import com.tristankechlo.livingthings.client.renderer.layer.AncientBlazeChargedLayer;
import com.tristankechlo.livingthings.entities.AncientBlazeEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AncientBlazeRenderer extends MobRenderer<AncientBlazeEntity, AncientBlazeModel<AncientBlazeEntity>> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(LivingThings.MOD_ID,
			"textures/entity/ancient_blaze/ancient_blaze.png");

	public AncientBlazeRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new AncientBlazeModel<>(), 0.5F);
		this.addLayer(new AncientBlazeChargedLayer(this));
	}

	@Override
	protected int getBlockLightLevel(AncientBlazeEntity entityIn, BlockPos partialTicks) {
		return 15;
	}

	@Override
	public ResourceLocation getTextureLocation(AncientBlazeEntity entity) {
		return TEXTURE;
	}

}
