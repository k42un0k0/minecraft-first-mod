package com.example.examplemod;

import net.minecraft.data.*;

import java.util.function.Consumer;

public class ExampleModRecipeProvider extends RecipeProvider {
    public ExampleModRecipeProvider(DataGenerator gen) {
        super(gen);
    }
    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(Blocks.TITANIUM_BLOCK.get())
                .define('#', Items.TITANIUM_INGOT.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_titanium_ingot", has(Items.TITANIUM_INGOT.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(Items.TITANIUM_INGOT.get(), 9)
                .requires(Blocks.TITANIUM_BLOCK.get())
                .group("titanium_ingot")
                .unlockedBy("has_titanium_block", has(Blocks.TITANIUM_BLOCK.get()))
                .save(consumer, "examplemod:titanium_ingot_from_titanium_block");
    }
}
