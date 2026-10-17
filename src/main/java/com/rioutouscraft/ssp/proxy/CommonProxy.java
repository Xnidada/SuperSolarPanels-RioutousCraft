/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.IWorldGenerator
 *  net.minecraftforge.fml.common.event.FMLInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPostInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 *  net.minecraftforge.fml.common.registry.GameRegistry
 */
package com.rioutouscraft.ssp.proxy;

import com.rioutouscraft.ssp.BlocksRegister;
import com.rioutouscraft.ssp.SSPWorldDecorator;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {
    public void scheduleClientTask(Runnable task) {
        task.run();
    }

    public void preInit(FMLPreInitializationEvent event) {
        BlocksRegister.register();
    }

    public void init(FMLInitializationEvent event) {
        GameRegistry.registerWorldGenerator((IWorldGenerator)new SSPWorldDecorator(), (int)0);
    }

    public void postInit(FMLPostInitializationEvent event) {
    }
}

