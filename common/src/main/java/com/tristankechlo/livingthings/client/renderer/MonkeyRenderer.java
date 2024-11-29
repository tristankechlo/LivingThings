package com.tristankechlo.livingthings.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.MonkeyModel;
import com.tristankechlo.livingthings.client.model.entity.MonkeySittingModel;
import com.tristankechlo.livingthings.client.renderer.state.MonkeyRenderState;
import com.tristankechlo.livingthings.entity.MonkeyEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.builders.MeshTransformer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MonkeyRenderer extends MobRenderer<MonkeyEntity, MonkeyRenderState, EntityModel<MonkeyRenderState>> {

    private static final ResourceLocation TEXTURE = LivingThings.getEntityTexture("monkey/monkey.png");
    private final MonkeyModel<MonkeyRenderState> modelAdult;
    private final MonkeyModel<MonkeyRenderState> modelBaby;
    private final MonkeySittingModel<MonkeyRenderState> modelSitting;
    private final MonkeySittingModel<MonkeyRenderState> modelSittingBaby;
    public static final MeshTransformer BABY_TRANSFORMER = MeshTransformer.scaling(0.5F);

    public MonkeyRenderer(Context context) {
        super(context, new MonkeyModel<>(context.bakeLayer(ModelLayer.MONKEY)), 0.35F);
        this.modelAdult = new MonkeyModel<>(context.bakeLayer(ModelLayer.MONKEY));
        this.modelBaby = new MonkeyModel<>(context.bakeLayer(ModelLayer.MONKEY_BABY));
        this.modelSitting = new MonkeySittingModel<>(context.bakeLayer(ModelLayer.MONKEY_SITTING));
        this.modelSittingBaby = new MonkeySittingModel<>(context.bakeLayer(ModelLayer.MONKEY_SITTING_BABY));
    }

    @Override
    public MonkeyRenderState createRenderState() {
        return new MonkeyRenderState();
    }

    @Override
    public void extractRenderState(MonkeyEntity entity, MonkeyRenderState state, float $$2) {
        super.extractRenderState(entity, state, $$2);
        state.fromEntity(entity);
    }

    @Override
    public ResourceLocation getTextureLocation(MonkeyRenderState entity) {
        return TEXTURE;
    }

    @Override
    public void render(MonkeyRenderState state, PoseStack poseStack, MultiBufferSource bufferIn, int packedLightIn) {
        if (state.isSitting) {
            this.model = state.isBaby ? this.modelSittingBaby : this.modelSitting;
        } else {
            this.model = state.isBaby ? this.modelBaby : this.modelAdult;
        }
        super.render(state, poseStack, bufferIn, packedLightIn);
    }

}