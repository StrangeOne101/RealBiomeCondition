package com.strangeone101.realbiomecondition;

import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.SkillCondition;
import io.lumine.xikage.mythicmobs.skills.conditions.ILocationCondition;
import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
import io.lumine.xikage.mythicmobs.util.annotations.MythicField;

import java.util.HashSet;
import java.util.Set;

@MythicCondition(author = "StrangeOne101", name = "realbiome", description = "Tests if the target is within the given list of real biomes (biomes added by datapacks)")
public class RealBiomeCondition extends SkillCondition implements ILocationCondition {

    @MythicField(name = "biome", aliases = {"b"}, description = "A list of biomes to check")
    private Set<String> biomes = new HashSet<>();

    private Set<Integer> biomeIds = new HashSet<>();

    public RealBiomeCondition(String line, MythicLineConfig mlc, String conditionVar) {
        super(line);

        String b = mlc.getString(new String[] { "biome", "b" }, "plains", new String[] { conditionVar });
        for (String s : b.split(",")) {
            if (!s.contains(":")) {
                s = "minecraft:" + s;
            }
            this.biomes.add(s.toLowerCase());
        }


        Set<Integer> biomeIds = new HashSet<>();
        for (String biome : biomes) {
            if (RealBiomeConditionPlugin.getPlugin().getBiomeReader().getBiomeMap().containsKey(biome)) {
                biomeIds.add(RealBiomeConditionPlugin.getPlugin().getBiomeReader().getBiomeMap().get(biome));
            } else {
                RealBiomeConditionPlugin.getPlugin().getLogger().warning("Could not locate biome with name \"" + biome + "\" in file "
                        + mlc.getFileName());
            }
        }
    }

    @Override
    public boolean check(AbstractLocation abstractLocation) {
        return biomeIds.contains(RealBiomeConditionPlugin.getPlugin().getBiomeReader().getBiomeAt(abstractLocation.toPosition().toLocation()));
    }
}
