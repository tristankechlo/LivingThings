package com.tristankechlo.livingthings.mixin.worldgen;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.config.entity.NetherKnightConfig;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import com.tristankechlo.livingthings.util.StructureAddon;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mixin(ConfiguredStructureFeature.class)
public abstract class StructureMixin implements StructureAddon {

    @Override
    public void setupSpawnOverrides() {
        MobSpawnSettings.SpawnerData netherKnightSpawnData = new MobSpawnSettings.SpawnerData(ModEntityTypes.NETHER_KNIGHT.get(),
                NetherKnightConfig.get().spawnWeight.get(),
                NetherKnightConfig.get().minSpawnCount.get(),
                NetherKnightConfig.get().maxSpawnCount.get());

        Map<MobCategory, StructureSpawnOverride> customSpawnOverrides = new HashMap<>(this.spawnOverrides); //make copy, because original is unmodifiable
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

        LivingThings.LOGGER.info("Added Nether Knight to Fortress Spawns");
        this.setSpawnOverrides(customSpawnOverrides);
    }

    @Mutable
    @Accessor("spawnOverrides")
    public abstract void setSpawnOverrides(Map<MobCategory, StructureSpawnOverride> spawnOverrides);

    @Shadow
    @Final
    public Map<MobCategory, StructureSpawnOverride> spawnOverrides;

}
