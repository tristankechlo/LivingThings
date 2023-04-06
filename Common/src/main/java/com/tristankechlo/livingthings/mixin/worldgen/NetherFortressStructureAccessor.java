package com.tristankechlo.livingthings.mixin.worldgen;

import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.structure.structures.NetherFortressStructure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(NetherFortressStructure.class)
public interface NetherFortressStructureAccessor {

    @Mutable
    @Accessor("FORTRESS_ENEMIES")
    static void setFortressEnemies(WeightedRandomList<MobSpawnSettings.SpawnerData> FORTRESS_ENEMIES) {
        throw new AssertionError();
    }

}
