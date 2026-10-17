package com.tristankechlo.livingthings.util;

import com.mojang.serialization.MapCodec;
import com.tristankechlo.livingthings.NeoForgeLivingThings;
import com.tristankechlo.livingthings.config.GeneralConfig;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.Weighted;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.MobSpawnSettingsBuilder;
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LivingThingsBiomeModifier implements BiomeModifier {

    private Map<Identifier, List<Weighted<MobSpawnSettings.SpawnerData>>> spawnData = null;
    private static final LivingThingsBiomeModifier INSTANCE = new LivingThingsBiomeModifier();
    public static final MapCodec<LivingThingsBiomeModifier> CODEC = MapCodec.unit(INSTANCE);

    @Override
    public void modify(RegistryAccess access, Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (spawnData == null) {
            this.spawnData = GeneralConfig.getSpawnData();
        }
        Optional<ResourceKey<Biome>> biomeID = biome.unwrapKey();
        if (biomeID.isEmpty()) {
            return;
        }
        Identifier biomeName = biomeID.get().identifier();
        if (phase == Phase.ADD && spawnData.containsKey(biomeName)) {
            MobSpawnSettingsBuilder spawns = builder.getMobSpawnSettings();
            List<Weighted<MobSpawnSettings.SpawnerData>> spawnerData = spawnData.get(biomeName);
            for (Weighted<MobSpawnSettings.SpawnerData> data : spawnerData) {
                spawns.addSpawn(data.value().type(), data.weight(), UniformInt.of(
                        data.value().count().minInclusive(),
                        data.value().count().maxInclusive()
                ));
            }
        }
    }

    @Override
    public MapCodec<? extends BiomeModifier> codec() {
        return NeoForgeLivingThings.BIOME_MODIFIER_CODEC.get();
    }

}
