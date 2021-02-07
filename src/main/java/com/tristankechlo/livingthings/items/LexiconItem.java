package com.tristankechlo.livingthings.items;

import java.util.List;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.init.ModItems;
import com.tristankechlo.livingthings.misc.ILexiconEntry;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.network.play.server.STitlePacket;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
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
			if (LivingThings.patchouliLoaded) {
				//open the lexicon
				final ResourceLocation book = this.getRegistryName();
				vazkii.patchouli.api.PatchouliAPI.instance.openBookGUI(player, book);
			} else {
				// send error messages
				player.connection.sendPacket(new STitlePacket(STitlePacket.Type.TITLE, new TranslationTextComponent("messages.livingthings.nopatchouli.title"), 10, 100, 10));
				player.connection.sendPacket(new STitlePacket(STitlePacket.Type.SUBTITLE, new TranslationTextComponent("messages.livingthings.nopatchouli.subtitle"), 10, 100, 10));
			}
		}
		ItemStack stack = playerIn.getHeldItem(handIn);
		return new ActionResult<>(ActionResultType.SUCCESS, stack);
	}
	
	@Override
	public ActionResultType itemInteractionForEntity(ItemStack stack, PlayerEntity player, LivingEntity target, Hand hand) {
		if(target.world.isRemote && LivingThings.patchouliLoaded && (target instanceof ILexiconEntry)) {
			//open lexicon page for the corresponding entity
			final ResourceLocation book = this.getRegistryName();
			final ResourceLocation entry = ((ILexiconEntry)target).getLexiconEntry();
			vazkii.patchouli.api.PatchouliAPI.instance.openBookEntry(book, entry, 0);
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.PASS;
	}

	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		final Block block = context.getWorld().getBlockState(context.getPos()).getBlock();
		if(context.getWorld().isRemote() && LivingThings.patchouliLoaded && (block instanceof ILexiconEntry)) {
			//open lexicon page for the corresponding block
			final ResourceLocation book = this.getRegistryName();
			final ResourceLocation entry = ((ILexiconEntry)block).getLexiconEntry();
			vazkii.patchouli.api.PatchouliAPI.instance.openBookEntry(book, entry, 0);
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.PASS;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		// add the subtitle for the item
		tooltip.add(getEdition().deepCopy().mergeStyle(TextFormatting.GRAY));
	}

	public static ITextComponent getEdition() {
		if (LivingThings.patchouliLoaded) {
			return vazkii.patchouli.api.PatchouliAPI.instance.getSubtitle(ForgeRegistries.ITEMS.getKey(ModItems.LEXICON.get()));
		}
		return new StringTextComponent("2nd Edition");
	}

}
