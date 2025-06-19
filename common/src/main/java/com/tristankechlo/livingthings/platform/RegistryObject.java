package com.tristankechlo.livingthings.platform;


import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public interface RegistryObject<T> extends Supplier<T> {

    ResourceKey<T> getResourceKey();

    ResourceLocation getId();

    @Override
    T get();

    Holder<T> asHolder();

}
