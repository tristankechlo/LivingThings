package com.tristankechlo.livingthings.items;

import java.util.List;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.init.ModItems;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.STitlePacket;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

public class LexiconItem extends Item {

	public LexiconItem(Properties properties) {
		super(properties);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		if (playerIn instanceof ServerPlayerEntity) {
			ServerPlayerEntity player = (ServerPlayerEntity) playerIn;
			if(LivingThings.patchouliLoaded) {
				vazkii.patchouli.api.PatchouliAPI.instance.openBookGUI(player, ForgeRegistries.ITEMS.getKey(this));
			} else {
				//send error messages
				player.connection.sendPacket(new STitlePacket(STitlePacket.Type.TITLE, new TranslationTextComponent("messages.livingthings.nopatchouli.title"), 10, 100, 10));
				player.connection.sendPacket(new STitlePacket(STitlePacket.Type.SUBTITLE, new TranslationTextComponent("messages.livingthings.nopatchouli.subtitle"), 10, 100, 10));
			}
		}
		ItemStack stack = playerIn.getHeldItem(handIn);
		return new ActionResult<>(ActionResultType.SUCCESS, stack);
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		//add the subtitle for the item
		tooltip.add(getEdition().deepCopy().mergeStyle(TextFormatting.GRAY));
	}

	public static ITextComponent getEdition() {
		if(LivingThings.patchouliLoaded) {
			return vazkii.patchouli.api.PatchouliAPI.instance.getSubtitle(ForgeRegistries.ITEMS.getKey(ModItems.LEXICON.get()));
		}
		return new StringTextComponent("1st Edition");
	}

}
