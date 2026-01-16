package org.pacc.ByteObj.Format.ByteObject.Json;

import org.pacc.ByteObj.DirectByteObj;
import org.pacc.ByteObj.Format.Object.Json.Value.JsonNull;
import org.pacc.ByteObj.Serializer.FormatObjSerializer;

public class BJsonNull extends DirectByteObj<JsonNull>
{
    public BJsonNull(JsonNull object)
    {
        super(object);
    }

    public BJsonNull()
    {
        super(new JsonNull());
    }

    public BJsonNull(byte[] objectBytesData)
    {
        super(objectBytesData);
    }

    @Override
    public byte[] serialize(JsonNull object)
    {
        return FormatObjSerializer.serialize(object);
    }

    @Override
    public JsonNull deserialize(byte[] objectBytesData)
    {
        return FormatObjSerializer.deserializeJsonNull(objectBytesData);
    }
}
