package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.GiraffeModel;
import com.tristankechlo.livingthings.client.renderer.state.GiraffeRenderState;
import com.tristankechlo.livingthings.entity.GiraffeEntity;
import net.minecraft.client.model.geom.builders.MeshTransformer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class GiraffeRenderer extends AgeableMobRenderer<GiraffeEntity, GiraffeRenderState, GiraffeModel<GiraffeRenderState>> {

    protected static final ResourceLocation TEXTURE_1 = LivingThings.getEntityTexture("giraffe/giraffe_1.png");
    protected static final ResourceLocation TEXTURE_2 = LivingThings.getEntityTexture("giraffe/giraffe_2.png");
    protected static final ResourceLocation TEXTURE_WHITE = LivingThings.getEntityTexture("giraffe/giraffe_white.png");
    public static final MeshTransformer BABY_TRANSFORMER = MeshTransformer.scaling(0.6F);

    public GiraffeRenderer(Context context) {
        super(context, GiraffeModel::new, ModelLayer.GIRAFFE, ModelLayer.GIRAFFE_BABY, 0.8F);
    }

    @Override
    public GiraffeRenderState createRenderState() {
        return new GiraffeRenderState();
    }

    @Override
    public void extractRenderState(GiraffeEntity entity, GiraffeRenderState state, float $$2) {
        super.extractRenderState(entity, state, $$2);
        state.fromEntity(entity);
    }

    @Override
    public ResourceLocation getTextureLocation(GiraffeRenderState state) {
        byte variant = state.variant;
        if (variant == 2) {
            return TEXTURE_WHITE;
        } else if (variant == 1) {
            return TEXTURE_2;
        }
        return TEXTURE_1;
    }

}

