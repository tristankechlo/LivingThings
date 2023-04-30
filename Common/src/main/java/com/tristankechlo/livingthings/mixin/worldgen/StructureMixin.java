package com.tristankechlo.livingthings.mixin.worldgen;

import com.tristankechlo.livingthings.config.entity.NetherKnightConfig;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.StructureType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mixin(Structure.class)
public abstract class StructureMixin {

    private Map<MobCategory, StructureSpawnOverride> customSpawnOverrides = null;

    //add Nether Knight to Fortress Spawns
    @Inject(at = @At("HEAD"), method = "spawnOverrides", cancellable = true)
    private void LivingThings$spawnOverrides(CallbackInfoReturnable<Map<MobCategory, StructureSpawnOverride>> cir) {
        if (this.type() != StructureType.FORTRESS) {
            return;
        }
        if (customSpawnOverrides != null) {
            //if already loaded, return cached map
            cir.setReturnValue(customSpawnOverrides);
            return;
        }

        MobSpawnSettings.SpawnerData netherKnightSpawnData = new MobSpawnSettings.SpawnerData(ModEntityTypes.NETHER_KNIGHT.get(),
                NetherKnightConfig.get().spawnWeight.get(),
                NetherKnightConfig.get().minSpawnCount.get(),
                NetherKnightConfig.get().maxSpawnCount.get());

        customSpawnOverrides = new HashMap<>(settings.spawnOverrides()); //make copy, because original is unmodifiable
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

        cir.setReturnValue(customSpawnOverrides);
    }

    @Shadow
    @Final
    protected Structure.StructureSettings settings;

    @Shadow
    public abstract StructureType<?> type();

}
