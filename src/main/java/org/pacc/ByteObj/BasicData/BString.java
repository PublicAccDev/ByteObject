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

public class BString extends CacheByteObj<String> implements Serializable
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
        return SerializableSerializer.serialize(object);
    }

    @Override
    public String deserialize(byte[] objectBytesData)
    {
        try
        {
            return (String) SerializableSerializer.deserialize(objectBytesData);
        } catch (ClassCastException e)
        {
            throw new InvalidFormatException(e, String.class);
        }
    }
}
