package org.pacc.ByteObj.BasicData;

import org.pacc.ByteObj.CacheByteObj;
import org.pacc.ByteObj.Exception.InvalidFormatException;
import org.pacc.ByteObj.Serializer.BasicDataSerializer;
import org.pacc.ByteObj.Serializer.SerializableSerializer;

import java.io.Serializable;

public class BLong extends CacheByteObj<Long> implements Serializable
{
    public BLong(Long object)
    {
        super(object);
    }

    public BLong(byte[] objectBytesData)
    {
        super(objectBytesData);
    }

    @Override
    public byte[] serialize(Long object)
    {
        return SerializableSerializer.serialize(object);
    }

    @Override
    public Long deserialize(byte[] objectBytesData)
    {
        try
        {
            return (Long) SerializableSerializer.deserialize(objectBytesData);
        } catch (ClassCastException e)
        {
            throw new InvalidFormatException(e, Long.class);
        }
    }

}
