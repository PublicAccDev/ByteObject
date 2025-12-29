package org.pacc.ByteObj.BasicData;

import org.pacc.ByteObj.CacheByteObj;
import org.pacc.ByteObj.Serializer.BasicDataSerializer;

public class BDouble extends CacheByteObj<Double>
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
    public Double deserialize(byte[] objectBytesData)
    {
        return BasicDataSerializer.deserializeDouble(this.getBytes());
    }

}
