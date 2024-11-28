package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.PeacockModel;
import com.tristankechlo.livingthings.client.renderer.state.PeacockRenderState;
import com.tristankechlo.livingthings.entity.PeacockEntity;
import net.minecraft.client.model.geom.builders.MeshTransformer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class PeacockRenderer extends AgeableMobRenderer<PeacockEntity, PeacockRenderState, PeacockModel<PeacockRenderState>> {

    protected static final ResourceLocation TEXTURE = LivingThings.getEntityTexture("peacock/peacock.png");
    public static final MeshTransformer BABY_TRANSFORMER = MeshTransformer.scaling(0.5F);

    public PeacockRenderer(EntityRendererProvider.Context context) {
        super(context, PeacockModel::new, ModelLayer.PEACOCK, ModelLayer.PEACOCK_BABY, 0.3F);
    }

    @Override
    public PeacockRenderState createRenderState() {
        return new PeacockRenderState();
    }

    @Override
    public void extractRenderState(PeacockEntity entity, PeacockRenderState state, float $$2) {
        super.extractRenderState(entity, state, $$2);
        state.fromEntity(entity);
    }

    @Override
    public ResourceLocation getTextureLocation(PeacockRenderState entity) {
        return TEXTURE;
    }

}
