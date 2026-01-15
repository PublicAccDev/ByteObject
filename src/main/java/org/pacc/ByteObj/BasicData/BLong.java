package org.pacc.ByteObj.BasicData;

import org.pacc.ByteObj.DirectByteObj;
import org.pacc.ByteObj.Exception.InvalidFormatException;
import org.pacc.ByteObj.Serializer.BasicDataSerializer;

public class BLong extends DirectByteObj<Long>
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
    public Long deserialize(byte[] objectBytesData) throws InvalidFormatException
    {
        try
        {
            return BasicDataSerializer.deserializeLong(this.getBytes());
        } catch (Exception e)
        {
            throw new InvalidFormatException(e, Long.class);
        }
    }


}