package at.renehollander.advancedmanager.tilentity.redstonecontroller;

import at.renehollander.advancedmanager.tilentity.TileEntityAwesomeMod;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityRedstoneController extends TileEntityAwesomeMod {

    private boolean firstUpdate;

    private RedstoneControllerProperties props;
    private JavascriptRunner runner;

    @SideOnly(Side.CLIENT)
    private GUI gui;

    public TileEntityRedstoneController() {

        this.firstUpdate = true;
        this.props = new RedstoneControllerProperties();
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            doServerStuff();
        } else if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
            doClientStuff();
        }
    }

    private void doClientStuff() {

    }

    private void doServerStuff() {
        this.runner = new JavascriptRunner(props);
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

    public JavascriptRunner getRunner() {
        return this.runner;
    }

    @Override
    public void writeToPacket(ByteBuf buf) {
        props.writeToPacket(buf);
    }

    @Override
    public void readFromPacket(ByteBuf buf) {
        props.readFromPacket(buf);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        NBTTagCompound propsTag = new NBTTagCompound();
        this.getProps().writeToNBT(propsTag);
        tag.setTag("props", propsTag);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        this.getProps().readFromNBT(tag.getCompoundTag("props"));
    }
}
