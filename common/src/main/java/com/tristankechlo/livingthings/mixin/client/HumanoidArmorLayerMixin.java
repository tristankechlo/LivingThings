package com.tristankechlo.livingthings.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.tristankechlo.livingthings.init.ModItems;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidArmorLayer.class)
public abstract class HumanoidArmorLayerMixin<T extends LivingEntity, A extends HumanoidModel<T>> {

    @Inject(at = @At("HEAD"), method = "renderArmorPiece", cancellable = true)
    private void livingthings$render(PoseStack poseStack, MultiBufferSource buffer, T entity, EquipmentSlot slot, int $$4, A model, CallbackInfo ci) {
        ItemStack stack = entity.getItemBySlot(slot);
        if (stack.is(ModItems.ANCIENT_HELMET.get())) {
            // this mod registers a new model to render the armor, so no need to try the vanilla one
            ci.cancel();
        }
    }

}
