package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.SnailModel;
import com.tristankechlo.livingthings.client.renderer.layer.SnailShellPatternLayer;
import com.tristankechlo.livingthings.client.renderer.state.SnailRenderState;
import com.tristankechlo.livingthings.entity.SnailEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.builders.MeshTransformer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class SnailRenderer extends AgeableMobRenderer<SnailEntity, SnailRenderState, EntityModel<SnailRenderState>> {

    public static final MeshTransformer NORMAL_TRANSFORMER = MeshTransformer.scaling(0.8F);
    public static final MeshTransformer BABY_TRANSFORMER = MeshTransformer.scaling(0.45F);

    public SnailRenderer(Context context) {
        super(context, SnailModel::new, ModelLayer.SNAIL, ModelLayer.SNAIL_BABY, 0.35F);
        this.addLayer(new SnailShellPatternLayer(this, context.getModelSet(), SnailEntity.PatternType.BACKGROUND));
        this.addLayer(new SnailShellPatternLayer(this, context.getModelSet(), SnailEntity.PatternType.FOREGROUND));
    }

    @Override
    public SnailRenderState createRenderState() {
        return new SnailRenderState();
    }

    @Override
    public void extractRenderState(SnailEntity snail, SnailRenderState state, float $$2) {
        super.extractRenderState(snail, state, $$2);
        state.fromEntity(snail);
    }

    @Override
    public ResourceLocation getTextureLocation(SnailRenderState snail) {
        return snail.bodyTexture;
    }

}
