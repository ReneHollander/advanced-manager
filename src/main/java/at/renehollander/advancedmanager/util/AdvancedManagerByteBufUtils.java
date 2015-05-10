package at.renehollander.advancedmanager.util;

import io.netty.buffer.ByteBuf;

import java.io.*;
import java.util.EnumMap;

public class AdvancedManagerByteBufUtils {

    public static <E extends Enum<E>, O> void writeEnumMap(ByteBuf buf, Class<E> enumClass, EnumMap<E, O> map) {
        E[] enumConstants = enumClass.getEnumConstants();
        for (E key : enumConstants) {
            writeObject(buf, map.get(key));
        }
    }

    public static <E extends Enum<E>, O> EnumMap<E, O> readEnumMap(ByteBuf buf, Class<E> enumClass) {
        EnumMap<E, O> map = new EnumMap<E, O>(enumClass);
        readEnumMap(buf, enumClass, map);
        return map;
    }

    public static <E extends Enum<E>, O> void readEnumMap(ByteBuf buf, Class<E> enumClass, EnumMap<E, O> map) {
        E[] enumConstants = enumClass.getEnumConstants();
        for (E key : enumConstants) {
            map.put(key, (O) readObject(buf));
        }
    }

    public static void writeObject(ByteBuf buf, Object obj) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            oos.close();
            byte[] data = baos.toByteArray();
            buf.writeInt(data.length);
            buf.writeBytes(data);
            baos.close();
        } catch (Exception e) {
            buf.writeInt(-1);
        }
    }

    public static Object readObject(ByteBuf buf) {
        try {
            int size = buf.readInt();
            if (size == -1) return null;
            byte[] data = new byte[size];
            ByteArrayInputStream bais = new ByteArrayInputStream(data);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Object obj = ois.readObject();
            ois.close();
            bais.close();
            return obj;
        } catch (Exception e) {
            return null;
        }
    }

}
