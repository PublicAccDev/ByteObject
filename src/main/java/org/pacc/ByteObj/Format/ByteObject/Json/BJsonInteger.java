package org.pacc.ByteObj.Format.ByteObject.Json;

import org.pacc.ByteObj.DirectByteObj;
import org.pacc.ByteObj.Format.Object.Json.Value.JsonInteger;
import org.pacc.ByteObj.Serializer.FormatObjSerializer;

public class BJsonInteger extends DirectByteObj<JsonInteger>
{
    public BJsonInteger(JsonInteger object) {
        super(object);
    }

    public BJsonInteger(byte[] objectBytesData) {
        super(objectBytesData);
    }

    @Override
    public byte[] serialize(JsonInteger object) {
        return FormatObjSerializer.serialize(object);
    }

    @Override
    public JsonInteger deserialize(byte[] objectBytesData) {
        return FormatObjSerializer.deserializeJsonInteger(objectBytesData);
    }
}
