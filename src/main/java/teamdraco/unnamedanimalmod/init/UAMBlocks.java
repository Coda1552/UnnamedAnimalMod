package teamdraco.unnamedanimalmod.init;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;
import teamdraco.unnamedanimalmod.UnnamedAnimalMod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamdraco.unnamedanimalmod.common.block.*;

import java.util.function.BiFunction;
import java.util.function.Supplier;

// Todo - mineable tags
public class UAMBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, UnnamedAnimalMod.MOD_ID);

    public static final RegistryObject<Block> SALT_BLOCK = register("salt_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_PINK).sound(SoundType.STONE).strength(1.5f)));
    public static final RegistryObject<Block> SALT = BLOCKS.register("salt", () -> new SaltPowderBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak()));
    public static final RegistryObject<Block> MANGROVE_SAPLING = register("mangrove_sapling", () -> new MangroveSaplingBlock(BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));

    public static final RegistryObject<Block> STRIPPED_MANGROVE_LOG = register("stripped_mangrove_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_RED).sound(SoundType.WOOD).strength(1.75F, 4.0F)));
    public static final RegistryObject<Block> MANGROVE_LOG = registerRotatedPillar("mangrove_log", STRIPPED_MANGROVE_LOG, BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_YELLOW).sound(SoundType.WOOD).strength(1.75F, 4.0F));
    public static final RegistryObject<Block> STRIPPED_MANGROVE_WOOD = register("stripped_mangrove_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_RED).sound(SoundType.WOOD).strength(1.75F, 4.0F)));
    public static final RegistryObject<Block> MANGROVE_WOOD = registerRotatedPillar("mangrove_wood", STRIPPED_MANGROVE_WOOD, BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_YELLOW).sound(SoundType.WOOD).strength(1.75F, 4.0F));
    
    public static final RegistryObject<Block> MANGROVE_PLANKS = register("mangrove_planks", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_RED).sound(SoundType.WOOD).strength(1.75F, 4.0F)));
    public static final RegistryObject<Block> MANGROVE_SLAB = register("mangrove_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_RED).sound(SoundType.WOOD).strength(1.75F, 4.0F)));
    public static final RegistryObject<Block> MANGROVE_STAIRS = register("mangrove_stairs", () -> new StairBlock(() -> MANGROVE_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_RED).sound(SoundType.WOOD).strength(1.75F, 4.0F)));
    public static final RegistryObject<Block> MANGROVE_DOOR = register("mangrove_door", () -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_RED).sound(SoundType.WOOD).strength(1.75F, 4.0F).noOcclusion()));
    public static final RegistryObject<Block> MANGROVE_TRAPDOOR = register("mangrove_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_RED).sound(SoundType.WOOD).strength(1.75F, 4.0F).noOcclusion()));
    public static final RegistryObject<Block> MANGROVE_BUTTON = register("mangrove_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION, MaterialColor.COLOR_RED).sound(SoundType.WOOD).strength(1.75F, 4.0F)));
    public static final RegistryObject<Block> MANGROVE_PRESSURE_PLATE = register("mangrove_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_RED).sound(SoundType.WOOD).strength(1.75F, 4.0F)));
    public static final RegistryObject<Block> MANGROVE_FENCE = register("mangrove_fence", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_RED).sound(SoundType.WOOD).strength(1.75F, 4.0F)));
    public static final RegistryObject<Block> MANGROVE_FENCE_GATE = register("mangrove_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_RED).sound(SoundType.WOOD).strength(1.75F, 4.0F)));

    public static final RegistryObject<Block> MUD_BLOCK = register("mud_block", () -> new Block(BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.COLOR_BROWN).sound(SoundType.GRAVEL).strength(0.6F)));
    public static final RegistryObject<Block> MUD_BRICKS = register("mud_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.COLOR_BROWN).sound(SoundType.STONE).strength(2.5F)));
    public static final RegistryObject<Block> CHISELED_MUD_BRICKS = register("chiseled_mud_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.COLOR_BROWN).sound(SoundType.STONE).strength(2.5F)));
    public static final RegistryObject<Block> MUD_BRICK_SLAB = register("mud_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.STONE).sound(SoundType.STONE).strength(2.5F)));
    public static final RegistryObject<Block> MUD_BRICK_STAIRS = register("mud_brick_stairs", () -> new StairBlock(() -> MUD_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.COLOR_BROWN).sound(SoundType.STONE).strength(2.5F)));
    public static final RegistryObject<Block> DRIED_MUD_BLOCK = register("dried_mud_block", () -> new DriedMudBlock(MUD_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.COLOR_BROWN).sound(SoundType.STONE).strength(0.9F)));
    public static final RegistryObject<Block> DRIED_MUD_BRICKS = register("dried_mud_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.COLOR_BROWN).sound(SoundType.STONE).strength(2.5F)));
    public static final RegistryObject<Block> CHISELED_DRIED_MUD_BRICKS = register("chiseled_dried_mud_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.COLOR_BROWN).sound(SoundType.STONE).strength(2.5F)));
    public static final RegistryObject<Block> DRIED_MUD_BRICK_SLAB = register("dried_mud_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.COLOR_BROWN).sound(SoundType.GRAVEL).strength(2.5F)));
    public static final RegistryObject<Block> DRIED_MUD_BRICK_STAIRS = register("dried_mud_brick_stairs", () -> new StairBlock(() -> DRIED_MUD_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.COLOR_BROWN).sound(SoundType.STONE).strength(2.5F)));

    public static final RegistryObject<Block> RICH_FARMLAND = register("rich_farmland", () -> new RichFarmlandBlock(BlockBehaviour.Properties.copy(Blocks.FARMLAND)));

    public static final RegistryObject<Block> MANGROVE_LEAVES = register("mangrove_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.JUNGLE_LEAVES)));
    public static final RegistryObject<Block> FLOWERING_MANGROVE_LEAVES = register("flowering_mangrove_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.JUNGLE_LEAVES)));
    public static final RegistryObject<Block> MANGROVE_FRUIT = BLOCKS.register("mangrove_fruit", () -> new MangroveFruitBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));

    private static RegistryObject<Block> registerRotatedPillar(String name, Supplier<Block> stripped, BlockBehaviour.Properties properties) {
        return register(name, () -> new RotatedPillarBlock(properties) {
            @Override
            public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
                if (toolAction == ToolActions.AXE_STRIP) {
                    return stripped.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS));
                }
                return super.getToolModifiedState(state, context, toolAction, simulate);
            }
        });
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
        return register(name, block, new Item.Properties().tab(UnnamedAnimalMod.GROUP));
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block, Item.Properties itemProperties) {
        return register(name, block, BlockItem::new, itemProperties);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block, BiFunction<Block, Item.Properties, BlockItem> item, Item.Properties itemProperties) {
        final RegistryObject<T> registryObject = BLOCKS.register(name, block);
        if (itemProperties != null)
            UAMItems.ITEMS.register(name, () -> item == null ? new BlockItem(registryObject.get(), itemProperties) : item.apply(registryObject.get(), itemProperties));
        return registryObject;
    }
}