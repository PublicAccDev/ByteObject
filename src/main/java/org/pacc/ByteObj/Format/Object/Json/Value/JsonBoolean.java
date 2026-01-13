package org.pacc.ByteObj.Format.Object.Json.Value;

import lombok.Setter;
import org.pacc.ByteObj.Format.Object.Json.JsonParser;

public final class JsonBoolean implements JsonValue
{
    @Setter
    private boolean value;

    public JsonBoolean(boolean value)
    {
        this.value = value;
    }

    public static JsonBoolean fromString(String json)
    {
        return (JsonBoolean) JsonParser.parse(json);
    }

    @Override
    public Boolean getValue()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return String.valueOf(value);
    }
}
