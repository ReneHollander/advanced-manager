package at.renehollander.advancedmanager.util;

import at.renehollander.advancedmanager.Reference;
import net.minecraftforge.fml.common.FMLLog;
import org.apache.logging.log4j.Level;

public class Log {

    private static final Level level = Level.ALL;

    public static void log(Level logLevel, Object object) {
        if (logLevel.isAtLeastAsSpecificAs(level)) {
            FMLLog.log(Reference.MOD_NAME, Level.INFO, String.valueOf(object));
        }
    }

    public static void all(Object object) {
        log(Level.ALL, object);
    }

    public static void debug(Object object) {
        log(Level.DEBUG, object);
    }

    public static void error(Object object) {
        log(Level.ERROR, object);
    }

    public static void error(Object object, Throwable t) {
        FMLLog.log(Reference.MOD_NAME, Level.ERROR, t, String.valueOf(object));
    }

    public static void fatal(Object object) {
        log(Level.FATAL, object);
    }

    public static void info(Object object) {
        log(Level.INFO, object);
    }

    public static void off(Object object) {
        log(Level.OFF, object);
    }

    public static void trace(Object object) {
        log(Level.TRACE, object);
    }

    public static void warn(Object object) {
        log(Level.WARN, object);
    }

}
