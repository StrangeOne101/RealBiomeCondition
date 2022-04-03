package com.strangeone101.realbiomecondition.conditions;

import com.strangeone101.realbiomecondition.BiomeReader;
import com.strangeone101.realbiomecondition.Plugin;
import io.lumine.mythic.api.adapters.AbstractLocation;
import io.lumine.mythic.api.config.MythicLineConfig;
import io.lumine.mythic.api.skills.conditions.ILocationCondition;
import io.lumine.mythic.core.skills.SkillCondition;
import io.lumine.mythic.core.utils.annotations.MythicCondition;
import io.lumine.mythic.core.utils.annotations.MythicField;

import java.util.HashSet;
import java.util.Set;

@MythicCondition(author = "StrangeOne101", name = "realBiome", description = "Tests if the target is within the given list of real biomes (biomes added by DataPacks)")
public class RealBiomeCondition extends SkillCondition implements ILocationCondition {

    private static final String[] KEY = new String[] { "biome", "b" };

    @MythicField(name = "biome", aliases = "b", description = "A list of biomes to check")
    private final Set<Integer> biomes = new HashSet<>();

    public RealBiomeCondition(MythicLineConfig mlc, String conditionVar) {
        super(mlc.getLine());

        String b = mlc.getString(KEY, "plains", conditionVar);
        Set<String> stringBiomes = new HashSet<>();
        for (String s : b.split(","))
            stringBiomes.add(s.contains(":") ? s : "minecraft:" + s.toLowerCase());

        BiomeReader reader = Plugin.getBiomeReader();
        stringBiomes.forEach(s -> {
            if (reader.getBiomeMap().containsKey(s))
                biomes.add(reader.getBiomeMap().get(s));
            else
                Plugin.warn("Could not locate biome with name \"" + s + "\" in file " + mlc.getFileName());
        });
    }

    @Override
    public boolean check(AbstractLocation abstractLocation) {
        return biomes.contains(Plugin.getBiomeReader().getBiomeAt(abstractLocation.toPosition().toLocation()));
    }
}
