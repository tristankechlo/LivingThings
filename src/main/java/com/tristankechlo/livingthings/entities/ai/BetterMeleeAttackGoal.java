package com.tristankechlo.livingthings.entities.ai;

import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.entities.CrabEntity;
import com.tristankechlo.livingthings.entities.ElephantEntity;
import com.tristankechlo.livingthings.entities.GiraffeEntity;
import com.tristankechlo.livingthings.entities.LionEntity;
import com.tristankechlo.livingthings.entities.MonkeyEntity;
import com.tristankechlo.livingthings.entities.RaccoonEntity;
import com.tristankechlo.livingthings.entities.SharkEntity;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.Difficulty;

public class BetterMeleeAttackGoal extends MeleeAttackGoal {

	public BetterMeleeAttackGoal(CreatureEntity creature, double speedIn, boolean useLongMemory) {
		super(creature, speedIn, useLongMemory);
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
		if (this.mob instanceof ElephantEntity) {
			return LivingThingsConfig.ELEPHANT.canAttack.get();
		} else if (this.mob instanceof GiraffeEntity) {
			return LivingThingsConfig.GIRAFFE.canAttack.get();
		} else if (this.mob instanceof LionEntity) {
			return LivingThingsConfig.LION.canAttack.get();
		} else if (this.mob instanceof SharkEntity) {
			return LivingThingsConfig.SHARK.canAttack.get();
		} else if (this.mob instanceof CrabEntity) {
			return LivingThingsConfig.CRAB.canAttack.get();
		} else if (this.mob instanceof RaccoonEntity) {
			return LivingThingsConfig.RACCOON.canAttack.get();
		} else if (this.mob instanceof MonkeyEntity) {
			return LivingThingsConfig.MONKEY.canAttack.get();
		}
		return false;
	}

}
