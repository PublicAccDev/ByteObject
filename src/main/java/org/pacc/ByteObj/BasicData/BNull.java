package org.pacc.ByteObj.BasicData;

import org.pacc.ByteObj.DirectByteObj;

public class BNull extends DirectByteObj<Object>
{
    public BNull(byte[] objectBytesData)
    {
        super(objectBytesData);
    }

    public BNull(Object object)
    {
        super(object);
    }

    @Override
    public byte[] serialize(Object object)
    {
        return new byte[]{0, 0};
    }

    @Override
    public Object deserialize(byte[] objectBytesData)
    {
        return null;
    }
}
