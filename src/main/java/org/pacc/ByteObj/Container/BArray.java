package org.pacc.ByteObj.Container;

import org.pacc.ByteObj.DirectByteObj;
import org.pacc.ByteObj.Exception.BytesConstructorMissingException;
import org.pacc.ByteObj.Serializer.ContainerSerializer;
import org.pacc.ByteObj.Serializer.DeserializeResult;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;

public class BArray<ByteObj extends DirectByteObj<?>> extends DirectByteObj<ByteObj[]>
{
    private Constructor<ByteObj> constructor;

    public BArray(ByteObj[] object)
    {
        super(object);
    }

    public BArray(ByteObj[] object, Class<ByteObj> clazz)
    {
        super(object);
    }

    @SuppressWarnings("unchecked")
    public BArray(Class<ByteObj> clazz)
    {
        super((ByteObj[]) Array.newInstance(clazz, 0));
    }

    public BArray(byte[] objectBytesData)
    {
        super(objectBytesData);
    }

    @Override
    public byte[] serialize(ByteObj[] object)
    {
        return ContainerSerializer.serialize(object, this.constructor);
    }

    @SuppressWarnings("unchecked")
    @Override
    public ByteObj[] deserialize(byte[] objectBytesData)
    {
        DeserializeResult result = ContainerSerializer.deserializeArray(objectBytesData, this.constructor);
        this.constructor = (Constructor<ByteObj>) result.constructor()[0];
        return (ByteObj[]) result.object();
    }

    @SuppressWarnings("unchecked")
    private void initConstructor(Class<?> clazz)
    {
        try
        {
            this.constructor = clazz == null ? null : (Constructor<ByteObj>) clazz.getConstructor(byte[].class);
        } catch (NoSuchMethodException e)
        {
            throw new BytesConstructorMissingException(clazz);
        }
    }

    public int length()
    {
        return this.getObject().length;
    }
}
