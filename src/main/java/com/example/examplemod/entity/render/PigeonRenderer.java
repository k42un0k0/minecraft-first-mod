package com.example.examplemod.entity.render;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.entity.custom.PigeonEntity;
import com.example.examplemod.entity.model.PigeonModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class PigeonRenderer extends MobRenderer<PigeonEntity, PigeonModel<PigeonEntity>>
{
    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(ExampleMod.MOD_ID, "textures/entity/pigeon.png");

    public PigeonRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new PigeonModel<>(), 0.2F);
    }

    @Override
    public ResourceLocation getTextureLocation(PigeonEntity entity) {
        return TEXTURE;
    }

}