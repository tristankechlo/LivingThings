package com.tristankechlo.livingthings;

import com.tristankechlo.livingthings.entity.*;
import com.tristankechlo.livingthings.init.ModBlocks;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import com.tristankechlo.livingthings.init.ModItems;
import com.tristankechlo.livingthings.init.ModSounds;
import com.tristankechlo.livingthings.mixin.SpawnPlacementsInvoker;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SpawnPlacements.Type;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.BiConsumer;

public final class LivingThings {

    public static final String MOD_ID = "livingthings";
    public static final String MOD_NAME = "Living Things";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static void init() {
        //make sure all classes are loaded
        ModItems.init();
        ModBlocks.init();
        ModSounds.init();
        ModEntityTypes.init();
    }

    public static void registerMobAttributes(BiConsumer<EntityType<? extends LivingEntity>, AttributeSupplier.Builder> consumer) {
        LivingThings.LOGGER.info("Registering MobAttributes");
        consumer.accept(ModEntityTypes.ELEPHANT.get(), ElephantEntity.createAttributes());
        consumer.accept(ModEntityTypes.GIRAFFE.get(), GiraffeEntity.createAttributes());
        consumer.accept(ModEntityTypes.LION.get(), LionEntity.createAttributes());
        consumer.accept(ModEntityTypes.SHARK.get(), SharkEntity.createAttributes());
        consumer.accept(ModEntityTypes.PENGUIN.get(), PenguinEntity.createAttributes());
        consumer.accept(ModEntityTypes.OSTRICH.get(), OstrichEntity.createAttributes());
        consumer.accept(ModEntityTypes.FLAMINGO.get(), FlamingoEntity.createAttributes());
        consumer.accept(ModEntityTypes.CRAB.get(), CrabEntity.createAttributes());
        consumer.accept(ModEntityTypes.MANTARAY.get(), MantarayEntity.createAttributes());
        consumer.accept(ModEntityTypes.RACCOON.get(), RaccoonEntity.createAttributes());
        consumer.accept(ModEntityTypes.OWL.get(), OwlEntity.createAttributes());
        consumer.accept(ModEntityTypes.ANCIENT_BLAZE.get(), AncientBlazeEntity.createAttributes());
        consumer.accept(ModEntityTypes.KOALA.get(), KoalaEntity.createAttributes());
        consumer.accept(ModEntityTypes.SNAIL.get(), SnailEntity.createAttributes());
        consumer.accept(ModEntityTypes.MONKEY.get(), MonkeyEntity.createAttributes());
        consumer.accept(ModEntityTypes.NETHER_KNIGHT.get(), NetherKnightEntity.createAttributes());
        consumer.accept(ModEntityTypes.SHROOMIE.get(), ShroomieEntity.createAttributes());
        consumer.accept(ModEntityTypes.SEAHORSE.get(), SeahorseEntity.createAttributes());
        consumer.accept(ModEntityTypes.BABY_ENDER_DRAGON.get(), BabyEnderDragonEntity.createAttributes());
        consumer.accept(ModEntityTypes.PEACOCK.get(), PeacockEntity.createAttributes());
    }

    public static void registerSpawnPlacements() {
        LivingThings.LOGGER.info("Registering SpawnPlacements");
        SpawnPlacementsInvoker.register(ModEntityTypes.ELEPHANT.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ElephantEntity::checkElephantSpawnRules);
        SpawnPlacementsInvoker.register(ModEntityTypes.GIRAFFE.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GiraffeEntity::checkGiraffeSpawnRules);
        SpawnPlacementsInvoker.register(ModEntityTypes.LION.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LionEntity::checkLionSpawnRules);
        SpawnPlacementsInvoker.register(ModEntityTypes.SHARK.get(), Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SharkEntity::checkSharkSpawnRules);
        SpawnPlacementsInvoker.register(ModEntityTypes.PENGUIN.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PenguinEntity::checkPenguinSpawnRules);
        SpawnPlacementsInvoker.register(ModEntityTypes.OSTRICH.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, OstrichEntity::checkOstrichSpawnRules);
        SpawnPlacementsInvoker.register(ModEntityTypes.FLAMINGO.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, FlamingoEntity::checkFlamingoSpawnRules);
        SpawnPlacementsInvoker.register(ModEntityTypes.CRAB.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CrabEntity::checkCrabSpawnRules);
        SpawnPlacementsInvoker.register(ModEntityTypes.MANTARAY.get(), Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MantarayEntity::checkMantaraySpawnRules);
        SpawnPlacementsInvoker.register(ModEntityTypes.RACCOON.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RaccoonEntity::checkRaccoonSpawnRules);
        SpawnPlacementsInvoker.register(ModEntityTypes.OWL.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, OwlEntity::checkOwlSpawnRules);
        SpawnPlacementsInvoker.register(ModEntityTypes.ANCIENT_BLAZE.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnPlacementsInvoker.register(ModEntityTypes.KOALA.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, KoalaEntity::checkKoalaSpawnRules);
        SpawnPlacementsInvoker.register(ModEntityTypes.SNAIL.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SnailEntity::checkSnailSpawnRules);
        SpawnPlacementsInvoker.register(ModEntityTypes.MONKEY.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, MonkeyEntity::checkMonkeySpawnRules);
        SpawnPlacementsInvoker.register(ModEntityTypes.NETHER_KNIGHT.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnPlacementsInvoker.register(ModEntityTypes.SHROOMIE.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ShroomieEntity::checkShroomieSpawnRules);
        SpawnPlacementsInvoker.register(ModEntityTypes.SEAHORSE.get(), Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SeahorseEntity::checkSeahorseSpawnRules);
        SpawnPlacementsInvoker.register(ModEntityTypes.BABY_ENDER_DRAGON.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BabyEnderDragonEntity::checkBabyEnderDragonSpawnRules);
        SpawnPlacementsInvoker.register(ModEntityTypes.PEACOCK.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PeacockEntity::checkPeacockSpawnRules);
    }

}
