package com.example.examplemod.util;

import net.minecraft.data.ItemModelProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

public class ExampleItemModelProperties {
    public static void makeBow(Item item) {
        ItemModelsProperties.register(item, new ResourceLocation("pull"),
                (itemStack, clientWorld, livingEntity) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                return livingEntity.getUseItem() != itemStack ? 0.0F :
                        (float) (itemStack.getUseDuration() - livingEntity.getUseItemRemainingTicks()) / 20.0F;
            }
        });
        ItemModelsProperties.register(item, new ResourceLocation("pulling"), (itemStack, clientWorld,
                                                                                   livingEntity) -> {
            return livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == itemStack ?
                    1.0F : 0.0F;
        });
    }
}
