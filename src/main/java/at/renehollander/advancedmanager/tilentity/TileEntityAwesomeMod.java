package at.renehollander.advancedmanager.tilentity;

import at.renehollander.advancedmanager.network.DescriptionHandler;
import io.netty.buffer.Unpooled;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;

public abstract class TileEntityAwesomeMod extends TileEntity {

    @Override
    public Packet getDescriptionPacket() {
        PacketBuffer buf = new PacketBuffer(Unpooled.buffer());
        buf.writeBlockPos(pos);
        writeToPacket(buf);
        return new FMLProxyPacket(buf, DescriptionHandler.CHANNEL);
    }

    public void writeToPacket(PacketBuffer buf) {

    }

    public void readFromPacket(PacketBuffer buf) {

    }

}
