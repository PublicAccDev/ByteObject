package org.pacc.ByteObj.Format.ByteObject.Json;

import org.pacc.ByteObj.DirectByteObj;
import org.pacc.ByteObj.Format.Object.Json.Value.JsonProperty;
import org.pacc.ByteObj.Serializer.FormatObjSerializer;

public class BJsonProperty extends DirectByteObj<JsonProperty>
{
    public BJsonProperty(JsonProperty object) {
        super(object);
    }

    public BJsonProperty(byte[] objectBytesData) {
        super(objectBytesData);
    }

    @Override
    public byte[] serialize(JsonProperty object) {
        return FormatObjSerializer.serialize(object);
    }

    @Override
    public JsonProperty deserialize(byte[] objectBytesData) {
        return FormatObjSerializer.deserializeJsonProperty(objectBytesData);
    }
}
