package net.moonflower.waterimbuedenchantmentmod.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.*;
import net.moonflower.waterimbuedenchantmentmod.config.CommonConfig;

public class ThunderUmbuedEnchantment extends Enchantment {
    protected ThunderUmbuedEnchantment(Rarity weight, EquipmentSlot... slotTypes) {
        super(weight, EnchantmentTarget.TRIDENT, slotTypes);
    }

    @Override
    public boolean canAccept(Enchantment other) {

        if (other == Enchantments.MENDING) {
            return CommonConfig.thunder_mending_compatible;
        }

        return super.canAccept(other) && other != Enchantments.RIPTIDE && other != ModEnchantments.WATER_IMBUED;
    }

    @Override
    public boolean isTreasure() {
        return CommonConfig.thunder_treasure_chests;
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return CommonConfig.thunder_villagers_trade;
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return CommonConfig.thunder_enchanting_table;
    }
}
