package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.FlamingoModel;
import com.tristankechlo.livingthings.client.renderer.state.FlamingoRenderState;
import com.tristankechlo.livingthings.entity.FlamingoEntity;
import net.minecraft.client.model.geom.builders.MeshTransformer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class FlamingoRenderer extends AgeableMobRenderer<FlamingoEntity, FlamingoRenderState, FlamingoModel<FlamingoRenderState>> {

    protected static final ResourceLocation TEXTURE = LivingThings.getEntityTexture("flamingo/flamingo.png");
    public static final MeshTransformer BABY_TRANSFORMER = MeshTransformer.scaling(0.5F);

    public FlamingoRenderer(EntityRendererProvider.Context context) {
        super(context, FlamingoModel::new, ModelLayer.FLAMINGO, ModelLayer.FLAMINGO_BABY, 0.33F);
    }

    @Override
    public FlamingoRenderState createRenderState() {
        return new FlamingoRenderState();
    }

    @Override
    public void extractRenderState(FlamingoEntity entity, FlamingoRenderState state, float $$2) {
        super.extractRenderState(entity, state, $$2);
        state.fromEntity(entity);
    }

    @Override
    public ResourceLocation getTextureLocation(FlamingoRenderState entity) {
        return TEXTURE;
    }

}
