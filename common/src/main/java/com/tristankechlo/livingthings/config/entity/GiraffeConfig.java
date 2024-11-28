package com.tristankechlo.livingthings.config.entity;

import com.tristankechlo.livingthings.config.util.EntityConfig;
import com.tristankechlo.livingthings.config.util.SpawnData;
import com.tristankechlo.livingthings.config.values.BooleanValue;
import com.tristankechlo.livingthings.config.values.ListValue;
import com.tristankechlo.livingthings.config.values.NumberValue.DoubleValue;
import com.tristankechlo.livingthings.config.values.NumberValue.IntegerValue;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biomes;

import java.util.List;

public final class GiraffeConfig extends EntityConfig {

    private static final GiraffeConfig INSTANCE = new GiraffeConfig();

    public final BooleanValue canAttack = new BooleanValue("canAttack", true);
    public final DoubleValue health = new DoubleValue("health", 30.0D, MIN_HEALTH, MAX_HEALTH);
    public final DoubleValue movementSpeed = new DoubleValue("movementSpeed", 0.3D, MIN_SPEED, MAX_SPEED);
    public final DoubleValue attackDamage = new DoubleValue("attackDamage", 4.0D, MIN_DAMAGE, MAX_DAMAGE);
    public final DoubleValue temptRange = new DoubleValue("temptRange", 10.0D, MIN_TEMPT, MAX_TEMPT);
    public final IntegerValue maxSpawnedInChunk = new IntegerValue("maxSpawnedInChunk", 5, 1, 15);
    public final ListValue<SpawnData> spawnBiomes = new ListValue<>("spawnBiomes", createDefaultSpawns(), SpawnData::serialize, SpawnData::deserialize);

    public final IntegerValue color1Weight = new IntegerValue("color1Weight", 50, 0, Integer.MAX_VALUE);
    public final IntegerValue color2Weight = new IntegerValue("color2Weight", 50, 0, Integer.MAX_VALUE);
    public final IntegerValue colorWhiteWeight = new IntegerValue("colorAlbinoWeight", 1, 0, Integer.MAX_VALUE);

    private GiraffeConfig() {
        super("giraffe");
        this.registerConfigValues(canAttack, health, movementSpeed, attackDamage, temptRange, maxSpawnedInChunk, spawnBiomes);
        this.registerForCategory("colorWeights", color1Weight, color2Weight, colorWhiteWeight);
    }

    public static GiraffeConfig get() {
        return INSTANCE;
    }

    public static boolean canAttack() {
        return INSTANCE.canAttack.get();
    }

    public static double health() {
        return INSTANCE.health.get();
    }

    public static double movementSpeed() {
        return INSTANCE.movementSpeed.get();
    }

    public static double attackDamage() {
        return INSTANCE.attackDamage.get();
    }

    public static double temptRange() {
        return INSTANCE.temptRange.get();
    }

    public static int maxSpawnedInChunk() {
        return INSTANCE.maxSpawnedInChunk.get();
    }

    private static List<SpawnData> createDefaultSpawns() {
        return List.of(new SpawnData(15, 3, 5, new ResourceKey[]{Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU, Biomes.WINDSWEPT_SAVANNA}));
    }

}
