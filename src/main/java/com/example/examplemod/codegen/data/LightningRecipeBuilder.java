package com.example.examplemod.codegen.data;

import com.example.examplemod.data.recipes.ExampleRecipeTypes;
import com.example.examplemod.data.recipes.LightningChannelerRecipe;
import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.IRequirementsStrategy;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;

public class LightningRecipeBuilder {
    private final Item output;
    private final List<Ingredient> ingredients = Lists.newArrayListWithCapacity(2);
    private final String weather;
    private final IRecipeSerializer<?> type = ExampleRecipeTypes.LIGHTNING_SERIALIZER.get();

    public LightningRecipeBuilder(IItemProvider p_i50787_3_, String p_i50787_4_) {
        this.output = p_i50787_3_.asItem();
        this.weather = p_i50787_4_;
        for (int i = 0; i < 2; i++) {
            this.ingredients.add(Ingredient.EMPTY);
        }
    }

    public static LightningRecipeBuilder thundering(IItemProvider item) {
        return new LightningRecipeBuilder(item, "thundering");
    }

    public static LightningRecipeBuilder rain(IItemProvider item) {
        return new LightningRecipeBuilder(item, "rain");
    }

    public static LightningRecipeBuilder clear(IItemProvider item) {
        return new LightningRecipeBuilder(item, "clear");
    }

    public LightningRecipeBuilder slot0(IItemProvider item) {
        this.ingredients.set(0, Ingredient.of(item.asItem()));

        return this;
    }

    public LightningRecipeBuilder slot1(IItemProvider item) {
        this.ingredients.set(1, Ingredient.of(item.asItem()));

        return this;
    }


    public void save(Consumer<IFinishedRecipe> p_200482_1_) {
        this.save(p_200482_1_, Registry.ITEM.getKey(this.output) + "_from_lightning_channeler");
    }

    public void save(Consumer<IFinishedRecipe> p_218645_1_, String p_218645_2_) {
        ResourceLocation resourcelocation = Registry.ITEM.getKey(this.output);
        if ((new ResourceLocation(p_218645_2_)).equals(resourcelocation)) {
            throw new IllegalStateException("Single Item Recipe " + p_218645_2_ + " should remove its 'save' argument");
        } else {
            this.save(p_218645_1_, new ResourceLocation(p_218645_2_));
        }
    }

    public void save(Consumer<IFinishedRecipe> p_218647_1_, ResourceLocation p_218647_2_) {
        p_218647_1_.accept(new LightningRecipeBuilder.Result(p_218647_2_, this.type, this.output, this.weather,
                this.ingredients));
    }

    public static class Result implements IFinishedRecipe {
        private final ResourceLocation id;
        private final List<Ingredient> ingredients;
        private final Item output;
        private final String weather;
        private final IRecipeSerializer<?> type;

        public Result(ResourceLocation id, IRecipeSerializer<?> type, Item output, String weather,
                      List<Ingredient> ingredients
        ) {
            this.id = id;
            this.type = type;
            this.weather = weather;
            this.output = output;
            this.ingredients = ingredients;
        }

        public void serializeRecipeData(JsonObject p_218610_1_) {
            JsonArray jsonarray = new JsonArray();

            for (Ingredient ingredient : this.ingredients) {
                jsonarray.add(ingredient.toJson());
            }

            p_218610_1_.add("ingredients", jsonarray);
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("item", Registry.ITEM.getKey(this.output).toString());
            p_218610_1_.add("output", jsonobject);
            p_218610_1_.addProperty("weather", this.weather);
        }

        public ResourceLocation getId() {
            return this.id;
        }

        public IRecipeSerializer<?> getType() {
            return this.type;
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return null;
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return null;
        }

    }
}