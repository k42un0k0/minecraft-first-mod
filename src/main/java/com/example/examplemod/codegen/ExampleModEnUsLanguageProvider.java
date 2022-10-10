package com.example.examplemod.codegen;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.block.ExampleBlocks;
import com.example.examplemod.item.ExampleItemGroup;
import com.example.examplemod.item.ExampleItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ExampleModEnUsLanguageProvider extends LanguageProvider {
    public ExampleModEnUsLanguageProvider(DataGenerator gen, String modId) {
        super(gen, modId, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(ExampleBlocks.TITANIUM_BLOCK.get(), "Block of Titanium");
        add(ExampleItems.TITANIUM_INGOT.get(), "Titanium Ingot");
        add(ExampleBlocks.AMETHYST_BLOCK.get(), "Block of Amethyst");
        add(ExampleItems.AMETHYST_ORE.get(), "Amethyst Ore");
        add(ExampleItems.AMETHYST.get(), "Amethyst");
        add("itemGroup."+ ExampleMod.MOD_ID, "Example Mod");
        add("commands.hunger.success",  "set %s 's food level to %d");
    }
}
