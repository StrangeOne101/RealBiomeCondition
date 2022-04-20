package com.strangeone101.realbiomecondition;

import com.strangeone101.realbiomecondition.nms.BiomeReader_1_18_1;
import io.lumine.mythic.bukkit.events.MythicConditionLoadEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class RealBiomeConditionPlugin extends JavaPlugin implements Listener {

    private IBiomeReader biomeReader;
    private static RealBiomeConditionPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;

        if (Bukkit.getServer().getClass().getCanonicalName().split("\\Q.\\E")[3].equalsIgnoreCase("1_17_R1")) {
            getLogger().severe("You do not have a compatible version of Spigot! This plugin ONLY works on 1.17.x!");
            getLogger().severe("This plugin is available on GitHub if you want to update it: https://github.com/StrangeOne101/RealBiomeCondition");
            setEnabled(false);
            return;
        }
        Bukkit.getPluginManager().registerEvents(this, this);

        biomeReader = new BiomeReader_1_18_1();
    }

    public static RealBiomeConditionPlugin getPlugin() {
        return plugin;
    }

    public IBiomeReader getBiomeReader() {
        return biomeReader;
    }

    @EventHandler
    public void onCustomConditionLoad(MythicConditionLoadEvent event) {
        if (event.getConditionName().equalsIgnoreCase("realbiome")) {
            event.register(new RealBiomeCondition(event.getConfig().getLine(), event.getConfig(), event.getContainer().getConditionArgument()));
        } else if (event.getConditionName().equalsIgnoreCase("biometype") || event.getConditionName().equalsIgnoreCase("biomecategory")) {
            event.register(new BiomeTypeCondition(event.getConfig().getLine(), event.getConfig(), event.getContainer().getConditionArgument()));
        }
    }
}
