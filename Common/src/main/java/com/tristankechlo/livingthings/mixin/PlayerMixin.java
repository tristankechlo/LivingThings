package com.tristankechlo.livingthings.mixin;

import com.tristankechlo.livingthings.init.ModItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin {

    private final MobEffectInstance effect = new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 2400, 0, false, false, true);

    @Inject(at = @At("HEAD"), method = "turtleHelmetTick")
    private void livingThings$onArmorTick(CallbackInfo ci) {
        ItemStack helmet = this.getItemBySlot(EquipmentSlot.HEAD);
        if (helmet.is(ModItems.ANCIENT_HELMET.get())) {
            ((Player) (Object) this).addEffect(effect);
        }
    }

    @Shadow
    public abstract ItemStack getItemBySlot(EquipmentSlot slotIn);

}
