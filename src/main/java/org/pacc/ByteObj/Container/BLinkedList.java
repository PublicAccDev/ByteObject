package org.pacc.ByteObj.Container;

import org.pacc.ByteObj.DirectByteObj;
import org.pacc.ByteObj.Exception.BytesConstructorMissingException;
import org.pacc.ByteObj.FastByteObj;
import org.pacc.ByteObj.Serializer.ContainerSerializer;
import org.pacc.ByteObj.Serializer.DeserializeResult;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class BLinkedList<ByteObj extends DirectByteObj<?>> extends DirectByteObj<LinkedList<ByteObj>>
{
    private Constructor<ByteObj> constructor;

    public BLinkedList()
    {
        super(new LinkedList<>());
        initConstructor(null);
    }

    public BLinkedList(LinkedList<ByteObj> object)
    {
        super(object);
        initConstructor(null);
    }

    public BLinkedList(Collection<ByteObj> object)
    {
        super(new LinkedList<>(object));
        initConstructor(null);
    }

    public BLinkedList(LinkedList<ByteObj> object, Class<ByteObj> clazz)
    {
        super(object);
        initConstructor(clazz);
    }

    public BLinkedList(Collection<ByteObj> object, Class<ByteObj> clazz)
    {
        super(new LinkedList<>(object));
        initConstructor(clazz);
    }

    public BLinkedList(Class<ByteObj> clazz)
    {
        super(new LinkedList<>());
        initConstructor(clazz);
    }

    public BLinkedList(byte[] objectBytesData)
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
    public byte[] serialize(LinkedList<ByteObj> object)
    {
        return ContainerSerializer.serialize(object, this.constructor);
    }

    @SuppressWarnings("unchecked")
    @Override
    public LinkedList<ByteObj> deserialize(byte[] objectBytesData)
    {
        DeserializeResult result = ContainerSerializer.deserializeIterable(objectBytesData, this.constructor);
        this.constructor = (Constructor<ByteObj>) result.constructor()[0];
        return (LinkedList<ByteObj>) result.object();
    }

    public ByteObj get(int index)
    {
        return this.getObject().get(index);
    }

    public ByteObj getFirst()
    {
        return this.getObject().getFirst();
    }

    public ByteObj getLast()
    {
        return this.getObject().getLast();
    }

    public ByteObj set(int index, ByteObj element)
    {
        LinkedList<ByteObj> list = this.getObject();
        ByteObj r = list.set(index, element);
        this.setObject(list);
        return r;
    }

    public boolean add(ByteObj e)
    {
        LinkedList<ByteObj> list = this.getObject();
        boolean r = list.add(e);
        this.setObject(list);
        return r;
    }

    public void add(int index, ByteObj element)
    {
        LinkedList<ByteObj> list = this.getObject();
        list.add(index, element);
        this.setObject(list);
    }

    public boolean addAll(Collection<? extends ByteObj> c)
    {
        LinkedList<ByteObj> list = this.getObject();
        boolean r = list.addAll(c);
        this.setObject(list);
        return r;
    }

    public boolean addAll(int index, Collection<? extends ByteObj> c)
    {
        LinkedList<ByteObj> list = this.getObject();
        boolean r = list.addAll(index, c);
        this.setObject(list);
        return r;
    }

    public ByteObj remove(int index)
    {
        LinkedList<ByteObj> list = this.getObject();
        ByteObj r = list.remove(index);
        this.setObject(list);
        return r;
    }

    public boolean remove(ByteObj o)
    {
        LinkedList<ByteObj> list = this.getObject();
        boolean r = list.remove(o);
        this.setObject(list);
        return r;
    }

    public boolean removeAll(Collection<ByteObj> c)
    {
        LinkedList<ByteObj> list = this.getObject();
        boolean r = list.removeAll(c);
        this.setObject(list);
        return r;
    }

    public boolean removeIf(Predicate<? super ByteObj> filter)
    {
        LinkedList<ByteObj> list = this.getObject();
        boolean r = list.removeIf(filter);
        this.setObject(list);
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

    public boolean contains(ByteObj o)
    {
        return this.getObject().contains(o);
    }

    public boolean containsAll(Collection<ByteObj> c)
    {
        return this.getObject().containsAll(c);
    }

    public void replaceAll(UnaryOperator<ByteObj> operator)
    {
        LinkedList<ByteObj> list = this.getObject();
        list.replaceAll(operator);
        this.setObject(list);
    }

    public int indexOf(ByteObj o)
    {
        return this.getObject().indexOf(o);
    }

    public int lastIndexOf(ByteObj o)
    {
        return this.getObject().lastIndexOf(o);
    }

    public boolean retainAll(Collection<ByteObj> c)
    {
        LinkedList<ByteObj> list = this.getObject();
        boolean r = list.retainAll(c);
        this.setObject(list);
        return r;
    }

    public LinkedList<ByteObj> subLinkedList(int fromIndex, int toIndex)
    {
        return new LinkedList<>(this.getObject().subList(fromIndex, toIndex));
    }

    public Stream<ByteObj> stream()
    {
        return this.getObject().stream();
    }

    public Stream<ByteObj> parallelStream()
    {
        return this.getObject().parallelStream();
    }

    public int size()
    {
        return this.getObject().size();
    }

    public void clear()
    {
        this.setObject(new LinkedList<>());
    }

    public boolean isEmpty()
    {
        return this.getObject().isEmpty();
    }

    public void forEach(Consumer<? super ByteObj> action)
    {
        this.getObject().forEach(action);
    }

    public void sort(Comparator<? super ByteObj> c)
    {
        LinkedList<ByteObj> list = this.getObject();
        list.sort(c);
        this.setObject(list);
    }
}
