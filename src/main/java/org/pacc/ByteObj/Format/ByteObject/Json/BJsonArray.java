package org.pacc.ByteObj.Format.ByteObject.Json;

import org.pacc.ByteObj.DirectByteObj;
import org.pacc.ByteObj.Format.Object.Json.Value.JsonArray;
import org.pacc.ByteObj.Format.Object.Json.Value.JsonValue;
import org.pacc.ByteObj.Serializer.FormatObjSerializer;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class BJsonArray extends DirectByteObj<JsonArray>
{
    public BJsonArray(JsonArray object)
    {
        super(object);
    }

    public BJsonArray(JsonValue... array)
    {
        super(new JsonArray(array));
    }

    public BJsonArray(Collection<JsonValue> array)
    {
        super(new JsonArray(array));
    }

    public BJsonArray(byte[] objectBytesData)
    {
        super(objectBytesData);
    }

    @Override
    public byte[] serialize(JsonArray object)
    {
        return FormatObjSerializer.serialize(object);
    }

    @Override
    public JsonArray deserialize(byte[] objectBytesData)
    {
        return FormatObjSerializer.deserializeJsonArray(objectBytesData);
    }
}
