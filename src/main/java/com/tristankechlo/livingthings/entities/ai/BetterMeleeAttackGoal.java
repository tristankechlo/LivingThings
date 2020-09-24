package com.tristankechlo.livingthings.entities.ai;

import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.entities.CrabEntity;
import com.tristankechlo.livingthings.entities.ElephantEntity;
import com.tristankechlo.livingthings.entities.GiraffeEntity;
import com.tristankechlo.livingthings.entities.LionEntity;
import com.tristankechlo.livingthings.entities.SharkEntity;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.Difficulty;

public class BetterMeleeAttackGoal extends MeleeAttackGoal {
	
	public BetterMeleeAttackGoal(CreatureEntity creature, double speedIn, boolean useLongMemory) {
		super(creature, speedIn, useLongMemory);
	}
	
	@Override
	public boolean shouldExecute() {
		boolean peaceful = (this.attacker.world.getDifficulty() == Difficulty.PEACEFUL) ? true : false;
		boolean ambientMode = LivingThingsConfig.GENERAL.ambientMode.get();
		if (peaceful || ambientMode || this.canEntityAttack() == false) {
			return false;
		}
		return super.shouldExecute();
	}
	
	@Override
	public boolean shouldContinueExecuting() {
		boolean peaceful = (this.attacker.world.getDifficulty() == Difficulty.PEACEFUL) ? true : false;
		boolean ambientMode = LivingThingsConfig.GENERAL.ambientMode.get();
		if (peaceful || ambientMode || this.canEntityAttack() == false) {
			return false;
		}
		return super.shouldContinueExecuting();
	}
		
	private boolean canEntityAttack() {
		if(this.attacker instanceof ElephantEntity) {
			return LivingThingsConfig.ELEPHANT.canAttack.get();
		} else if(this.attacker instanceof GiraffeEntity) {
			return LivingThingsConfig.GIRAFFE.canAttack.get();
		} else if(this.attacker instanceof LionEntity) {
			return LivingThingsConfig.LION.canAttack.get();
		} else if(this.attacker instanceof SharkEntity) {
			return LivingThingsConfig.SHARK.canAttack.get();
		} else if(this.attacker instanceof CrabEntity) {
			return LivingThingsConfig.CRAB.canAttack.get();
		}
		return false;
	}

}
