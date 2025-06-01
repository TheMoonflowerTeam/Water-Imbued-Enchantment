package net.moonflower.waterimbuedenchantmentmod.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.moonflower.waterimbuedenchantmentmod.WaterImbuedEnchantmentMod;

public class ModEnchantments {

    public static Enchantment WATER_IMBUED = register("water_imbued", new WaterImbuedEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND));
    public static Enchantment THUNDER_IMBUED = register("thunder_imbued", new ThunderUmbuedEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND));

    private static Enchantment register(String name, Enchantment enchantment) {
        return Registry.register(Registries.ENCHANTMENT, new Identifier(WaterImbuedEnchantmentMod.MOD_ID, name), enchantment);
    }

    public static void registerModEnchantments() {
        //System.out.println("Registering Enchantments for " + WaterImbuedEnchantmentMod.MOD_ID);
    }
}
