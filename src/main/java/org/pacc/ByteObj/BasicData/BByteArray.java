package org.pacc.ByteObj.BasicData;

import org.pacc.ByteObj.CacheByteObj;

public class BByteArray extends CacheByteObj<byte[]>
{

    public BByteArray(byte[] objectBytesData)
    {
        super(objectBytesData, false);
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
