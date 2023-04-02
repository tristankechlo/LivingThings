package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.NetherKnightModel;
import com.tristankechlo.livingthings.client.renderer.layer.NetherKnightHeldItemLayer;
import com.tristankechlo.livingthings.entity.NetherKnightEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class NetherKnightRenderer extends MobRenderer<NetherKnightEntity, NetherKnightModel<NetherKnightEntity>> {

    private static final ResourceLocation TEXTURE = ModelLayer.getEntityTexture("nether_knight/nether_knight.png");

    public NetherKnightRenderer(Context context) {
        super(context, new NetherKnightModel<>(context.bakeLayer(ModelLayer.NETHER_KNIGHT)), 0.5F);
        this.addLayer(new NetherKnightHeldItemLayer<>(this, context.getItemInHandRenderer()));
    }

    @Override
    public ResourceLocation getTextureLocation(NetherKnightEntity entity) {
        return TEXTURE;
    }

}
