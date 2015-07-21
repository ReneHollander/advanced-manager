package at.renehollander.advancedmanager.network;

import at.renehollander.advancedmanager.AdvancedManager;
import at.renehollander.advancedmanager.Reference;
import at.renehollander.advancedmanager.tilentity.base.TileEntityAdvancedManager;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;

@ChannelHandler.Sharable
public class DescriptionHandler extends SimpleChannelInboundHandler<FMLProxyPacket> {
    public static final String CHANNEL = Reference.MODID + "Description";

    static {
        NetworkRegistry.INSTANCE.newChannel(CHANNEL, new DescriptionHandler());
    }

    public static void preInit() {
        //not actually doing anything here, apart from loading the class. If the channel registry goes in here, Netty will throw a duplicate
        //channel error.
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FMLProxyPacket msg) throws Exception {
        PacketBuffer buf = new PacketBuffer(msg.payload());
        BlockPos pos = buf.readBlockPos();
        TileEntity te = AdvancedManager.proxy.getClientPlayer().worldObj.getTileEntity(pos);
        if (te instanceof TileEntityAdvancedManager) {
            ((TileEntityAdvancedManager) te).readFromPacket(new PacketBuffer(buf));
        }
    }

}
