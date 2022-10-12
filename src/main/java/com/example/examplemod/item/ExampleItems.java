package com.example.examplemod.item;

import com.example.examplemod.block.ExampleBlocks;
import com.example.examplemod.ExampleMod;
import com.example.examplemod.item.custom.Firestone;
import net.minecraft.block.Block;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ExampleItems {

    private static Item.Properties defaultProperties() {
        return new Item.Properties().tab(ExampleItemGroup.TAB);
    }

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ExampleMod.MOD_ID);

    public static final RegistryObject<Item> TITANIUM_INGOT = ITEMS.register("titanium_ingot", () -> new Item(defaultProperties()));

    public static final RegistryObject<Item> TITANIUM_BLOCK = registerBlockItem("titanium_block", ExampleBlocks.TITANIUM_BLOCK);

    public static final RegistryObject<Item> AMETHYST = ITEMS.register("amethyst", () -> new Item(defaultProperties()));

    public static final RegistryObject<Item> AMETHYST_BLOCK = registerBlockItem("amethyst_block", ExampleBlocks.AMETHYST_BLOCK);

    public static final RegistryObject<Item> AMETHYST_ORE = registerBlockItem("amethyst_ore", ExampleBlocks.AMETHYST_ORE);

    public static final RegistryObject<Item> AMETHYST_STAIRS = registerBlockItem("amethyst_stairs", ExampleBlocks.AMETHYST_STAIRS);

    public static final RegistryObject<Item> AMETHYST_WALL = registerBlockItem("amethyst_wall", ExampleBlocks.AMETHYST_WALL);

    public static final RegistryObject<Item> AMETHYST_DOOR = registerBlockItem("amethyst_door", ExampleBlocks.AMETHYST_DOOR);

    public static final RegistryObject<Item> AMETHYST_TRAP_DOOR = registerBlockItem("amethyst_trap_door", ExampleBlocks.AMETHYST_TRAP_DOOR);

    public static final RegistryObject<Item> AMETHYST_PANE = registerBlockItem("amethyst_pane", ExampleBlocks.AMETHYST_PANE);

    public static final RegistryObject<Item> AMETHYST_SLAB = registerBlockItem("amethyst_slab", ExampleBlocks.AMETHYST_SLAB);
    public static final RegistryObject<Item> AMETHYST_BUTTON = registerBlockItem("amethyst_button", ExampleBlocks.AMETHYST_BUTTON);
    public static final RegistryObject<Item> AMETHYST_PRESSURE_PLATE = registerBlockItem("amethyst_pressure_plate", ExampleBlocks.AMETHYST_PRESSURE_PLATE);

    public static final RegistryObject<Item> FIRESTONE = ITEMS.register("firestone", () ->
            new Firestone(defaultProperties().durability(8)));

    public static final RegistryObject<Item> AMETHYST_SWORD = ITEMS.register("amethyst_sword", () ->
            new SwordItem(ExampleItemTier.AMETHYST, 2, 3f, defaultProperties()));
    public static final RegistryObject<Item> AMETHYST_PICKAXE = ITEMS.register("amethyst_pickaxe", () ->
            new PickaxeItem(ExampleItemTier.AMETHYST, 0, -1f, defaultProperties()));
    public static final RegistryObject<Item> AMETHYST_AXE = ITEMS.register("amethyst_axe", () ->
            new AxeItem(ExampleItemTier.AMETHYST, 4, -6f, defaultProperties()));
    public static final RegistryObject<Item> AMETHYST_SHOVEL = ITEMS.register("amethyst_shovel", () ->
            new ShovelItem(ExampleItemTier.AMETHYST, 0, -1f, defaultProperties()));
    public static final RegistryObject<Item> AMETHYST_HOE = ITEMS.register("amethyst_hoe", () ->
            new HoeItem(ExampleItemTier.AMETHYST, 0, 0f, defaultProperties()));

    public static final RegistryObject<Item> AMETHYST_BOOTS = ITEMS.register("amethyst_boots", () ->
            new ArmorItem(ExampleArmorMaterial.AMETHYST, EquipmentSlotType.FEET, defaultProperties()));
    public static final RegistryObject<Item> AMETHYST_HELMET = ITEMS.register("amethyst_helmet", () ->
            new ArmorItem(ExampleArmorMaterial.AMETHYST, EquipmentSlotType.HEAD, defaultProperties()));
    public static final RegistryObject<Item> AMETHYST_CHESTPLATE = ITEMS.register("amethyst_chestplate", () ->
            new ArmorItem(ExampleArmorMaterial.AMETHYST, EquipmentSlotType.CHEST, defaultProperties()));
    public static final RegistryObject<Item> AMETHYST_LEGGINGS = ITEMS.register("amethyst_leggings", () ->
            new ArmorItem(ExampleArmorMaterial.AMETHYST, EquipmentSlotType.LEGS, defaultProperties()));
    public static final RegistryObject<Item> FIRESTONE_BLOCK = registerBlockItem("firestone_block", ExampleBlocks.FIRESTONE_BLOCK);

    public static final RegistryObject<Item> OATS = ITEMS.register("oats", () ->
            new BlockItem(ExampleBlocks.OATS.get(), defaultProperties().food(new Food.Builder().nutrition(1).saturationMod(0.1f).fast().build())));
    public static final RegistryObject<Item> REDWOOD_LOG = registerBlockItem("redwood_log", ExampleBlocks.REDWOOD_LOG);
    public static final RegistryObject<Item> REDWOOD_WOOD = registerBlockItem("redwood_wood", ExampleBlocks.REDWOOD_WOOD);
    public static final RegistryObject<Item> STRIPPED_REDWOOD_LOG = registerBlockItem("stripped_redwood_log", ExampleBlocks.STRIPPED_REDWOOD_LOG);
    public static final RegistryObject<Item> STRIPPED_REDWOOD_WOOD = registerBlockItem("stripped_redwood_wood", ExampleBlocks.STRIPPED_REDWOOD_WOOD);
    public static final RegistryObject<Item> REDWOOD_PLANKS = registerBlockItem("redwood_planks", ExampleBlocks.REDWOOD_PLANKS);


    private static RegistryObject<Item> registerBlockItem(String name, RegistryObject<Block> blockRegistryObject) {
        return registerItem(name, () ->
                new BlockItem(blockRegistryObject.get(), defaultProperties()));
    }
    private static RegistryObject<Item> registerItem(String name, Supplier<Item> sup) {
        return ITEMS.register(name,sup);
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


}
