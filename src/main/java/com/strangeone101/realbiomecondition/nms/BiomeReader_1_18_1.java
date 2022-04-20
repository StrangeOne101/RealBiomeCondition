package com.strangeone101.realbiomecondition.nms;

import com.strangeone101.realbiomecondition.IBiomeReader;
import com.strangeone101.realbiomecondition.RealBiomeConditionPlugin;
import net.minecraft.core.Registry;
import net.minecraft.core.WritableRegistry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_18_R1.CraftServer;
import org.bukkit.craftbukkit.v1_18_R1.CraftWorld;

import java.util.HashMap;
import java.util.Map;

public class BiomeReader_1_18_1 implements IBiomeReader {

    @Override
    public String getBiomeAt(Location location) {
        Biome biome = ((CraftWorld)location.getWorld()).getHandle().getNoiseBiome(location.getBlockX(), location.getBlockY(), location.getBlockZ());
        return getCustomBiomeRegistry().getKey(biome).toString();
    }

    @Override
    public String getBiomeCategoryAt(Location location) {
        Biome biome = ((CraftWorld)location.getWorld()).getHandle().getNoiseBiome(location.getBlockX(), location.getBlockY(), location.getBlockZ());
        return biome.getBiomeCategory().toString();
    }

    @Override
    public boolean biomeExists(String string) {
        return getCustomBiomeRegistry().containsKey(ResourceLocation.tryParse(string));
    }

    private Registry<Biome> getCustomBiomeRegistry() {
        return BuiltinRegistries.BIOME;
    }
}
