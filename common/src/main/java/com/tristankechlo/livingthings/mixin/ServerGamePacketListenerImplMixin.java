package com.tristankechlo.livingthings.mixin;

import com.tristankechlo.livingthings.entity.ElephantEntity;
import net.minecraft.network.protocol.game.ServerboundPlayerCommandPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerGamePacketListenerImpl.class)
public abstract class ServerGamePacketListenerImplMixin {

    @Shadow
    public ServerPlayer player;

    @Inject(method = "handlePlayerCommand", at = @At("RETURN"))
    private void livingthings$handlePlayerCommand(ServerboundPlayerCommandPacket packet, CallbackInfo ci) {
        // since vanilla only checks for AbstractHorse, we want to open the Elephant inventory here
        if (packet.getAction() != ServerboundPlayerCommandPacket.Action.OPEN_INVENTORY) {
            return;
        }
        Entity vehicle = this.player.getVehicle();
        if (vehicle instanceof ElephantEntity) {
            ((ElephantEntity) vehicle).openInventory(this.player);
        }
    }

}
