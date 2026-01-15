package org.pacc.ByteObj.BasicData;

import lombok.Setter;
import org.pacc.ByteObj.CacheByteObj;
import org.pacc.ByteObj.Exception.InvalidFormatException;
import org.pacc.ByteObj.Serializer.BasicDataSerializer;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class BCharArray extends CacheByteObj<char[]>
{
    @Setter
    private Charset charset;

    public BCharArray(char[] object)
    {
        super(object);
    }

    public BCharArray(char[] object, Charset charset)
    {
        super(object);
        this.charset = charset;
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
            return BasicDataSerializer.serialize(object, this.getCharset());
        } catch (Exception e)
        {
            throw new InvalidFormatException(e, char[].class);
        }
    }

    @Override
    public char[] deserialize(byte[] objectBytesData)
    {
        return BasicDataSerializer.deserializeChars(objectBytesData, this.getCharset());
    }

    private Charset getCharset()
    {
        return Optional.ofNullable(charset).orElse(StandardCharsets.UTF_8);
    }
}