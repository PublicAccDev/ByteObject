package org.pacc.ByteObj.Container;

import org.pacc.ByteObj.DirectByteObj;
import org.pacc.ByteObj.Exception.BytesConstructorMissingException;
import org.pacc.ByteObj.FastByteObj;
import org.pacc.ByteObj.Serializer.ContainerSerializer;
import org.pacc.ByteObj.Serializer.DeserializeResult;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class BHashSet<ByteObj extends DirectByteObj<?>> extends FastByteObj<HashSet<ByteObj>>
{
    private Constructor<ByteObj> constructor;

    public BHashSet()
    {
        super(new HashSet<>());
        initConstructor(null);
    }

    public BHashSet(HashSet<ByteObj> object)
    {
        super(object);
        initConstructor(null);
    }

    public BHashSet(Collection<ByteObj> object)
    {
        super(new HashSet<>(object));
        initConstructor(null);
    }

    public BHashSet(int initialCapacity)
    {
        super(new HashSet<>(initialCapacity));
        initConstructor(null);
    }

    public BHashSet(int initialCapacity, float loadFactor)
    {
        super(new HashSet<>(initialCapacity, loadFactor));
        initConstructor(null);
    }

    public BHashSet(HashSet<ByteObj> object, Class<ByteObj> clazz)
    {
        super(object);
        initConstructor(clazz);
    }

    public BHashSet(Collection<ByteObj> object, Class<ByteObj> clazz)
    {
        super(new HashSet<>(object));
        initConstructor(clazz);
    }

    public BHashSet(int initialCapacity, Class<ByteObj> clazz)
    {
        super(new HashSet<>(initialCapacity));
        initConstructor(clazz);
    }

    public BHashSet(int initialCapacity, float loadFactor, Class<ByteObj> clazz)
    {
        super(new HashSet<>(initialCapacity, loadFactor));
        initConstructor(clazz);
    }

    public BHashSet(Class<ByteObj> clazz)
    {
        super(new HashSet<>());
        initConstructor(clazz);
    }

    public BHashSet(byte[] objectBytesData)
    {
        super(objectBytesData);
        initConstructor(null);
    }

    private void initConstructor(Class<ByteObj> clazz)
    {
        try
        {
            constructor = clazz == null ? null : clazz.getConstructor(byte[].class);
        } catch (NoSuchMethodException e)
        {
            throw new BytesConstructorMissingException(clazz);
        }
    }
    
    @Override
    public byte[] serialize(HashSet<ByteObj> object)
    {
        return ContainerSerializer.serialize(object, this.constructor);
    }

    @SuppressWarnings("unchecked")
    @Override
    public HashSet<ByteObj> deserialize(byte[] objectBytesData)
    {
        DeserializeResult result = ContainerSerializer.deserializeIterable(objectBytesData, this.constructor);
        this.constructor = (Constructor<ByteObj>) result.constructor()[0];
        return (HashSet<ByteObj>) result.object();
    }

    public boolean add(ByteObj e)
    {
        HashSet<ByteObj> set = this.getObject();
        boolean r = set.add(e);
        this.setObject(set);
        return r;
    }

    public boolean addAll(Collection<? extends ByteObj> c)
    {
        HashSet<ByteObj> set = this.getObject();
        boolean r = set.addAll(c);
        this.setObject(set);
        return r;
    }

    public boolean remove(ByteObj o)
    {
        HashSet<ByteObj> set = this.getObject();
        boolean r = set.remove(o);
        this.setObject(set);
        return r;
    }

    public boolean removeAll(Collection<ByteObj> c)
    {
        HashSet<ByteObj> set = this.getObject();
        boolean r = set.removeAll(c);
        this.setObject(set);
        return r;
    }

    public boolean removeIf(Predicate<? super ByteObj> o)
    {
        HashSet<ByteObj> set = this.getObject();
        boolean r = set.removeIf(o);
        this.setObject(set);
        return r;
    }

    public boolean contains(ByteObj o)
    {
        return this.getObject().contains(o);
    }

    public boolean containsAll(Collection<ByteObj> c)
    {
        return this.getObject().containsAll(c);
    }

    public boolean retainAll(Collection<ByteObj> c)
    {
        HashSet<ByteObj> set = this.getObject();
        boolean r = set.retainAll(c);
        this.setObject(set);
        return r;
    }

    public Object[] toArray()
    {
        return this.getObject().toArray();
    }

    public ByteObj[] toArray(ByteObj[] a)
    {
        return this.getObject().toArray(a);
    }

    public ByteObj[] toArray(IntFunction<ByteObj[]> generator)
    {
        return this.getObject().toArray(generator);
    }

    public void clear()
    {
        this.setObject(new HashSet<>());
    }

    public int size()
    {
        return this.getObject().size();
    }

    public boolean isEmpty()
    {
        return this.getObject().isEmpty();
    }

    public void forEach(Consumer<? super ByteObj> action)
    {
        this.getObject().forEach(action);
    }

    public Stream<ByteObj> stream()
    {
        return this.getObject().stream();
    }

    public Stream<ByteObj> parallelStream()
    {
        return this.getObject().parallelStream();
    }
}
