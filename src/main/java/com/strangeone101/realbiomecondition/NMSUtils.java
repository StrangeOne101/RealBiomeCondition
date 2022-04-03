package com.strangeone101.realbiomecondition;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.world.level.biome.Biome;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_18_R2.CraftServer;
import org.bukkit.craftbukkit.v1_18_R2.CraftWorld;

public final class NMSUtils {

    public static BlockPos locToBlockPos(Location loc) {
        return new BlockPos(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
    }

    public static Holder<Biome> getBiome(Location loc) {
        return ((CraftWorld)loc.getWorld()).getHandle().getBiome(NMSUtils.locToBlockPos(loc));
    }

    public static Registry<Biome> biomeRegistry() {
        return ((CraftServer) Bukkit.getServer()).getHandle().getServer().registryHolder.registryOrThrow(Registry.BIOME_REGISTRY);
    }
}
