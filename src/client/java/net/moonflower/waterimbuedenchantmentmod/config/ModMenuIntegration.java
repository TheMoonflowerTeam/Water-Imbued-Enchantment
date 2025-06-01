package net.moonflower.waterimbuedenchantmentmod.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import eu.midnightdust.lib.config.MidnightConfig;

import static net.moonflower.waterimbuedenchantmentmod.WaterImbuedEnchantmentMod.MOD_ID;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> MidnightConfig.getScreen(parent, MOD_ID);
    }
}
