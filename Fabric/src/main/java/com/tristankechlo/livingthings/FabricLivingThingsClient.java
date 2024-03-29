package com.tristankechlo.livingthings;

import com.tristankechlo.livingthings.client.LivingThingsClient;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.armor.AncientArmorModel;
import com.tristankechlo.livingthings.client.model.entity.*;
import com.tristankechlo.livingthings.client.renderer.*;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

@Environment(EnvType.CLIENT)
public final class FabricLivingThingsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        registerRenderers();
        registerLayerDefinitions();
    }

    private void registerRenderers() {
        EntityRendererRegistry.register(ModEntityTypes.ELEPHANT.get(), LivingThingsClient.ELEPHANT_RENDERER);
        EntityRendererRegistry.register(ModEntityTypes.GIRAFFE.get(), GiraffeRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.LION.get(), LionRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.SHARK.get(), LivingThingsClient.SHARK_RENDERER);
        EntityRendererRegistry.register(ModEntityTypes.PENGUIN.get(), PenguinRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.OSTRICH.get(), LivingThingsClient.OSTRICH_RENDERER);
        EntityRendererRegistry.register(ModEntityTypes.FLAMINGO.get(), LivingThingsClient.FLAMINGO_RENDERER);
        EntityRendererRegistry.register(ModEntityTypes.CRAB.get(), CrabRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.MANTARAY.get(), MantarayRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.RACCOON.get(), LivingThingsClient.RACCOON_RENDERER);
        EntityRendererRegistry.register(ModEntityTypes.OWL.get(), OwlRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.ANCIENT_BLAZE.get(), AncientBlazeRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.KOALA.get(), LivingThingsClient.KOALA_RENDERER);
        EntityRendererRegistry.register(ModEntityTypes.SNAIL.get(), SnailRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.MONKEY.get(), MonkeyRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.NETHER_KNIGHT.get(), NetherKnightRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.SHROOMIE.get(), ShroomieRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.SEAHORSE.get(), SeahorseRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.BABY_ENDER_DRAGON.get(), BabyEnderDragonRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.PEACOCK.get(), LivingThingsClient.PEACOCK_RENDERER);
        EntityRendererRegistry.register(ModEntityTypes.THROWN_OSTRICH_EGG.get(), ThrownItemRenderer::new);
    }

    private void registerLayerDefinitions() {
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.ELEPHANT, ElephantModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.GIRAFFE, GiraffeModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.LION, LionModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.SHARK, SharkModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.PENGUIN, PenguinModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.OSTRICH, OstrichModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.FLAMINGO, FlamingoModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.CRAB, CrabModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.MANTARAY, MantarayModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.RACCOON, RaccoonModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.OWL, OwlModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.ANCIENT_BLAZE, AncientBlazeModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.KOALA, KoalaModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.SNAIL, SnailModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.MONKEY, MonkeyModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.MONKEY_SITTING, MonkeySittingModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.NETHER_KNIGHT, NetherKnightModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.SHROOMIE, ShroomieModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.SEAHORSE, SeahorseModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.BABY_ENDER_DRAGON, BabyEnderDragonModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.BABY_ENDER_DRAGON_SITTING, BabyEnderDragonSittingModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.ANCIENT_ARMOR, AncientArmorModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.PEACOCK, PeacockModel::createBodyLayer);
    }

}
