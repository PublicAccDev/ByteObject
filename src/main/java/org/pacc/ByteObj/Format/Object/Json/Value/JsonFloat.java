package org.pacc.ByteObj.Format.Object.Json.Value;

import lombok.Setter;

public final class JsonFloat implements JsonValue
{
    @Setter
    private Float value;

    public JsonFloat(Float value)
    {
        this.value = value;
    }

    @Override
    public Float getValue()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return String.valueOf(value);
    }
}
