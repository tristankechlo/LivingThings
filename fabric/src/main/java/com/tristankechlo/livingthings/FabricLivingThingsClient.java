package com.tristankechlo.livingthings;

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
import net.minecraft.client.model.geom.builders.MeshTransformer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

@Environment(EnvType.CLIENT)
public final class FabricLivingThingsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        registerRenderers();
        registerLayerDefinitions();
    }

    private void registerRenderers() {
        EntityRendererRegistry.register(ModEntityTypes.ANCIENT_BLAZE.get(), AncientBlazeRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.BABY_ENDER_DRAGON.get(), BabyEnderDragonRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.CRAB.get(), CrabRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.ELEPHANT.get(), ElephantRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.FLAMINGO.get(), FlamingoRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.GIRAFFE.get(), GiraffeRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.KOALA.get(), KoalaRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.LION.get(), LionRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.MANTARAY.get(), MantarayRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.MONKEY.get(), MonkeyRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.NETHER_KNIGHT.get(), NetherKnightRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.OSTRICH.get(), OstrichRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.OWL.get(), OwlRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.PEACOCK.get(), PeacockRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.PENGUIN.get(), PenguinRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.RACCOON.get(), RaccoonRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.SEAHORSE.get(), SeahorseRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.SHARK.get(), SharkRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.SHROOMIE.get(), ShroomieRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.SNAIL.get(), SnailRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.THROWN_OSTRICH_EGG.get(), ThrownItemRenderer::new);
    }

    private void registerLayerDefinitions() {
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.ANCIENT_BLAZE, AncientBlazeModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.BABY_ENDER_DRAGON, BabyEnderDragonModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.BABY_ENDER_DRAGON_SITTING, BabyEnderDragonSittingModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.CRAB, CrabModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.CRAB_BABY, transform(CrabModel::createBodyLayer, CrabRenderer.BABY_TRANSFORMER));
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.ELEPHANT, ElephantModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.ELEPHANT_BABY, transform(ElephantModel::createBodyLayer, ElephantRenderer.BABY_TRANSFORMER));
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.FLAMINGO, FlamingoModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.FLAMINGO_BABY, transform(FlamingoModel::createBodyLayer, FlamingoRenderer.BABY_TRANSFORMER));
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.GIRAFFE, GiraffeModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.GIRAFFE_BABY, transform(GiraffeModel::createBodyLayer, GiraffeRenderer.BABY_TRANSFORMER));
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.KOALA, KoalaModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.KOALA_BABY, transform(KoalaModel::createBodyLayer, KoalaRenderer.BABY_TRANSFORMER));
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.LION, LionModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.LION_BABY, transform(LionModel::createBodyLayer, LionRenderer.BABY_TRANSFORMER));
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.MANTARAY, MantarayModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.MONKEY, MonkeyModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.MONKEY_BABY, transform(MonkeyModel::createBodyLayer, MonkeyRenderer.BABY_TRANSFORMER));
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.MONKEY_SITTING, MonkeySittingModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.MONKEY_SITTING_BABY, transform(MonkeySittingModel::createBodyLayer, MonkeyRenderer.BABY_TRANSFORMER));
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.NETHER_KNIGHT, NetherKnightModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.OSTRICH, OstrichModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.OSTRICH_BABY, transform(OstrichModel::createBodyLayer, OstrichRenderer.BABY_TRANSFORMER));
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.OWL, transform(OwlModel::createBodyLayer, OwlRenderer.NORMAL_TRANSFORMER));
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.OWL_BABY, transform(OwlModel::createBodyLayer, OwlRenderer.BABY_TRANSFORMER));
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.PEACOCK, PeacockModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.PEACOCK_BABY, transform(PeacockModel::createBodyLayer, PeacockRenderer.BABY_TRANSFORMER));
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.PENGUIN, PenguinModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.PENGUIN_BABY, transform(PenguinModel::createBodyLayer, PenguinRenderer.BABY_TRANSFORMER));
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.RACCOON, RaccoonModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.RACCOON_BABY, transform(RaccoonModel::createBodyLayer, RaccoonRenderer.BABY_TRANSFORMER));
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.SEAHORSE, SeahorseModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.SHARK, SharkModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.SHROOMIE, ShroomieModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.SNAIL, transform(SnailModel::createBodyLayer, SnailRenderer.NORMAL_TRANSFORMER));
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.SNAIL_BABY, transform(SnailModel::createBodyLayer, SnailRenderer.BABY_TRANSFORMER));

        EntityModelLayerRegistry.registerModelLayer(ModelLayer.ANCIENT_ARMOR, AncientArmorModel::createBodyLayer);
    }

    private static EntityModelLayerRegistry.TexturedModelDataProvider transform(EntityModelLayerRegistry.TexturedModelDataProvider provider, MeshTransformer transformer) {
        return () -> provider.createModelData().apply(transformer);
    }

}
