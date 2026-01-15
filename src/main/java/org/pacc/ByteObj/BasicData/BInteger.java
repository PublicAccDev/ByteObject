package org.pacc.ByteObj.BasicData;

import org.pacc.ByteObj.CacheByteObj;
import org.pacc.ByteObj.Exception.InvalidFormatException;
import org.pacc.ByteObj.Serializer.BasicDataSerializer;
import org.pacc.ByteObj.Serializer.SerializableSerializer;

import java.io.Serializable;

public class BInteger extends CacheByteObj<Integer> implements Serializable
{
    public BInteger(Integer object)
    {
        super(object);
    }

    public BInteger(byte[] objectBytesData)
    {
        super(objectBytesData);
    }

    @Override
    public byte[] serialize(Integer object)
    {
        return SerializableSerializer.serialize(object);
    }

    @Override
    public Integer deserialize(byte[] objectBytesData)
    {
        try
        {
            return (Integer) SerializableSerializer.deserialize(objectBytesData);
        } catch (ClassCastException e)
        {
            throw new InvalidFormatException(e, Integer.class);
        }
    }

}
