package teamdraco.unnamedanimalmod.init;

import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.registries.RegistryObject;
import teamdraco.unnamedanimalmod.UnnamedAnimalMod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class UAMBiomes {
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, UnnamedAnimalMod.MOD_ID);

    public static final RegistryObject<Biome> MANGROVE_FOREST = BIOMES.register("red_mangrove_forest", UAMBiomes::redMangroveForest);

    public static Biome redMangroveForest() {
        MobSpawnSettings.Builder mobspawnsettings$builder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(mobspawnsettings$builder);
        BiomeDefaultFeatures.commonSpawns(mobspawnsettings$builder);
        mobspawnsettings$builder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SLIME, 1, 1, 1));
        mobspawnsettings$builder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FROG, 10, 2, 5));
        BiomeGenerationSettings.Builder biomegenerationsettings$builder = new BiomeGenerationSettings.Builder();
        BiomeDefaultFeatures.addFossilDecoration(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultSprings(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addSurfaceFreezing(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultOres(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addSwampClayDisk(biomegenerationsettings$builder);
        biomegenerationsettings$builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FLOWER_SWAMP);
        biomegenerationsettings$builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_NORMAL);
        biomegenerationsettings$builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH);
        biomegenerationsettings$builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_WATERLILY);
        biomegenerationsettings$builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.BROWN_MUSHROOM_SWAMP);
        biomegenerationsettings$builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.RED_MUSHROOM_SWAMP);
        BiomeDefaultFeatures.addDefaultMushrooms(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addSwampExtraVegetation(biomegenerationsettings$builder);
        biomegenerationsettings$builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_SWAMP);
        Music music = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_SWAMP);
        return (new Biome.BiomeBuilder()).precipitation(Biome.Precipitation.RAIN).temperature(0.8F).downfall(0.9F).specialEffects((new BiomeSpecialEffects.Builder()).grassColorOverride(8242525).waterColor(6012586).waterFogColor(4696184).fogColor(12638463).skyColor(getSkyColor(0.8F)).foliageColorOverride(10662207).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music).build()).mobSpawnSettings(mobspawnsettings$builder.build()).generationSettings(biomegenerationsettings$builder.build()).build();
    }

    protected static int getSkyColor(float temperature) {
        float value = temperature / 3.0F;
        value = Mth.clamp(value, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - value * 0.05F, 0.5F + value * 0.1F, 1.0F);
    }
}
