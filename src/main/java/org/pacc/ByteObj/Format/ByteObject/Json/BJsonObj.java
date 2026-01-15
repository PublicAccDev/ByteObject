package org.pacc.ByteObj.Format.ByteObject.Json;

import org.pacc.ByteObj.DirectByteObj;
import org.pacc.ByteObj.Format.Object.Json.Value.*;
import org.pacc.ByteObj.Serializer.FormatObjSerializer;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BJsonObj extends DirectByteObj<JsonObj>
{
    public BJsonObj(JsonObj object)
    {
        super(object);
    }

    public BJsonObj(byte[] objectBytesData)
    {
        super(objectBytesData);
    }

    @Override
    public byte[] serialize(JsonObj object)
    {
        return FormatObjSerializer.serialize(object);
    }

    @Override
    public JsonObj deserialize(byte[] objectBytesData)
    {
        return FormatObjSerializer.deserializeJsonObj(objectBytesData);
    }

    public String getPropertyAsString(String key)
    {
        return this.getObject().getPropertyAsString(key);
    }

    public Boolean getPropertyAsBoolean(String key)
    {
        return this.getObject().getPropertyAsBoolean(key);
    }

    public Double getPropertyAsDouble(String key)
    {
        return this.getObject().getPropertyAsDouble(key);
    }

    public Float getPropertyAsFloat(String key)
    {
        return this.getObject().getPropertyAsFloat(key);
    }

    public Integer getPropertyAsInteger(String key)
    {
        return this.getObject().getPropertyAsInteger(key);
    }

    public Long getPropertyAsLong(String key)
    {
        return this.getObject().getPropertyAsLong(key);
    }

    public JsonValue[] getPropertyAsJsonArray(String key)
    {
        return this.getObject().getPropertyAsJsonArray(key);
    }

    public JsonObj getPropertyAsJsonObj(String key)
    {
        return this.getObject().getPropertyAsJsonObj(key);
    }

    public List<JsonProperty> getStringProperties()
    {
        return this.getObject().getStringProperties();
    }

    public List<JsonProperty> getBooleanProperties()
    {
        return this.getObject().getBooleanProperties();
    }

    public List<JsonProperty> getDoubleProperties()
    {
        return this.getObject().getDoubleProperties();
    }

    public List<JsonProperty> getFloatProperties()
    {
        return this.getObject().getFloatProperties();
    }

    public List<JsonProperty> getIntegerProperties()
    {
        return this.getObject().getIntegerProperties();
    }

    public List<JsonProperty> getLongProperties()
    {
        return this.getObject().getLongProperties();
    }

    public List<JsonProperty> getJsonArrayProperties()
    {
        return this.getObject().getJsonArrayProperties();
    }

    public List<JsonProperty> getJsonObjProperties()
    {
        return this.getObject().getJsonObjProperties();
    }

    public boolean isNull(String key)
    {
        return this.getObject().isNull(key);
    }

    public int size()
    {
        return this.getObject().size();
    }

    public List<JsonProperty> findProperties(Predicate<JsonProperty> condition)
    {
        return this.getObject().findProperties(condition);
    }


    public List<JsonProperty> getProperties(int indexFrom, int indexTo)
    {
        return this.getObject().getProperties(indexFrom, indexTo);
    }

    public JsonProperty getProperty(String key)
    {
        return this.getObject().getProperty(key);
    }

    public JsonProperty getProperty(int index)
    {
        return this.getObject().getProperty(index);
    }

    public void forEach(Consumer<JsonProperty> consumer)
    {
        this.getObject().forEach(consumer);
    }

    public void addProperty(JsonProperty... property)
    {
        JsonObj obj = this.getObject();
        obj.addProperty(property);
        this.setObject(obj);
    }

    public void addProperty(Collection<JsonProperty> property)
    {
        JsonObj obj = this.getObject();
        obj.addProperty(property);
        this.setObject(obj);
    }

    public void removeProperty(String... key)
    {
        JsonObj obj = this.getObject();
        obj.removeProperty(key);
        this.setObject(obj);
    }

    public void removeProperty(Collection<String> property)
    {
        JsonObj obj = this.getObject();
        obj.removeProperty(property);
        this.setObject(obj);
    }

    public void clear()
    {
        JsonObj obj = this.getObject();
        obj.clear();
        this.setObject(obj);
    }

    public boolean has(String... key)
    {
        return this.getObject().has(key);
    }

    public boolean has(Collection<String> key)
    {
        return this.getObject().has(key);
    }

    public Set<String> getPropertiesKeys()
    {
        return this.getObject().getPropertiesKeys();
    }

    public Set<String> getPropertiesKeys(Collection<JsonProperty> c)
    {
        return this.getObject().getPropertiesKeys(c);
    }

    public Set<String> getPropertiesKeys(JsonProperty... c)
    {
        return this.getObject().getPropertiesKeys(c);
    }

    public JsonObj merge(JsonObj org, JsonObj other)
    {
        return this.getObject().merge(org, other);
    }

    public JsonObj mergeWithOverride(JsonObj org, JsonObj other)
    {
        return this.getObject().mergeWithOverride(org, other);
    }

    public void mergeInto(JsonObj target)
    {
        JsonObj obj = this.getObject();
        obj.mergeInto(target);
        this.setObject(obj);
    }

    public void mergeIntoWithOverride(JsonObj target)
    {
        JsonObj obj = this.getObject();
        obj.mergeIntoWithOverride(target);
        this.setObject(obj);
    }

    public void mergeFrom(JsonObj source)
    {
        JsonObj obj = this.getObject();
        obj.mergeFrom(source);
        this.setObject(obj);
    }

    public void mergeFromWithOverride(JsonObj source)
    {
        JsonObj obj = this.getObject();
        obj.mergeWithOverride(obj, source);
        this.setObject(obj);
    }
}
