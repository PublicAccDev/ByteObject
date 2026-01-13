package org.pacc.ByteObj.Format.Object.Json.Value;

import org.pacc.ByteObj.Format.Object.Json.JsonParser;

public sealed interface JsonValue permits JsonArray, JsonBoolean, JsonDouble, JsonFloat, JsonInteger, JsonLong, JsonNull, JsonObj, JsonString
{
    static JsonValue fromString(String json)
    {
        return JsonParser.parse(json);
    }

    Object getValue();

    String toString();
}
