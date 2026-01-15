package org.pacc.ByteObj.Format.ByteObject.Json;

import org.pacc.ByteObj.DirectByteObj;
import org.pacc.ByteObj.Format.Object.Json.Value.JsonBoolean;

public class BJsonBoolean extends DirectByteObj<JsonBoolean>
{
    public BJsonBoolean(JsonBoolean object)
    {
        super(object);
    }

    public BJsonBoolean(byte[] objectBytesData)
    {
        super(objectBytesData);
    }

    @Override
    public byte[] serialize(JsonBoolean object)
    {
        return new byte[0];
    }

    @Override
    public JsonBoolean deserialize(byte[] objectBytesData)
    {
        return null;
    }
}
