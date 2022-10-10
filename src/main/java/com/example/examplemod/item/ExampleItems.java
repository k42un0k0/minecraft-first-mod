package com.example.examplemod.item;

import com.example.examplemod.block.ExampleBlocks;
import com.example.examplemod.ExampleMod;
import com.example.examplemod.item.custom.Firestone;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ExampleItems {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ExampleMod.MOD_ID);
    public static final RegistryObject<Item> TITANIUM_BLOCK = ITEMS.register("titanium_block", () -> new BlockItem(ExampleBlocks.TITANIUM_BLOCK.get(), new Item.Properties()
            .tab(ExampleItemGroup.TAB)));
    public static final RegistryObject<Item> TITANIUM_INGOT = ITEMS.register("titanium_ingot", () -> new Item(new Item.Properties()
            .tab(ExampleItemGroup.TAB)));

    public static final RegistryObject<Item> AMETHYST_BLOCK = ITEMS.register("amethyst_block", () -> new BlockItem(ExampleBlocks.AMETHYST_BLOCK.get(), new Item.Properties()
            .tab(ExampleItemGroup.TAB)));

    public static final RegistryObject<Item> AMETHYST_ORE = ITEMS.register("amethyst_ore", () -> new BlockItem(ExampleBlocks.AMETHYST_ORE.get(), new Item.Properties()
            .tab(ExampleItemGroup.TAB)));
    public static final RegistryObject<Item> AMETHYST = ITEMS.register("amethyst", () -> new Item(new Item.Properties()
            .tab(ExampleItemGroup.TAB)));

    public static final RegistryObject<Item> FIRESTONE = ITEMS.register("firestone", () -> new Firestone(new Item.Properties().durability(8)
            .tab(ExampleItemGroup.TAB)));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
