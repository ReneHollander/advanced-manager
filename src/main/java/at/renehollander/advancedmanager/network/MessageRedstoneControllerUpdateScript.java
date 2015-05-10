package at.renehollander.advancedmanager.network;

import at.renehollander.advancedmanager.tilentity.redstonecontroller.TileEntityRedstoneController;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;

public class MessageRedstoneControllerUpdateScript extends MessageBase<MessageRedstoneControllerUpdateScript> {

    private BlockPos pos;
    private String script;

    public MessageRedstoneControllerUpdateScript() {
    }

    public MessageRedstoneControllerUpdateScript(BlockPos pos, String script) {
        this.pos = pos;
        this.script = script;
    }

    @Override
    public void handleClientSide(MessageRedstoneControllerUpdateScript message, EntityPlayer player) {
    }

    @Override
    public void handleServerSide(MessageRedstoneControllerUpdateScript message, EntityPlayer player) {
        TileEntityRedstoneController te = (TileEntityRedstoneController) player.worldObj.getTileEntity(message.pos);
        te.getRunner().getProps().setScript(message.script);
        te.getRunner().reload();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(pos.getX());
        buf.writeInt(pos.getY());
        buf.writeInt(pos.getZ());
        ByteBufUtils.writeUTF8String(buf, script);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
        this.script = ByteBufUtils.readUTF8String(buf);
    }

}
