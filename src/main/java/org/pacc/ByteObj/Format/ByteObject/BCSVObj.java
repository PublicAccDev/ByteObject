package org.pacc.ByteObj.Format.ByteObject;

import lombok.Setter;
import org.pacc.ByteObj.CacheByteObj;
import org.pacc.ByteObj.Format.Object.CSVObj;
import org.pacc.ByteObj.Serializer.FormatObjSerializer;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class BCSVObj extends CacheByteObj<CSVObj>
{
    @Setter
    private Charset charset;

    public BCSVObj(CSVObj object)
    {
        super(object);
    }

    public BCSVObj(CSVObj object, Charset charset)
    {
        super(object);
        this.charset = charset;
    }

    public BCSVObj(byte[] objectBytesData)
    {
        super(objectBytesData);
    }

    @Override
    public byte[] serialize(CSVObj object)
    {
        return FormatObjSerializer.serialize(object, this.getCharset());
    }

    @Override
    public CSVObj deserialize(byte[] objectBytesData)
    {
        return FormatObjSerializer.deserializeCSValues(objectBytesData, this.getCharset());
    }

    private Charset getCharset()
    {
        return Optional.ofNullable(this.charset).orElse(StandardCharsets.UTF_8);
    }
}
