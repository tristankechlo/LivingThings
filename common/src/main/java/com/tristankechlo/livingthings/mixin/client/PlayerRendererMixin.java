package com.tristankechlo.livingthings.mixin.client;

import com.tristankechlo.livingthings.client.renderer.layer.AncientArmorLayer;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AvatarRenderer.class)
public abstract class PlayerRendererMixin {

    @Inject(at = @At("RETURN"), method = "<init>")
    private void LivingThings$PlayerRendererMixin(EntityRendererProvider.Context context, boolean $$1, CallbackInfo info) {
        ((LivingEntityRendererAccessor<AvatarRenderState, PlayerModel>) this).getLayers().add(new AncientArmorLayer<>((AvatarRenderer) (Object) this, context));
    }

}
