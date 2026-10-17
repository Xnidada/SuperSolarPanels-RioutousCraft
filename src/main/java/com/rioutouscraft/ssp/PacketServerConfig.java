package com.rioutouscraft.ssp;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

/** Server-authoritative solar and module values sent to the client after login. */
public final class PacketServerConfig implements IMessage {
    private static final int LEVELS = 14;
    final double[] solarDay = new double[LEVELS];
    final double[] solarNight = new double[LEVELS];
    final double[] solarStorage = new double[LEVELS];
    final double[] solarOutput = new double[LEVELS];
    final double[] batteryCapacity = new double[LEVELS];
    final double[] moduleOutput = new double[LEVELS];
    final int[] moduleOutputTier = new int[LEVELS];

    public PacketServerConfig() {
    }

    PacketServerConfig(double[] solarDay, double[] solarNight, double[] solarStorage, double[] solarOutput,
                       double[] batteryCapacity, double[] moduleOutput, int[] moduleOutputTier) {
        System.arraycopy(solarDay, 0, this.solarDay, 0, LEVELS);
        System.arraycopy(solarNight, 0, this.solarNight, 0, LEVELS);
        System.arraycopy(solarStorage, 0, this.solarStorage, 0, LEVELS);
        System.arraycopy(solarOutput, 0, this.solarOutput, 0, LEVELS);
        System.arraycopy(batteryCapacity, 0, this.batteryCapacity, 0, LEVELS);
        System.arraycopy(moduleOutput, 0, this.moduleOutput, 0, LEVELS);
        System.arraycopy(moduleOutputTier, 0, this.moduleOutputTier, 0, LEVELS);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        readDoubles(buf, solarDay);
        readDoubles(buf, solarNight);
        readDoubles(buf, solarStorage);
        readDoubles(buf, solarOutput);
        readDoubles(buf, batteryCapacity);
        readDoubles(buf, moduleOutput);
        for (int i = 0; i < LEVELS; i++) moduleOutputTier[i] = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        writeDoubles(buf, solarDay);
        writeDoubles(buf, solarNight);
        writeDoubles(buf, solarStorage);
        writeDoubles(buf, solarOutput);
        writeDoubles(buf, batteryCapacity);
        writeDoubles(buf, moduleOutput);
        for (int tier : moduleOutputTier) buf.writeInt(tier);
    }

    private static void readDoubles(ByteBuf buf, double[] values) {
        for (int i = 0; i < LEVELS; i++) values[i] = buf.readDouble();
    }

    private static void writeDoubles(ByteBuf buf, double[] values) {
        for (double value : values) buf.writeDouble(value);
    }

    public static final class Handler implements IMessageHandler<PacketServerConfig, IMessage> {
        @Override
        public IMessage onMessage(final PacketServerConfig message, MessageContext context) {
            if (context.side == Side.CLIENT) {
                SuperSolarPanels.proxy.scheduleClientTask(new Runnable() {
                    @Override
                    public void run() {
                        ConfigSync.apply(message);
                    }
                });
            }
            return null;
        }
    }
}
