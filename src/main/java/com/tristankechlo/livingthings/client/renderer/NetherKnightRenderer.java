package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.model.entity.NetherKnightModel;
import com.tristankechlo.livingthings.client.renderer.layer.NetherKnightHeldItemLayer;
import com.tristankechlo.livingthings.entities.NetherKnightEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NetherKnightRenderer extends MobRenderer<NetherKnightEntity, NetherKnightModel<NetherKnightEntity>> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(LivingThings.MOD_ID,
			"textures/entity/nether_knight.png");

	public NetherKnightRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new NetherKnightModel<>(), 0.5F);
		this.addLayer(new NetherKnightHeldItemLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(NetherKnightEntity entity) {
		return TEXTURE;
	}

}
