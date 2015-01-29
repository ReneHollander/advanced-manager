package at.renehollander.advancedmanager.tilentity.redstonecontroller;

import java.util.UUID;

public class ClientRedstoneController extends RedstoneController {

    public ClientRedstoneController(UUID uuid, TileEntityRedstoneController tileEntity) {
        super(uuid, tileEntity);
    }

    public void setName(String name) {
        this.name = name;
        // TODO send new name to server
    }

    public void setMainScript(String mainScript) {
        this.name = name;
        // TODO send new main script to server
    }

}
