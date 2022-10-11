package com.example.examplemod.item;

import com.example.examplemod.block.ExampleBlocks;
import com.example.examplemod.ExampleMod;
import com.example.examplemod.item.custom.Firestone;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ExampleItems {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ExampleMod.MOD_ID);

    public static final RegistryObject<Item> TITANIUM_INGOT = ITEMS.register("titanium_ingot", () ->
            new Item(new Item.Properties()
                    .tab(ExampleItemGroup.TAB)));

    public static final RegistryObject<Item> TITANIUM_BLOCK = registerBlock("titanium_block", ExampleBlocks.TITANIUM_BLOCK);

    public static final RegistryObject<Item> AMETHYST = ITEMS.register("amethyst", () ->
            new Item(new Item.Properties()
                    .tab(ExampleItemGroup.TAB)));

    public static final RegistryObject<Item> AMETHYST_BLOCK = registerBlock("amethyst_block", ExampleBlocks.AMETHYST_BLOCK);

    public static final RegistryObject<Item> AMETHYST_ORE = registerBlock("amethyst_ore", ExampleBlocks.AMETHYST_ORE);

    public static final RegistryObject<Item> AMETHYST_STAIRS = registerBlock("amethyst_stairs", ExampleBlocks.AMETHYST_STAIRS);

    public static final RegistryObject<Item> AMETHYST_WALL = registerBlock("amethyst_wall", ExampleBlocks.AMETHYST_WALL);

    public static final RegistryObject<Item> AMETHYST_DOOR = registerBlock("amethyst_door", ExampleBlocks.AMETHYST_DOOR);

    public static final RegistryObject<Item> AMETHYST_TRAP_DOOR = registerBlock("amethyst_trap_door", ExampleBlocks.AMETHYST_TRAP_DOOR);

    public static final RegistryObject<Item> AMETHYST_PANE = registerBlock("amethyst_pane", ExampleBlocks.AMETHYST_PANE);

    public static final RegistryObject<Item> AMETHYST_SLAB = registerBlock("amethyst_slab", ExampleBlocks.AMETHYST_SLAB);
    public static final RegistryObject<Item> AMETHYST_BUTTON = registerBlock("amethyst_button", ExampleBlocks.AMETHYST_BUTTON);
    public static final RegistryObject<Item> AMETHYST_PRESSURE_PLATE = registerBlock("amethyst_pressure_plate", ExampleBlocks.AMETHYST_PRESSURE_PLATE);

    public static final RegistryObject<Item> FIRESTONE = ITEMS.register("firestone", () ->
            new Firestone(new Item.Properties().durability(8)
                    .tab(ExampleItemGroup.TAB)));

    public static final RegistryObject<Item> AMETHYST_SWORD = ITEMS.register("amethyst_sword", () ->
            new SwordItem(ExampleItemTier.AMETHYST, 2, 3f, new Item.Properties()
                    .tab(ExampleItemGroup.TAB)));
    public static final RegistryObject<Item> AMETHYST_PICKAXE = ITEMS.register("amethyst_pickaxe", () ->
            new PickaxeItem(ExampleItemTier.AMETHYST, 0, -1f, new Item.Properties()
                    .tab(ExampleItemGroup.TAB)));
    public static final RegistryObject<Item> AMETHYST_AXE = ITEMS.register("amethyst_axe", () ->
            new AxeItem(ExampleItemTier.AMETHYST, 4, -6f, new Item.Properties()
                    .tab(ExampleItemGroup.TAB)));
    public static final RegistryObject<Item> AMETHYST_SHOVEL = ITEMS.register("amethyst_shovel", () ->
            new ShovelItem(ExampleItemTier.AMETHYST, 0, -1f, new Item.Properties()
                    .tab(ExampleItemGroup.TAB)));
    public static final RegistryObject<Item> AMETHYST_HOE = ITEMS.register("amethyst_hoe", () ->
            new HoeItem(ExampleItemTier.AMETHYST, 0, 0f, new Item.Properties()
                    .tab(ExampleItemGroup.TAB)));
    public static final RegistryObject<Item> FIRESTONE_BLOCK = registerBlock("firestone_block", ExampleBlocks.FIRESTONE_BLOCK);

    private static RegistryObject<Item> registerBlock(String name, RegistryObject<Block> blockRegistryObject) {
        return ITEMS.register(name, () ->
                new BlockItem(blockRegistryObject.get(), new Item.Properties().tab(ExampleItemGroup.TAB)));
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


}
