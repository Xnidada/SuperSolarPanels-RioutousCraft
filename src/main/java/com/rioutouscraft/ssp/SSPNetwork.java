package com.rioutouscraft.ssp;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public final class SSPNetwork {
    private static final SimpleNetworkWrapper CHANNEL = NetworkRegistry.INSTANCE.newSimpleChannel("ssp_config");

    private SSPNetwork() {
    }

    public static void register() {
        CHANNEL.registerMessage(PacketServerConfig.Handler.class, PacketServerConfig.class, 0, Side.CLIENT);
    }

    public static void sendConfig(EntityPlayerMP player) {
        CHANNEL.sendTo(ConfigSync.createPacket(), player);
    }
}
