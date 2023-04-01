package com.tristankechlo.livingthings.config.entity;

import com.tristankechlo.livingthings.config.util.EntityConfig;
import com.tristankechlo.livingthings.config.values.BooleanValue;
import com.tristankechlo.livingthings.config.values.IngredientValue;
import com.tristankechlo.livingthings.config.values.NumberValue.DoubleValue;
import com.tristankechlo.livingthings.config.values.NumberValue.IntegerValue;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public final class GiraffeConfig extends EntityConfig {

    private static final GiraffeConfig INSTANCE = new GiraffeConfig();

    public final BooleanValue canAttack = new BooleanValue("canAttack", true);
    public final DoubleValue health = new DoubleValue("health", 30.0D, MIN_HEALTH, MAX_HEALTH);
    public final DoubleValue movementSpeed = new DoubleValue("movementSpeed", 0.3D, MIN_SPEED, MAX_SPEED);
    public final DoubleValue attackDamage = new DoubleValue("attackDamage", 4.0D, MIN_DAMAGE, MAX_DAMAGE);
    public final IntegerValue maxSpawnedInChunk = new IntegerValue("maxSpawnedInChunk", 5, 1, 15);
    public final IngredientValue temptationItems = new IngredientValue("temptationItems", Ingredient.of(Items.WHEAT));

    public final IntegerValue color1Weight = new IntegerValue("color1Weight", 50, 0, Integer.MAX_VALUE);
    public final IntegerValue color2Weight = new IntegerValue("color2Weight", 50, 0, Integer.MAX_VALUE);
    public final IntegerValue colorAlbinoWeight = new IntegerValue("colorAlbinoWeight", 1, 0, Integer.MAX_VALUE);

    private GiraffeConfig() {
        super("giraffe");
        this.registerConfigValues(this.canAttack, this.health, this.movementSpeed, this.attackDamage, this.maxSpawnedInChunk, this.temptationItems);
        this.registerForCategory("colorsVariants", this.color1Weight, this.color2Weight, this.colorAlbinoWeight);
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

    public static int maxSpawnedInChunk() {
        return INSTANCE.maxSpawnedInChunk.get();
    }

    public static Ingredient temptationItems() {
        return INSTANCE.temptationItems.get();
    }

}
