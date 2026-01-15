package org.pacc.ByteObj.BasicData;

import org.pacc.ByteObj.DirectByteObj;
import org.pacc.ByteObj.Exception.InvalidFormatException;
import org.pacc.ByteObj.Serializer.BasicDataSerializer;

public class BFloat extends DirectByteObj<Float>
{
    public BFloat(Float object)
    {
        super(object);
    }

    public BFloat(byte[] objectBytesData)
    {
        super(objectBytesData);
    }

    @Override
    public byte[] serialize(Float object)
    {
        return BasicDataSerializer.serialize(object);
    }

    @Override
    public Float deserialize(byte[] objectBytesData) throws InvalidFormatException
    {
        try
        {
            return BasicDataSerializer.deserializeFloat(objectBytesData);
        } catch (Exception e)
        {
            throw new InvalidFormatException(e, Float.class);
        }
    }

    
}