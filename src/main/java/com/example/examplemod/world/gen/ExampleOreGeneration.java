package com.example.examplemod.world.gen;

import com.example.examplemod.world.biome.ExampleBiomes;
import net.minecraft.block.Blocks;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.Dimension;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class ExampleOreGeneration {

    public static void generateOres(final BiomeLoadingEvent event) {
        spawnOreInSpecificModBiome(ExampleBiomes.RIFT_BIOME.get(), OreType.AMETHYST, event, Dimension.OVERWORLD.toString());
        spawnOreInSpecificBiome(Biomes.DARK_FOREST, OreType.AMETHYST, event, Dimension.OVERWORLD.toString());
        spawnOreInAllBiomes(OreType.FIRESTONE, event, Dimension.NETHER.toString());
    }


    private static OreFeatureConfig getOverworldFeatureConfig(OreType ore) {
        return new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                ore.getBlock().get().defaultBlockState(), ore.getMaxVeinSize());
    }

    private static OreFeatureConfig getNetherFeatureConfig(OreType ore) {
        return new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK,
                ore.getBlock().get().defaultBlockState(), ore.getMaxVeinSize());
    }

    private static OreFeatureConfig getEndFeatureConfig(OreType ore) {
        return new OreFeatureConfig(new BlockMatchRuleTest(Blocks.END_STONE),
                ore.getBlock().get().defaultBlockState(), ore.getMaxVeinSize());
    }

    private static ConfiguredFeature<?, ?> makeOreFeature(OreType ore, String dimensionToSpawnIn) {
        OreFeatureConfig oreFeatureConfig = null;

        if (dimensionToSpawnIn.equals(Dimension.OVERWORLD.toString())) {
            oreFeatureConfig = getOverworldFeatureConfig(ore);
        } else if (dimensionToSpawnIn.equals(Dimension.NETHER.toString())) {
            oreFeatureConfig = getNetherFeatureConfig(ore);
        } else if (dimensionToSpawnIn.equals(Dimension.END.toString())) {
            oreFeatureConfig = getEndFeatureConfig(ore);
        }

        ConfiguredPlacement<TopSolidRangeConfig> configuredPlacement = Placement.RANGE.configured(
                new TopSolidRangeConfig(ore.getMinHeight(), ore.getMinHeight(), ore.getMaxHeight()));

        return registerOreFeature(ore, oreFeatureConfig, configuredPlacement);
    }

    private static ConfiguredFeature<?, ?> registerOreFeature(OreType ore, OreFeatureConfig oreFeatureConfig,
                                                              ConfiguredPlacement configuredPlacement) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, ore.getBlock().get().getRegistryName(),
                Feature.ORE.configured(oreFeatureConfig).decorated(configuredPlacement)
                        .squared().count(ore.getVeinsPerChunk()));
    }
    private static void spawnOreInSpecificModBiome(Biome biomeToSpawnIn, OreType currentOreType,
                                                   final BiomeLoadingEvent event, String dimension) {
        if (event.getName().toString().contains(biomeToSpawnIn.getRegistryName().toString())) {
            event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                    makeOreFeature(currentOreType, dimension));
        }
    }

    private static void spawnOreInSpecificBiome(RegistryKey<Biome> biomeToSpawnIn, OreType currentOreType,
                                                final BiomeLoadingEvent event, String dimension) {
        if (event.getName().toString().contains(biomeToSpawnIn.location().toString())) {
            event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                    makeOreFeature(currentOreType, dimension));
        }
    }

    private static void spawnOreInAllBiomes(OreType currentOreType, final BiomeLoadingEvent event, String dimension) {
        event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                makeOreFeature(currentOreType, dimension));
    }
}
