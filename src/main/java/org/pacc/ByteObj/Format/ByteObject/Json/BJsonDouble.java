package org.pacc.ByteObj.Format.ByteObject.Json;

import org.pacc.ByteObj.DirectByteObj;
import org.pacc.ByteObj.Format.Object.Json.Value.JsonDouble;
import org.pacc.ByteObj.Serializer.FormatObjSerializer;

public class BJsonDouble extends DirectByteObj<JsonDouble>
{
    public BJsonDouble(JsonDouble object)
    {
        super(object);
    }

    public BJsonDouble(byte[] objectBytesData)
    {
        super(objectBytesData);
    }

    @Override
    public byte[] serialize(JsonDouble object)
    {
        return FormatObjSerializer.serialize(object);
    }

    @Override
    public JsonDouble deserialize(byte[] objectBytesData)
    {
        return FormatObjSerializer.deserializeJsonDouble(objectBytesData);
    }
}
