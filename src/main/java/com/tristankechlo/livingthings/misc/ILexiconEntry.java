package com.tristankechlo.livingthings.misc;

import com.tristankechlo.livingthings.init.ModItems;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public interface ILexiconEntry {

	ResourceLocation getLexiconEntry();

	default boolean isLexicon(Item item) {
		return item.equals(ModItems.LEXICON.get());
	}

	default boolean isLexicon(ItemStack stack) {
		return this.isLexicon(stack.getItem());
	}

}
