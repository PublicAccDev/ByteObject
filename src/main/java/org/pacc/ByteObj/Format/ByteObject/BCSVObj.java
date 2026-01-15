package org.pacc.ByteObj.Format.ByteObject;

import lombok.Setter;
import org.pacc.ByteObj.DirectByteObj;
import org.pacc.ByteObj.Format.Object.CSVObj;
import org.pacc.ByteObj.Serializer.FormatObjSerializer;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class BCSVObj extends DirectByteObj<CSVObj>
{
    public BCSVObj(CSVObj object)
    {
        super(object);
    }

    public BCSVObj(byte[] objectBytesData)
    {
        super(objectBytesData);
    }

    @Override
    public byte[] serialize(CSVObj object)
    {
        return FormatObjSerializer.serialize(object);
    }

    @Override
    public CSVObj deserialize(byte[] objectBytesData)
    {
        return FormatObjSerializer.deserializeCSValues(objectBytesData);
    }
}
