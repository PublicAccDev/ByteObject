package org.pacc.ByteObj.Format.ByteObject.Json;

import org.pacc.ByteObj.DirectByteObj;
import org.pacc.ByteObj.Format.Object.Json.Value.*;
import org.pacc.ByteObj.Serializer.FormatObjSerializer;

public class BJsonProperty extends DirectByteObj<JsonProperty>
{
    public BJsonProperty(JsonProperty object)
    {
        super(object);
    }

    public <jsonValue extends JsonValue> BJsonProperty(String name, jsonValue value)
    {
        super(new JsonProperty(name, value));
    }

    public BJsonProperty(String name, String value)
    {
        super(new JsonProperty(name, value));
    }

    public BJsonProperty(String name, boolean value)
    {
        super(new JsonProperty(name, value));
    }

    public BJsonProperty(String name, Double value)
    {
        super(new JsonProperty(name, value));
    }

    public BJsonProperty(String name, Float value)
    {
        super(new JsonProperty(name, value));
    }

    public BJsonProperty(String name, Integer value)
    {
        super(new JsonProperty(name, value));
    }

    public BJsonProperty(String name, Long value)
    {
        super(new JsonProperty(name, value));
    }

    public BJsonProperty(String name, JsonArray value)
    {
        super(new JsonProperty(name, value));
    }


    public <jsonValue extends JsonValue> BJsonProperty(String name, jsonValue[] value)
    {
        super(new JsonProperty(name, value));
    }

    public BJsonProperty(String name, JsonObj value)
    {
        super(new JsonProperty(name, value));
    }

    public BJsonProperty(String name)
    {
        super(new JsonProperty(name, new JsonNull()));
    }

    public BJsonProperty(byte[] objectBytesData)
    {
        super(objectBytesData);
    }

    @Override
    public byte[] serialize(JsonProperty object)
    {
        return FormatObjSerializer.serialize(object);
    }

    @Override
    public JsonProperty deserialize(byte[] objectBytesData)
    {
        return FormatObjSerializer.deserializeJsonProperty(objectBytesData);
    }

    public void setName(String name)
    {
        JsonProperty property = this.getObject();
        property.setName(name);
        this.setObject(property);
    }

    public String getAsString()
    {
        return this.getObject().getAsString();
    }

    public Boolean getAsBoolean()
    {
        return this.getObject().getAsBoolean();
    }

    public Double getAsDouble()
    {
        return this.getObject().getAsDouble();
    }

    public Float getAsFloat()
    {
        return this.getObject().getAsFloat();
    }

    public Integer getAsInteger()
    {
        return this.getObject().getAsInteger();
    }

    public Long getAsLong()
    {
        return this.getObject().getAsLong();
    }

    public JsonValue[] getAsJsonArray()
    {
        return this.getObject().getAsJsonArray();
    }

    public JsonObj getAsJsonObj()
    {
        return this.getObject().getAsJsonObj();
    }

    public boolean isNull()
    {
        return this.getObject().isNull();
    }
}
