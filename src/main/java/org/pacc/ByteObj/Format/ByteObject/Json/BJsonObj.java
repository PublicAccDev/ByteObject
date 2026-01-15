package org.pacc.ByteObj.Format.ByteObject.Json;

import org.pacc.ByteObj.DirectByteObj;
import org.pacc.ByteObj.Format.Object.Json.Value.JsonObj;
import org.pacc.ByteObj.Serializer.FormatObjSerializer;

public class BJsonObj extends DirectByteObj<JsonObj>
{
    public BJsonObj(JsonObj object) {
        super(object);
    }

    public BJsonObj(byte[] objectBytesData) {
        super(objectBytesData);
    }

    @Override
    public byte[] serialize(JsonObj object) {
        return FormatObjSerializer.serialize(object);
    }

    @Override
    public JsonObj deserialize(byte[] objectBytesData) {
        return FormatObjSerializer.deserializeJsonObj(objectBytesData);
    }
}
