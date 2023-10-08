package teamdraco.unnamedanimalmod.init;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.registries.RegistryObject;
import teamdraco.unnamedanimalmod.UnnamedAnimalMod;
import teamdraco.unnamedanimalmod.common.worldgen.trees.mangrove.MangroveLandTreeFeature;
import teamdraco.unnamedanimalmod.common.worldgen.trees.mangrove.MangroveWaterTreeFeature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class UAMFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, UnnamedAnimalMod.MOD_ID);

    public static final RegistryObject<MangroveWaterTreeFeature> WATER_TREE_FEATURE = FEATURES.register("submerged_mangrove_tree", MangroveWaterTreeFeature::new);
    public static final RegistryObject<MangroveLandTreeFeature> LAND_TREE_FEATURE = FEATURES.register("mangrove_tree", MangroveLandTreeFeature::new);

/*    static {
        Registry.register(Registry.CONFIGURED_FEATURE_REGISTRY, UnnamedAnimalMod.MOD_ID + ":" + "mangrove_tree_feature", LAND_TREE.configured(NoneFeatureConfiguration.INSTANCE).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(2, 0.5F, 1))));
        Registry.register(Registry.CONFIGURED_FEATURE_REGISTRY, UnnamedAnimalMod.MOD_ID + ":" + "disk_mud", Feature.DISK.configured(new SphereReplaceConfig(UAMBlocks.MUD.defaultBlockState(), FeatureSpread.of(3, 1), 2, ImmutableList.of(Blocks.DIRT.defaultBlockState(), UAMBlocks.MUD.defaultBlockState()))).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE));
        Registry.register(Registry.CONFIGURED_FEATURE_REGISTRY, UnnamedAnimalMod.MOD_ID + ":" + "submerged_mangrove_tree_feature", WATER_TREE.configured(INSTANCE).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(2, 0.5F, 1))));
    }*/
}