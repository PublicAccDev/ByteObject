package org.pacc.ByteObj.Format.Object.Json.Value;

import lombok.Getter;
import org.pacc.ByteObj.Format.Object.Json.JsonParser;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class JsonObj implements JsonValue
{
    @Getter
    private final List<JsonProperty> properties;
    private Map<String, JsonProperty> propertyMap;
    private Set<String> keySet;

    public JsonObj(JsonProperty... properties)
    {
        this.properties = new ArrayList<>(List.of(properties));
        buildPropertyMap();
    }

    public JsonObj()
    {
        this.properties = new ArrayList<>();
        this.propertyMap = new HashMap<>();
        this.keySet = new HashSet<>();
    }

    public static JsonObj fromString(String json)
    {
        return (JsonObj) JsonParser.parse(json);
    }

    private void buildPropertyMap()
    {
        this.propertyMap = new HashMap<>();
        this.keySet = new HashSet<>();
        for (JsonProperty property : properties)
        {
            propertyMap.put(property.getName(), property);
            keySet.add(property.getName());
        }
    }

    private void updateCache(JsonProperty property)
    {
        propertyMap.put(property.getName(), property);
        keySet.add(property.getName());
    }

    private void removeFromCache(String key)
    {
        propertyMap.remove(key);
        keySet.remove(key);
    }

    public String getPropertyAsString(String key)
    {
        return getProperty(key).getAsString();
    }

    public Boolean getPropertyAsBoolean(String key)
    {
        return getProperty(key).getAsBoolean();
    }

    public Double getPropertyAsDouble(String key)
    {
        return getProperty(key).getAsDouble();
    }

    public Float getPropertyAsFloat(String key)
    {
        return getProperty(key).getAsFloat();
    }

    public Integer getPropertyAsInteger(String key)
    {
        return getProperty(key).getAsInteger();
    }

    public Long getPropertyAsLong(String key)
    {
        return getProperty(key).getAsLong();
    }

    public JsonValue[] getPropertyAsJsonArray(String key)
    {
        return getProperty(key).getAsJsonArray();
    }

    public JsonObj getPropertyAsJsonObj(String key)
    {
        return getProperty(key).getAsJsonObj();
    }

    public List<JsonProperty> getStringProperties()
    {
        return properties.stream().filter(o -> o.getValue() instanceof JsonString).collect(Collectors.toList());
    }

    public List<JsonProperty> getBooleanProperties()
    {
        return properties.stream().filter(o -> o.getValue() instanceof JsonBoolean).collect(Collectors.toList());
    }

    public List<JsonProperty> getDoubleProperties()
    {
        return properties.stream().filter(o -> o.getValue() instanceof JsonDouble).collect(Collectors.toList());
    }

    public List<JsonProperty> getFloatProperties()
    {
        return properties.stream().filter(o -> o.getValue() instanceof JsonFloat).collect(Collectors.toList());
    }

    public List<JsonProperty> getIntegerProperties()
    {
        return properties.stream().filter(o -> o.getValue() instanceof JsonInteger).collect(Collectors.toList());
    }

    public List<JsonProperty> getLongProperties()
    {
        return properties.stream().filter(o -> o.getValue() instanceof JsonLong).collect(Collectors.toList());
    }

    public List<JsonProperty> getJsonArrayProperties()
    {
        return properties.stream().filter(o -> o.getValue() instanceof JsonArray).collect(Collectors.toList());
    }

    public List<JsonProperty> getJsonObjProperties()
    {
        return properties.stream().filter(o -> o.getValue() instanceof JsonObj).collect(Collectors.toList());
    }

    public boolean isNull(String key)
    {
        return getProperty(key).isNull();
    }

    public int size()
    {
        return properties.size();
    }

    public List<JsonProperty> findProperties(Predicate<JsonProperty> condition)
    {
        return properties.stream().filter(condition).toList();
    }


    public List<JsonProperty> getProperties(int indexFrom, int indexTo)
    {
        return properties.subList(indexFrom, indexTo);
    }

    public JsonProperty getProperty(String key)
    {
        return propertyMap.get(key);
    }

    public JsonProperty getProperty(int index)
    {
        return properties.get(index);
    }

    public void forEach(Consumer<JsonProperty> consumer)
    {
        properties.forEach(consumer);
    }

    public void addProperty(JsonProperty... property)
    {
        for (JsonProperty p : property)
        {
            if (!propertyMap.containsKey(p.getName()))
            {
                properties.add(p);
                updateCache(p);
            }
        }
    }

    public void addProperty(Collection<JsonProperty> property)
    {
        for (JsonProperty p : property)
        {
            if (!propertyMap.containsKey(p.getName()))
            {
                properties.add(p);
                updateCache(p);
            }
        }
    }

    public void removeProperty(String... key)
    {
        for (String k : key)
        {
            properties.removeIf(property -> {
                boolean toRemove = property.getName().equals(k);
                if (toRemove)
                {
                    removeFromCache(k);
                }
                return toRemove;
            });
        }
    }

    public void removeProperty(Collection<String> property)
    {
        removeProperty(property.toArray(new String[0]));
    }

    public void clear()
    {
        properties.clear();
        propertyMap.clear();
        keySet.clear();
    }

    public boolean has(String... key)
    {
        return keySet.containsAll(List.of(key));
    }

    public boolean has(Collection<String> key)
    {
        return keySet.containsAll(key);
    }

    public Set<String> getPropertiesKeys()
    {
        return new HashSet<>(keySet);
    }

    public Set<String> getPropertiesKeys(Collection<JsonProperty> c)
    {
        Set<String> names = new HashSet<>();
        c.forEach(property -> names.add(property.getName()));
        return names;
    }

    public Set<String> getPropertiesKeys(JsonProperty... c)
    {
        Set<String> names = new HashSet<>();
        Set.of(c).forEach(property -> names.add(property.getName()));
        return names;
    }

    public JsonObj merge(JsonObj org, JsonObj other)
    {
        JsonObj merged = new JsonObj();

        for (JsonProperty property : org.properties)
        {
            if (!merged.propertyMap.containsKey(property.getName()))
            {
                merged.properties.add(property);
                merged.updateCache(property);
            }
        }


        if (other != null)
        {
            for (JsonProperty property : other.properties)
            {
                if (!merged.propertyMap.containsKey(property.getName()))
                {
                    merged.properties.add(property);
                    merged.updateCache(property);
                }
            }
        }

        return merged;
    }

    public JsonObj mergeWithOverride(JsonObj org, JsonObj other)
    {
        JsonObj merged = new JsonObj();


        for (JsonProperty property : org.properties)
        {
            merged.properties.add(property);
            merged.updateCache(property);
        }


        if (other != null)
        {
            for (JsonProperty property : other.properties)
            {
                if (merged.propertyMap.containsKey(property.getName()))
                {

                    merged.properties.removeIf(p -> p.getName().equals(property.getName()));
                }
                merged.properties.add(property);
                merged.updateCache(property);
            }
        }

        return merged;
    }

    public void mergeInto(JsonObj target)
    {
        if (target == null)
        {
            return;
        }

        for (JsonProperty property : this.properties)
        {
            if (!target.propertyMap.containsKey(property.getName()))
            {
                target.properties.add(property);
                target.updateCache(property);
            }
        }
    }

    public void mergeIntoWithOverride(JsonObj target)
    {
        if (target == null)
        {
            return;
        }

        for (JsonProperty property : this.properties)
        {
            if (target.propertyMap.containsKey(property.getName()))
            {
                target.properties.removeIf(p -> p.getName().equals(property.getName()));
            }
            target.properties.add(property);
            target.updateCache(property);
        }
    }

    public void mergeFrom(JsonObj source)
    {
        if (source == null)
        {
            return;
        }

        for (JsonProperty property : source.properties)
        {
            if (!this.propertyMap.containsKey(property.getName()))
            {
                this.properties.add(property);
                this.updateCache(property);
            }
        }
    }

    public void mergeFromWithOverride(JsonObj source)
    {
        if (source == null)
        {
            return;
        }

        for (JsonProperty property : source.properties)
        {
            if (this.propertyMap.containsKey(property.getName()))
            {
                this.properties.removeIf(p -> p.getName().equals(property.getName()));
            }
            this.properties.add(property);
            this.updateCache(property);
        }
    }


    @Override
    public List<JsonProperty> getValue()
    {
        return properties;
    }

    @Override
    public String toString()
    {
        if (properties.isEmpty())
        {
            return "{}";
        }

        StringBuilder sb = new StringBuilder(properties.size() * 32); // 预估容量
        sb.append("{");

        boolean first = true;
        for (JsonProperty property : properties)
        {
            if (!first)
            {
                sb.append(",");
            }

            sb.append("\"").append(escapeJsonString(property.getName())).append("\"");
            sb.append(":");
            sb.append(property.getValue().toString());

            first = false;
        }

        sb.append("}");
        return sb.toString();
    }


    private String escapeJsonString(String input)
    {
        if (input == null)
        {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (char c : input.toCharArray())
        {
            switch (c)
            {
                case '"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                default:
                    if (c < 0x20)
                    {
                        sb.append(String.format("\\u%04x", (int) c));
                    }
                    else
                    {
                        sb.append(c);
                    }
                    break;
            }
        }
        return sb.toString();
    }
}
