package org.pacc.ByteObj.Format.Object.Json.Value;

import lombok.Setter;
import org.pacc.ByteObj.Format.Object.Json.JsonParser;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public final class JsonArray implements JsonValue
{
    @Setter
    private List<JsonValue> array;

    public JsonArray(JsonValue... array)
    {
        this.array = List.of(array);
    }

    public JsonArray(Collection<JsonValue> array)
    {
        this.array = new LinkedList<>(array);
    }

    public static JsonArray fromString(String json)
    {
        return (JsonArray) JsonParser.parse(json);
    }

    public JsonValue[] getArray()
    {
        return array.toArray(new JsonValue[0]);
    }

    @Override
    public List<JsonValue> getValue()
    {
        return array;
    }

    @Override
    public String toString()
    {
        List<String> stringList = new LinkedList<>();
        for (JsonValue jsonValue : array)
        {
            stringList.add(jsonValue.toString());
        }
        return "[" + String.join(",", stringList) + "]";
    }
}
