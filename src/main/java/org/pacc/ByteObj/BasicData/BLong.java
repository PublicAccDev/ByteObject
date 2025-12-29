package org.pacc.ByteObj.BasicData;

import org.pacc.ByteObj.CacheByteObj;
import org.pacc.ByteObj.Serializer.BasicDataSerializer;

public class BLong extends CacheByteObj<Long>
{
    public BLong(Long object)
    {
        super(object);
    }

    public BLong(byte[] objectBytesData)
    {
        super(objectBytesData);
    }

    @Override
    public byte[] serialize(Long object)
    {
        return BasicDataSerializer.serialize(object);
    }

    @Override
    public Long deserialize(byte[] objectBytesData)
    {
        return BasicDataSerializer.deserializeLong(this.getBytes());
    }

}
