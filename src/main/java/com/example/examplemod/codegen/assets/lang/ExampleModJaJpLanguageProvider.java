package com.example.examplemod.codegen.assets.lang;

import com.example.examplemod.block.ExampleBlocks;
import com.example.examplemod.item.ExampleItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ExampleModJaJpLanguageProvider  extends LanguageProvider {
    public ExampleModJaJpLanguageProvider(DataGenerator gen, String modId) {
        super(gen, modId, "ja_jp");
    }

    @Override
    protected void addTranslations() {
        add(ExampleBlocks.TITANIUM_BLOCK.get(), "チタンブロック");
        add(ExampleItems.TITANIUM_INGOT.get(), "チタンインゴット");
        add(ExampleItems.AMETHYST_BLOCK.get(), "アメジストブロック");
        add(ExampleItems.AMETHYST_ORE.get(), "アメジスト鉱石");
        add(ExampleItems.AMETHYST.get(), "アメジスト");
    }
}
