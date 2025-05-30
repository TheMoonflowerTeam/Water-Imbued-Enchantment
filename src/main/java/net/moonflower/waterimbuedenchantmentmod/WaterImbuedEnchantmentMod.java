package net.moonflower.waterimbuedenchantmentmod;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;
import net.moonflower.waterimbuedenchantmentmod.config.CommonConfig;
import net.moonflower.waterimbuedenchantmentmod.enchantment.ModEnchantments;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WaterImbuedEnchantmentMod implements ModInitializer {

	public static final String MOD_ID = "water_imbued_enchantment";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		MidnightConfig.init(MOD_ID, CommonConfig.class);
		ModEnchantments.registerModEnchantments();
	}
}
