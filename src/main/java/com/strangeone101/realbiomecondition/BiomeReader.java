package com.strangeone101.realbiomecondition;

import net.minecraft.world.level.biome.Biome;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;

public class BiomeReader {

    private final Map<String, Integer> biomeMap = new HashMap<>();

    public int getBiomeAt(Location location) {
        return NMSUtils.biomeRegistry().getId(NMSUtils.getBiome(location).value());
    }

    public String getBiomeCategoryAt(Location location) {
        return Biome.getBiomeCategory(NMSUtils.getBiome(location)).getName();
    }

    public Map<String, Integer> getBiomeMap() {
        if (biomeMap.isEmpty()) {
            NMSUtils.biomeRegistry().entrySet().forEach(entry -> {
                String name = entry.getKey().location().toString();
                int id = NMSUtils.biomeRegistry().getId(entry.getValue());
                biomeMap.put(name, id);
            });
        }
        return biomeMap;
    }
}
