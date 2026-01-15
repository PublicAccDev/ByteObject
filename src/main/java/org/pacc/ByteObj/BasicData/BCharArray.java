package org.pacc.ByteObj.BasicData;

import org.pacc.ByteObj.DirectByteObj;
import org.pacc.ByteObj.Exception.InvalidFormatException;
import org.pacc.ByteObj.Serializer.BasicDataSerializer;

public class BCharArray extends DirectByteObj<char[]>
{
    public BCharArray(char[] object)
    {
        super(object);
    }

    public BCharArray(byte[] objectBytesData)
    {
        super(objectBytesData);
    }

    @Override
    public byte[] serialize(char[] object) throws InvalidFormatException
    {
        try
        {
            return BasicDataSerializer.serialize(object);
        } catch (Exception e)
        {
            throw new InvalidFormatException(e, char[].class);
        }
    }

    @Override
    public char[] deserialize(byte[] objectBytesData)
    {
        return BasicDataSerializer.deserializeChars(objectBytesData);
    }


}