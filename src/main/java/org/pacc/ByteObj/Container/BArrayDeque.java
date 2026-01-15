package org.pacc.ByteObj.Container;

import org.pacc.ByteObj.DirectByteObj;
import org.pacc.ByteObj.Exception.BytesConstructorMissingException;
import org.pacc.ByteObj.FastByteObj;
import org.pacc.ByteObj.Serializer.ContainerSerializer;

import java.lang.reflect.Constructor;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class BArrayDeque<ByteObj extends DirectByteObj<?>> extends FastByteObj<ArrayDeque<ByteObj>>
{
    private final Constructor<ByteObj> constructor;

    public BArrayDeque(ArrayDeque<ByteObj> object, Class<ByteObj> clazz)
    {
        super(object);
        try
        {
            constructor = clazz.getConstructor(byte[].class);
        } catch (NoSuchMethodException e)
        {
            throw new BytesConstructorMissingException(clazz);
        }
    }

    public BArrayDeque(Collection<ByteObj> object, Class<ByteObj> clazz)
    {
        super(new ArrayDeque<>(object));
        try
        {
            constructor = clazz.getConstructor(byte[].class);
        } catch (NoSuchMethodException e)
        {
            throw new BytesConstructorMissingException(clazz);
        }
    }

    public BArrayDeque(int numElements, Class<ByteObj> clazz)
    {
        super(new ArrayDeque<>(numElements));
        try
        {
            constructor = clazz.getConstructor(byte[].class);
        } catch (NoSuchMethodException e)
        {
            throw new BytesConstructorMissingException(clazz);
        }
    }

    public BArrayDeque(Class<ByteObj> clazz)
    {
        super(new ArrayDeque<>());
        try
        {
            constructor = clazz.getConstructor(byte[].class);
        } catch (NoSuchMethodException e)
        {
            throw new BytesConstructorMissingException(clazz);
        }
    }

    @Override
    public byte[] serialize(ArrayDeque<ByteObj> object)
    {
        return ContainerSerializer.serialize(object);
    }

    @Override
    public ArrayDeque<ByteObj> deserialize(byte[] objectBytesData)
    {
        return ContainerSerializer.deserializeArrayDeque(objectBytesData, this.constructor);
    }

    public void addFirst(ByteObj e)
    {
        ArrayDeque<ByteObj> deque = this.getObject();
        deque.addFirst(e);
        this.setObject(deque);
    }

    public void addLast(ByteObj e)
    {
        ArrayDeque<ByteObj> deque = this.getObject();
        deque.addLast(e);
        this.setObject(deque);
    }

    public boolean offerFirst(ByteObj e)
    {
        ArrayDeque<ByteObj> deque = this.getObject();
        boolean result = deque.offerFirst(e);
        this.setObject(deque);
        return result;
    }

    public boolean offerLast(ByteObj e)
    {
        ArrayDeque<ByteObj> deque = this.getObject();
        boolean result = deque.offerLast(e);
        this.setObject(deque);
        return result;
    }

    public ByteObj removeFirst()
    {
        ArrayDeque<ByteObj> deque = this.getObject();
        ByteObj result = deque.removeFirst();
        this.setObject(deque);
        return result;
    }

    public ByteObj removeLast()
    {
        ArrayDeque<ByteObj> deque = this.getObject();
        ByteObj result = deque.removeLast();
        this.setObject(deque);
        return result;
    }

    public ByteObj pollFirst()
    {
        ArrayDeque<ByteObj> deque = this.getObject();
        ByteObj result = deque.pollFirst();
        this.setObject(deque);
        return result;
    }

    public ByteObj pollLast()
    {
        ArrayDeque<ByteObj> deque = this.getObject();
        ByteObj result = deque.pollLast();
        this.setObject(deque);
        return result;
    }

    public ByteObj getFirst()
    {
        return this.getObject().getFirst();
    }

    public ByteObj getLast()
    {
        return this.getObject().getLast();
    }

    public ByteObj peekFirst()
    {
        return this.getObject().peekFirst();
    }

    public ByteObj peekLast()
    {
        return this.getObject().peekLast();
    }

    public boolean removeFirstOccurrence(Object o)
    {
        ArrayDeque<ByteObj> deque = this.getObject();
        boolean result = deque.removeFirstOccurrence(o);
        this.setObject(deque);
        return result;
    }

    public boolean removeLastOccurrence(Object o)
    {
        ArrayDeque<ByteObj> deque = this.getObject();
        boolean result = deque.removeLastOccurrence(o);
        this.setObject(deque);
        return result;
    }

    public boolean add(ByteObj e)
    {
        ArrayDeque<ByteObj> deque = this.getObject();
        boolean result = deque.add(e);
        this.setObject(deque);
        return result;
    }

    public boolean offer(ByteObj e)
    {
        ArrayDeque<ByteObj> deque = this.getObject();
        boolean result = deque.offer(e);
        this.setObject(deque);
        return result;
    }

    public ByteObj remove()
    {
        ArrayDeque<ByteObj> deque = this.getObject();
        ByteObj result = deque.remove();
        this.setObject(deque);
        return result;
    }

    public ByteObj poll()
    {
        ArrayDeque<ByteObj> deque = this.getObject();
        ByteObj result = deque.poll();
        this.setObject(deque);
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

    public void push(ByteObj e)
    {
        ArrayDeque<ByteObj> deque = this.getObject();
        deque.push(e);
        this.setObject(deque);
    }

    public ByteObj pop()
    {
        ArrayDeque<ByteObj> deque = this.getObject();
        ByteObj result = deque.pop();
        this.setObject(deque);
        return result;
    }

    public boolean remove(Object o)
    {
        ArrayDeque<ByteObj> deque = this.getObject();
        boolean result = deque.remove(o);
        this.setObject(deque);
        return result;
    }

    public boolean removeAll(Collection<?> c)
    {
        ArrayDeque<ByteObj> deque = this.getObject();
        boolean result = deque.removeAll(c);
        this.setObject(deque);
        return result;
    }

    public boolean retainAll(Collection<?> c)
    {
        ArrayDeque<ByteObj> deque = this.getObject();
        boolean result = deque.retainAll(c);
        this.setObject(deque);
        return result;
    }

    public boolean containsAll(Collection<?> c)
    {
        return this.getObject().containsAll(c);
    }

    public boolean contains(Object o)
    {
        return this.getObject().contains(o);
    }

    public boolean isEmpty()
    {
        return this.getObject().isEmpty();
    }

    public int size()
    {
        return this.getObject().size();
    }

    public Iterator<ByteObj> iterator()
    {
        return this.getObject().iterator();
    }

    public Iterator<ByteObj> descendingIterator()
    {
        return this.getObject().descendingIterator();
    }

    public Object[] toArray()
    {
        return this.getObject().toArray();
    }

    public <T> T[] toArray(T[] a)
    {
        return this.getObject().toArray(a);
    }

    public boolean addAll(Collection<? extends ByteObj> c)
    {
        ArrayDeque<ByteObj> deque = this.getObject();
        boolean result = deque.addAll(c);
        this.setObject(deque);
        return result;
    }

    public boolean removeAllOccurrences(Object o)
    {
        ArrayDeque<ByteObj> deque = this.getObject();
        boolean result = deque.removeIf(item -> item.equals(o));
        this.setObject(deque);
        return result;
    }

    public boolean removeIf(Predicate<? super ByteObj> filter)
    {
        ArrayDeque<ByteObj> deque = this.getObject();
        boolean result = deque.removeIf(filter);
        this.setObject(deque);
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
        this.setObject(new ArrayDeque<>());
    }
}
