package org.pacc.ByteObj.Container;

import org.pacc.ByteObj.DirectByteObj;
import org.pacc.ByteObj.Exception.BytesConstructorMissingException;
import org.pacc.ByteObj.FastByteObj;
import org.pacc.ByteObj.Serializer.ContainerSerializer;
import org.pacc.ByteObj.Serializer.DeserializeResult;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

public class BHashMap<Key extends DirectByteObj<?>, Value extends DirectByteObj<?>> extends DirectByteObj<HashMap<Key, Value>>
{

    private Constructor<Key> keyConstructor;
    private Constructor<Value> valueConstructor;

    public BHashMap()
    {
        super(new HashMap<>());
        initConstructor(null, null);
    }

    public BHashMap(HashMap<Key, Value> map)
    {
        super(map);
        initConstructor(null, null);
    }

    public BHashMap(Map<Key, Value> map)
    {
        super(new HashMap<>(map));
        initConstructor(null, null);
    }

    public BHashMap(int initialCapacity)
    {
        super(new HashMap<>(initialCapacity));
        initConstructor(null, null);
    }

    public BHashMap(int initialCapacity, float loadFactor)
    {
        super(new HashMap<>(initialCapacity, loadFactor));
        initConstructor(null, null);
    }

    public BHashMap(HashMap<Key, Value> map, Class<Key> keyClass, Class<Value> valueClass)
    {
        super(map);
        initConstructor(keyClass, valueClass);
    }

    public BHashMap(Map<Key, Value> map, Class<Key> keyClass, Class<Value> valueClass)
    {
        super(new HashMap<>(map));
        initConstructor(keyClass, valueClass);
    }

    public BHashMap(int initialCapacity, Class<Key> keyClass, Class<Value> valueClass)
    {
        super(new HashMap<>(initialCapacity));
        initConstructor(keyClass, valueClass);
    }

    public BHashMap(int initialCapacity, float loadFactor, Class<Key> keyClass, Class<Value> valueClass)
    {
        super(new HashMap<>(initialCapacity, loadFactor));
        initConstructor(keyClass, valueClass);
    }

    public BHashMap(Class<Key> keyClass, Class<Value> valueClass)
    {
        super(new HashMap<>());
        initConstructor(keyClass, valueClass);
    }

    public BHashMap(byte[] objectBytesData)
    {
        super(objectBytesData);
        initConstructor(null, null);
    }

    private void initConstructor(Class<Key> keyClass, Class<Value> valueClass)
    {
        try
        {
            this.keyConstructor = keyClass == null ? null : keyClass.getConstructor(byte[].class);
            this.valueConstructor = valueClass == null ? null : valueClass.getConstructor(byte[].class);
        } catch (NoSuchMethodException e)
        {
            throw new BytesConstructorMissingException(keyClass, valueClass);
        }
    }

    @Override
    public byte[] serialize(HashMap<Key, Value> object)
    {
        return ContainerSerializer.serialize(object, this.keyConstructor, this.valueConstructor);
    }

    @SuppressWarnings("unchecked")
    @Override
    public HashMap<Key, Value> deserialize(byte[] objectBytesData)
    {
        DeserializeResult result = ContainerSerializer.deserializeMap(objectBytesData, this.keyConstructor, this.valueConstructor);
        this.keyConstructor = (Constructor<Key>) result.constructor()[0];
        this.valueConstructor = (Constructor<Value>) result.constructor()[1];
        return (HashMap<Key, Value>) result.object();
    }

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
