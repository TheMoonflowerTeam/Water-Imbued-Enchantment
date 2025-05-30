package net.moonflower.waterimbuedenchantmentmod.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.moonflower.waterimbuedenchantmentmod.config.CommonConfig;

public class WaterImbuedEnchantment extends Enchantment {
    public WaterImbuedEnchantment(Enchantment.Rarity weight, EquipmentSlot ... slotTypes) {
        super(weight, EnchantmentTarget.TRIDENT, slotTypes);
    }

    @Override
    public boolean canAccept(Enchantment other) {
        if (other == Enchantments.MENDING) {
            return CommonConfig.water_mending_compatible;
        }

        return super.canAccept(other) && other != Enchantments.LOYALTY && other != Enchantments.CHANNELING && other != ModEnchantments.THUNDER_IMBUED;
    }

    @Override
    public boolean isTreasure() {
        return CommonConfig.water_treasure_chests;
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return CommonConfig.water_villagers_trade;
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return CommonConfig.water_enchanting_table;
    }
}
