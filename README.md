# RealBiomeCondition
A small side plugin for MythicMobs that adds a condition for checking custom biomes & biome types

# Requirements
- MythicMobs 5.0.0+
- Java 16
- Spigot 1.17.1

Note: The plugin requires a manual update every time there is a new minecraft release. This will not be compatible with 1.18 until manually updated.
# Usage
This plugin adds two new conditions to MythicMobs. They are `realbiome` and `biometype`

These conditions would replace the normal biome condition in MythicMobs. See [Mythic Mob Wiki](https://mythicmobs.net/manual/doku.php/conditions/biome) for details.

## Real biome
**Syntax**: `realbiome <biomes> true`

Checks if the mob is within the biomes. Biomes that are non-vanilla MUST pre prefixed with the namespace. E.g. `terralith` (terralith biomes), `overworld` (Iris biomes), etc.

If you are unsure about the name you should put for the biome, use the biome name visible in F3.

### Examples:

- `realbiome terralith:dark_swamp,swamp,swamp_hills true`
- `realbiome overworld:sakura_pink,overworld:osaka_red`

## Biome type
**Syntax**: `biometype <types> true`

Checks if the mob is within the type of biomes provided. The types MUST be from the list bellow or it won't work. These types are defined by vanilla and you cannot define custom types.

### Examples:
- `biometype forest true`
- `biometype ocean,river,beach true`

### Valid types:
- `plains`
- `forest`
- `taiga`
- `icy`
- `extreme_hills`
- `desert`
- `mesa`
- `savanna`
- `jungle`
- `swamp`
- `mushroom`
- `beach`
- `river`
- `ocean`
- `nether`
- `the_end`
- `underground`
- `none`
  


