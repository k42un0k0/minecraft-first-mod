package com.example.examplemod.world.biome;

import com.example.examplemod.ExampleMod;
import net.minecraft.client.audio.BackgroundMusicTracks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ExampleBiomes {
    public static final DeferredRegister<Biome> BIOMES
            = DeferredRegister.create(ForgeRegistries.BIOMES, ExampleMod.MOD_ID);

    public static final RegistryObject<Biome> RIFT_BIOME = BIOMES.register("rift_biome",
            () -> makeRiftBiome(() -> ExampleConfiguredSurfaceBuilders.RIFT_SURFACE, 0.125f, 0.05f));

    private static Biome makeRiftBiome(final Supplier<ConfiguredSurfaceBuilder<?>> surfaceBuilder, float depth, float scale) {
        MobSpawnInfo.Builder mobspawninfo$builder = new MobSpawnInfo.Builder();
        DefaultBiomeFeatures.farmAnimals(mobspawninfo$builder);
        DefaultBiomeFeatures.commonSpawns(mobspawninfo$builder);
        mobspawninfo$builder.addSpawn(EntityClassification.MONSTER,
                new MobSpawnInfo.Spawners(EntityType.BLAZE, 100, 7, 10));
        mobspawninfo$builder.addSpawn(EntityClassification.MONSTER,
                new MobSpawnInfo.Spawners(EntityType.ZOMBIFIED_PIGLIN, 50, 4, 4));
        BiomeGenerationSettings.Builder biomegenerationsettings$builder =
                (new BiomeGenerationSettings.Builder()).surfaceBuilder(surfaceBuilder);

        biomegenerationsettings$builder.addStructureStart(StructureFeatures.MINESHAFT);
        biomegenerationsettings$builder.addStructureStart(StructureFeatures.RUINED_PORTAL_SWAMP);
        biomegenerationsettings$builder.addStructureStart(StructureFeatures.BURIED_TREASURE);

        DefaultBiomeFeatures.addDefaultCarvers(biomegenerationsettings$builder);

        DefaultBiomeFeatures.addDefaultLakes(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultMonsterRoom(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultUndergroundVariety(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultOres(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addSwampClayDisk(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultMushrooms(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDesertExtraVegetation(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultSprings(biomegenerationsettings$builder);

        biomegenerationsettings$builder.addFeature(GenerationStage.Decoration.LAKES, Features.LAKE_LAVA);
        DefaultBiomeFeatures.addSurfaceFreezing(biomegenerationsettings$builder);

        return (new Biome.Builder()).precipitation(Biome.RainType.NONE).biomeCategory(Biome.Category.DESERT).depth(depth).scale(scale)
                .temperature(1.5F).downfall(0.9F).specialEffects((new BiomeAmbience.Builder()).waterColor(-3407872).waterFogColor(-16777216)
                        .fogColor(-65536).skyColor(getSkyColorWithTemperatureModifier(0.8F)).foliageColorOverride(-3407872).grassColorOverride(-3407872)
                        .ambientParticle(new ParticleEffectAmbience(ParticleTypes.LAVA, 0.003f)).skyColor(-65536)
                        .ambientLoopSound(SoundEvents.AMBIENT_CRIMSON_FOREST_LOOP)
                        .ambientMoodSound(new MoodSoundAmbience(SoundEvents.AMBIENT_WARPED_FOREST_MOOD, 6000, 8, 2.0D))
                        .ambientAdditionsSound(new SoundAdditionsAmbience(SoundEvents.AMBIENT_NETHER_WASTES_ADDITIONS, 0.0111D))
                        .backgroundMusic(BackgroundMusicTracks.createGameMusic(SoundEvents.MUSIC_BIOME_CRIMSON_FOREST))
                        .build())
                .mobSpawnSettings(mobspawninfo$builder.build()).generationSettings(biomegenerationsettings$builder.build()).build();
    }

    private static int getSkyColorWithTemperatureModifier(float temperature) {
        float lvt_1_1_ = temperature / 3.0F;
        lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -1.0F, 1.0F);
        return MathHelper.hsvToRgb(0.2460909F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
    }

    public static void register(IEventBus eventBus) {
        BIOMES.register(eventBus);
    }
}
