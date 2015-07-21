package at.renehollander.advancedmanager.proxy;

import at.renehollander.advancedmanager.Reference;
import at.renehollander.advancedmanager.block.BlockHandler;
import at.renehollander.advancedmanager.block.TileEntityHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit() {
        super.preInit();
    }

    @Override
    public void init() {
        super.init();
        // Blocks
        BlockHandler.blocks().forEach((block) -> {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(Reference.MODID + ":" + block.getName(), "inventory"));
        });

        // TileEntitySpecialRenderers
        TileEntityHandler.tileEntities().forEach((block) -> {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(Reference.MODID + ":" + block.getName(), "inventory"));
            if (block.hasTileEntitySpecialRenderer()) {
                ClientRegistry.bindTileEntitySpecialRenderer(block.getTileEntityClass(), block.getTileEntitySpecialRenderer());
            }
        });
    }

    @Override
    public void postInit() {
        super.postInit();
    }

    @Override
    public EntityPlayer getClientPlayer() {
        return Minecraft.getMinecraft().thePlayer;
    }

    @Override
    public boolean isServer() {
        return false;
    }

    @Override
    public boolean isClient() {
        return true;
    }
}
