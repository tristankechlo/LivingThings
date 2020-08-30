package com.tristankechlo.livingthings.entities.ai;

import com.tristankechlo.livingthings.config.LivingThingsConfig;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.IAngerable;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.Difficulty;

public class BetterMeleeAttackGoal extends MeleeAttackGoal{
	
	public BetterMeleeAttackGoal(CreatureEntity creature, double speedIn, boolean useLongMemory) {
		super(creature, speedIn, useLongMemory);
	}
	
	@Override
	public boolean shouldExecute() {
		boolean peaceful = (this.attacker.world.getDifficulty() == Difficulty.PEACEFUL) ? true : false;
		boolean ambientMode = LivingThingsConfig.SERVER.ambientMode.get();
		if (peaceful || ambientMode) {
			return false;
		}
		return super.shouldExecute();
	}
	
	@Override
	public boolean shouldContinueExecuting() {
		boolean peaceful = (this.attacker.world.getDifficulty() == Difficulty.PEACEFUL) ? true : false;
		boolean ambientMode = LivingThingsConfig.SERVER.ambientMode.get();
		if (peaceful || ambientMode) {
			return false;
		}
		return super.shouldContinueExecuting();
	}
	
	@Override
	public void resetTask() {
		if(this.attacker instanceof IAngerable) {
			IAngerable test = (IAngerable)this.attacker;
			test.func_241355_J__();
		}
		super.resetTask();
	}

}
