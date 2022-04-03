package com.strangeone101.realbiomecondition.conditions;

import com.strangeone101.realbiomecondition.Plugin;
import io.lumine.mythic.api.adapters.AbstractLocation;
import io.lumine.mythic.api.config.MythicLineConfig;
import io.lumine.mythic.api.skills.conditions.ILocationCondition;
import io.lumine.mythic.core.skills.SkillCondition;
import io.lumine.mythic.core.utils.annotations.MythicCondition;
import io.lumine.mythic.core.utils.annotations.MythicField;

import java.util.HashSet;
import java.util.Set;

@MythicCondition(author = "StrangeOne101", name = "biomeType", description = "Tests if the target is within the given list of biome types")
public class BiomeTypeCondition extends SkillCondition implements ILocationCondition {

    private static final String[] KEY = new String[] { "type", "t" };

    @MythicField(name = "type", aliases = "t", description = "A list of biome types to check")
    private final Set<String> biomes = new HashSet<>();

    public BiomeTypeCondition(MythicLineConfig mlc, String conditionVar) {
        super(mlc.getLine());

        String b = mlc.getPlaceholderString(KEY, "forest", conditionVar).get();
        for (String s : b.split(","))
            this.biomes.add(s.toLowerCase());
    }

    @Override
    public boolean check(AbstractLocation abstractLocation) {
        return biomes.contains(Plugin.getBiomeReader().getBiomeCategoryAt(abstractLocation.toPosition().toLocation()));
    }
}