package com.example.examplemod.block;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.block.custom.FirestoneBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Collection;


public class ExampleBlocks {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ExampleMod.MOD_ID);
    public static final RegistryObject<Block> TITANIUM_BLOCK = BLOCKS.register("titanium_block", () -> new Block(AbstractBlock.Properties
            .of(Material.METAL, MaterialColor.METAL)
            .requiresCorrectToolForDrops()
            .strength(5.0F, 6.0F)
            .sound(SoundType.METAL)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(1)
    ));

    public static final RegistryObject<Block> AMETHYST_BLOCK = BLOCKS.register("amethyst_block", () -> new Block(AbstractBlock.Properties
            .of(Material.STONE, MaterialColor.COLOR_PURPLE)
            .requiresCorrectToolForDrops()
            .strength(5.0F, 6.0F)
            .sound(SoundType.METAL)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(1)
    ));

    public static final RegistryObject<Block> AMETHYST_ORE = BLOCKS.register("amethyst_ore",
            () -> new Block(AbstractBlock.Properties.of(Material.STONE)
                    .harvestLevel(2).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(5f)));

    public static final RegistryObject<Block> FIRESTONE_BLOCK = BLOCKS.register("firestone_block",
            () -> new FirestoneBlock(AbstractBlock.Properties.of(Material.METAL)
                    .harvestLevel(2).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(6f)));

    public static final RegistryObject<Block> AMETHYST_STAIRS = BLOCKS.register("amethyst_stairs",
            () -> new StairsBlock(()-> AMETHYST_BLOCK.get().defaultBlockState(),
                    AbstractBlock.Properties.of(Material.METAL).harvestLevel(2).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(6f)));

    public static final RegistryObject<Block> AMETHYST_WALL = BLOCKS.register("amethyst_wall",
            () -> new WallBlock(AbstractBlock.Properties.of(Material.METAL).harvestLevel(2).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(6f)));

    public static final RegistryObject<Block> AMETHYST_DOOR = BLOCKS.register("amethyst_door",
            () -> new DoorBlock(AbstractBlock.Properties.of(Material.WOOD).harvestLevel(2).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(6f)));

    public static final RegistryObject<Block> AMETHYST_TRAP_DOOR = BLOCKS.register("amethyst_trap_door",
            () -> new TrapDoorBlock(AbstractBlock.Properties.of(Material.WOOD).harvestLevel(2).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(6f)));

    public static final RegistryObject<Block> AMETHYST_PANE = BLOCKS.register("amethyst_pane",
            () -> new PaneBlock(AbstractBlock.Properties.of(Material.METAL).harvestLevel(2).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(6f)));

    public static final RegistryObject<Block> AMETHYST_SLAB = BLOCKS.register("amethyst_slab",
            () -> new SlabBlock(AbstractBlock.Properties.of(Material.METAL).harvestLevel(2).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(6f)));

    public static final RegistryObject<Block> AMETHYST_BUTTON = BLOCKS.register("amethyst_button",
            () ->  new WoodButtonBlock(AbstractBlock.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> AMETHYST_PRESSURE_PLATE = BLOCKS.register("amethyst_pressure_plate",
            () ->  new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)));



    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
    public static Collection<RegistryObject<Block>> getEntries() {
        return BLOCKS.getEntries();
    }
}
