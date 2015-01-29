package at.renehollander.advancedmanager.tilentity.redstonecontroller;

import at.renehollander.advancedmanager.tilentity.redstonecontroller.TileEntityRedstoneController;

import java.util.UUID;

public abstract class RedstoneController {

    private final UUID uuid;
    private final TileEntityRedstoneController tileEntity;
    protected String name;
    protected String mainScript;

    public RedstoneController(UUID uuid, TileEntityRedstoneController tileEntity) {
        this.uuid = uuid;
        this.tileEntity = tileEntity;
        this.name = this.uuid.toString();
        this.mainScript = null;
    }

    public UUID getUuid() {
        return uuid;
    }

    public TileEntityRedstoneController getTileEntity() {
        return tileEntity;
    }

    public abstract void setName(String name);

    public String getName() {
        return name;
    }

    public abstract void setMainScript(String mainScript);

    public String getMainScript() {
        return mainScript;
    }
}
