package com.example.examplemod.entity.render;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.entity.custom.BuffZombieEntity;
import com.example.examplemod.entity.model.BuffZombieModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class BuffZombieRenderer extends MobRenderer<BuffZombieEntity, BuffZombieModel<BuffZombieEntity>>
{
    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(ExampleMod.MOD_ID, "textures/entity/buff_zombie.png");

    public BuffZombieRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new BuffZombieModel<>(), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(BuffZombieEntity entity) {
        return TEXTURE;
    }

}