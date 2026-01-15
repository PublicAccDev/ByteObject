package org.pacc.ByteObj.Container;

import org.pacc.ByteObj.DirectByteObj;
import org.pacc.ByteObj.Exception.BytesConstructorMissingException;
import org.pacc.ByteObj.FastByteObj;
import org.pacc.ByteObj.Serializer.ContainerSerializer;

import java.lang.reflect.Constructor;

public class BArray<ByteObj extends DirectByteObj<?>> extends FastByteObj<ByteObj[]>
{
    private final Constructor<ByteObj> constructor;
    private final Class<ByteObj> clazz;

    @SuppressWarnings("unchecked")
    public BArray(ByteObj[] object)
    {
        super(object);
        this.clazz = (Class<ByteObj>) object.getClass().getComponentType();
        try
        {
            this.constructor = clazz.getConstructor(byte[].class);
        } catch (NoSuchMethodException e)
        {
            throw new BytesConstructorMissingException(clazz);
        }
    }

    public BArray(ByteObj[] object, Class<ByteObj> clazz)
    {
        super(object);
        this.clazz = clazz;
        try
        {
            this.constructor = clazz.getConstructor(byte[].class);
        } catch (NoSuchMethodException e)
        {
            throw new BytesConstructorMissingException(clazz);
        }
    }

    public BArray(Class<ByteObj> clazz)
    {
        super(new byte[0]);
        this.clazz = clazz;
        try
        {
            this.constructor = clazz.getConstructor(byte[].class);
        } catch (NoSuchMethodException e)
        {
            throw new BytesConstructorMissingException(clazz);
        }
    }

    @Override
    public byte[] serialize(ByteObj[] object)
    {
        return ContainerSerializer.serialize(object);
    }

    @Override
    public ByteObj[] deserialize(byte[] objectBytesData)
    {
        return ContainerSerializer.deserializeArray(objectBytesData, this.clazz, this.constructor);
    }

    public int length()
    {
        return this.getObject().length;
    }
}
