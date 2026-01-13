package org.pacc.ByteObj.Format.Object.Json.Value;

import org.pacc.ByteObj.Format.Object.Json.JsonParser;

public final class JsonNull implements JsonValue
{
    public static JsonNull fromString(String json)
    {
        return (JsonNull) JsonParser.parse(json);
    }

    @Override
    public Object getValue()
    {
        return null;
    }

    @Override
    public String toString()
    {
        return "null";
    }
}
