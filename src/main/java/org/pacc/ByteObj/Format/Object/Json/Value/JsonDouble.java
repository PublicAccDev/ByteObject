package org.pacc.ByteObj.Format.Object.Json.Value;

import lombok.Setter;

public final class JsonDouble implements JsonValue
{
    @Setter
    private Double value;

    public JsonDouble(Double value)
    {
        this.value = value;
    }

    @Override
    public Double getValue()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return String.valueOf(value);
    }
}
