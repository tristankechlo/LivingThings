package com.tristankechlo.livingthings.items;

import java.util.function.Consumer;

import com.tristankechlo.livingthings.client.ClientEvents;
import com.tristankechlo.livingthings.init.ModItems;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.IItemRenderProperties;

public class ModArmorItem extends ArmorItem {

	public ModArmorItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builderIn) {
		super(materialIn, slot, builderIn);
	}

	@Override
	public void onArmorTick(ItemStack stack, Level world, Player player) {
		if (!player.isInLava()) {
			MobEffectInstance effect = new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 2400, 0, false, false, true);
			if (stack.getItem() == ModItems.ANCIENT_HELMET.get()) {
				player.addEffect(effect);
			}
		}
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void initializeClient(Consumer<IItemRenderProperties> consumer) {
		consumer.accept(Rendering.INSTANCE);
	}

	@OnlyIn(Dist.CLIENT)
	private static final class Rendering implements IItemRenderProperties {

		private static final Rendering INSTANCE = new ModArmorItem.Rendering();

		private Rendering() {}

		@Override
		public HumanoidModel<?> getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot,
				HumanoidModel<?> _default) {
			return ClientEvents.ANCIENT_ARMOR_MODEL;
		}

	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
		return "livingthings:textures/models/armor/ancient_layer_1.png";
	}

}
