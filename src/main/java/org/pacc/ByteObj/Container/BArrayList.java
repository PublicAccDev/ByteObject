package org.pacc.ByteObj.Container;

import org.pacc.ByteObj.DirectByteObj;
import org.pacc.ByteObj.Exception.BytesConstructorMissingException;
import org.pacc.ByteObj.FastByteObj;
import org.pacc.ByteObj.Serializer.ContainerSerializer;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class BArrayList<ByteObj extends DirectByteObj<?>> extends FastByteObj<ArrayList<ByteObj>>
{
    private final Constructor<ByteObj> constructor;

    @SuppressWarnings("unchecked")
    public BArrayList(ArrayList<ByteObj> object)
    {
        super(object);
        try
        {
            this.constructor = (Constructor<ByteObj>) object.getClass().getComponentType().getConstructor(byte[].class);
        } catch (NoSuchMethodException e)
        {
            throw new BytesConstructorMissingException(object.getClass().getComponentType());
        }
    }

    public BArrayList(ArrayList<ByteObj> object, Class<ByteObj> clazz)
    {
        super(object);
        try
        {
            this.constructor = clazz.getConstructor(byte[].class);
        } catch (NoSuchMethodException e)
        {
            throw new BytesConstructorMissingException(clazz);
        }
    }

    public BArrayList(Class<ByteObj> clazz)
    {
        super(new byte[0]);
        try
        {
            this.constructor = clazz.getConstructor(byte[].class);
        } catch (NoSuchMethodException e)
        {
            throw new BytesConstructorMissingException(clazz);
        }
    }

    @Override
    public byte[] serialize(ArrayList<ByteObj> object)
    {
        return ContainerSerializer.serialize(object);
    }

    @Override
    public ArrayList<ByteObj> deserialize(byte[] objectBytesData)
    {
        return ContainerSerializer.deserializeArrayList(objectBytesData, this.constructor);
    }

    public ByteObj get(int index)
    {
        return this.getObject().get(index);
    }

    public ByteObj set(int index, ByteObj element)
    {
        ArrayList<ByteObj> list = this.getObject();
        ByteObj r = list.set(index, element);
        this.setObject(list);
        return r;
    }

    public boolean add(ByteObj e)
    {
        ArrayList<ByteObj> list = this.getObject();
        boolean r = list.add(e);
        this.setObject(list);
        return r;
    }

    public void add(int index, ByteObj element)
    {
        ArrayList<ByteObj> list = this.getObject();
        list.add(index, element);
        this.setObject(list);
    }

    public boolean addAll(Collection<? extends ByteObj> c)
    {
        ArrayList<ByteObj> list = this.getObject();
        boolean r = list.addAll(c);
        this.setObject(list);
        return r;
    }

    public boolean addAll(int index, Collection<? extends ByteObj> c)
    {
        ArrayList<ByteObj> list = this.getObject();
        boolean r = list.addAll(index, c);
        this.setObject(list);
        return r;
    }

    public ByteObj remove(int index)
    {
        ArrayList<ByteObj> list = this.getObject();
        ByteObj r = list.remove(index);
        this.setObject(list);
        return r;
    }

    public boolean remove(ByteObj o)
    {
        ArrayList<ByteObj> list = this.getObject();
        boolean r = list.remove(o);
        this.setObject(list);
        return r;
    }

    public boolean removeAll(Collection<ByteObj> c)
    {
        ArrayList<ByteObj> list = this.getObject();
        boolean r = list.removeAll(c);
        this.setObject(list);
        return r;
    }

    public boolean removeIf(Predicate<? super ByteObj> filter)
    {
        ArrayList<ByteObj> list = this.getObject();
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
        ArrayList<ByteObj> list = this.getObject();
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
        ArrayList<ByteObj> list = this.getObject();
        boolean r = list.retainAll(c);
        this.setObject(list);
        return r;
    }

    public ArrayList<ByteObj> subArrayList(int fromIndex, int toIndex)
    {
        return new ArrayList<>(this.getObject().subList(fromIndex, toIndex));
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
        this.setObject(new ArrayList<>());
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
        ArrayList<ByteObj> list = this.getObject();
        list.sort(c);
        this.setObject(list);
    }
}
