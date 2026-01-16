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

public class BLinkedHashMap<Key extends DirectByteObj<?>, Value extends DirectByteObj<?>> extends DirectByteObj<LinkedHashMap<Key, Value>>
{

    private Constructor<Key> keyConstructor;
    private Constructor<Value> valueConstructor;

    public BLinkedHashMap()
    {
        super(new LinkedHashMap<>());
        initConstructor(null, null);
    }

    public BLinkedHashMap(LinkedHashMap<Key, Value> map)
    {
        super(map);
        initConstructor(null, null);
    }

    public BLinkedHashMap(Map<Key, Value> map)
    {
        super(new LinkedHashMap<>(map));
        initConstructor(null, null);
    }

    public BLinkedHashMap(int initialCapacity)
    {
        super(new LinkedHashMap<>(initialCapacity));
        initConstructor(null, null);
    }

    public BLinkedHashMap(int initialCapacity, float loadFactor)
    {
        super(new LinkedHashMap<>(initialCapacity, loadFactor));
        initConstructor(null, null);
    }

    public BLinkedHashMap(int initialCapacity, float loadFactor, boolean accessOrder)
    {
        super(new LinkedHashMap<>(initialCapacity, loadFactor, accessOrder));
        initConstructor(null, null);
    }

    public BLinkedHashMap(LinkedHashMap<Key, Value> map, Class<Key> keyClass, Class<Value> valueClass)
    {
        super(map);
        initConstructor(keyClass, valueClass);
    }

    public BLinkedHashMap(Map<Key, Value> map, Class<Key> keyClass, Class<Value> valueClass)
    {
        super(new LinkedHashMap<>(map));
        initConstructor(keyClass, valueClass);
    }

    public BLinkedHashMap(int initialCapacity, Class<Key> keyClass, Class<Value> valueClass)
    {
        super(new LinkedHashMap<>(initialCapacity));
        initConstructor(keyClass, valueClass);
    }

    public BLinkedHashMap(int initialCapacity, float loadFactor, Class<Key> keyClass, Class<Value> valueClass)
    {
        super(new LinkedHashMap<>(initialCapacity, loadFactor));
        initConstructor(keyClass, valueClass);
    }

    public BLinkedHashMap(int initialCapacity, float loadFactor, boolean accessOrder, Class<Key> keyClass, Class<Value> valueClass)
    {
        super(new LinkedHashMap<>(initialCapacity, loadFactor, accessOrder));
        initConstructor(keyClass, valueClass);
    }

    public BLinkedHashMap(Class<Key> keyClass, Class<Value> valueClass)
    {
        super(new LinkedHashMap<>());
        initConstructor(keyClass, valueClass);
    }

    public BLinkedHashMap(byte[] objectBytesData)
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
    public byte[] serialize(LinkedHashMap<Key, Value> object)
    {
        return ContainerSerializer.serialize(object, this.keyConstructor, this.valueConstructor);
    }

    @SuppressWarnings("unchecked")
    @Override
    public LinkedHashMap<Key, Value> deserialize(byte[] objectBytesData)
    {
        DeserializeResult result = ContainerSerializer.deserializeMap(objectBytesData, this.keyConstructor, this.valueConstructor);
        this.keyConstructor = (Constructor<Key>) result.constructor()[0];
        this.valueConstructor = (Constructor<Value>) result.constructor()[1];
        return (LinkedHashMap<Key, Value>) result.object();
    }

    public Value put(Key key, Value value)
    {
        LinkedHashMap<Key, Value> map = getObject();
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
        LinkedHashMap<Key, Value> map = getObject();
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
        setObject(new LinkedHashMap<>());
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
        LinkedHashMap<Key, Value> map = getObject();
        Value r = map.computeIfAbsent(key, mappingFunction);
        setObject(map);
        return r;
    }

    public Value computeIfPresent(Key key, BiFunction<? super Key, ? super Value, ? extends Value> remappingFunction)
    {
        LinkedHashMap<Key, Value> map = getObject();
        Value r = map.computeIfPresent(key, remappingFunction);
        setObject(map);
        return r;
    }

    public Value compute(Key key, BiFunction<? super Key, ? super Value, ? extends Value> remappingFunction)
    {
        LinkedHashMap<Key, Value> map = getObject();
        Value r = map.compute(key, remappingFunction);
        setObject(map);
        return r;
    }

    public Value merge(Key key, Value value, BiFunction<? super Value, ? super Value, ? extends Value> remappingFunction)
    {
        LinkedHashMap<Key, Value> map = getObject();
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
