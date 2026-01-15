package org.pacc.ByteObj.BasicData;

import org.pacc.ByteObj.CacheByteObj;
import org.pacc.ByteObj.Exception.InvalidFormatException;
import org.pacc.ByteObj.Serializer.BasicDataSerializer;
import org.pacc.ByteObj.Serializer.SerializableSerializer;

import java.io.Serializable;

public class BDouble extends CacheByteObj<Double> implements Serializable
{
    public BDouble(Double object)
    {
        super(object);
    }

    public BDouble(byte[] objectBytesData)
    {
        super(objectBytesData);
    }

    @Override
    public byte[] serialize(Double object)
    {
        return SerializableSerializer.serialize(object);
    }

    @Override
    public Double deserialize(byte[] objectBytesData) throws InvalidFormatException
    {
        try
        {
            return (Double) SerializableSerializer.deserialize(objectBytesData);
        } catch (ClassCastException e)
        {
            throw new InvalidFormatException(e, Double.class);
        }
    }

}
