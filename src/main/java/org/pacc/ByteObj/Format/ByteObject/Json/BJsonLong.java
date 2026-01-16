package org.pacc.ByteObj.Format.ByteObject.Json;

import org.pacc.ByteObj.DirectByteObj;
import org.pacc.ByteObj.Format.Object.Json.Value.JsonInteger;
import org.pacc.ByteObj.Format.Object.Json.Value.JsonLong;
import org.pacc.ByteObj.Serializer.FormatObjSerializer;

public class BJsonLong extends DirectByteObj<JsonLong>
{
    public BJsonLong(JsonLong object)
    {
        super(object);
    }

    public BJsonLong(Long value)
    {
        super(new JsonLong(value));
    }

    public BJsonLong(byte[] objectBytesData)
    {
        super(objectBytesData);
    }

    @Override
    public byte[] serialize(JsonLong object)
    {
        return FormatObjSerializer.serialize(object);
    }

    @Override
    public JsonLong deserialize(byte[] objectBytesData)
    {
        return FormatObjSerializer.deserializeJsonLong(objectBytesData);
    }
}
