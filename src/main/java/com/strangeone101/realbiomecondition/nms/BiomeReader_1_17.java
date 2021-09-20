package com.strangeone101.realbiomecondition.nms;

import com.strangeone101.realbiomecondition.IBiomeReader;
import com.strangeone101.realbiomecondition.RealBiomeConditionPlugin;
import net.minecraft.core.IRegistry;
import net.minecraft.core.IRegistryWritable;
import net.minecraft.world.level.biome.BiomeBase;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;

import java.util.HashMap;
import java.util.Map;

public class BiomeReader_1_17 implements IBiomeReader {

    private Map<String, Integer> biomeMap = new HashMap<>();

    @Override
    public int getBiomeAt(Location location) {
        BiomeBase biome = ((CraftWorld)location.getWorld()).getHandle().getBiome(location.getBlockX(), location.getBlockY(), location.getBlockZ());
        return getCustomBiomeRegistry().getId(biome);
    }

    @Override
    public String getBiomeCategoryAt(Location location) {
        BiomeBase biome = ((CraftWorld)location.getWorld()).getHandle().getBiome(location.getBlockX(), location.getBlockY(), location.getBlockZ());
        return biome.t().getName();
    }


    @Override
    public Map<String, Integer> getBiomeMap() {
        if (biomeMap.isEmpty()) {

                getCustomBiomeRegistry().d().forEach(entry -> {//Name, id
                    String name = entry.getKey().a().toString();
                    int id = getCustomBiomeRegistry().getId(entry.getValue());
                        biomeMap.put(name, id);
                        //RealBiomeConditionPlugin.getPlugin().getLogger().info("Debug " + name + " | " + id + " | " + entry.getValue().t().getName());
            });

        }


        return biomeMap;
    }

    private IRegistryWritable<BiomeBase> getCustomBiomeRegistry() {

        return ((CraftServer) Bukkit.getServer()).getHandle().getServer().getCustomRegistry().b(IRegistry.aO);
    }
}
