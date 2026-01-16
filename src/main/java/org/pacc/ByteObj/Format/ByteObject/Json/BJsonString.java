package org.pacc.ByteObj.Format.ByteObject.Json;

import org.pacc.ByteObj.DirectByteObj;
import org.pacc.ByteObj.Format.Object.Json.Value.JsonString;
import org.pacc.ByteObj.Serializer.FormatObjSerializer;

public class BJsonString extends DirectByteObj<JsonString>
{
    public BJsonString(JsonString object)
    {
        super(object);
    }

    public BJsonString(String value)
    {
        super(new JsonString(value));
    }

    public BJsonString(byte[] objectBytesData)
    {
        super(objectBytesData);
    }

    @Override
    public byte[] serialize(JsonString object)
    {
        return FormatObjSerializer.serialize(object);
    }

    @Override
    public JsonString deserialize(byte[] objectBytesData)
    {
        return FormatObjSerializer.deserializeJsonString(objectBytesData);
    }
}
