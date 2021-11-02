package com.tristankechlo.livingthings.entities.ai;

import java.util.function.Supplier;

import com.tristankechlo.livingthings.config.LivingThingsConfig;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.Difficulty;

public class BetterMeleeAttackGoal extends MeleeAttackGoal {

	private final Supplier<Boolean> canAttack;

	public BetterMeleeAttackGoal(PathfinderMob creature, double speedIn, boolean useLongMemory,
			Supplier<Boolean> canAttack) {
		super(creature, speedIn, useLongMemory);
		this.canAttack = canAttack;
	}

	@Override
	public boolean canUse() {
		boolean peaceful = (this.mob.level.getDifficulty() == Difficulty.PEACEFUL);
		boolean ambientMode = LivingThingsConfig.GENERAL.ambientMode.get();
		if (peaceful || ambientMode || !this.canEntityAttack()) {
			return false;
		}
		return super.canUse();
	}

	@Override
	public boolean canContinueToUse() {
		boolean peaceful = (this.mob.level.getDifficulty() == Difficulty.PEACEFUL);
		boolean ambientMode = LivingThingsConfig.GENERAL.ambientMode.get();
		if (peaceful || ambientMode || !this.canEntityAttack()) {
			return false;
		}
		return super.canContinueToUse();
	}

	private boolean canEntityAttack() {
		return this.canAttack.get();
	}

}
