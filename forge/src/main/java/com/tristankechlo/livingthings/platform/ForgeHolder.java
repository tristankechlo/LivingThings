package com.tristankechlo.livingthings.platform;

import com.mojang.datafixers.util.Either;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderOwner;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

// custom implementation of the holder, to delay the call to the actual object through a supplier
public class ForgeHolder<I> implements Holder<I> {

    private final Supplier<I> value;

    public ForgeHolder(Supplier<I> value) {
        this.value = value;
    }

    @Override
    public I value() {
        return this.value.get();
    }

    @Override
    public boolean isBound() {
        return false;
    }

    @Override
    public boolean is(ResourceLocation resourceLocation) {
        return false;
    }

    @Override
    public boolean is(ResourceKey<I> resourceKey) {
        return false;
    }

    @Override
    public boolean is(Predicate<ResourceKey<I>> predicate) {
        return false;
    }

    @Override
    public boolean is(TagKey<I> tagKey) {
        return false;
    }

    @Override
    public boolean is(Holder<I> holder) {
        return false;
    }

    @Override
    public Stream<TagKey<I>> tags() {
        return Stream.of();
    }

    @Override
    public Either<ResourceKey<I>, I> unwrap() {
        return Either.right(this.value());
    }

    @Override
    public Optional<ResourceKey<I>> unwrapKey() {
        return Optional.empty();
    }

    @Override
    public Kind kind() {
        return Holder.Kind.DIRECT;
    }

    @Override
    public boolean canSerializeIn(HolderOwner<I> holderOwner) {
        return true;
    }
}
