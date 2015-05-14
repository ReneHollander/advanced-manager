package at.renehollander.advancedmanager.tilentity.redstonecontroller;

import at.renehollander.advancedmanager.AdvancedManager;
import at.renehollander.advancedmanager.tilentity.base.TileEntityAdvancedManager;
import at.renehollander.advancedmanager.tilentity.redstonecontroller.scripting.JavascriptRunner;
import at.renehollander.advancedmanager.util.AdvancedManagerNBTUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.UUID;

public class TileEntityRedstoneController extends TileEntityAdvancedManager implements IUpdatePlayerListBox {

    private RedstoneController redstoneController;

    private RedstoneControllerProperties props;
    private JavascriptRunner runner;

    @SideOnly(Side.CLIENT)
    private GUI gui;

    public TileEntityRedstoneController() {
        this.redstoneController = RedstoneController.fromUUID(UUID.randomUUID(), this);
        this.props = new RedstoneControllerProperties();
    }

    @SideOnly(Side.CLIENT)
    public GUI getGui() {
        return gui;
    }

    @SideOnly(Side.CLIENT)
    public void setGui(GUI gui) {
        this.gui = gui;
    }

    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return (oldState.getBlock() != newSate.getBlock());
    }

    public void update() {
        if (props.isDirty()) {
            props.resetDirty();
            worldObj.markBlockForUpdate(pos);
            worldObj.notifyBlockOfStateChange(pos, this.getBlockType());
        }
        if (!worldObj.isRemote) {
            this.runner.tick();
        }
    }
/*
    @Override
    public void firstTick() {
        if (!worldObj.isRemote) {
            this.runner = new JavascriptRunner(this.props);
            this.runner.start();
        }
    }
    */

    @Override
    public void onChunkUnload() {
        super.onChunkUnload();
        // TODO stop everything
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
    public void writeToPacket(PacketBuffer buf) {
        buf.writeUuid(this.getRedstoneController().getUuid());
        props.writeToPacket(buf);
    }

    @Override
    public void readFromPacket(PacketBuffer buf) {
        UUID uuid = buf.readUuid();
        if (!this.getRedstoneController().getUuid().equals(uuid)) {
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
        if (!this.getRedstoneController().getUuid().equals(uuid)) {
            this.redstoneController = RedstoneController.fromUUID(uuid, this);
        }
    }
}
