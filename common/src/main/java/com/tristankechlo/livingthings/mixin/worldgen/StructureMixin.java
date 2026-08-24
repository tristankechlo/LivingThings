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
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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
        if (NetherKnightConfig.get().spawnWeight.get() <= 0) {
            LivingThings.LOGGER.info("Nether Knight spawn weight is set to 0, not added to Fortress Spawns");
            return;
        }

        final List<MobSpawnSettings.SpawnerData> newEnemies = new ArrayList<>(NetherFortressStructure.FORTRESS_ENEMIES.unwrap());
        final boolean present = newEnemies.stream().anyMatch((data) -> data.type == ModEntityTypes.NETHER_KNIGHT.get());
        if (present) {
            LivingThings.LOGGER.info("Nether Knight is already present in FORTRESS_ENEMIES, skipping addition");
            return;
        }
        newEnemies.add(livingThings$makeSpawnerData());
        NetherFortressStructureAccessor.setFortressEnemies(WeightedRandomList.create(newEnemies));
        LivingThings.LOGGER.info("Added Nether Knight to FORTRESS_ENEMIES");
    }

    @Unique
    private Map<MobCategory, StructureSpawnOverride> livingThings$customSpawnOverrides = null;

    //add Nether Knight to Fortress Spawns
    @Inject(at = @At("HEAD"), method = "spawnOverrides", cancellable = true)
    private void livingThings$spawnOverrides(CallbackInfoReturnable<Map<MobCategory, StructureSpawnOverride>> cir) {
        if (this.type() != StructureType.FORTRESS || NetherKnightConfig.get().spawnWeight.get() <= 0) {
            return;
        }

        // if spawn overrides already created, return them
        if (this.livingThings$customSpawnOverrides != null) {
            cir.setReturnValue(this.livingThings$customSpawnOverrides);
            return;
        }

        // make copy of existing spawn overrides, because original is unmodifiable
        this.livingThings$customSpawnOverrides = new HashMap<>(this.settings.spawnOverrides());
        // check if already present
        final StructureSpawnOverride oldMonsterSpawns = this.livingThings$customSpawnOverrides.get(MobCategory.MONSTER);
        List<MobSpawnSettings.SpawnerData> newMonsterSpawns = new ArrayList<>();
        if (oldMonsterSpawns != null) {
            newMonsterSpawns = new ArrayList<>(oldMonsterSpawns.spawns().unwrap());
        }
        final boolean present = newMonsterSpawns.stream().anyMatch((data) -> data.type == ModEntityTypes.NETHER_KNIGHT.get());
        if (present) {
            LivingThings.LOGGER.info("Nether Knight is already present in Fortress Spawns, skipping addition");
            return;
        }

        // create new spawn override with Nether Knight added
        newMonsterSpawns.add(livingThings$makeSpawnerData());
        final WeightedRandomList<MobSpawnSettings.SpawnerData> weightedRandomList = WeightedRandomList.create(newMonsterSpawns);
        final StructureSpawnOverride newOverrides = new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.PIECE, weightedRandomList);
        this.livingThings$customSpawnOverrides.put(MobCategory.MONSTER, newOverrides);
        cir.setReturnValue(this.livingThings$customSpawnOverrides);
        LivingThings.LOGGER.info("Added Nether Knight to Fortress Spawns via spawnOverrides");
    }

    @Unique
    private MobSpawnSettings.SpawnerData livingThings$makeSpawnerData() {
        return new MobSpawnSettings.SpawnerData(ModEntityTypes.NETHER_KNIGHT.get(),
                NetherKnightConfig.get().spawnWeight.get(),
                NetherKnightConfig.get().minSpawnCount.get(),
                NetherKnightConfig.get().maxSpawnCount.get()
        );
    }

    @Shadow
    @Final
    protected Structure.StructureSettings settings;

    @Shadow
    public abstract StructureType<?> type();

}
