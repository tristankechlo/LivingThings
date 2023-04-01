package com.tristankechlo.livingthings;

import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.*;
import com.tristankechlo.livingthings.client.renderer.*;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public final class FabricLivingThingsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        registerRenderers();
        registerLayerDefinitions();
    }

    private void registerRenderers() {
        EntityRendererRegistry.register(ModEntityTypes.ELEPHANT.get(), ElephantRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.GIRAFFE.get(), GiraffeRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.LION.get(), LionRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.SHARK.get(), SharkRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.PENGUIN.get(), PenguinRenderer::new);
    }

    private void registerLayerDefinitions() {
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.ELEPHANT, ElephantModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.GIRAFFE, GiraffeModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.LION, LionModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.SHARK, SharkModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.PENGUIN, PenguinModel::createBodyLayer);
    }

}
