package org.pacc.ByteObj.BasicData;

import org.pacc.ByteObj.DirectByteObj;
import org.pacc.ByteObj.Exception.InvalidFormatException;
import org.pacc.ByteObj.Serializer.BasicDataSerializer;

public class BString extends DirectByteObj<String>
{
    public BString(String object)
    {
        super(object);
    }


    public BString(byte[] objectBytesData)
    {
        super(objectBytesData);
    }

    @Override
    public byte[] serialize(String object)
    {
        return BasicDataSerializer.serialize(object);
    }

    @Override
    public String deserialize(byte[] objectBytesData) throws InvalidFormatException
    {
        try
        {
            return BasicDataSerializer.deserializeString(objectBytesData);
        } catch (Exception e)
        {
            throw new InvalidFormatException(e, String.class);
        }
    }


}