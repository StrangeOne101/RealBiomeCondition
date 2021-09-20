package com.strangeone101.realbiomecondition;

import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.SkillCondition;
import io.lumine.xikage.mythicmobs.skills.conditions.ILocationCondition;
import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
import jdk.internal.joptsimple.internal.Strings;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@MythicCondition(author = "StrangeOne101", name = "realbiome", description = "Tests if the target is within the given list of real biomes (biomes added by datapacks)")
public class RealBiomeCondition extends SkillCondition implements ILocationCondition {

    @MythicField(name = "biome", aliases = {"b"}, description = "A list of biomes to check")
    private Set<Integer> biomes = new HashSet<>();

    public RealBiomeCondition(String line, MythicLineConfig mlc, String conditionVar) {
        super(line);

        String b = mlc.getString(new String[] { "biome", "b" }, "plains", new String[] { conditionVar });
        Set<String> stringBiomes = new HashSet<>();
        for (String s : b.split(",")) {
            if (!s.contains(":")) {
                s = "minecraft:" + s;
            }
            stringBiomes.add(s.toLowerCase());
        }

        for (String biome : stringBiomes) {
            if (RealBiomeConditionPlugin.getPlugin().getBiomeReader().getBiomeMap().containsKey(biome)) {
                int id = RealBiomeConditionPlugin.getPlugin().getBiomeReader().getBiomeMap().get(biome);
                biomes.add(id);
                //RealBiomeConditionPlugin.getPlugin().getLogger().info("Added " + id);
            } else {
                RealBiomeConditionPlugin.getPlugin().getLogger().warning("Could not locate biome with name \"" + biome + "\" in file "
                        + mlc.getFileName());
            }
        }
    }

    @Override
    public boolean check(AbstractLocation abstractLocation) {
        int id = RealBiomeConditionPlugin.getPlugin().getBiomeReader().getBiomeAt(abstractLocation.toPosition().toLocation());
        //RealBiomeConditionPlugin.getPlugin().getLogger().info("Checked on biome with id " + id);
        if (biomes.contains(id)) {
            //RealBiomeConditionPlugin.getPlugin().getLogger().info("Found in list with ID " + id);
        } else {
            String s = "";
            for (int i : biomes) s = s + ", " + i;
            //RealBiomeConditionPlugin.getPlugin().getLogger().info("Failed to find " + id + " in " + (s.length() > 0 ? s.substring(1) : ""));
        }
        return biomes.contains(id);
    }
}
