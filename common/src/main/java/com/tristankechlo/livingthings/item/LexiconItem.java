package com.tristankechlo.livingthings.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import java.util.function.Consumer;

public class LexiconItem extends Item {

    private static final String URL = "https://github.com/tristankechlo/Living-Things/wiki";
    private static final MutableComponent SUBTITLE = Component.literal("2nd Edition").withStyle(ChatFormatting.GRAY);

    public LexiconItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level worldIn, Player playerIn, InteractionHand handIn) {
        // TODO re-implement lexicon
        if (playerIn instanceof ServerPlayer player) {
            player.sendSystemMessage(Component.literal("The lexicon is currently disabled."));
        }
        /*if (IPlatformHelper.INSTANCE.isModLoaded("patchouli")) {
            // open the lexicon
            final ResourceLocation book = BuiltInRegistries.ITEM.getKey(ModItems.LEXICON.get());
            IPlatformHelper.INSTANCE.openBookGui(player, book);
        } else {
            // send error messages
            player.connection.send(new ClientboundSetTitleTextPacket(Component.translatable("messages.livingthings.nopatchouli.title")));
            player.connection.send(new ClientboundSetSubtitleTextPacket(Component.translatable("messages.livingthings.nopatchouli.subtitle")));
            player.sendSystemMessage(Component.translatable("messages.livingthings.nopatchouli.wiki", URL));
        }*/
        return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player playerIn, LivingEntity target, InteractionHand hand) {
        // TODO re-implement lexicon
        if (playerIn instanceof ServerPlayer player) {
            player.sendSystemMessage(Component.literal("The lexicon is currently disabled."));
        }
        /*boolean patchouliLoaded = IPlatformHelper.INSTANCE.isModLoaded("patchouli");
        if (target.level().isClientSide() && patchouliLoaded && (target instanceof ILexiconEntry)) {
            // open lexicon page for the corresponding entity
            final ResourceLocation book = BuiltInRegistries.ITEM.getKey(ModItems.LEXICON.get());
            final ResourceLocation entry = ((ILexiconEntry) target).getLexiconEntry();
            IPlatformHelper.INSTANCE.openBookEntry(book, entry, 0);
            return InteractionResult.SUCCESS;
        }*/
        return InteractionResult.PASS;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        // TODO re-implement lexicon
        if (context.getPlayer() instanceof ServerPlayer player) {
            player.sendSystemMessage(Component.literal("The lexicon is currently disabled."));
        }
        /*final Block block = context.getLevel().getBlockState(context.getClickedPos()).getBlock();
        boolean patchouliLoaded = IPlatformHelper.INSTANCE.isModLoaded("patchouli");
        if (context.getLevel().isClientSide() && patchouliLoaded && (block instanceof ILexiconEntry)) {
            // open lexicon page for the corresponding block
            final ResourceLocation book = BuiltInRegistries.ITEM.getKey(ModItems.LEXICON.get());
            final ResourceLocation entry = ((ILexiconEntry) block).getLexiconEntry();
            IPlatformHelper.INSTANCE.openBookEntry(book, entry, 0);
            return InteractionResult.SUCCESS;
        }*/
        return InteractionResult.PASS;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag);
        // add the subtitle for the item
        tooltipAdder.accept(SUBTITLE);
    }

}
