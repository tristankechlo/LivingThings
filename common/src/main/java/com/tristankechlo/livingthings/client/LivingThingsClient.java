package com.tristankechlo.livingthings.client;

import com.tristankechlo.livingthings.client.model.armor.AncientArmorModel;
import com.tristankechlo.livingthings.client.model.entity.*;
import com.tristankechlo.livingthings.client.renderer.*;
import com.tristankechlo.livingthings.entity.projectile.ThrownOstrichEgg;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshTransformer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class LivingThingsClient {

    public static <E extends Entity> void registerRenderers(BiConsumer<EntityType<?>, EntityRendererProvider<E>> consumer) {
        consumer.accept(ModEntityTypes.ANCIENT_BLAZE.get(), (c) -> (EntityRenderer<E, ?>) new AncientBlazeRenderer(c));
        consumer.accept(ModEntityTypes.BABY_ENDER_DRAGON.get(), (c) -> (EntityRenderer<E, ?>) new BabyEnderDragonRenderer(c));
        consumer.accept(ModEntityTypes.CRAB.get(), (c) -> (EntityRenderer<E, ?>) new CrabRenderer(c));
        consumer.accept(ModEntityTypes.ELEPHANT.get(), (c) -> (EntityRenderer<E, ?>) new ElephantRenderer(c));
        consumer.accept(ModEntityTypes.FLAMINGO.get(), (c) -> (EntityRenderer<E, ?>) new FlamingoRenderer(c));
        consumer.accept(ModEntityTypes.GIRAFFE.get(), (c) -> (EntityRenderer<E, ?>) new GiraffeRenderer(c));
        consumer.accept(ModEntityTypes.KOALA.get(), (c) -> (EntityRenderer<E, ?>) new KoalaRenderer(c));
        consumer.accept(ModEntityTypes.LION.get(), (c) -> (EntityRenderer<E, ?>) new LionRenderer(c));
        consumer.accept(ModEntityTypes.MANTARAY.get(), (c) -> (EntityRenderer<E, ?>) new MantarayRenderer(c));
        consumer.accept(ModEntityTypes.MONKEY.get(), (c) -> (EntityRenderer<E, ?>) new MonkeyRenderer(c));
        consumer.accept(ModEntityTypes.NETHER_KNIGHT.get(), (c) -> (EntityRenderer<E, ?>) new NetherKnightRenderer(c));
        consumer.accept(ModEntityTypes.OSTRICH.get(), (c) -> (EntityRenderer<E, ?>) new OstrichRenderer(c));
        consumer.accept(ModEntityTypes.OWL.get(), (c) -> (EntityRenderer<E, ?>) new OwlRenderer(c));
        consumer.accept(ModEntityTypes.PEACOCK.get(), (c) -> (EntityRenderer<E, ?>) new PeacockRenderer(c));
        consumer.accept(ModEntityTypes.PENGUIN.get(), (c) -> (EntityRenderer<E, ?>) new PenguinRenderer(c));
        consumer.accept(ModEntityTypes.RACCOON.get(), (c) -> (EntityRenderer<E, ?>) new RaccoonRenderer(c));
        consumer.accept(ModEntityTypes.SEAHORSE.get(), (c) -> (EntityRenderer<E, ?>) new SeahorseRenderer(c));
        consumer.accept(ModEntityTypes.SHARK.get(), (c) -> (EntityRenderer<E, ?>) new SharkRenderer(c));
        consumer.accept(ModEntityTypes.SHROOMIE.get(), (c) -> (EntityRenderer<E, ?>) new ShroomieRenderer(c));
        consumer.accept(ModEntityTypes.SNAIL.get(), (c) -> (EntityRenderer<E, ?>) new SnailRenderer(c));
        consumer.accept(ModEntityTypes.THROWN_OSTRICH_EGG.get(), (c) -> (EntityRenderer<E, ?>) new ThrownItemRenderer<ThrownOstrichEgg>(c));
    }

    public static void registerLayerDefinition(BiConsumer<ModelLayerLocation, Supplier<LayerDefinition>> consumer) {
        consumer.accept(ModelLayer.ANCIENT_BLAZE, AncientBlazeModel::createBodyLayer);
        consumer.accept(ModelLayer.BABY_ENDER_DRAGON, BabyEnderDragonModel::createBodyLayer);
        consumer.accept(ModelLayer.BABY_ENDER_DRAGON_SITTING, BabyEnderDragonSittingModel::createBodyLayer);
        consumer.accept(ModelLayer.CRAB, CrabModel::createBodyLayer);
        consumer.accept(ModelLayer.CRAB_BABY, transform(CrabModel::createBodyLayer, CrabRenderer.BABY_TRANSFORMER));
        consumer.accept(ModelLayer.ELEPHANT, ElephantModel::createBodyLayer);
        consumer.accept(ModelLayer.ELEPHANT_BABY, transform(ElephantModel::createBodyLayer, ElephantRenderer.BABY_TRANSFORMER));
        consumer.accept(ModelLayer.FLAMINGO, FlamingoModel::createBodyLayer);
        consumer.accept(ModelLayer.FLAMINGO_BABY, transform(FlamingoModel::createBodyLayer, FlamingoRenderer.BABY_TRANSFORMER));
        consumer.accept(ModelLayer.GIRAFFE, GiraffeModel::createBodyLayer);
        consumer.accept(ModelLayer.GIRAFFE_BABY, transform(GiraffeModel::createBodyLayer, GiraffeRenderer.BABY_TRANSFORMER));
        consumer.accept(ModelLayer.KOALA, KoalaModel::createBodyLayer);
        consumer.accept(ModelLayer.KOALA_BABY, transform(KoalaModel::createBodyLayer, KoalaRenderer.BABY_TRANSFORMER));
        consumer.accept(ModelLayer.LION, LionModel::createBodyLayer);
        consumer.accept(ModelLayer.LION_BABY, transform(LionModel::createBodyLayer, LionRenderer.BABY_TRANSFORMER));
        consumer.accept(ModelLayer.MANTARAY, MantarayModel::createBodyLayer);
        consumer.accept(ModelLayer.MONKEY, MonkeyModel::createBodyLayer);
        consumer.accept(ModelLayer.MONKEY_BABY, transform(MonkeyModel::createBodyLayer, MonkeyRenderer.BABY_TRANSFORMER));
        consumer.accept(ModelLayer.MONKEY_SITTING, MonkeySittingModel::createBodyLayer);
        consumer.accept(ModelLayer.MONKEY_SITTING_BABY, transform(MonkeySittingModel::createBodyLayer, MonkeyRenderer.BABY_TRANSFORMER));
        consumer.accept(ModelLayer.NETHER_KNIGHT, NetherKnightModel::createBodyLayer);
        consumer.accept(ModelLayer.OSTRICH, OstrichModel::createBodyLayer);
        consumer.accept(ModelLayer.OSTRICH_BABY, transform(OstrichModel::createBodyLayer, OstrichRenderer.BABY_TRANSFORMER));
        consumer.accept(ModelLayer.OWL, transform(OwlModel::createBodyLayer, OwlRenderer.NORMAL_TRANSFORMER));
        consumer.accept(ModelLayer.OWL_BABY, transform(OwlModel::createBodyLayer, OwlRenderer.BABY_TRANSFORMER));
        consumer.accept(ModelLayer.PEACOCK, PeacockModel::createBodyLayer);
        consumer.accept(ModelLayer.PEACOCK_BABY, transform(PeacockModel::createBodyLayer, PeacockRenderer.BABY_TRANSFORMER));
        consumer.accept(ModelLayer.PENGUIN, PenguinModel::createBodyLayer);
        consumer.accept(ModelLayer.PENGUIN_BABY, transform(PenguinModel::createBodyLayer, PenguinRenderer.BABY_TRANSFORMER));
        consumer.accept(ModelLayer.RACCOON, RaccoonModel::createBodyLayer);
        consumer.accept(ModelLayer.RACCOON_BABY, transform(RaccoonModel::createBodyLayer, RaccoonRenderer.BABY_TRANSFORMER));
        consumer.accept(ModelLayer.SEAHORSE, SeahorseModel::createBodyLayer);
        consumer.accept(ModelLayer.SHARK, SharkModel::createBodyLayer);
        consumer.accept(ModelLayer.SHROOMIE, ShroomieModel::createBodyLayer);
        consumer.accept(ModelLayer.SNAIL, transform(SnailModel::createBodyLayer, SnailRenderer.NORMAL_TRANSFORMER));
        consumer.accept(ModelLayer.SNAIL_BABY, transform(SnailModel::createBodyLayer, SnailRenderer.BABY_TRANSFORMER));

        consumer.accept(ModelLayer.ANCIENT_ARMOR, AncientArmorModel::createBodyLayer);
    }

    private static Supplier<LayerDefinition> transform(Supplier<LayerDefinition> provider, MeshTransformer transformer) {
        return () -> provider.get().apply(transformer);
    }

}
