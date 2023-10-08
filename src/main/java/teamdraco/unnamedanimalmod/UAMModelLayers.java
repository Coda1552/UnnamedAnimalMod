package teamdraco.unnamedanimalmod;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class UAMModelLayers {
    public static final ModelLayerLocation TOMATO_FROG = create("tomato_frog");
    public static final ModelLayerLocation BANANA_SLUG = create("banana_slug");
    public static final ModelLayerLocation CAPYBARA = create("capybara");
    public static final ModelLayerLocation BLACK_DIAMOND_STINGRAY = create("black_diamond_stingray");
    public static final ModelLayerLocation ELEPHANTNOSE_FISH = create("elephantnose_fish");
    public static final ModelLayerLocation FIDDLER_CRAB = create("fiddler_crab");
    public static final ModelLayerLocation FLASHLIGHT_FISH = create("flashlight_fish");
    public static final ModelLayerLocation GREATER_PRAIRIE_CHICKEN = create("greater_prairie_chicken");
    public static final ModelLayerLocation HUMPHEAD_PARROTFISH = create("humphead_parrotfish");
    public static final ModelLayerLocation LEAFY_SEADRAGON = create("leafy_seadragon");
    public static final ModelLayerLocation MANGROVE_SNAKE = create("mangrove_snake");
    public static final ModelLayerLocation MARINE_IGUANA = create("marine_iguana");
    public static final ModelLayerLocation MUDSKIPPER = create("mudskipper");
    public static final ModelLayerLocation MUSK_OX = create("musk_ox");
    public static final ModelLayerLocation PACMAN_FROG = create("pacman_frog");
    public static final ModelLayerLocation PLATYPUS = create("platypus");
    public static final ModelLayerLocation ROCKET_KILLIFISH = create("rocket_killifish");
    public static final ModelLayerLocation SOUTHERN_RIGHT_WHALE = create("southern_right_whale");
    public static final ModelLayerLocation SPOTTED_GARDEN_EEL = create("spotted_garden_eel");
    public static final ModelLayerLocation SPOTTED_GARDEN_EEL_HIDING = create("spotted_garden_eel_hiding");

    private static ModelLayerLocation create(String name) {
        return new ModelLayerLocation(new ResourceLocation(UnnamedAnimalMod.MOD_ID, name), "main");
    }
}
