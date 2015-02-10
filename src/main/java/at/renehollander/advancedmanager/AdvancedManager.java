package at.renehollander.advancedmanager;

import at.renehollander.advancedmanager.init.ModBlocks;
import at.renehollander.advancedmanager.init.ModTileEntities;
import at.renehollander.advancedmanager.network.DescriptionHandler;
import at.renehollander.advancedmanager.network.NetworkHandler;
import at.renehollander.advancedmanager.proxy.CommonProxy;
import at.renehollander.advancedmanager.util.Log;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;

@Mod(modid = Reference.MODID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class AdvancedManager {

    @Mod.Instance(Reference.MODID)
    public static AdvancedManager instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        for (ScriptEngineFactory sef : scriptEngineManager.getEngineFactories()) {
            System.out.println(sef.getEngineName() + ": " + sef.getLanguageName());
        }

        ModBlocks.init();
        ModTileEntities.init();
        proxy.preInit();
        NetworkHandler.init();
        DescriptionHandler.init();
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
