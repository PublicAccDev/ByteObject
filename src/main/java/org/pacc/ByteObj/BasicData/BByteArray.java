package org.pacc.ByteObj.BasicData;

import org.pacc.ByteObj.CacheByteObj;
import org.pacc.ByteObj.Exception.InvalidFormatException;
import org.pacc.ByteObj.Serializer.BasicDataSerializer;
import org.pacc.ByteObj.Serializer.SerializableSerializer;

import java.io.Serializable;

public class BByteArray extends CacheByteObj<byte[]>
{
    public BByteArray(byte[] object)
    {
        super(object, false);
    }

    @Override
    public byte[] serialize(byte[] object)
    {
        return object;
    }

    @Override
    public byte[] deserialize(byte[] objectBytesData)
    {
        return objectBytesData;
    }
}
