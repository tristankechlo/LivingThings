package com.tristankechlo.livingthings.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.SpawnPlacements.SpawnPredicate;
import net.minecraft.world.entity.SpawnPlacements.Type;
import net.minecraft.world.level.levelgen.Heightmap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(SpawnPlacements.class)
public interface SpawnPlacementsInvoker {

    @Invoker("register")
    public static<T extends Mob> void register(EntityType<T> entityType, Type type, Heightmap.Types heightmap, SpawnPredicate<T> predicate) {
        throw new IllegalStateException("Mixin did not apply!");
    }

}
