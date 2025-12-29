package org.pacc.ByteObj.BasicData;

import lombok.Setter;
import org.pacc.ByteObj.CacheByteObj;
import org.pacc.ByteObj.Serializer.BasicDataSerializer;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class BString extends CacheByteObj<String>
{
    @Setter
    private Charset charset;

    public BString(String object)
    {
        super(object);
    }

    public BString(String object, Charset charset)
    {
        super(object);
        this.charset = charset;
    }

    public BString(byte[] objectBytesData)
    {
        super(objectBytesData);
    }

    @Override
    public byte[] serialize(String object)
    {
        return BasicDataSerializer.serialize(object, this.getCharset());
    }

    @Override
    public String deserialize(byte[] objectBytesData)
    {
        return BasicDataSerializer.deserializeString(objectBytesData, this.getCharset());
    }

    private Charset getCharset()
    {
        return Optional.ofNullable(charset).orElse(StandardCharsets.UTF_8);
    }
}
