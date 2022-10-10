package com.example.examplemod.events;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.commands.HungerCommand;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = ExampleMod.MOD_ID)
public class ExampleEvents {
        @SubscribeEvent
        public static void onCommandsRegister(RegisterCommandsEvent event){
            HungerCommand.register(event.getDispatcher());
            ConfigCommand.register(event.getDispatcher());
        }
}
