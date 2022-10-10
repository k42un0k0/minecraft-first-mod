package com.example.examplemod.codegen;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.item.ExampleItems;
import com.example.examplemod.util.ExampleTags;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.Objects;
import java.util.function.Consumer;

public class ExampleModRecipeProvider extends RecipeProvider {
    public ExampleModRecipeProvider(DataGenerator gen) {
        super(gen);
    }
    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        addReversibleCraft(consumer,ExampleItems.TITANIUM_BLOCK.get(),ExampleItems.TITANIUM_INGOT.get());
        addReversibleCraft(consumer,ExampleItems.AMETHYST_BLOCK.get(),ExampleItems.AMETHYST.get());
        addReversibleCraft(consumer,ExampleItems.FIRESTONE_BLOCK.get(),ExampleItems.FIRESTONE.get());
        Item amethyst = ExampleItems.AMETHYST.get();
        ShapedRecipeBuilder.shaped(ExampleItems.AMETHYST_ORE.get())
                .define('#', ExampleTags.Items.AMETHYST)
                .define('S', Items.STONE)
                .pattern("#S#")
                .pattern("S#S")
                .pattern("#S#")
                .unlockedBy(getUnlockCriteriaName(amethyst), has(amethyst))
                .save(consumer);
    }

    private String getUnlockCriteriaName(Item item){
        return "has_"+ getItemPath(item);
    }

    private String getItemPath(Item item){
        return Objects.requireNonNull(item.getRegistryName()).getPath();
    }

    private void addReversibleCraft(Consumer<IFinishedRecipe> consumer, Item block, Item item){
        ShapedRecipeBuilder.shaped(block)
                .define('#', item)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy(getUnlockCriteriaName(item), has(item))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(item, 9)
                .requires(block)
                .group(getItemPath(item))
                .unlockedBy(getUnlockCriteriaName(block), has(block))
                .save(consumer, ExampleMod.MOD_ID +":"+ Objects.requireNonNull(item.getRegistryName()).getPath() +"_from_" +Objects.requireNonNull(block.getRegistryName()).getPath() );

    }
}
