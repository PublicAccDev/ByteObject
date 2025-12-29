package org.pacc.ByteObj.Serializer;

import org.pacc.ByteObj.ByteObject;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.util.*;

public class ContainerSerializer
{

    public static <ByteObj extends ByteObject<?>> byte[] serialize(ByteObj[] objects)
    {
        int totalLength = 0;
        for (ByteObj obj : objects)
        {
            totalLength += 4 + obj.getBytes().length;
        }

        ByteBuffer buffer = ByteBuffer.allocate(totalLength);
        for (ByteObj obj : objects)
        {
            buffer.putInt(obj.getBytes().length);
            buffer.put(obj.getBytes());
        }
        return buffer.array();
    }

    @SuppressWarnings("unchecked")
    public static <ByteObj extends ByteObject<?>> ByteObj[] deserializeArray(byte[] data, Class<ByteObj> clazz, Constructor<ByteObj> constructor)
    {
        try
        {
            int index = 0;
            List<ByteObj> list = new ArrayList<>();
            while (index < data.length)
            {
                int length = ByteBuffer.wrap(data, index, 4).getInt();
                index += 4;
                byte[] slice = new byte[length];
                System.arraycopy(data, index, slice, 0, length);
                list.add(constructor.newInstance((Object) slice));
                index += length;
            }
            return list.toArray((ByteObj[]) Array.newInstance(clazz, list.size()));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static <ByteObj extends ByteObject<?>> byte[] serialize(List<ByteObj> list)
    {
        int totalLength = 0;
        for (ByteObj obj : list)
            totalLength += 4 + obj.getBytes().length;
        ByteBuffer buffer = ByteBuffer.allocate(totalLength);
        for (ByteObj obj : list)
        {
            buffer.putInt(obj.getBytes().length);
            buffer.put(obj.getBytes());
        }
        return buffer.array();
    }

    public static <ByteObj extends ByteObject<?>> ArrayList<ByteObj> deserializeArrayList(byte[] data, Constructor<ByteObj> constructor)
    {
        try
        {
            int index = 0;
            ArrayList<ByteObj> list = new ArrayList<>();
            while (index < data.length)
            {
                int length = ByteBuffer.wrap(data, index, 4).getInt();
                index += 4;
                byte[] slice = new byte[length];
                System.arraycopy(data, index, slice, 0, length);
                list.add(constructor.newInstance((Object) slice));
                index += length;
            }
            return list;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static <ByteObj extends ByteObject<?>> byte[] serialize(HashSet<ByteObj> set)
    {
        int totalLength = 0;
        for (ByteObj obj : set)
            totalLength += 4 + obj.getBytes().length;
        ByteBuffer buffer = ByteBuffer.allocate(totalLength);
        for (ByteObj obj : set)
        {
            buffer.putInt(obj.getBytes().length);
            buffer.put(obj.getBytes());
        }
        return buffer.array();
    }

    public static <ByteObj extends ByteObject<?>> HashSet<ByteObj> deserializeHashSet(byte[] data, Constructor<ByteObj> constructor)
    {
        try
        {
            int index = 0;
            HashSet<ByteObj> set = new HashSet<>();
            while (index < data.length)
            {
                int length = ByteBuffer.wrap(data, index, 4).getInt();
                index += 4;
                byte[] slice = new byte[length];
                System.arraycopy(data, index, slice, 0, length);
                set.add(constructor.newInstance((Object) slice));
                index += length;
            }
            return set;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static <Key extends ByteObject<?>, Value extends ByteObject<?>> byte[] serialize(HashMap<Key, Value> map)
    {
        int totalLength = 0;
        for (Map.Entry<Key, Value> entry : map.entrySet())
            totalLength += 8 + entry.getKey().getBytes().length + entry.getValue().getBytes().length;
        ByteBuffer buffer = ByteBuffer.allocate(totalLength);
        for (Map.Entry<Key, Value> entry : map.entrySet())
        {
            buffer.putInt(entry.getKey().getBytes().length);
            buffer.putInt(entry.getValue().getBytes().length);
            buffer.put(entry.getKey().getBytes());
            buffer.put(entry.getValue().getBytes());
        }
        return buffer.array();
    }

    public static <Key extends ByteObject<?>, Value extends ByteObject<?>> HashMap<Key, Value> deserializeHashMap(byte[] data, Constructor<Key> keyConstructor, Constructor<Value> valueConstructor)
    {
        try
        {
            int index = 0;
            HashMap<Key, Value> map = new HashMap<>();
            while (index < data.length)
            {
                int keyLength = ByteBuffer.wrap(data, index, 4).getInt();
                int valueLength = ByteBuffer.wrap(data, index + 4, 4).getInt();
                byte[] keySlice = new byte[keyLength];
                byte[] valueSlice = new byte[valueLength];
                System.arraycopy(data, index + 8, keySlice, 0, keyLength);
                System.arraycopy(data, index + 8 + keyLength, valueSlice, 0, valueLength);
                map.put(keyConstructor.newInstance((Object) keySlice), valueConstructor.newInstance((Object) valueSlice));
                index += 8 + keyLength + valueLength;
            }
            return map;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static <ByteObj extends ByteObject<?>> byte[] serialize(LinkedHashSet<ByteObj> set)
    {
        int totalLength = 0;
        for (ByteObj obj : set)
            totalLength += 4 + obj.getBytes().length;
        ByteBuffer buffer = ByteBuffer.allocate(totalLength);
        for (ByteObj obj : set)
        {
            buffer.putInt(obj.getBytes().length);
            buffer.put(obj.getBytes());
        }
        return buffer.array();
    }

    public static <ByteObj extends ByteObject<?>> LinkedHashSet<ByteObj> deserializeLinkedHashSet(byte[] data, Constructor<ByteObj> constructor)
    {
        try
        {
            int index = 0;
            LinkedHashSet<ByteObj> set = new LinkedHashSet<>();
            while (index < data.length)
            {
                int length = ByteBuffer.wrap(data, index, 4).getInt();
                index += 4;
                byte[] slice = new byte[length];
                System.arraycopy(data, index, slice, 0, length);
                set.add(constructor.newInstance((Object) slice));
                index += length;
            }
            return set;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static <Key extends ByteObject<?>, Value extends ByteObject<?>> byte[] serialize(LinkedHashMap<Key, Value> map)
    {
        int totalLength = 0;
        for (Map.Entry<Key, Value> entry : map.entrySet())
            totalLength += 8 + entry.getKey().getBytes().length + entry.getValue().getBytes().length;
        ByteBuffer buffer = ByteBuffer.allocate(totalLength);
        for (Map.Entry<Key, Value> entry : map.entrySet())
        {
            buffer.putInt(entry.getKey().getBytes().length);
            buffer.putInt(entry.getValue().getBytes().length);
            buffer.put(entry.getKey().getBytes());
            buffer.put(entry.getValue().getBytes());
        }
        return buffer.array();
    }

    public static <Key extends ByteObject<?>, Value extends ByteObject<?>> LinkedHashMap<Key, Value> deserializeLinkedHashMap(byte[] data, Constructor<Key> keyConstructor, Constructor<Value> valueConstructor)
    {
        try
        {
            int index = 0;
            LinkedHashMap<Key, Value> map = new LinkedHashMap<>();
            while (index < data.length)
            {
                int keyLength = ByteBuffer.wrap(data, index, 4).getInt();
                int valueLength = ByteBuffer.wrap(data, index + 4, 4).getInt();
                byte[] keySlice = new byte[keyLength];
                byte[] valueSlice = new byte[valueLength];
                System.arraycopy(data, index + 8, keySlice, 0, keyLength);
                System.arraycopy(data, index + 8 + keyLength, valueSlice, 0, valueLength);
                map.put(keyConstructor.newInstance((Object) keySlice), valueConstructor.newInstance((Object) valueSlice));
                index += 8 + keyLength + valueLength;
            }
            return map;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e)
        {
            throw new RuntimeException(e);
        }
    }
}
