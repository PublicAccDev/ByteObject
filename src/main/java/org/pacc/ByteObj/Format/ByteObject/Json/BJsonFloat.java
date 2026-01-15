package org.pacc.ByteObj.Format.ByteObject.Json;

import org.pacc.ByteObj.DirectByteObj;
import org.pacc.ByteObj.Format.Object.Json.Value.JsonFloat;
import org.pacc.ByteObj.Serializer.FormatObjSerializer;

public class BJsonFloat extends DirectByteObj<JsonFloat>
{
    public BJsonFloat(JsonFloat object) {
        super(object);
    }

    public BJsonFloat(byte[] objectBytesData) {
        super(objectBytesData);
    }

    @Override
    public byte[] serialize(JsonFloat object) {
        return FormatObjSerializer.serialize(object);
    }

    @Override
    public JsonFloat deserialize(byte[] objectBytesData) {
        return FormatObjSerializer.deserializeJsonFloat(objectBytesData);
    }
}
