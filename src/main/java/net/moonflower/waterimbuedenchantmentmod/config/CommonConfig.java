package net.moonflower.waterimbuedenchantmentmod.config;

import eu.midnightdust.lib.config.MidnightConfig;

public class CommonConfig extends MidnightConfig {
    @Comment()
    public static Comment water_comment;

    @Entry()
    public static boolean water_mending_compatible = true;

    @Entry()
    public static boolean water_villagers_trade = false;

    @Entry()
    public static boolean water_treasure_chests = false;

    @Entry()
    public static boolean water_enchanting_table = false;

    @Comment()
    public static Comment spacer1;

    @Comment()
    public static Comment thunder_comment;

    @Entry()
    public static boolean thunder_mending_compatible = true;

    @Entry()
    public static boolean thunder_villagers_trade = false;

    @Entry()
    public static boolean thunder_treasure_chests = false;

    @Entry()
    public static boolean thunder_enchanting_table = false;
}