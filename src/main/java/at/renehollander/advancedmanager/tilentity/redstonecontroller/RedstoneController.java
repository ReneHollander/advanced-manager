package at.renehollander.advancedmanager.tilentity.redstonecontroller;

import at.renehollander.advancedmanager.AdvancedManager;

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

    public static RedstoneController fromUUID(UUID uuid, TileEntityRedstoneController te) {
        if (AdvancedManager.proxy().isServer()) {
            return new ServerRedstoneController(uuid, te);
        } else if (AdvancedManager.proxy().isClient()) {
            return new ClientRedstoneController(uuid, te);
        } else {
            throw new IllegalStateException("This can't happen...");
        }
    }

}
