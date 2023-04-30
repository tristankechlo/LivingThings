package com.tristankechlo.livingthings.mixin.worldgen;

import com.tristankechlo.livingthings.config.entity.NetherKnightConfig;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import net.minecraft.core.HolderSet;
import net.minecraft.data.worldgen.StructureFeatures;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mixin(ConfiguredStructureFeature.class)
public abstract class StructureMixin {

    //add Nether Knight to Fortress Spawns
    @Inject(at = @At("RETURN"), method = "<init>")
    private void LivingThings$Constructor(StructureFeature<? extends FeatureConfiguration> feature, FeatureConfiguration config,
                                          HolderSet<Biome> biomes, boolean adaptNoise, Map<MobCategory, StructureSpawnOverride> spawnOverrides, CallbackInfo ci) {
        if (feature != StructureFeatures.FORTRESS) {
            return;
        }

        MobSpawnSettings.SpawnerData netherKnightSpawnData = new MobSpawnSettings.SpawnerData(ModEntityTypes.NETHER_KNIGHT.get(),
                NetherKnightConfig.get().spawnWeight.get(),
                NetherKnightConfig.get().minSpawnCount.get(),
                NetherKnightConfig.get().maxSpawnCount.get());

        Map<MobCategory, StructureSpawnOverride> customSpawnOverrides = new HashMap<>(spawnOverrides); //make copy, because original is unmodifiable
        StructureSpawnOverride oldMonsterSpawns = customSpawnOverrides.get(MobCategory.MONSTER);
        List<MobSpawnSettings.SpawnerData> newMonsterSpawns = new ArrayList<>();
        if (oldMonsterSpawns != null) {
            newMonsterSpawns = new ArrayList<>(oldMonsterSpawns.spawns().unwrap()); //make copy, because original is unmodifiable
        }
        newMonsterSpawns.add(netherKnightSpawnData);
        WeightedRandomList<MobSpawnSettings.SpawnerData> weightedRandomList = WeightedRandomList.create(newMonsterSpawns);
        NetherFortressStructureAccessor.setFortressEnemies(weightedRandomList); //set static field 'FORTRESS_ENEMIES', values are used somewhere else too
        StructureSpawnOverride newOverrides = new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.PIECE, weightedRandomList);
        customSpawnOverrides.put(MobCategory.MONSTER, newOverrides);


        this.spawnOverrides = customSpawnOverrides;
    }

    @Mutable
    public Map<MobCategory, StructureSpawnOverride> spawnOverrides;

}
