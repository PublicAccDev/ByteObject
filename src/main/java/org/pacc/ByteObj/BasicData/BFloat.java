package org.pacc.ByteObj.BasicData;

import org.pacc.ByteObj.CacheByteObj;
import org.pacc.ByteObj.Serializer.BasicDataSerializer;

public class BFloat extends CacheByteObj<Float>
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
    public Float deserialize(byte[] objectBytesData)
    {
        return BasicDataSerializer.deserializeFloat(objectBytesData);
    }

}
