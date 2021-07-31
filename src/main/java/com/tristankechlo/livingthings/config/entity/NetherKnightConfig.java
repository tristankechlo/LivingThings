package com.tristankechlo.livingthings.config.entity;

import java.util.Arrays;
import java.util.List;

import com.tristankechlo.livingthings.config.LivingThingsConfig;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class NetherKnightConfig {

	public final DoubleValue health;
	public final DoubleValue speed;
	public final DoubleValue damage;
	public final BooleanValue canAttack;
	public final ConfigValue<List<? extends String>> swords;
	public final ConfigValue<List<? extends String>> axes;
	public final IntValue spawnWeight;
	public final IntValue minSpawnCount;
	public final IntValue maxSpawnCount;

	public NetherKnightConfig(ForgeConfigSpec.Builder builder) {
		builder.comment("Mob-Config for NetherKnight").push("NetherKnight");

		health = builder.comment(LivingThingsConfig.REQUIRES_RESTART).worldRestart().defineInRange("Health", 60.0D,
				LivingThingsConfig.MIN_HEALTH, LivingThingsConfig.MAX_HEALTH);

		speed = builder.comment(LivingThingsConfig.REQUIRES_RESTART + " | " + LivingThingsConfig.HIGH_IMPACT)
				.worldRestart()
				.defineInRange("MovementSpeed", 0.26D, LivingThingsConfig.MIN_SPEED, LivingThingsConfig.MAX_SPEED);

		damage = builder.comment(LivingThingsConfig.REQUIRES_RESTART).worldRestart().defineInRange("AttackDamage",
				10.0D, LivingThingsConfig.MIN_DAMAGE, LivingThingsConfig.MAX_DAMAGE);

		canAttack = builder.define("CanAttack", true);

		swords = builder
				.comment(LivingThingsConfig.REQUIRES_RESTART
						+ " | All names listed in here are possible names for the sword of the NetherKnight")
				.worldRestart().defineList("SwordNames",
						Arrays.asList("Edge of Suffering", "Dragon's Curse", "The Soul Harvester"),
						(string) -> validateString(string));

		axes = builder
				.comment(LivingThingsConfig.REQUIRES_RESTART
						+ " | All names listed in here are possible names for the axe of the NetherKnight")
				.worldRestart()
				.defineList("AxeNames", Arrays.asList("Demonic Soul Collector"), (string) -> validateString(string));

		builder.comment("Options for spawning in the nether fortress").push("Spawning");

		spawnWeight = builder.comment(LivingThingsConfig.REQUIRES_RESTART + " | spawnweight compared to other mobs")
				.worldRestart().defineInRange("SpawnWeight", 18, 1, Short.MAX_VALUE);
		minSpawnCount = builder.comment(LivingThingsConfig.REQUIRES_RESTART + " | minimum size of the spawn group")
				.worldRestart().defineInRange("MinSpawnCount", 1, 1, Short.MAX_VALUE);
		maxSpawnCount = builder.comment(LivingThingsConfig.REQUIRES_RESTART + " | maximum size of the spawn group")
				.worldRestart().defineInRange("MaxSpawnCount", 2, 1, Short.MAX_VALUE);

		builder.pop();

		builder.pop();
	}

	/**
	 * INVALID STRINGS:</br>
	 * <ul>
	 * <li>empty strings</li>
	 * <li>strings with a length > 40 chars</li>
	 * </ul>
	 */
	private static final boolean validateString(Object object) {
		String string = String.valueOf(object);
		return !string.equals("") && string.length() <= 40;
	}

}
