package com.strangeone101.realbiomecondition;

import com.strangeone101.realbiomecondition.conditions.BiomeTypeCondition;
import com.strangeone101.realbiomecondition.conditions.RealBiomeCondition;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicConditionLoadEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Plugin extends JavaPlugin implements Listener {

    private static final String CONDITION_REAL_BIOME = "realBiome";
    private static final String CONDITION_BIOME_TYPE = "biomeType";
    private static final String CONDITION_BIOME_CATEGORY = "biomeCategory";

    private static BiomeReader biomeReader;
    private static Plugin instance;

    public static BiomeReader getBiomeReader() {
        return biomeReader == null ? (biomeReader = new BiomeReader()) : biomeReader;
    }

    public void onEnable() {
        instance = this;
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onCustomConditionLoad(MythicConditionLoadEvent event) {
        if (event.getConditionName().equalsIgnoreCase(CONDITION_REAL_BIOME))
            event.register(new RealBiomeCondition(event.getConfig(), event.getContainer().getConditionArgument()));
        else if (event.getConditionName().equalsIgnoreCase(CONDITION_BIOME_TYPE) || event.getConditionName().equalsIgnoreCase(CONDITION_BIOME_CATEGORY))
            event.register(new BiomeTypeCondition(event.getConfig(), event.getContainer().getConditionArgument()));
    }

    public static void warn(String message) {
        instance.getLogger().warning(message);
    }

    public static void info(String message) {
        instance.getLogger().info(message);
    }
}
