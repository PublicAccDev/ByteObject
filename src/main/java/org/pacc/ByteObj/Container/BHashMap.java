package org.pacc.ByteObj.Container;

import org.pacc.ByteObj.ByteObject;
import org.pacc.ByteObj.CacheByteObj;
import org.pacc.ByteObj.Exception.BytesConstructorMissingException;
import org.pacc.ByteObj.Serializer.ContainerSerializer;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

public class BHashMap<Key extends ByteObject<?>, Value extends ByteObject<?>> extends CacheByteObj<HashMap<Key, Value>>
{

    private final Constructor<Key> keyConstructor;
    private final Constructor<Value> valueConstructor;

    public BHashMap(HashMap<Key, Value> map, Class<Key> keyClazz, Class<Value> valueClazz)
    {
        super(map);
        try
        {
            keyConstructor = keyClazz.getConstructor(byte[].class);
            valueConstructor = valueClazz.getConstructor(byte[].class);
        } catch (NoSuchMethodException e)
        {
            throw new BytesConstructorMissingException(keyClazz.getName() + " or " + valueClazz.getName() + " is missing constructor <init>([B)V and cannot be created using bytes data");
        }
    }

    public BHashMap(Class<Key> keyClazz, Class<Value> valueClazz)
    {
        super(new HashMap<>());
        try
        {
            keyConstructor = keyClazz.getConstructor(byte[].class);
            valueConstructor = valueClazz.getConstructor(byte[].class);
        } catch (NoSuchMethodException e)
        {
            throw new BytesConstructorMissingException(keyClazz.getName() + " or " + valueClazz.getName() + " is missing constructor <init>([B)V and cannot be created using bytes data");
        }
    }

    @Override
    public byte[] serialize(HashMap<Key, Value> map)
    {
        return ContainerSerializer.serialize(map);
    }

    @Override
    public HashMap<Key, Value> deserialize(byte[] objectBytesData)
    {
        return ContainerSerializer.deserializeHashMap(objectBytesData, keyConstructor, valueConstructor);
    }

    // Map-like API
    public Value put(Key key, Value value)
    {
        HashMap<Key, Value> map = getObject();
        Value r = map.put(key, value);
        setObject(map);
        return r;
    }

    public Value get(Key key)
    {
        return getObject().get(key);
    }

    public Value remove(Key key)
    {
        HashMap<Key, Value> map = getObject();
        Value r = map.remove(key);
        setObject(map);
        return r;
    }

    public boolean containsKey(Key key)
    {
        return getObject().containsKey(key);
    }

    public boolean containsValue(Value value)
    {
        return getObject().containsValue(value);
    }

    public void clear()
    {
        setObject(new HashMap<>());
    }

    public int size()
    {
        return getObject().size();
    }

    public boolean isEmpty()
    {
        return getObject().isEmpty();
    }

    public Set<Key> keySet()
    {
        return getObject().keySet();
    }

    public Collection<Value> values()
    {
        return getObject().values();
    }

    public Set<Map.Entry<Key, Value>> entrySet()
    {
        return getObject().entrySet();
    }

    public void forEach(BiConsumer<? super Key, ? super Value> action)
    {
        getObject().forEach(action);
    }

    public Value computeIfAbsent(Key key, Function<? super Key, ? extends Value> mappingFunction)
    {
        HashMap<Key, Value> map = getObject();
        Value r = map.computeIfAbsent(key, mappingFunction);
        setObject(map);
        return r;
    }

    public Value computeIfPresent(Key key, BiFunction<? super Key, ? super Value, ? extends Value> remappingFunction)
    {
        HashMap<Key, Value> map = getObject();
        Value r = map.computeIfPresent(key, remappingFunction);
        setObject(map);
        return r;
    }

    public Value compute(Key key, BiFunction<? super Key, ? super Value, ? extends Value> remappingFunction)
    {
        HashMap<Key, Value> map = getObject();
        Value r = map.compute(key, remappingFunction);
        setObject(map);
        return r;
    }

    public Value merge(Key key, Value value, BiFunction<? super Value, ? super Value, ? extends Value> remappingFunction)
    {
        HashMap<Key, Value> map = getObject();
        Value r = map.merge(key, value, remappingFunction);
        setObject(map);
        return r;
    }

    public Stream<Map.Entry<Key, Value>> stream()
    {
        return getObject().entrySet().stream();
    }

    public Stream<Map.Entry<Key, Value>> parallelStream()
    {
        return getObject().entrySet().parallelStream();
    }
}
