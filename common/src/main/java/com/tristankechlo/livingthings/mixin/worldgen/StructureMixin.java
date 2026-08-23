package com.tristankechlo.livingthings.mixin.worldgen;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.config.entity.NetherKnightConfig;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import com.tristankechlo.livingthings.util.StructureAddon;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.structures.NetherFortressStructure;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mixin(Structure.class)
public abstract class StructureMixin implements StructureAddon {

    @Override
    public void livingthings$setupSpawnOverrides() {
        if (this.type() != StructureType.FORTRESS) {
            LivingThings.LOGGER.info("Structure is not a Nether Fortress, skipping Nether Knight spawn addition");
            return;
        }
        final int spawnWeight = NetherKnightConfig.get().spawnWeight.get();
        if (spawnWeight <= 0) {
            LivingThings.LOGGER.info("Nether Knight spawn weight is set to 0, not added to Fortress Spawns");
            return;
        }
        MobSpawnSettings.SpawnerData netherKnightSpawnData = new MobSpawnSettings.SpawnerData(ModEntityTypes.NETHER_KNIGHT.get(),
                spawnWeight,
                NetherKnightConfig.get().minSpawnCount.get(),
                NetherKnightConfig.get().maxSpawnCount.get()
        );

        this.livingthings$setupFortressEnemies(netherKnightSpawnData);
        this.livingthings$setupSpawnOverrides(netherKnightSpawnData);
    }

    @Unique
    private void livingthings$setupSpawnOverrides(MobSpawnSettings.SpawnerData netherKnightSpawnData) {
        // make copy of existing spawn overrides, because original is unmodifiable
        Map<MobCategory, StructureSpawnOverride> customSpawnOverrides = new HashMap<>(this.settings.spawnOverrides());
        StructureSpawnOverride oldMonsterSpawns = customSpawnOverrides.get(MobCategory.MONSTER);
        List<MobSpawnSettings.SpawnerData> newMonsterSpawns = new ArrayList<>();
        if (oldMonsterSpawns != null) {
            newMonsterSpawns = new ArrayList<>(oldMonsterSpawns.spawns().unwrap());
        }

        // check if already present
        final boolean present = newMonsterSpawns.stream().anyMatch((data) -> data.type == ModEntityTypes.NETHER_KNIGHT.get());
        if (present) {
            LivingThings.LOGGER.info("Nether Knight is already present in Fortress Spawns, skipping addition");
            return;
        }

        // create new spawn override with Nether Knight added
        newMonsterSpawns.add(netherKnightSpawnData);
        WeightedRandomList<MobSpawnSettings.SpawnerData> weightedRandomList = WeightedRandomList.create(newMonsterSpawns);
        StructureSpawnOverride newOverrides = new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.PIECE, weightedRandomList);
        customSpawnOverrides.put(MobCategory.MONSTER, newOverrides);

        // overwrite settings with new structure settings
        Structure.StructureSettings newSettings = new Structure.StructureSettings(
                this.settings.biomes(),
                customSpawnOverrides,
                this.settings.step(),
                this.settings.terrainAdaptation()
        );
        this.setSettings(newSettings);
        LivingThings.LOGGER.info("Added Nether Knight to Fortress Spawns");
    }

    @Unique
    private void livingthings$setupFortressEnemies(MobSpawnSettings.SpawnerData netherKnightSpawnData) {
        List<MobSpawnSettings.SpawnerData> newEnemies = new ArrayList<>(NetherFortressStructure.FORTRESS_ENEMIES.unwrap());
        final boolean present = newEnemies.stream().anyMatch((data) -> data.type == ModEntityTypes.NETHER_KNIGHT.get());
        if (present) {
            LivingThings.LOGGER.info("Nether Knight is already present in FORTRESS_ENEMIES, skipping addition");
            return;
        }
        newEnemies.add(netherKnightSpawnData);
        WeightedRandomList<MobSpawnSettings.SpawnerData> weightedList = WeightedRandomList.create(newEnemies);
        NetherFortressStructureAccessor.setFortressEnemies(weightedList);
        LivingThings.LOGGER.info("Added Nether Knight to FORTRESS_ENEMIES");
    }

    @Mutable
    @Accessor("settings")
    public abstract void setSettings(Structure.StructureSettings newSettings);

    @Shadow
    @Final
    protected Structure.StructureSettings settings;

    @Shadow
    public abstract StructureType<?> type();

}
