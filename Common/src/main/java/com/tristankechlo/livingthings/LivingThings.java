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
    }

    public static void registerSpawnPlacements() {
        LivingThings.LOGGER.info("Registering SpawnPlacements");
        SpawnPlacementsInvoker.register(ModEntityTypes.ELEPHANT.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ElephantEntity::checkElephantSpawnRules);
        SpawnPlacementsInvoker.register(ModEntityTypes.GIRAFFE.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GiraffeEntity::checkGiraffeSpawnRules);
        SpawnPlacementsInvoker.register(ModEntityTypes.LION.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LionEntity::checkLionSpawnRules);
        SpawnPlacementsInvoker.register(ModEntityTypes.SHARK.get(), Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SharkEntity::checkSharkSpawnRules);
        SpawnPlacementsInvoker.register(ModEntityTypes.PENGUIN.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PenguinEntity::checkPenguinSpawnRules);
    }

}
