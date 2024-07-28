package com.tristankechlo.livingthings;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.LivingThingsClient;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.armor.AncientArmorModel;
import com.tristankechlo.livingthings.client.model.entity.*;
import com.tristankechlo.livingthings.client.renderer.*;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = LivingThings.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class NeoForgeLivingThingsClient {

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityTypes.ELEPHANT.get(), LivingThingsClient.ELEPHANT_RENDERER);
        event.registerEntityRenderer(ModEntityTypes.GIRAFFE.get(), GiraffeRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.LION.get(), LionRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.SHARK.get(), LivingThingsClient.SHARK_RENDERER);
        event.registerEntityRenderer(ModEntityTypes.PENGUIN.get(), PenguinRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.OSTRICH.get(), LivingThingsClient.OSTRICH_RENDERER);
        event.registerEntityRenderer(ModEntityTypes.FLAMINGO.get(), LivingThingsClient.FLAMINGO_RENDERER);
        event.registerEntityRenderer(ModEntityTypes.CRAB.get(), CrabRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.MANTARAY.get(), MantarayRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.RACCOON.get(), LivingThingsClient.RACCOON_RENDERER);
        event.registerEntityRenderer(ModEntityTypes.OWL.get(), OwlRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.ANCIENT_BLAZE.get(), AncientBlazeRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.KOALA.get(), LivingThingsClient.KOALA_RENDERER);
        event.registerEntityRenderer(ModEntityTypes.SNAIL.get(), SnailRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.MONKEY.get(), MonkeyRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.NETHER_KNIGHT.get(), NetherKnightRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.SHROOMIE.get(), ShroomieRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.SEAHORSE.get(), SeahorseRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.BABY_ENDER_DRAGON.get(), BabyEnderDragonRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.PEACOCK.get(), LivingThingsClient.PEACOCK_RENDERER);
        event.registerEntityRenderer(ModEntityTypes.THROWN_OSTRICH_EGG.get(), ThrownItemRenderer::new);
    }

    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModelLayer.ELEPHANT, ElephantModel::createBodyLayer);
        event.registerLayerDefinition(ModelLayer.GIRAFFE, GiraffeModel::createBodyLayer);
        event.registerLayerDefinition(ModelLayer.LION, LionModel::createBodyLayer);
        event.registerLayerDefinition(ModelLayer.SHARK, SharkModel::createBodyLayer);
        event.registerLayerDefinition(ModelLayer.PENGUIN, PenguinModel::createBodyLayer);
        event.registerLayerDefinition(ModelLayer.OSTRICH, OstrichModel::createBodyLayer);
        event.registerLayerDefinition(ModelLayer.FLAMINGO, FlamingoModel::createBodyLayer);
        event.registerLayerDefinition(ModelLayer.CRAB, CrabModel::createBodyLayer);
        event.registerLayerDefinition(ModelLayer.MANTARAY, MantarayModel::createBodyLayer);
        event.registerLayerDefinition(ModelLayer.RACCOON, RaccoonModel::createBodyLayer);
        event.registerLayerDefinition(ModelLayer.OWL, OwlModel::createBodyLayer);
        event.registerLayerDefinition(ModelLayer.ANCIENT_BLAZE, AncientBlazeModel::createBodyLayer);
        event.registerLayerDefinition(ModelLayer.KOALA, KoalaModel::createBodyLayer);
        event.registerLayerDefinition(ModelLayer.SNAIL, SnailModel::createBodyLayer);
        event.registerLayerDefinition(ModelLayer.MONKEY, MonkeyModel::createBodyLayer);
        event.registerLayerDefinition(ModelLayer.MONKEY_SITTING, MonkeySittingModel::createBodyLayer);
        event.registerLayerDefinition(ModelLayer.NETHER_KNIGHT, NetherKnightModel::createBodyLayer);
        event.registerLayerDefinition(ModelLayer.SHROOMIE, ShroomieModel::createBodyLayer);
        event.registerLayerDefinition(ModelLayer.SEAHORSE, SeahorseModel::createBodyLayer);
        event.registerLayerDefinition(ModelLayer.BABY_ENDER_DRAGON, BabyEnderDragonModel::createBodyLayer);
        event.registerLayerDefinition(ModelLayer.BABY_ENDER_DRAGON_SITTING, BabyEnderDragonSittingModel::createBodyLayer);
        event.registerLayerDefinition(ModelLayer.ANCIENT_ARMOR, AncientArmorModel::createBodyLayer);
        event.registerLayerDefinition(ModelLayer.PEACOCK, PeacockModel::createBodyLayer);
    }

}
