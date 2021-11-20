package com.tristankechlo.livingthings.items;

import java.util.List;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.init.ModItems;
import com.tristankechlo.livingthings.misc.ILexiconEntry;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.protocol.game.ClientboundSetSubtitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

public class LexiconItem extends Item {

	private static final String URL = "https://github.com/tristankechlo/Living-Things/wiki";

	public LexiconItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
		if (playerIn instanceof ServerPlayer) {
			ServerPlayer player = (ServerPlayer) playerIn;
			if (LivingThings.patchouliLoaded) {
				// open the lexicon
				final ResourceLocation book = this.getRegistryName();
				vazkii.patchouli.api.PatchouliAPI.get().openBookGUI(player, book);
			} else {
				// send error messages
				player.connection.send(new ClientboundSetTitleTextPacket(
						new TranslatableComponent("messages.livingthings.nopatchouli.title")));
				player.connection.send(new ClientboundSetSubtitleTextPacket(
						new TranslatableComponent("messages.livingthings.nopatchouli.subtitle")));
				player.sendMessage(new TranslatableComponent("messages.livingthings.nopatchouli.wiki", URL),
						player.getUUID());
			}
		}
		ItemStack stack = playerIn.getItemInHand(handIn);
		return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
	}

	@Override
	public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target,
			InteractionHand hand) {
		if (target.level.isClientSide() && LivingThings.patchouliLoaded && (target instanceof ILexiconEntry)) {
			// open lexicon page for the corresponding entity
			final ResourceLocation book = this.getRegistryName();
			final ResourceLocation entry = ((ILexiconEntry) target).getLexiconEntry();
			vazkii.patchouli.api.PatchouliAPI.get().openBookEntry(book, entry, 0);
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		final Block block = context.getLevel().getBlockState(context.getClickedPos()).getBlock();
		if (context.getLevel().isClientSide() && LivingThings.patchouliLoaded && (block instanceof ILexiconEntry)) {
			// open lexicon page for the corresponding block
			final ResourceLocation book = this.getRegistryName();
			final ResourceLocation entry = ((ILexiconEntry) block).getLexiconEntry();
			vazkii.patchouli.api.PatchouliAPI.get().openBookEntry(book, entry, 0);
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		// add the subtitle for the item
		tooltip.add(getEdition().plainCopy().withStyle(ChatFormatting.GRAY));
	}

	public static Component getEdition() {
		if (LivingThings.patchouliLoaded) {
			return vazkii.patchouli.api.PatchouliAPI.get()
					.getSubtitle(ForgeRegistries.ITEMS.getKey(ModItems.LEXICON.get()));
		}

		return new TextComponent("2nd Edition");
	}

}
