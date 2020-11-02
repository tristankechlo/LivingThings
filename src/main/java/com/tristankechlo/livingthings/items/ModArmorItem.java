package com.tristankechlo.livingthings.items;

import com.tristankechlo.livingthings.client.model.armor.AncientArmorModel;
import com.tristankechlo.livingthings.init.ModItems;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ModArmorItem extends ArmorItem {

	public ModArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builderIn) {
		super(materialIn, slot, builderIn);
	}

	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
		if (!player.isInLava()) {
			EffectInstance effect = new EffectInstance(Effects.FIRE_RESISTANCE, 2400, 0, false, false, true);
			if (stack.getItem() == ModItems.ANCIENT_HELMET.get()) {
				player.addPotionEffect(effect);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@OnlyIn(Dist.CLIENT)
	public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
		AncientArmorModel model = new AncientArmorModel(2.0F);

		model.bipedHead.showModel = (armorSlot == EquipmentSlotType.HEAD);

		model.isChild = _default.isChild;
		model.isSneak = _default.isSneak;
		model.isSitting = _default.isSitting;
		model.rightArmPose = _default.rightArmPose;
		model.leftArmPose = _default.leftArmPose;

		return (A) model;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
		return "livingthings:textures/models/armor/ancient_layer_1.png";
	}

}
