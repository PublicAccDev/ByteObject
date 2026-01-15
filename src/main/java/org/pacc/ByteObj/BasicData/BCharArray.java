package org.pacc.ByteObj.BasicData;

import lombok.Setter;
import org.pacc.ByteObj.CacheByteObj;
import org.pacc.ByteObj.Exception.InvalidFormatException;
import org.pacc.ByteObj.Serializer.BasicDataSerializer;
import org.pacc.ByteObj.Serializer.SerializableSerializer;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class BCharArray extends CacheByteObj<Character[]> implements Serializable
{
    public BCharArray(Character[] object)
    {
        super(object);
    }

    public BCharArray(byte[] objectBytesData)
    {
        super(objectBytesData);
    }

    @Override
    public byte[] serialize(Character[] object)
    {
        return SerializableSerializer.serialize(object);
    }

    @Override
    public Character[] deserialize(byte[] objectBytesData) throws InvalidFormatException
    {
        try
        {
            return (Character[]) SerializableSerializer.deserialize(objectBytesData);
        } catch (ClassCastException e)
        {
            throw new InvalidFormatException(e, Character[].class);
        }
    }
}
