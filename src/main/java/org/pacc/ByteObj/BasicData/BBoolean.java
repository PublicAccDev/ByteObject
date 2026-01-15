package org.pacc.ByteObj.BasicData;

import org.pacc.ByteObj.CacheByteObj;
import org.pacc.ByteObj.Exception.InvalidFormatException;
import org.pacc.ByteObj.Serializer.SerializableSerializer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class BBoolean extends CacheByteObj<Boolean> implements Serializable
{
    public BBoolean(Boolean object)
    {
        super(object);
    }

    public BBoolean(byte[] objectBytesData)
    {
        super(objectBytesData);
    }

    @Override
    public byte[] serialize(Boolean object)
    {
        return SerializableSerializer.serialize(object);
    }

    @Override
    public Boolean deserialize(byte[] objectBytesData)
    {
        try
        {
            return (Boolean) SerializableSerializer.deserialize(objectBytesData);
        } catch (ClassCastException e)
        {
            throw new InvalidFormatException(e, Boolean.class);
        }
    }

}
