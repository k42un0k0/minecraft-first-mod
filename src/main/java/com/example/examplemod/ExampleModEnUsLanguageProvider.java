package com.example.examplemod;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.IDataProvider;
import net.minecraftforge.common.data.LanguageProvider;

public class ExampleModEnUsLanguageProvider extends LanguageProvider {
    public ExampleModEnUsLanguageProvider(DataGenerator gen, String modId) {
        super(gen, modId, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(Blocks.TITANIUM_BLOCK.get(), "Block of Titanium");
        add(Items.TITANIUM_INGOT.get(), "Titanium Ingot");
    }
}
