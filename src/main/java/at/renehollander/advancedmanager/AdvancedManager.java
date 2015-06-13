package at.renehollander.advancedmanager;

import at.renehollander.advancedmanager.event.BlockEventHook;
import at.renehollander.advancedmanager.init.ModBlocks;
import at.renehollander.advancedmanager.init.ModTileEntities;
import at.renehollander.advancedmanager.network.DescriptionHandler;
import at.renehollander.advancedmanager.network.NetworkHandler;
import at.renehollander.advancedmanager.proxy.CommonProxy;
import at.renehollander.advancedmanager.util.Log;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class AdvancedManager {

    @Mod.Instance(Reference.MODID)
    public static AdvancedManager instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ModBlocks.init();
        ModTileEntities.init();
        proxy.preInit();
        NetworkHandler.init();
        DescriptionHandler.init();
        MinecraftForge.EVENT_BUS.register(new BlockEventHook());
        Log.info("Pre Initialization Complete!");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
        Log.info("Initialization Complete!");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit();
        Log.info("Post Initialization Complete!");
    }

    public static AdvancedManager instance() {
        return instance;
    }

    public static CommonProxy proxy() {
        return proxy;
    }
}
