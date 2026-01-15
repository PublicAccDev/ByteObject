package org.pacc.ByteObj.Container;

import org.pacc.ByteObj.BasicByteObject;
import org.pacc.ByteObj.CacheByteObj;
import org.pacc.ByteObj.Exception.BytesConstructorMissingException;
import org.pacc.ByteObj.Serializer.ContainerSerializer;

import java.lang.reflect.Constructor;

public class BArray<ByteObj extends BasicByteObject<?>> extends CacheByteObj<ByteObj[]>
{
    private final Constructor<ByteObj> constructor;
    private final Class<ByteObj> clazz;

    public BArray(ByteObj[] object, Class<ByteObj> clazz)
    {
        super(object);
        this.clazz = clazz;
        try
        {
            constructor = clazz.getConstructor(byte[].class);
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
            constructor = clazz.getConstructor(byte[].class);
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
        return ContainerSerializer.deserializeArray(objectBytesData, clazz, constructor);
    }

    public int length()
    {
        return this.getObject().length;
    }
}
