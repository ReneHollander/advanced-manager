package at.renehollander.advancedmanager.util;

import io.netty.buffer.Unpooled;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.PacketBuffer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AdvancedManagerNBTUtils {

    public static void setUUID(NBTTagCompound nbt, String name, UUID uuid) {
        PacketBuffer buf = new PacketBuffer(Unpooled.buffer(16));
        buf.writeUuid(uuid);
        nbt.setByteArray(name, buf.array());
        buf.release();
    }

    public static UUID getUUID(NBTTagCompound nbt, String name) {
        PacketBuffer buf = new PacketBuffer(Unpooled.wrappedBuffer(nbt.getByteArray(name)));
        UUID uuid = buf.readUuid();
        buf.release();
        return uuid;
    }

    public static <E extends Enum<E>, O> void writeEnumMap(NBTTagCompound nbt, String name, Class<E> enumClass, EnumMap<E, O> map) {
        E[] keyUniverse = enumClass.getEnumConstants();
        NBTTagList mapNbt = new NBTTagList();
        for (E key : keyUniverse) {
            NBTTagCompound val = new NBTTagCompound();
            val.setString("name", key.name());
            writeObject(val, "data", map.get(key));
        }
        nbt.setTag(name, mapNbt);
    }

    public static <E extends Enum<E>, O> EnumMap<E, O> readEnumMap(NBTTagCompound nbt, String name, Class<E> enumClass) {
        EnumMap<E, O> map = new EnumMap<E, O>(enumClass);
        readEnumMap(nbt, name, enumClass, map);
        return map;
    }

    public static <E extends Enum<E>, O> void readEnumMap(NBTTagCompound nbt, String name, Class<E> enumClass, EnumMap<E, O> map) {
        NBTTagList mapNbt = (NBTTagList) nbt.getTag(name);
        Map<String, E> enumMapping = createEnumNameMapping(enumClass);
        for (int i = 0; i < mapNbt.tagCount(); i++) {
            NBTTagCompound val = mapNbt.getCompoundTagAt(i);
            map.put(enumMapping.get(val.getString("name")), (O) readObject(val, "data"));
        }
    }

    public static void writeObject(NBTTagCompound nbt, String name, Object obj) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            oos.close();
            byte[] data = baos.toByteArray();
            nbt.setByteArray(name, data);
            baos.close();
        } catch (Exception e) {
            nbt.setByteArray(name, new byte[0]);
        }
    }

    public static Object readObject(NBTTagCompound nbt, String name) {
        try {
            byte[] data = nbt.getByteArray(name);
            if (data.length == 0) return null;
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

    private static <E extends Enum<E>> Map<String, E> createEnumNameMapping(Class<E> enumClass) {
        Map<String, E> map = new HashMap<String, E>();
        E[] enumConstants = enumClass.getEnumConstants();
        for (E e : enumConstants) {
            map.put(e.name(), e);
        }
        return map;
    }

}
