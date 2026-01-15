package org.pacc.ByteObj.BasicData;

import org.pacc.ByteObj.DirectByteObj;
import org.pacc.ByteObj.Exception.InvalidFormatException;
import org.pacc.ByteObj.Serializer.BasicDataSerializer;

public class BDouble extends DirectByteObj<Double>
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
        return BasicDataSerializer.serialize(object);
    }

    @Override
    public Double deserialize(byte[] objectBytesData) throws InvalidFormatException
    {
        try
        {
            return BasicDataSerializer.deserializeDouble(this.getBytes());
        } catch (Exception e)
        {
            throw new InvalidFormatException(e, Double.class);
        }
    }

    
}