package com.strangeone101.realbiomecondition;

import org.bukkit.Location;

import java.util.Map;

public interface IBiomeReader {

    String getBiomeAt(Location location);

    String getBiomeCategoryAt(Location location);

    boolean biomeExists(String string);
}
