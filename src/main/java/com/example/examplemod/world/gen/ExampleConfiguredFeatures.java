package com.example.examplemod.world.gen;

import com.example.examplemod.block.ExampleBlocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class ExampleConfiguredFeatures {

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> REDWOOD =
            register("redwood", Feature.TREE.configured((
                    new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(ExampleBlocks.REDWOOD_LOG.get().defaultBlockState()),
                            new SimpleBlockStateProvider(ExampleBlocks.REDWOOD_LEAVES.get().defaultBlockState()),
                            new BlobFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), 3),
                            new StraightTrunkPlacer(6, 4, 3),
                            new TwoLayerFeature(1, 0, 1))).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> HYACINTH_CONFIG = Feature.FLOWER.configured((
            new BlockClusterFeatureConfig.Builder(
                    new SimpleBlockStateProvider(ExampleBlocks.HYACINTH.get().defaultBlockState()),
                    SimpleBlockPlacer.INSTANCE)).tries(12).build())
            .decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(5);

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key,
                                                                                 ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, key, configuredFeature);
    }
}
