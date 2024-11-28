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
import net.minecraft.resources.ResourceLocation;

public class MonkeyRenderer extends AgeableMobRenderer<MonkeyEntity, MonkeyRenderState, EntityModel<MonkeyRenderState>> {

    private static final ResourceLocation TEXTURE = LivingThings.getEntityTexture("monkey/monkey.png");
    private final MonkeyModel<MonkeyRenderState> modelNormal;
    private final MonkeySittingModel<MonkeyRenderState> modelSitting;
    private byte lastAction;
    public static final MeshTransformer BABY_TRANSFORMER = MeshTransformer.scaling(0.5F);

    public MonkeyRenderer(Context context) {
        super(context, MonkeyModel::new, ModelLayer.MONKEY, ModelLayer.MONKEY_BABY, 0.35F);
        this.modelNormal = new MonkeyModel<>(context.bakeLayer(ModelLayer.MONKEY));
        this.modelSitting = new MonkeySittingModel<>(context.bakeLayer(ModelLayer.MONKEY_SITTING));
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
        byte monkeyAction = (byte) ((state.isSitting) ? 1 : 0);
        if (monkeyAction != this.lastAction) {
            if (monkeyAction == 1) {
                this.model = this.modelSitting;
            } else {
                this.model = modelNormal;
            }
        }
        this.lastAction = monkeyAction;
        super.render(state, poseStack, bufferIn, packedLightIn);
    }

}