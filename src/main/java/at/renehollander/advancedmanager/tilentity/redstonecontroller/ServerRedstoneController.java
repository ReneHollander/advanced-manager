package at.renehollander.advancedmanager.tilentity.redstonecontroller;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.UUID;

public class ServerRedstoneController extends RedstoneController {

    public ServerRedstoneController(UUID uuid, TileEntityRedstoneController tileEntity) {
        super(uuid, tileEntity);
    }

    public void setName(String name) {
        this.name = name;
        // TODO write new name to disk
    }

    public void setMainScript(String mainScript) {
        this.name = name;
        // TODO write new main script to disk
    }

}
