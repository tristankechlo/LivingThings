package com.tristankechlo.livingthings.item;

import com.tristankechlo.livingthings.init.ModItems;
import com.tristankechlo.livingthings.platform.IPlatformHelper;
import com.tristankechlo.livingthings.util.ILexiconEntry;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
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

import java.util.List;

public class LexiconItem extends Item {

    private static final String URL = "https://github.com/tristankechlo/Living-Things/wiki";

    public LexiconItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        if (playerIn instanceof ServerPlayer) {
            ServerPlayer player = (ServerPlayer) playerIn;
            if (IPlatformHelper.INSTANCE.isModLoaded("patchouli")) {
                // open the lexicon
                final ResourceLocation book = Registry.ITEM.getKey(ModItems.LEXICON.get());
                IPlatformHelper.INSTANCE.openBookGui(player, book);
            } else {
                // send error messages
                player.connection.send(new ClientboundSetTitleTextPacket(Component.translatable("messages.livingthings.nopatchouli.title")));
                player.connection.send(new ClientboundSetSubtitleTextPacket(Component.translatable("messages.livingthings.nopatchouli.subtitle")));
                player.sendSystemMessage(Component.translatable("messages.livingthings.nopatchouli.wiki", URL));
            }
        }
        ItemStack stack = playerIn.getItemInHand(handIn);
        return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        boolean patchouliLoaded = IPlatformHelper.INSTANCE.isModLoaded("patchouli");
        if (target.level.isClientSide() && patchouliLoaded && (target instanceof ILexiconEntry)) {
            // open lexicon page for the corresponding entity
            final ResourceLocation book = Registry.ITEM.getKey(ModItems.LEXICON.get());
            final ResourceLocation entry = ((ILexiconEntry) target).getLexiconEntry();
            IPlatformHelper.INSTANCE.openBookEntry(book, entry, 0);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        final Block block = context.getLevel().getBlockState(context.getClickedPos()).getBlock();
        boolean patchouliLoaded = IPlatformHelper.INSTANCE.isModLoaded("patchouli");
        if (context.getLevel().isClientSide() && patchouliLoaded && (block instanceof ILexiconEntry)) {
            // open lexicon page for the corresponding block
            final ResourceLocation book = Registry.ITEM.getKey(ModItems.LEXICON.get());
            final ResourceLocation entry = ((ILexiconEntry) block).getLexiconEntry();
            IPlatformHelper.INSTANCE.openBookEntry(book, entry, 0);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        // add the subtitle for the item
        tooltip.add(getEdition().plainCopy().withStyle(ChatFormatting.GRAY));
    }

    public static Component getEdition() {
        if (IPlatformHelper.INSTANCE.isModLoaded("patchouli")) {
            ResourceLocation book = Registry.ITEM.getKey(ModItems.LEXICON.get());
            return IPlatformHelper.INSTANCE.getPatchouliSubtitle(book);
        }

        return Component.literal("2nd Edition");
    }

}
