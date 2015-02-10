package at.renehollander.advancedmanager.tilentity.redstonecontroller;

import at.renehollander.advancedmanager.tilentity.TileEntityAwesomeMod;
import at.renehollander.advancedmanager.tilentity.redstonecontroller.scripting.JavascriptRunner;
import at.renehollander.advancedmanager.util.AdvancedManagerByteBufUtils;
import at.renehollander.advancedmanager.util.AdvancedManagerNBTUtils;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import net.minecraft.nbt.NBTTagCompound;

import java.util.UUID;

public class TileEntityRedstoneController extends TileEntityAwesomeMod {

    private RedstoneController redstoneController;

    private boolean firstUpdate;

    private RedstoneControllerProperties props;
    private JavascriptRunner runner;

    @SideOnly(Side.CLIENT)
    private GUI gui;

    public TileEntityRedstoneController() {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
        } else if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
        }

        this.firstUpdate = true;
        this.props = new RedstoneControllerProperties();
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            this.runner = new JavascriptRunner(this.props);
        }
    }

    @SideOnly(Side.CLIENT)
    public GUI getGui() {
        return gui;
    }

    @SideOnly(Side.CLIENT)
    public void setGui(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void updateEntity() {
        if (firstUpdate) {
            firstUpdate = false;
            if (this.getRedstoneController() == null) {
                this.redstoneController = RedstoneController.fromUUID(UUID.randomUUID(), this);
            }
            if (!worldObj.isRemote) {
                this.runner.start();
            }
        }
        if (props.isDirty()) {
            props.resetDirty();
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, this.getBlockType());
        }
        if (!worldObj.isRemote) {
            this.runner.tick();
        }
    }

    public RedstoneControllerProperties getProps() {
        return this.props;
    }

    public RedstoneController getRedstoneController() {
        return this.redstoneController;
    }

    public JavascriptRunner getRunner() {
        return this.runner;
    }

    @Override
    public void writeToPacket(ByteBuf buf) {
        AdvancedManagerByteBufUtils.writeUUID(buf, this.getRedstoneController().getUuid());
        props.writeToPacket(buf);
    }

    @Override
    public void readFromPacket(ByteBuf buf) {
        UUID uuid = AdvancedManagerByteBufUtils.readUUID(buf);
        if (this.getRedstoneController() == null) {
            this.redstoneController = RedstoneController.fromUUID(uuid, this);
        }
        props.readFromPacket(buf);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        AdvancedManagerNBTUtils.setUUID(tag, "uuid", this.getRedstoneController().getUuid());
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        UUID uuid = AdvancedManagerNBTUtils.getUUID(tag, "uuid");
        if (this.getRedstoneController() == null) {
            RedstoneController.fromUUID(uuid, this);
        }
    }

}
