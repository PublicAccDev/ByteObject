package org.pacc.ByteObj.Container;

import org.pacc.ByteObj.DirectByteObj;
import org.pacc.ByteObj.Exception.BytesConstructorMissingException;
import org.pacc.ByteObj.FastByteObj;
import org.pacc.ByteObj.Serializer.ContainerSerializer;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class BPriorityQueue<ByteObj extends DirectByteObj<?>> extends FastByteObj<PriorityQueue<ByteObj>>
{
    private final Constructor<ByteObj> constructor;

    public BPriorityQueue(PriorityQueue<ByteObj> object, Class<ByteObj> clazz)
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

    public BPriorityQueue(Collection<ByteObj> object, Class<ByteObj> clazz)
    {
        super(new PriorityQueue<>(object));
        try
        {
            this.constructor = clazz.getConstructor(byte[].class);
        } catch (NoSuchMethodException e)
        {
            throw new BytesConstructorMissingException(clazz);
        }
    }

    public BPriorityQueue(int initialCapacity, Class<ByteObj> clazz)
    {
        super(new PriorityQueue<>(initialCapacity));
        try
        {
            this.constructor = clazz.getConstructor(byte[].class);
        } catch (NoSuchMethodException e)
        {
            throw new BytesConstructorMissingException(clazz);
        }
    }

    public BPriorityQueue(int initialCapacity, Comparator<? super ByteObj> comparator, Class<ByteObj> clazz)
    {
        super(new PriorityQueue<>(initialCapacity, comparator));
        try
        {
            this.constructor = clazz.getConstructor(byte[].class);
        } catch (NoSuchMethodException e)
        {
            throw new BytesConstructorMissingException(clazz);
        }
    }

    public BPriorityQueue(Comparator<? super ByteObj> comparator, Class<ByteObj> clazz)
    {
        super(new PriorityQueue<>(comparator));
        try
        {
            this.constructor = clazz.getConstructor(byte[].class);
        } catch (NoSuchMethodException e)
        {
            throw new BytesConstructorMissingException(clazz);
        }
    }

    public BPriorityQueue(Class<ByteObj> clazz)
    {
        super(new PriorityQueue<>());
        try
        {
            this.constructor = clazz.getConstructor(byte[].class);
        } catch (NoSuchMethodException e)
        {
            throw new BytesConstructorMissingException(clazz);
        }
    }

    @Override
    public byte[] serialize(PriorityQueue<ByteObj> object)
    {
        return ContainerSerializer.serialize(object);
    }

    @Override
    public PriorityQueue<ByteObj> deserialize(byte[] objectBytesData)
    {
        return ContainerSerializer.deserializePriorityQueue(objectBytesData, this.constructor);
    }

    public boolean add(ByteObj e)
    {
        PriorityQueue<ByteObj> queue = this.getObject();
        boolean result = queue.add(e);
        this.setObject(queue);
        return result;
    }

    public boolean offer(ByteObj e)
    {
        PriorityQueue<ByteObj> queue = this.getObject();
        boolean result = queue.offer(e);
        this.setObject(queue);
        return result;
    }

    public ByteObj remove()
    {
        PriorityQueue<ByteObj> queue = this.getObject();
        ByteObj result = queue.remove();
        this.setObject(queue);
        return result;
    }

    public ByteObj poll()
    {
        PriorityQueue<ByteObj> queue = this.getObject();
        ByteObj result = queue.poll();
        this.setObject(queue);
        return result;
    }

    public ByteObj element()
    {
        return this.getObject().element();
    }

    public ByteObj peek()
    {
        return this.getObject().peek();
    }

    public boolean remove(Object o)
    {
        PriorityQueue<ByteObj> queue = this.getObject();
        boolean result = queue.remove(o);
        this.setObject(queue);
        return result;
    }

    public boolean contains(Object o)
    {
        return this.getObject().contains(o);
    }

    public int size()
    {
        return this.getObject().size();
    }

    public boolean isEmpty()
    {
        return this.getObject().isEmpty();
    }

    public Iterator<ByteObj> iterator()
    {
        return this.getObject().iterator();
    }

    public Object[] toArray()
    {
        return this.getObject().toArray();
    }

    public <T> T[] toArray(T[] a)
    {
        return this.getObject().toArray(a);
    }

    public boolean removeAll(Collection<?> c)
    {
        PriorityQueue<ByteObj> queue = this.getObject();
        boolean result = queue.removeAll(c);
        this.setObject(queue);
        return result;
    }

    public boolean retainAll(Collection<?> c)
    {
        PriorityQueue<ByteObj> queue = this.getObject();
        boolean result = queue.retainAll(c);
        this.setObject(queue);
        return result;
    }

    public boolean containsAll(Collection<?> c)
    {
        return this.getObject().containsAll(c);
    }

    public boolean addAll(Collection<? extends ByteObj> c)
    {
        PriorityQueue<ByteObj> queue = this.getObject();
        boolean result = queue.addAll(c);
        this.setObject(queue);
        return result;
    }

    public boolean removeIf(Predicate<? super ByteObj> filter)
    {
        PriorityQueue<ByteObj> queue = this.getObject();
        boolean result = queue.removeIf(filter);
        this.setObject(queue);
        return result;
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

    public Spliterator<ByteObj> spliterator()
    {
        return this.getObject().spliterator();
    }

    public void clear()
    {
        this.setObject(new PriorityQueue<>());
    }

    public Comparator<? super ByteObj> comparator()
    {
        return this.getObject().comparator();
    }
}
