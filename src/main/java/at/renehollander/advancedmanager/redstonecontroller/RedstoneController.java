package at.renehollander.advancedmanager.redstonecontroller;

import at.renehollander.advancedmanager.tilentity.redstonecontroller.TileEntityRedstoneController;

import java.util.UUID;

public class RedstoneController {

    private UUID uuid;
    private TileEntityRedstoneController tileEntity;

    private String name;

    public RedstoneController(UUID uuid, TileEntityRedstoneController tileEntity) {
        this.uuid = uuid;
        this.tileEntity = tileEntity;

        this.name = null;
    }

    public static RedstoneController newInstance(TileEntityRedstoneController tileEntityRedstoneController) {
        return new RedstoneController(UUID.randomUUID(), tileEntityRedstoneController);
    }

}
