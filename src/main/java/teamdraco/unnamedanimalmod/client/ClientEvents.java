package teamdraco.unnamedanimalmod.client;

import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import teamdraco.unnamedanimalmod.UAMModelLayers;
import teamdraco.unnamedanimalmod.UnnamedAnimalMod;
import teamdraco.unnamedanimalmod.client.model.*;
import teamdraco.unnamedanimalmod.client.renderer.*;
import teamdraco.unnamedanimalmod.common.block.SaltPowderBlock;
import teamdraco.unnamedanimalmod.init.UAMBlocks;
import teamdraco.unnamedanimalmod.init.UAMEntities;

@Mod.EventBusSubscriber(modid = UnnamedAnimalMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers e) {
        e.registerEntityRenderer(UAMEntities.BLACK_DIAMOND_STINGRAY.get(), BlackDiamondStingrayRenderer::new);
        e.registerEntityRenderer(UAMEntities.TOMATO_FROG.get(), TomatoFrogRenderer::new);
        e.registerEntityRenderer(UAMEntities.SOUTHERN_RIGHT_WHALE.get(), SouthernRightWhaleRenderer::new);
        e.registerEntityRenderer(UAMEntities.GREATER_PRAIRIE_CHICKEN.get(), GreaterPrairieChickenRenderer::new);
        e.registerEntityRenderer(UAMEntities.FLASHLIGHT_FISH.get(), FlashlightFishRenderer::new);
        e.registerEntityRenderer(UAMEntities.HUMPHEAD_PARROTFISH.get(), HumpheadParrotfishRenderer::new);
        e.registerEntityRenderer(UAMEntities.MUSK_OX.get(), MuskOxRenderer::new);
        e.registerEntityRenderer(UAMEntities.BANANA_SLUG.get(), BananaSlugRenderer::new);
        e.registerEntityRenderer(UAMEntities.MARINE_IGUANA.get(), MarineIguanaRenderer::new);
        e.registerEntityRenderer(UAMEntities.PLATYPUS.get(), PlatypusRenderer::new);
        e.registerEntityRenderer(UAMEntities.ELEPHANTNOSE_FISH.get(), ElephantnoseFishRenderer::new);
        e.registerEntityRenderer(UAMEntities.PACMAN_FROG.get(), PacmanFrogRenderer::new);
        e.registerEntityRenderer(UAMEntities.CAPYBARA.get(), CapybaraRenderer::new);
        e.registerEntityRenderer(UAMEntities.ROCKET_KILLIFISH.get(), RocketKillifishRenderer::new);
        e.registerEntityRenderer(UAMEntities.MANGROVE_SNAKE.get(), MangroveSnakeRenderer::new);
        e.registerEntityRenderer(UAMEntities.MANGROVE_BOAT.get(), MangroveBoatRenderer::new);
        e.registerEntityRenderer(UAMEntities.FIDDLER_CRAB.get(), FiddlerCrabRenderer::new);
        e.registerEntityRenderer(UAMEntities.LEAFY_SEADRAGON.get(), LeafySeadragonRenderer::new);
        e.registerEntityRenderer(UAMEntities.SPOTTED_GARDEN_EEL.get(), SpottedGardenEelRenderer::new);
        e.registerEntityRenderer(UAMEntities.MUDSKIPPER.get(), MudskipperRenderer::new);

        e.registerEntityRenderer(UAMEntities.GREATER_PRAIRIE_CHICKEN_EGG.get(), ThrownItemRenderer::new);
        e.registerEntityRenderer(UAMEntities.MANGROVE_SNAKE_EGG.get(), ThrownItemRenderer::new);
        e.registerEntityRenderer(UAMEntities.MARINE_IGUANA_EGG.get(), ThrownItemRenderer::new);
        e.registerEntityRenderer(UAMEntities.PLATYPUS_EGG.get(), ThrownItemRenderer::new);
    }

    @SubscribeEvent
    public static void registerModelLayers(EntityRenderersEvent.RegisterLayerDefinitions e) {
        e.registerLayerDefinition(UAMModelLayers.BANANA_SLUG, BananaSlugModel::createBodyLayer);
        e.registerLayerDefinition(UAMModelLayers.BLACK_DIAMOND_STINGRAY, BlackDiamondStingrayModel::createBodyLayer);
        e.registerLayerDefinition(UAMModelLayers.CAPYBARA, CapybaraModel::createBodyLayer);
        e.registerLayerDefinition(UAMModelLayers.ELEPHANTNOSE_FISH, ElephantnoseFishModel::createBodyLayer);
        e.registerLayerDefinition(UAMModelLayers.FLASHLIGHT_FISH, FlashlightFishModel::createBodyLayer);
        e.registerLayerDefinition(UAMModelLayers.FIDDLER_CRAB, FiddlerCrabModel::createBodyLayer);
        e.registerLayerDefinition(UAMModelLayers.GREATER_PRAIRIE_CHICKEN, GreaterPrairieChickenModel::createBodyLayer);
        e.registerLayerDefinition(UAMModelLayers.HUMPHEAD_PARROTFISH, HumpheadParrotfishModel::createBodyLayer);
        e.registerLayerDefinition(UAMModelLayers.LEAFY_SEADRAGON, LeafySeadragonModel::createBodyLayer);
        e.registerLayerDefinition(UAMModelLayers.MANGROVE_SNAKE, MangroveSnakeModel::createBodyLayer);
        e.registerLayerDefinition(UAMModelLayers.MARINE_IGUANA, MarineIguanaModel::createBodyLayer);
        e.registerLayerDefinition(UAMModelLayers.MUDSKIPPER, MudskipperModel::createBodyLayer);
        e.registerLayerDefinition(UAMModelLayers.TOMATO_FROG, TomatoFrogModel::createBodyLayer);
        e.registerLayerDefinition(UAMModelLayers.MUSK_OX, MuskOxModel::createBodyLayer);
        e.registerLayerDefinition(UAMModelLayers.PACMAN_FROG, PacmanFrogModel::createBodyLayer);
        e.registerLayerDefinition(UAMModelLayers.PLATYPUS, PlatypusModel::createBodyLayer);
        e.registerLayerDefinition(UAMModelLayers.ROCKET_KILLIFISH, RocketKillifishModel::createBodyLayer);
        e.registerLayerDefinition(UAMModelLayers.SOUTHERN_RIGHT_WHALE, SouthernRightWhaleModel::createBodyLayer);
        e.registerLayerDefinition(UAMModelLayers.SPOTTED_GARDEN_EEL, SpottedGardenEelModel::createBodyLayer);
        e.registerLayerDefinition(UAMModelLayers.SPOTTED_GARDEN_EEL_HIDING, SpottedGardenEelHidingModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerClient(FMLClientSetupEvent event) {
        UnnamedAnimalMod.CALLBACKS.forEach(Runnable::run);
        UnnamedAnimalMod.CALLBACKS.clear();
    }

    @SubscribeEvent
    public static void blockColors(RegisterColorHandlersEvent.Block event) {
        BlockColors handler = event.getBlockColors();
        handler.register((p_228059_0_, p_228059_1_, p_228059_2_, p_228059_3_) -> SaltPowderBlock.getColor(), UAMBlocks.SALT.get());
    }
}
