package com.tristankechlo.livingthings.mixin.client;

import com.tristankechlo.livingthings.entity.ElephantEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MultiPlayerGameMode.class)
public abstract class MultiPlayerGameModeMixin {

    @Shadow
    @Final
    private Minecraft minecraft;

    @Inject(method = "isServerControlledInventory", at = @At("HEAD"), cancellable = true)
    private void livingthings$isServerControlledInventory(CallbackInfoReturnable<Boolean> cir) {
        // when this methods returns 'true' a package will be send to the server to open an mob inventory
        LocalPlayer player = this.minecraft.player;
        if (player == null) {
            return;
        }
        if (player.isPassenger() && player.getVehicle() instanceof ElephantEntity) {
            cir.setReturnValue(true);
        }
    }

}
