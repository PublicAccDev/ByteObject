package org.pacc.ByteObj.Format.Object.Json.Value;

import lombok.Getter;
import lombok.Setter;

public class JsonProperty
{
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private JsonValue value;

    public <jsonValue extends JsonValue> JsonProperty(String name, jsonValue value)
    {
        this.name = name;
        this.value = value;
    }

    public JsonProperty(String name, String value)
    {
        this.name = name;
        this.value = new JsonString(value);
    }

    public JsonProperty(String name, boolean value)
    {
        this.name = name;
        this.value = new JsonBoolean(value);
    }

    public JsonProperty(String name, Double value)
    {
        this.name = name;
        this.value = new JsonDouble(value);
    }

    public JsonProperty(String name, Float value)
    {
        this.name = name;
        this.value = new JsonFloat(value);
    }

    public JsonProperty(String name, Integer value)
    {
        this.name = name;
        this.value = new JsonInteger(value);
    }

    public JsonProperty(String name, Long value)
    {
        this.name = name;
        this.value = new JsonLong(value);
    }

    public JsonProperty(String name, JsonArray value)
    {
        this.name = name;
        this.value = value;
    }


    public <jsonValue extends JsonValue> JsonProperty(String name, jsonValue[] value)
    {
        this.name = name;
        this.value = new JsonArray(value);
    }

    public JsonProperty(String name, JsonObj value)
    {
        this.name = name;
        this.value = value;
    }

    public JsonProperty(String name)
    {
        this.name = name;
        this.value = new JsonNull();
    }

    public String getAsString()
    {
        return ((JsonString) value).getValue();
    }

    public Boolean getAsBoolean()
    {
        return ((JsonBoolean) value).getValue();
    }

    public Double getAsDouble()
    {
        return ((JsonDouble) value).getValue();
    }

    public Float getAsFloat()
    {
        return ((JsonFloat) value).getValue();
    }

    public Integer getAsInteger()
    {
        return ((JsonInteger) value).getValue();
    }

    public Long getAsLong()
    {
        return ((JsonLong) value).getValue();
    }

    public JsonValue[] getAsJsonArray()
    {
        return ((JsonArray) value).getArray();
    }

    public JsonObj getAsJsonObj()
    {
        return (JsonObj) value;
    }

    public boolean isNull()
    {
        return value instanceof JsonNull || value.getValue() == null;
    }
}
