package com.example.examplemod.codegen;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.block.ExampleBlocks;
import com.example.examplemod.item.ExampleItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ExampleModEnUsLanguageProvider extends LanguageProvider {
    public ExampleModEnUsLanguageProvider(DataGenerator gen, String modId) {
        super(gen, modId, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(ExampleItems.AMETHYST.get(), "Amethyst");
        add(ExampleItems.AMETHYST_BLOCK.get(), "Block of Amethyst");
        add(ExampleItems.AMETHYST_ORE.get(), "Amethyst Ore");
        add(ExampleItems.AMETHYST_STAIRS.get(), "Amethyst Stairs");
        add(ExampleItems.AMETHYST_WALL.get(), "Amethyst Wall");
        add(ExampleItems.AMETHYST_PANE.get(), "Amethyst Pane");
        add(ExampleItems.AMETHYST_DOOR.get(), "Amethyst Door");
        add(ExampleItems.AMETHYST_TRAP_DOOR.get(), "Amethyst Trap Door");
        add(ExampleItems.AMETHYST_BUTTON.get(), "Amethyst Button");
        add(ExampleItems.AMETHYST_SLAB.get(), "Amethyst Slab");
        add(ExampleItems.AMETHYST_PRESSURE_PLATE.get(), "Amethyst Pressure Plate");
        add(ExampleItems.AMETHYST_SWORD.get(), "Amethyst Sword");
        add(ExampleItems.AMETHYST_PICKAXE.get(), "Amethyst Pickaxe");
        add(ExampleItems.AMETHYST_AXE.get(), "Amethyst Axe");
        add(ExampleItems.AMETHYST_SHOVEL.get(), "Amethyst Shovel");
        add(ExampleItems.AMETHYST_HOE.get(), "Amethyst Hoe");
        add(ExampleItems.AMETHYST_HELMET.get(), "Amethyst Helmet");
        add(ExampleItems.AMETHYST_CHESTPLATE.get(), "Amethyst Chestplate");
        add(ExampleItems.AMETHYST_LEGGINGS.get(), "Amethyst Leggings");
        add(ExampleItems.AMETHYST_BOOTS.get(), "Amethyst Boots");
        add(ExampleItems.FIRESTONE.get(), "Firestone");
        add(ExampleItems.FIRESTONE_BLOCK.get(), "Block of Firestone");
        add(ExampleItems.TITANIUM_BLOCK.get(), "Block of Titanium");
        add(ExampleItems.TITANIUM_INGOT.get(), "Titanium Ingot");
        add(ExampleItems.OATS.get(), "Oats");
        add(genKey("itemGroup",ExampleMod.MOD_ID), "Example Mod");
        commands(genKey("hunger","success"),"set %s 's food level to %d");
        tooltip(genKey(ExampleMod.MOD_ID,"firestone"),"Hold \u00A7eSHIFT\u00A7r for more Information!");
        tooltip(genKey(ExampleMod.MOD_ID,"firestone_shift"),"Might set you on fire. But also might protect from it!");
    }

    private static String genKey(String ... keys) {
        return String.join(".", keys);
    }
    private void commands(String key, String text) {
        add(genKey("commands",key),text);
    }
    private void tooltip(String key, String text) {
        add(genKey("tooltip",key),text);
    }
}
