package com.tristankechlo.livingthings.mixin;

import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.armor.AncientArmorModel;
import com.tristankechlo.livingthings.client.renderer.layer.AncientArmorLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerRenderer.class)
public abstract class PlayerRendererMixin {

    @Inject(at = @At("RETURN"), method = "<init>")
    private void LivingThings$PlayerRendererMixin(EntityRendererProvider.Context context, boolean $$1, CallbackInfo info) {
        AncientArmorModel model = new AncientArmorModel(context.bakeLayer(ModelLayer.ANCIENT_ARMOR));
        ((LivingEntityRendererAccessor) this).getLayers().add(new AncientArmorLayer<>((PlayerRenderer) (Object) this, model));
    }

}
