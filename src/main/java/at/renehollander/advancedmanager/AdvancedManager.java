package at.renehollander.advancedmanager;

import at.renehollander.advancedmanager.proxy.CommonProxy;
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
        proxy().preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy().init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy().postInit();
    }

    public static AdvancedManager instance() {
        return instance;
    }

    public static CommonProxy proxy() {
        return proxy;
    }
}
