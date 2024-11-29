package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.SharkModel;
import com.tristankechlo.livingthings.client.renderer.state.SharkRenderState;
import com.tristankechlo.livingthings.entity.SharkEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SharkRenderer extends MobRenderer<SharkEntity, SharkRenderState, SharkModel<SharkRenderState>> {

    protected static final ResourceLocation TEXTURE = LivingThings.getEntityTexture("shark/shark.png");

    public SharkRenderer(EntityRendererProvider.Context context) {
        super(context, new SharkModel<>(context.bakeLayer(ModelLayer.SHARK)), 0.8F);
    }

    @Override
    public SharkRenderState createRenderState() {
        return new SharkRenderState();
    }

    @Override
    public void extractRenderState(SharkEntity entity, SharkRenderState state, float $$2) {
        super.extractRenderState(entity, state, $$2);
        state.fromEntity(entity);
    }

    @Override
    public ResourceLocation getTextureLocation(SharkRenderState entity) {
        return TEXTURE;
    }

}
