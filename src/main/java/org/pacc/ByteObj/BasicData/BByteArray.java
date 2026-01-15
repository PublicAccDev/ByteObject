package org.pacc.ByteObj.BasicData;

import org.pacc.ByteObj.DirectByteObj;

public class BByteArray extends DirectByteObj<byte[]>
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
