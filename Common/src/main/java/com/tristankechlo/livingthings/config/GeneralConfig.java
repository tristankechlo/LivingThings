package com.tristankechlo.livingthings.config;

import com.google.common.collect.ImmutableMap;
import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.config.entity.*;
import com.tristankechlo.livingthings.config.util.EntityConfig;
import com.tristankechlo.livingthings.config.util.SpawnData;
import com.tristankechlo.livingthings.config.values.BooleanValue;
import com.tristankechlo.livingthings.config.values.NumberValue.DoubleValue;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.MobSpawnSettings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public final class GeneralConfig extends EntityConfig {

    private static final GeneralConfig INSTANCE = new GeneralConfig();

    public final BooleanValue ambientMode = new BooleanValue("ambientMode", false);
    public final BooleanValue doBananaDrops = new BooleanValue("doBananaDrops", true);
    public final DoubleValue bananaDropChance = new DoubleValue("bananaDropChance", 45.0D, 0.0D, 100.0D);

    private GeneralConfig() {
        super(LivingThings.MOD_ID);
        this.registerConfigValues(this.ambientMode, this.doBananaDrops, this.bananaDropChance);
    }

    public static GeneralConfig get() {
        return INSTANCE;
    }

    public static Map<ResourceLocation, List<MobSpawnSettings.SpawnerData>> getSpawnData() {
        Map<ResourceLocation, List<MobSpawnSettings.SpawnerData>> hashMap = new HashMap<>();
        getDefaults().forEach((entityType, spawnDataSupplier) -> {
            spawnDataSupplier.get().forEach((spawnData) -> {
                spawnData.getBiomes().forEach((biome) -> {
                    List<MobSpawnSettings.SpawnerData> spawnDataList = hashMap.getOrDefault(biome, new ArrayList<>());
                    spawnDataList.add(spawnData.asSpawnerData(entityType));
                    hashMap.put(biome, spawnDataList);
                });
            });
        });
        return hashMap;
    }

    private static Map<EntityType<?>, Supplier<List<SpawnData>>> getDefaults() {
        ImmutableMap.Builder<EntityType<?>, Supplier<List<SpawnData>>> builder = ImmutableMap.builder();
        builder.put(ModEntityTypes.CRAB.get(), CrabConfig.get().spawnBiomes::get);
        builder.put(ModEntityTypes.ELEPHANT.get(), ElephantConfig.get().spawnBiomes::get);
        builder.put(ModEntityTypes.FLAMINGO.get(), FlamingoConfig.get().spawnBiomes::get);
        builder.put(ModEntityTypes.GIRAFFE.get(), GiraffeConfig.get().spawnBiomes::get);
        builder.put(ModEntityTypes.KOALA.get(), KoalaConfig.get().spawnBiomes::get);
        builder.put(ModEntityTypes.LION.get(), LionConfig.get().spawnBiomes::get);
        builder.put(ModEntityTypes.MANTARAY.get(), MantarayConfig.get().spawnBiomes::get);
        builder.put(ModEntityTypes.MONKEY.get(), MonkeyConfig.get().spawnBiomes::get);
        builder.put(ModEntityTypes.OSTRICH.get(), OstrichConfig.get().spawnBiomes::get);
        builder.put(ModEntityTypes.OWL.get(), OwlConfig.get().spawnBiomes::get);
        builder.put(ModEntityTypes.PENGUIN.get(), PenguinConfig.get().spawnBiomes::get);
        builder.put(ModEntityTypes.RACCOON.get(), RaccoonConfig.get().spawnBiomes::get);
        builder.put(ModEntityTypes.SEAHORSE.get(), SeahorseConfig.get().spawnBiomes::get);
        builder.put(ModEntityTypes.SHARK.get(), SharkConfig.get().spawnBiomes::get);
        builder.put(ModEntityTypes.SHROOMIE.get(), ShroomieConfig.get().spawnBiomes::get);
        builder.put(ModEntityTypes.SNAIL.get(), SnailConfig.get().spawnBiomes::get);
        return builder.build();
    }

}
