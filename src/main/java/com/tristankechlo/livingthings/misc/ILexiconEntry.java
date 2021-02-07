package com.tristankechlo.livingthings.misc;

import com.tristankechlo.livingthings.init.ModItems;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public interface ILexiconEntry {

	ResourceLocation getLexiconEntry();

	default boolean isLexicon(Item item) {
		return item.equals(ModItems.LEXICON.get());
	}

	default boolean isLexicon(ItemStack stack) {
		return this.isLexicon(stack.getItem());
	}

}
