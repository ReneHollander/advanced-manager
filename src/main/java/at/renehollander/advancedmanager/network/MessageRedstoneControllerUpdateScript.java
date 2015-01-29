package at.renehollander.advancedmanager.network;

import at.renehollander.advancedmanager.tilentity.redstonecontroller.TileEntityRedstoneController;
import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class MessageRedstoneControllerUpdateScript extends MessageBase<MessageRedstoneControllerUpdateScript> {

    private int xcoord;
    private int ycoord;
    private int zcoord;
    private String script;

    public MessageRedstoneControllerUpdateScript() {
    }

    public MessageRedstoneControllerUpdateScript(int xcoord, int ycoord, int zcoord, String script) {
        this.xcoord = xcoord;
        this.ycoord = ycoord;
        this.zcoord = zcoord;
        this.script = script;
    }

    @Override
    public void handleClientSide(MessageRedstoneControllerUpdateScript message, EntityPlayer player) {
    }

    @Override
    public void handleServerSide(MessageRedstoneControllerUpdateScript message, EntityPlayer player) {
        TileEntityRedstoneController te = (TileEntityRedstoneController) player.worldObj.getTileEntity(message.xcoord, message.ycoord, message.zcoord);
        te.getRunner().getProps().setScript(message.script);
        te.getRunner().reload();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(xcoord);
        buf.writeInt(ycoord);
        buf.writeInt(zcoord);
        ByteBufUtils.writeUTF8String(buf, script);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.xcoord = buf.readInt();
        this.ycoord = buf.readInt();
        this.zcoord = buf.readInt();
        this.script = ByteBufUtils.readUTF8String(buf);
    }

}
