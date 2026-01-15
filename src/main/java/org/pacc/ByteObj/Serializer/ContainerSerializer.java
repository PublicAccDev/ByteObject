package org.pacc.ByteObj.Serializer;

import org.pacc.ByteObj.BaseByteObj;
import org.pacc.ByteObj.DirectByteObj;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.util.*;

public class ContainerSerializer
{

    public static <ByteObj extends BaseByteObj<?>> byte[] serialize(ByteObj[] objects)
    {
        int totalLength = Arrays.stream(objects)
                                .mapToInt(obj -> 4 + obj.getBytes().length)
                                .sum();

        ByteBuffer buffer = ByteBuffer.allocate(totalLength);
        Arrays.stream(objects).forEach(obj -> {
            buffer.putInt(obj.getBytes().length);
            buffer.put(obj.getBytes());
        });
        return buffer.array();
    }

    @SuppressWarnings("unchecked")
    public static <ByteObj extends DirectByteObj<?>> ByteObj[] deserializeArray(byte[] data, Class<ByteObj> clazz, Constructor<ByteObj> constructor)
    {
        List<ByteObj> list = (List<ByteObj>) deserializeCollection(data, constructor);
        return list.toArray((ByteObj[]) Array.newInstance(clazz, list.size()));
    }

    public static <ByteObj extends BaseByteObj<?>> byte[] serialize(List<ByteObj> list)
    {
        return serializeIterable(list);
    }

    public static <ByteObj extends DirectByteObj<?>> ArrayList<ByteObj> deserializeArrayList(byte[] data, Constructor<ByteObj> constructor)
    {
        return new ArrayList<>(deserializeCollection(data, constructor));
    }

    public static <ByteObj extends BaseByteObj<?>> byte[] serialize(Set<ByteObj> set)
    {
        return serializeIterable(set);
    }

    public static <ByteObj extends DirectByteObj<?>> HashSet<ByteObj> deserializeHashSet(byte[] data, Constructor<ByteObj> constructor)
    {
        return new HashSet<>(deserializeCollection(data, constructor));
    }

    public static <ByteObj extends BaseByteObj<?>> byte[] serialize(LinkedHashSet<ByteObj> set)
    {
        return serializeIterable(set);
    }

    public static <ByteObj extends DirectByteObj<?>> LinkedHashSet<ByteObj> deserializeLinkedHashSet(byte[] data, Constructor<ByteObj> constructor)
    {
        return new LinkedHashSet<>(deserializeCollection(data, constructor));
    }

    public static <Key extends BaseByteObj<?>, Value extends BaseByteObj<?>> byte[] serialize(Map<Key, Value> map)
    {
        int totalLength = map.entrySet().stream()
                             .mapToInt(entry -> 8 + entry.getKey().getBytes().length + entry.getValue().getBytes().length)
                             .sum();

        ByteBuffer buffer = ByteBuffer.allocate(totalLength);
        map.forEach((key, value) -> {
            buffer.putInt(key.getBytes().length);
            buffer.putInt(value.getBytes().length);
            buffer.put(key.getBytes());
            buffer.put(value.getBytes());
        });
        return buffer.array();
    }

    public static <Key extends DirectByteObj<?>, Value extends DirectByteObj<?>> HashMap<Key, Value> deserializeHashMap(byte[] data, Constructor<Key> keyConstructor, Constructor<Value> valueConstructor)
    {
        return new HashMap<>(deserializeMap(data, keyConstructor, valueConstructor));
    }

    public static <Key extends DirectByteObj<?>, Value extends DirectByteObj<?>> LinkedHashMap<Key, Value> deserializeLinkedHashMap(byte[] data, Constructor<Key> keyConstructor, Constructor<Value> valueConstructor)
    {
        return new LinkedHashMap<>(deserializeMap(data, keyConstructor, valueConstructor));
    }

    public static <ByteObj extends BaseByteObj<?>> byte[] serialize(ArrayDeque<ByteObj> deque) {
        return serializeIterable(deque);
    }

    public static <ByteObj extends DirectByteObj<?>> ArrayDeque<ByteObj> deserializeArrayDeque(byte[] data, Constructor<ByteObj> constructor) {
        Collection<ByteObj> collection = deserializeCollection(data, constructor);
        return new ArrayDeque<>(collection);
    }

    private static <ByteObj extends BaseByteObj<?>> byte[] serializeIterable(Iterable<ByteObj> iterable)
    {
        int totalLength = 0;
        for (ByteObj obj : iterable)
        {
            totalLength += 4 + obj.getBytes().length;
        }

        ByteBuffer buffer = ByteBuffer.allocate(totalLength);
        for (ByteObj obj : iterable)
        {
            buffer.putInt(obj.getBytes().length);
            buffer.put(obj.getBytes());
        }
        return buffer.array();
    }

    private static <ByteObj extends DirectByteObj<?>> Collection<ByteObj> deserializeCollection(byte[] data, Constructor<ByteObj> constructor)
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
            return list;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e)
        {
            throw new RuntimeException(e);
        }
    }

    private static <Key extends DirectByteObj<?>, Value extends DirectByteObj<?>> Map<Key, Value> deserializeMap(byte[] data, Constructor<Key> keyConstructor, Constructor<Value> valueConstructor)
    {
        try
        {
            int index = 0;
            Map<Key, Value> map = new LinkedHashMap<>();
            while (index < data.length)
            {
                int keyLength = ByteBuffer.wrap(data, index, 4).getInt();
                int valueLength = ByteBuffer.wrap(data, index + 4, 4).getInt();
                byte[] keySlice = new byte[keyLength];
                byte[] valueSlice = new byte[valueLength];
                System.arraycopy(data, index + 8, keySlice, 0, keyLength);
                System.arraycopy(data, index + 8 + keyLength, valueSlice, 0, valueLength);
                map.put(keyConstructor.newInstance((Object) keySlice),
                        valueConstructor.newInstance((Object) valueSlice));
                index += 8 + keyLength + valueLength;
            }
            return map;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e)
        {
            throw new RuntimeException(e);
        }
    }
}
