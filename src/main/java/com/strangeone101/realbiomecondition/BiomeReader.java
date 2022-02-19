package com.strangeone101.realbiomecondition;

import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;

public class BiomeReader {

    private final Map<String, Integer> biomeMap = new HashMap<>();

    public int getBiomeAt(Location location) {
        return NMSUtils.biomeRegistry().getId(NMSUtils.getBiome(location));
    }

    public String getBiomeCategoryAt(Location location) {
        return NMSUtils.getBiome(location).getBiomeCategory().getName();
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
