package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.NetherKnightModel;
import com.tristankechlo.livingthings.client.renderer.layer.NetherKnightHeldItemLayer;
import com.tristankechlo.livingthings.client.renderer.state.NetherKnightRenderState;
import com.tristankechlo.livingthings.entity.NetherKnightEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class NetherKnightRenderer extends MobRenderer<NetherKnightEntity, NetherKnightRenderState, NetherKnightModel<NetherKnightRenderState>> {

    private static final ResourceLocation TEXTURE = LivingThings.getEntityTexture("nether_knight/nether_knight.png");

    public NetherKnightRenderer(Context context) {
        super(context, new NetherKnightModel<>(context.bakeLayer(ModelLayer.NETHER_KNIGHT)), 0.5F);
        this.addLayer(new NetherKnightHeldItemLayer<>(this, context.getItemRenderer()));
    }

    @Override
    public NetherKnightRenderState createRenderState() {
        return new NetherKnightRenderState();
    }

    @Override
    public void extractRenderState(NetherKnightEntity entity, NetherKnightRenderState state, float $$2) {
        super.extractRenderState(entity, state, $$2);
        state.fromEntity(entity);
    }

    @Override
    public ResourceLocation getTextureLocation(NetherKnightRenderState entity) {
        return TEXTURE;
    }

}
