package teamdraco.unnamedanimalmod;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import teamdraco.unnamedanimalmod.client.ClientEvents;
import teamdraco.unnamedanimalmod.common.entity.*;
import teamdraco.unnamedanimalmod.init.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Mod(UnnamedAnimalMod.MOD_ID)
public class UnnamedAnimalMod {
    public static final String MOD_ID = "unnamedanimalmod";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final List<Runnable> CALLBACKS = new ArrayList<>();

    public UnnamedAnimalMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::registerCommon);
        bus.addListener(this::registerEntityAttributes);

        UAMSounds.SOUNDS.register(bus);
        UAMBlocks.BLOCKS.register(bus);
        UAMItems.ITEMS.register(bus);
        UAMEntities.ENTITIES.register(bus);
        UAMFeatures.FEATURES.register(bus);
        UAMBiomes.BIOMES.register(bus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, UAMConfig.Common.SPEC);
    }

    private void registerCommon(SpawnPlacementRegisterEvent e) {
        e.register(UAMEntities.BLACK_DIAMOND_STINGRAY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, UnnamedAnimalMod::canFishSpawn, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(UAMEntities.GREATER_PRAIRIE_CHICKEN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, UnnamedAnimalMod::canAnimalSpawn, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(UAMEntities.TOMATO_FROG.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, UnnamedAnimalMod::canAnimalSpawn, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(UAMEntities.FLASHLIGHT_FISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, UnnamedAnimalMod::canFlashlightFishSpawn, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(UAMEntities.MUSK_OX.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, UnnamedAnimalMod::canMuskOxSpawn, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(UAMEntities.MARINE_IGUANA.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, UnnamedAnimalMod::canMarineIguanaSpawn, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(UAMEntities.PLATYPUS.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, UnnamedAnimalMod::canAnimalSpawn, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(UAMEntities.BANANA_SLUG.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, UnnamedAnimalMod::canSlugSpawn, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(UAMEntities.ELEPHANTNOSE_FISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, UnnamedAnimalMod::canFishSpawn, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(UAMEntities.PACMAN_FROG.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, UnnamedAnimalMod::canAnimalSpawn, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(UAMEntities.ROCKET_KILLIFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, UnnamedAnimalMod::canFishSpawn, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(UAMEntities.MANGROVE_SNAKE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, UnnamedAnimalMod::canAnimalSpawn, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(UAMEntities.SOUTHERN_RIGHT_WHALE.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, UnnamedAnimalMod::canFishSpawn, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(UAMEntities.FIDDLER_CRAB.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, UnnamedAnimalMod::canFishSpawn, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(UAMEntities.LEAFY_SEADRAGON.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, UnnamedAnimalMod::canFishSpawn, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(UAMEntities.HUMPHEAD_PARROTFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, UnnamedAnimalMod::canFishSpawn, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(UAMEntities.SPOTTED_GARDEN_EEL.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, UnnamedAnimalMod::canFishSpawn, SpawnPlacementRegisterEvent.Operation.AND);
        e.register(UAMEntities.MUDSKIPPER.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, UnnamedAnimalMod::canFishSpawn, SpawnPlacementRegisterEvent.Operation.AND);
    }

    public static boolean canFishSpawn(EntityType<? extends PathfinderMob> type, LevelAccessor worldIn, MobSpawnType reason, BlockPos blockPos, RandomSource randomIn) {
        return worldIn.getBlockState(blockPos).is(Blocks.WATER) && worldIn.getBlockState(blockPos.above()).is(Blocks.WATER);
    }

    public static boolean canAnimalSpawn(EntityType<? extends Animal> type, LevelAccessor worldIn, MobSpawnType reason, BlockPos blockPos, RandomSource randomIn) {
        return worldIn.getBlockState(blockPos.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON) && worldIn.getRawBrightness(blockPos, 0) > 8;
    }

    public static boolean canMarineIguanaSpawn(EntityType<? extends Animal> type, LevelAccessor worldIn, MobSpawnType reason, BlockPos blockPos, RandomSource randomIn) {
        return worldIn.getBlockState(blockPos.below()).is(BlockTags.BASE_STONE_OVERWORLD) && worldIn.getRawBrightness(blockPos, 0) > 8;
    }

    public static boolean canSlugSpawn(EntityType<? extends Animal> type, LevelAccessor worldIn, MobSpawnType reason, BlockPos blockPos, RandomSource randomIn) {
        return worldIn.getBlockState(blockPos.below()).is(Blocks.PODZOL) || worldIn.getBlockState(blockPos.below()).is(Blocks.COARSE_DIRT) && worldIn.getRawBrightness(blockPos, 0) > 8;
    }

    public static boolean canFlashlightFishSpawn(EntityType<? extends AbstractFish> type, LevelAccessor worldIn, MobSpawnType reason, BlockPos blockPos, RandomSource randomIn) {
        return canFishSpawn(type, worldIn, reason, blockPos, randomIn) && worldIn.getLevelData().getDayTime() % 12000 > 0 && worldIn.getLevelData().getDayTime() % 24000 < 0; // todo - test
    }

    public static boolean canMuskOxSpawn(EntityType<? extends Animal> type, LevelAccessor worldIn, MobSpawnType reason, BlockPos blockPos, RandomSource randomIn) {
        return canAnimalSpawn(type, worldIn, reason, blockPos, randomIn) && worldIn.getLevelData().getDayTime() > 11500 && worldIn.getLevelData().getDayTime() < 13500; // todo - test
    }

    private void registerEntityAttributes(EntityAttributeCreationEvent event) {
       event.put(UAMEntities.BLACK_DIAMOND_STINGRAY.get(), BlackDiamondStingrayEntity.createAttributes().build());
       event.put(UAMEntities.TOMATO_FROG.get(), TomatoFrogEntity.createAttributes().build());
       event.put(UAMEntities.SOUTHERN_RIGHT_WHALE.get(), SouthernRightWhaleEntity.createAttributes().build());
       event.put(UAMEntities.GREATER_PRAIRIE_CHICKEN.get(), GreaterPrairieChickenEntity.createAttributes().build());
       event.put(UAMEntities.FLASHLIGHT_FISH.get(), FlashlightFishEntity.createAttributes().build());
       event.put(UAMEntities.HUMPHEAD_PARROTFISH.get(), HumpheadParrotfishEntity.createAttributes().build());
       event.put(UAMEntities.MUSK_OX.get(), MuskOxEntity.createAttributes().build());
       event.put(UAMEntities.BANANA_SLUG.get(), BananaSlugEntity.createAttributes().build());
       event.put(UAMEntities.MARINE_IGUANA.get(), MarineIguanaEntity.createAttributes().build());
       event.put(UAMEntities.PLATYPUS.get(), PlatypusEntity.createAttributes().build());
       event.put(UAMEntities.ELEPHANTNOSE_FISH.get(), ElephantnoseFishEntity.createAttributes().build());
       event.put(UAMEntities.PACMAN_FROG.get(), PacmanFrogEntity.createAttributes().build());
       event.put(UAMEntities.CAPYBARA.get(), CapybaraEntity.createAttributes().build());
       event.put(UAMEntities.ROCKET_KILLIFISH.get(), RocketKillifishEntity.createAttributes().build());
       event.put(UAMEntities.MANGROVE_SNAKE.get(), MangroveSnakeEntity.createAttributes().build());
       event.put(UAMEntities.FIDDLER_CRAB.get(), FiddlerCrabEntity.createAttributes().build());
       event.put(UAMEntities.LEAFY_SEADRAGON.get(), LeafySeadragonEntity.createAttributes().build());
       event.put(UAMEntities.SPOTTED_GARDEN_EEL.get(), SpottedGardenEelEntity.createAttributes().build());
       event.put(UAMEntities.MUDSKIPPER.get(), MudskipperEntity.createAttributes().build());
    }

    public final static CreativeModeTab GROUP = new CreativeModeTab(MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(UAMItems.MARINE_IGUANA_EGG.get());
        }
    };
}
