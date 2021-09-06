package com.strangeone101.realbiomecondition;

import org.bukkit.Location;

import java.util.Map;

public interface IBiomeReader {

    int getBiomeAt(Location location);

    String getBiomeCategoryAt(Location location);

    /**
     * Get a map of all biomes in the game. The key is the biome name, e.g. minecraft:ocean
     * The value is the biome id
     * @return
     */
    Map<String, Integer> getBiomeMap();
}
