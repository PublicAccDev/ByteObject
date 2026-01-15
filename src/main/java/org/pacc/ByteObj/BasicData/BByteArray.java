package org.pacc.ByteObj.BasicData;

import org.pacc.ByteObj.CacheByteObj;
import org.pacc.ByteObj.Exception.InvalidFormatException;
import org.pacc.ByteObj.Serializer.SerializableSerializer;

import java.io.Serializable;

public class BByteArray extends CacheByteObj<Byte[]> implements Serializable
{
    public BByteArray(Byte[] object)
    {
        super(object);
    }

    public BByteArray(byte[] objectBytesData)
    {
        super(objectBytesData, false);
    }

    @Override
    public byte[] serialize(Byte[] object)
    {
        return SerializableSerializer.serialize(object);
    }

    @Override
    public Byte[] deserialize(byte[] objectBytesData)
    {
        try
        {
            return (Byte[]) SerializableSerializer.deserialize(objectBytesData);
        } catch (ClassCastException e)
        {
            throw new InvalidFormatException(e, Byte[].class);
        }
    }
}
