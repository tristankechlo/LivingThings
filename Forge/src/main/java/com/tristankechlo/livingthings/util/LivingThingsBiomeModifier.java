package com.tristankechlo.livingthings.util;

import com.mojang.serialization.Codec;
import com.tristankechlo.livingthings.ForgeLivingThings;
import com.tristankechlo.livingthings.config.GeneralConfig;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.MobSpawnSettingsBuilder;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LivingThingsBiomeModifier implements BiomeModifier {

    private Map<ResourceLocation, List<MobSpawnSettings.SpawnerData>> spawnData = null;
    private static final LivingThingsBiomeModifier INSTANCE = new LivingThingsBiomeModifier();
    public static final Codec<LivingThingsBiomeModifier> CODEC = Codec.unit(INSTANCE);

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (spawnData == null) {
            this.spawnData = GeneralConfig.getSpawnData();
        }
        Optional<ResourceKey<Biome>> biomeID = biome.unwrapKey();
        if (biomeID.isEmpty()) {
            return;
        }
        ResourceLocation biomeName = biomeID.get().location();
        if (phase == Phase.ADD && spawnData.containsKey(biomeName)) {
            MobSpawnSettingsBuilder spawns = builder.getMobSpawnSettings();
            List<MobSpawnSettings.SpawnerData> spawnerData = spawnData.get(biomeName);
            for (MobSpawnSettings.SpawnerData data : spawnerData) {
                spawns.addSpawn(data.type.getCategory(), data);
            }
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return ForgeLivingThings.BIOME_MODIFIER_CODEC.get();
    }

}
