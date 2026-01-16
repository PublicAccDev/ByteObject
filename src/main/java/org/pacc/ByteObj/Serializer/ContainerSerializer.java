package org.pacc.ByteObj.Serializer;

import org.pacc.ByteObj.DirectByteObj;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.util.*;

public class ContainerSerializer
{

    public static <ByteObj extends DirectByteObj<?>> byte[] serialize(ByteObj[] objects, Constructor<ByteObj> constructor)
    {
        return serializeArrayInternal(objects, constructor);
    }

    public static <ByteObj extends DirectByteObj<?>> byte[] serialize(Collection<ByteObj> list, Constructor<ByteObj> constructor)
    {
        return serializeIterableInternal(list, constructor);
    }

    public static <ByteObj extends DirectByteObj<?>> byte[] serialize(LinkedList<ByteObj> list, Constructor<ByteObj> constructor)
    {
        return serializeIterableInternal(list, constructor);
    }

    public static <Key extends DirectByteObj<?>, Value extends DirectByteObj<?>> byte[] serialize(Map<Key, Value> map, Constructor<Key> keyConstructor, Constructor<Value> valueConstructor)
    {
        return serializeMapInternal(map, keyConstructor, valueConstructor);
    }

    public static <ByteObj extends DirectByteObj<?>> byte[] serializeArrayInternal(ByteObj[] objects)
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

