package com.example.examplemod.block;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.block.custom.*;
import com.example.examplemod.block.custom.trees.RedwoodTree;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Collection;
import java.util.function.Supplier;


public class ExampleBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            ExampleMod.MOD_ID);
    public static final RegistryObject<Block> TITANIUM_BLOCK = registerBlock("titanium_block",
            () -> new Block(AbstractBlock.Properties
                    .of(Material.METAL, MaterialColor.METAL)
                    .requiresCorrectToolForDrops()
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.METAL)
                    .harvestTool(ToolType.PICKAXE)
                    .harvestLevel(1)
            ));

    public static final RegistryObject<Block> AMETHYST_BLOCK = registerBlock("amethyst_block",
            () -> new Block(AbstractBlock.Properties
                    .of(Material.STONE, MaterialColor.COLOR_PURPLE)
                    .requiresCorrectToolForDrops()
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.METAL)
                    .harvestTool(ToolType.PICKAXE)
                    .harvestLevel(1)
            ));

    public static final RegistryObject<Block> AMETHYST_ORE = registerBlock("amethyst_ore",
            () -> new Block(AbstractBlock.Properties.of(Material.STONE)
                    .harvestLevel(2).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(5f)));

    public static final RegistryObject<Block> FIRESTONE_BLOCK = registerBlock("firestone_block",
            () -> new FirestoneBlock(AbstractBlock.Properties.of(Material.METAL)
                    .harvestLevel(2).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(6f)));

    public static final RegistryObject<Block> AMETHYST_STAIRS = registerBlock("amethyst_stairs",
            () -> new StairsBlock(() -> AMETHYST_BLOCK.get().defaultBlockState(),
                    AbstractBlock.Properties.copy(AMETHYST_BLOCK.get())));

    public static final RegistryObject<Block> AMETHYST_WALL = registerBlock("amethyst_wall",
            () -> new WallBlock(AbstractBlock.Properties.copy(AMETHYST_BLOCK.get())));

    public static final RegistryObject<Block> AMETHYST_DOOR = registerBlock("amethyst_door",
            () -> new DoorBlock(AbstractBlock.Properties.copy(AMETHYST_BLOCK.get()).noOcclusion()));

    public static final RegistryObject<Block> AMETHYST_TRAPDOOR = registerBlock("amethyst_trapdoor",
            () -> new TrapDoorBlock(AbstractBlock.Properties.copy(AMETHYST_BLOCK.get()).noOcclusion()));

    public static final RegistryObject<Block> AMETHYST_PANE = registerBlock("amethyst_pane",
            () -> new PaneBlock(AbstractBlock.Properties.copy(AMETHYST_BLOCK.get())));

    public static final RegistryObject<Block> AMETHYST_SLAB = registerBlock("amethyst_slab",
            () -> new SlabBlock(AbstractBlock.Properties.copy(AMETHYST_BLOCK.get())));

    public static final RegistryObject<Block> AMETHYST_BUTTON = registerBlock("amethyst_button",
            () -> new WoodButtonBlock(AbstractBlock.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> AMETHYST_PRESSURE_PLATE = registerBlock("amethyst_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    AbstractBlock.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> OATS = registerBlock("oats_crop",
            () -> new OatsBlock(AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP)));


    public static final RegistryObject<Block> REDWOOD_LOG = registerBlock("redwood_log",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.OAK_LOG)));
    public static final RegistryObject<Block> REDWOOD_WOOD = registerBlock("redwood_wood",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.OAK_WOOD)));
    public static final RegistryObject<Block> STRIPPED_REDWOOD_LOG = registerBlock("stripped_redwood_log",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final RegistryObject<Block> STRIPPED_REDWOOD_WOOD = registerBlock("stripped_redwood_wood",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));
    public static final RegistryObject<Block> REDWOOD_PLANKS = registerBlock("redwood_planks",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS)));

    public static final RegistryObject<Block> REDWOOD_LEAVES = registerBlock("redwood_leaves",
            () -> new LeavesBlock(AbstractBlock.Properties.copy(Blocks.OAK_LEAVES)));

    public static final RegistryObject<Block> REDWOOD_SAPLING = registerBlock("redwood_sapling",
            () -> new SaplingBlock(new RedwoodTree(), AbstractBlock.Properties.copy(Blocks.OAK_SAPLING)));

    public static final RegistryObject<Block> HYACINTH = registerBlock("hyacinth",
            () -> new FlowerBlock(Effects.HUNGER, 20, AbstractBlock.Properties.copy(Blocks.OAK_SAPLING)));

    public static final RegistryObject<Block> LIGHTNING_CHANNELER = registerBlock("lightning_channeler",
            () -> new LightningChannelerBlock(AbstractBlock.Properties.of(Material.METAL)));

    public static final RegistryObject<Block> REDWOOD_SIGN = registerBlock("redwood_sign",
            () -> new ExampleStandingSignBlock(AbstractBlock.Properties.of(Material.METAL), ExampleWoodType.REDWOOD));

    public static final RegistryObject<Block> REDWOOD_WALL_SIGN = registerBlock("redwood_wall_sign",
            () -> new ExampleWallSignBlock(AbstractBlock.Properties.of(Material.METAL), ExampleWoodType.REDWOOD));

    private static RegistryObject<Block> registerBlock(String name, Supplier<Block> sup) {
        return BLOCKS.register(name, sup);
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    public static Collection<RegistryObject<Block>> getEntries() {
        return BLOCKS.getEntries();
    }
}
