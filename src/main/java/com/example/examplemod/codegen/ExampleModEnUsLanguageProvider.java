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
        add(ExampleItems.FIRESTONE.get(), "Firestone");
        add(ExampleItems.FIRESTONE_BLOCK.get(), "Block of Firestone");
        add(ExampleItems.TITANIUM_BLOCK.get(), "Block of Titanium");
        add(ExampleItems.TITANIUM_INGOT.get(), "Titanium Ingot");
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
