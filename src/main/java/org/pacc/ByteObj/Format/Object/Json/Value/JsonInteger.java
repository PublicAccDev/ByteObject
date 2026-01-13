package org.pacc.ByteObj.Format.Object.Json.Value;

import lombok.Setter;

public final class JsonInteger implements JsonValue
{
    @Setter
    private Integer value;

    public JsonInteger(Integer value)
    {
        this.value = value;
    }

    @Override
    public Integer getValue()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return String.valueOf(value);
    }
}
