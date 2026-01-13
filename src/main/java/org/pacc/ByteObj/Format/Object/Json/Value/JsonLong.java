package org.pacc.ByteObj.Format.Object.Json.Value;

import lombok.Setter;

public final class JsonLong implements JsonValue
{
    @Setter
    private Long value;

    public JsonLong(Long value)
    {
        this.value = value;
    }

    @Override
    public Long getValue()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return String.valueOf(value);
    }
}
