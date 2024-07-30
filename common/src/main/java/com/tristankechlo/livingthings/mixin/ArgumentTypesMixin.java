package com.tristankechlo.livingthings.mixin;

import com.mojang.brigadier.arguments.ArgumentType;
import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.commands.ProjectLinksArgumentType;
import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraft.commands.synchronization.ArgumentTypeInfos;
import net.minecraft.commands.synchronization.SingletonArgumentInfo;
import net.minecraft.core.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ArgumentTypeInfos.class)
public abstract class ArgumentTypesMixin {

    @Inject(at = @At("HEAD"), method = "bootstrap")
    private static void LivingThings$bootstrap(Registry<ArgumentTypeInfo<?, ?>> registry, CallbackInfoReturnable<ArgumentTypeInfo<?, ?>> cir) {
        // register custom argument type
        register(registry, LivingThings.MOD_ID + ":sampler_types", ProjectLinksArgumentType.class, SingletonArgumentInfo.contextFree(ProjectLinksArgumentType::get));
    }

    @Shadow
    private static <A extends ArgumentType<?>, T extends ArgumentTypeInfo.Template<A>> ArgumentTypeInfo<A, T> register(
            Registry<ArgumentTypeInfo<?, ?>> registry, String id, Class<? extends A> clazz, ArgumentTypeInfo<A, T> info
    ) {
        throw new AssertionError();
    }

}