    public static <ByteObj extends DirectByteObj<?>> byte[] serializeArrayInternal(ByteObj[] objects, Constructor<ByteObj> constructor)
    {
        try
        {
            ByteObj first = objects.length != 0 ? objects[0] : null;
            return addClassHeader(serializeArrayInternal(objects), constructor == null ? first == null ? null : first.getClass().getConstructor(byte[].class) : constructor);
        } catch (NoSuchMethodException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static <Key extends DirectByteObj<?>, Value extends DirectByteObj<?>> byte[] serializeMapInternal(Map<Key, Value> map)
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

    public static <Key extends DirectByteObj<?>, Value extends DirectByteObj<?>> byte[] serializeMapInternal(Map<Key, Value> map, Constructor<Key> keyConstructor, Constructor<Value> valueConstructor)
    {
        try
        {
            Iterator<Map.Entry<Key, Value>> it = map.entrySet().iterator();
            Map.Entry<Key, Value> first = it.hasNext() ? it.next() : null;
            return addClassHeader(serializeMapInternal(map),
                                  keyConstructor == null ? first == null ? null : first.getKey().getClass().getConstructor(byte[].class) : keyConstructor,
                                  valueConstructor == null ? first == null ? null : first.getValue().getClass().getConstructor(byte[].class) : valueConstructor);
        } catch (NoSuchMethodException e)
        {
            throw new RuntimeException(e);
        }
    }

    private static <ByteObj extends DirectByteObj<?>> byte[] serializeIterableInternal(Iterable<ByteObj> iterable)
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

    private static <ByteObj extends DirectByteObj<?>> byte[] serializeIterableInternal(Iterable<ByteObj> iterable, Constructor<ByteObj> constructor)
    {
        try
        {
            ByteObj first = iterable.iterator().hasNext() ? iterable.iterator().next() : null;
            return addClassHeader(serializeIterableInternal(iterable), constructor == null ? first == null ? null : first.getClass().getConstructor(byte[].class) : constructor);
        } catch (NoSuchMethodException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static <Key extends DirectByteObj<?>, Value extends DirectByteObj<?>> DeserializeResult deserializeMap(byte[] data, Constructor<Key> keyConstructor, Constructor<Value> valueConstructor)
    {
        return deserializeMapInternal(data, keyConstructor, valueConstructor);
    }

    public static <ByteObj extends DirectByteObj<?>> DeserializeResult deserializeIterable(byte[] data, Constructor<ByteObj> constructor)
    {
        return deserializeCollectionInternal(data, constructor);
    }

    @SuppressWarnings("unchecked")
    public static <ByteObj extends DirectByteObj<?>> DeserializeResult deserializeArray(byte[] data, Constructor<ByteObj> constructor)
    {
        if(constructor == null)
        {
            constructor = (Constructor<ByteObj>) getSingleConstructor(data);
        }
        DeserializeResult result = deserializeCollectionInternal(data, constructor);
        List<ByteObj> list = (List<ByteObj>)result.object();
        return new DeserializeResult(list.toArray((ByteObj[]) Array.newInstance(constructor.getDeclaringClass(), list.size())), new Constructor[]{constructor});
    }

    @SuppressWarnings("unchecked")
    private static <ByteObj extends DirectByteObj<?>> DeserializeResult deserializeCollectionInternal(byte[] data, Constructor<ByteObj> constructor)
    {
        try
        {
            Class<?> oneClass = getSingleClass(data);
            Constructor<ByteObj> byteObjConstructor = constructor == null ? (Constructor<ByteObj>) getSingleConstructor(oneClass) : constructor;
            data = removeClassHeader(data);
            int index = 0;
            List<ByteObj> list = new ArrayList<>();
            while (index < data.length)
            {
                int length = ByteBuffer.wrap(data, index, 4).getInt();
                index += 4;
                byte[] slice = new byte[length];
                System.arraycopy(data, index, slice, 0, length);
                list.add(byteObjConstructor.newInstance((Object) slice));
                index += length;
            }
            return new DeserializeResult(list, new Constructor[]{byteObjConstructor});
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e)
        {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private static <Key extends DirectByteObj<?>, Value extends DirectByteObj<?>> DeserializeResult deserializeMapInternal(byte[] data, Constructor<Key> keyConstructor, Constructor<Value> valueConstructor)
    {
        try
        {
            Class<?>[] twoClass = getTwoClass(data);
            Constructor<?>[] constructors = new Constructor[0];
            if(keyConstructor == null || valueConstructor == null)
            {
                constructors = getTwoConstructors(twoClass);
            }
            Constructor<Key> keyByteObjConstructor = keyConstructor == null ? (Constructor<Key>) constructors[0] : keyConstructor;
            Constructor<Value> valueByteObjConstructor = valueConstructor == null ? (Constructor<Value>) constructors[1] : valueConstructor;
            data = removeClassHeader(data);
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
                map.put(keyByteObjConstructor.newInstance((Object) keySlice),
                        valueByteObjConstructor.newInstance((Object) valueSlice));
                index += 8 + keyLength + valueLength;
            }
            return new DeserializeResult(map, new Constructor[]{keyByteObjConstructor, valueByteObjConstructor});
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e)
        {
            throw new RuntimeException(e);
        }
    }

    private static Class<?> getSingleClass(byte[] bytes)
    {
        try
        {
            byte[] slice = new byte[4];
            System.arraycopy(bytes, 0, slice, 0, 4);
            int length = ByteBuffer.wrap(slice).getInt();
            byte[] nameSlice = new byte[length];
            System.arraycopy(bytes, 4, nameSlice, 0, length);

            return Class.forName(BasicDataSerializer.deserializeString(nameSlice));
        } catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }

    private static Class<?>[] getTwoClass(byte[] bytes)
    {
        try
        {
            byte[] kL = new byte[4];
            System.arraycopy(bytes, 0, kL, 0, 4);
            int kLength = ByteBuffer.wrap(kL).getInt();
            byte[] kName = new byte[kLength];
            System.arraycopy(bytes, 4, kName, 0, kLength);
            Class<?> keyClass = Class.forName(BasicDataSerializer.deserializeString(kName));

            byte[] vL = new byte[4];
            System.arraycopy(bytes, kLength+4, vL, 0, 4);
            int vLength = ByteBuffer.wrap(vL).getInt();
            byte[] vName = new byte[vLength];
            System.arraycopy(bytes, kLength+8, vName, 0, vLength);
            Class<?> valueClass = Class.forName(BasicDataSerializer.deserializeString(vName));

            return new Class[]{keyClass, valueClass};
        } catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }

    private static Constructor<?>[] getTwoConstructors(byte[] bytes)
    {
        Class<?>[] classes = getTwoClass(bytes);
        try
        {
            return new Constructor[]{classes[0].getConstructor(byte[].class), classes[1].getConstructor(byte[].class)};
        } catch (NoSuchMethodException e)
        {
            throw new RuntimeException(e);
        }
    }

    private static Constructor<?>[] getTwoConstructors(Class<?>[] clazz)
    {
        try
        {
            return new Constructor[]{clazz[0].getConstructor(byte[].class), clazz[1].getConstructor(byte[].class)};
        } catch (NoSuchMethodException e)
        {
            throw new RuntimeException(e);
        }
    }

    private static Constructor<?> getSingleConstructor(byte[] bytes)
    {
        try
        {
            return getSingleClass(bytes).getConstructor(byte[].class);
        } catch (NoSuchMethodException e)
        {
            throw new RuntimeException(e);
        }
    }

    private static Constructor<?> getSingleConstructor(Class<?> clazz)
    {
        try
        {
            return clazz.getConstructor(byte[].class);
        } catch (NoSuchMethodException e)
        {
            throw new RuntimeException(e);
        }
    }

    private static byte[] removeClassHeader(byte[] bytes)
    {
        byte[] slice = new byte[4];
        System.arraycopy(bytes, 0, slice, 0, 4);
        int length = ByteBuffer.wrap(slice).getInt();
        byte[] result = new byte[bytes.length-length-4];
        System.arraycopy(bytes, length+4, result, 0, bytes.length-length-4);
        return result;
    }

    private static byte[] addClassHeader(byte[] bytes, Constructor<?> c)
    {
        try
        {
            if(c == null)
            {
                c = Object.class.getConstructor();
            }
        } catch (NoSuchMethodException e)
        {
            throw new RuntimeException(e);
        }
        byte[] className = BasicDataSerializer.serialize(c.getDeclaringClass().getName());
        ByteBuffer buffer = ByteBuffer.allocate(className.length+4+bytes.length);
        buffer.putInt(className.length);
        buffer.put(className);
        buffer.put(bytes);
        return buffer.array();
    }

    private static byte[] addClassHeader(byte[] bytes, Constructor<?> c1, Constructor<?> c2)
    {
        try
        {
            if(c1 == null)
            {
                c1 = Object.class.getConstructor();
            }
            if(c2 == null)
            {
                c2 = Object.class.getConstructor();
            }
        } catch (NoSuchMethodException e)
        {
            throw new RuntimeException(e);
        }
        byte[] className1 = BasicDataSerializer.serialize(c1.getDeclaringClass().getName());
        byte[] className2 = BasicDataSerializer.serialize(c2.getDeclaringClass().getName());
        ByteBuffer buffer = ByteBuffer.allocate(className1.length+className2.length+8+bytes.length);
        buffer.putInt(className1.length);
        buffer.put(className1);
        buffer.putInt(className2.length);
        buffer.put(className2);
        buffer.put(bytes);
        return buffer.array();
    }

}
