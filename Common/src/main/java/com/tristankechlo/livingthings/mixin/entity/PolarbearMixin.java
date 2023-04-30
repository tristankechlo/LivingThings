package com.tristankechlo.livingthings.mixin.entity;

import com.tristankechlo.livingthings.entity.PenguinEntity;
import com.tristankechlo.livingthings.mixin.entity.MobAccessor;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.PolarBear;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PolarBear.class)
public abstract class PolarbearMixin {

    @Inject(at = @At("HEAD"), method = "registerGoals")
    private void LivingThings$registerGoals(CallbackInfo ci) {
        ((MobAccessor) this).getTargetSelector().addGoal(3, new NearestAttackableTargetGoal<>((PolarBear) (Object) this, PenguinEntity.class, 10, true, false, null));
    }

}
