package com.tristankechlo.livingthings.util;

import com.mojang.serialization.MapCodec;
import com.tristankechlo.livingthings.ForgeLivingThings;
import com.tristankechlo.livingthings.config.GeneralConfig;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.Weighted;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.attribute.EnvironmentAttributeMap;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.attribute.modifier.MobSpawnSettingsModifier;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.MobSpawnSettingsBuilder;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LivingThingsBiomeModifier implements BiomeModifier {

    private Map<Identifier, List<Weighted<MobSpawnSettings.SpawnerData>>> spawnData = null;
    private static final LivingThingsBiomeModifier INSTANCE = new LivingThingsBiomeModifier();
    public static final MapCodec<LivingThingsBiomeModifier> CODEC = MapCodec.unit(INSTANCE);

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (spawnData == null) {
            this.spawnData = GeneralConfig.getSpawnData();
        }
        Optional<ResourceKey<Biome>> biomeID = biome.unwrapKey();
        if (biomeID.isEmpty()) {
            return;
        }
        Identifier biomeName = biomeID.get().identifier();
        if (phase == Phase.ADD && spawnData.containsKey(biomeName)) {
            EnvironmentAttributeMap.Entry<MobSpawnSettings, ?> spawnSettings = builder.attributes().get(EnvironmentAttributes.NATURAL_MOB_SPAWNS);
            if (spawnSettings == null || spawnSettings.modifier() != MobSpawnSettingsModifier.overlay()) {
                return;
            }
            MobSpawnSettingsBuilder spawns = new MobSpawnSettingsBuilder(spawnSettings.cast(MobSpawnSettingsModifier.overlay()));
            List<Weighted<MobSpawnSettings.SpawnerData>> spawnerData = spawnData.get(biomeName);
            for (Weighted<MobSpawnSettings.SpawnerData> data : spawnerData) {
                spawns.addSpawn(data.value().type(), data.weight(), UniformInt.of(
                        data.value().count().minInclusive(),
                        data.value().count().maxInclusive()
                ));
            }
            builder.attributes().modify(EnvironmentAttributes.NATURAL_MOB_SPAWNS, MobSpawnSettingsModifier.overlay(), spawns.build());
        }
    }

    @Override
    public MapCodec<? extends BiomeModifier> codec() {
        return ForgeLivingThings.BIOME_MODIFIER_CODEC.get();
    }

}
