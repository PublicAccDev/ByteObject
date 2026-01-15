package org.pacc.ByteObj.BasicData;

import org.pacc.ByteObj.CacheByteObj;
import org.pacc.ByteObj.Exception.InvalidFormatException;
import org.pacc.ByteObj.Serializer.BasicDataSerializer;

public class BBoolean extends CacheByteObj<Boolean>
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
        return BasicDataSerializer.serialize(object);
    }

    @Override
    public Boolean deserialize(byte[] objectBytesData) throws InvalidFormatException
    {
        try
        {
            return BasicDataSerializer.deserializeBoolean(this.getBytes());
        } catch (Exception e)
        {
            throw new InvalidFormatException(e, Boolean.class);
        }
    }
}