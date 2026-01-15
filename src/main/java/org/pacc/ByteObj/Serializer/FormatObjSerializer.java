package org.pacc.ByteObj.Serializer;

import org.pacc.ByteObj.Format.Object.CSVObj;
import org.pacc.ByteObj.Format.Object.Json.JsonParser;
import org.pacc.ByteObj.Format.Object.Json.Value.*;

public class FormatObjSerializer
{
    public static byte[] serialize(CSVObj object)
    {
        return object.toCSVString().getBytes();
    }

    public static CSVObj deserializeCSValues(byte[] bytes)
    {
        return CSVObj.fromCSVString(new String(bytes));
    }

    public static byte[] serialize(JsonProperty property)
    {
        return property.toString().getBytes();
    }

    public static JsonProperty deserializeJsonProperty(byte[] bytes)
    {
        return JsonParser.parseProperty(new String(bytes));
    }

    public static byte[] serialize(JsonArray object)
    {
        return object.toString().getBytes();
    }

    public static JsonArray deserializeJsonArray(byte[] bytes)
    {
        return JsonParser.parseArray(new String(bytes));
    }

    public static byte[] serialize(JsonBoolean object)
    {
        return BasicDataSerializer.serialize(object.getValue());
    }

    public static JsonBoolean deserializeJsonBoolean(byte[] bytes)
    {
        return new JsonBoolean(BasicDataSerializer.deserializeBoolean(bytes));
    }

    public static byte[] serialize(JsonDouble object)
    {
        return BasicDataSerializer.serialize(object.getValue());
    }

    public static JsonDouble deserializeJsonDouble(byte[] bytes)
    {
        return new JsonDouble(BasicDataSerializer.deserializeDouble(bytes));
    }

    public static byte[] serialize(JsonFloat object)
    {
        return BasicDataSerializer.serialize(object.getValue());
    }

    public static JsonFloat deserializeJsonFloat(byte[] bytes)
    {
        return new JsonFloat(BasicDataSerializer.deserializeFloat(bytes));
    }

    public static byte[] serialize(JsonInteger object)
    {
        return BasicDataSerializer.serialize(object.getValue());
    }

    public static JsonInteger deserializeJsonInteger(byte[] bytes)
    {
        return new JsonInteger(BasicDataSerializer.deserializeInteger(bytes));
    }

    public static byte[] serialize(JsonLong object)
    {
        return BasicDataSerializer.serialize(object.getValue());
    }

    public static JsonLong deserializeJsonLong(byte[] bytes)
    {
        return new JsonLong(BasicDataSerializer.deserializeLong(bytes));
    }

    public static byte[] serialize(JsonNull object)
    {
        return new byte[]{0, 0};
    }

    public static JsonNull deserializeJsonNull(byte[] bytes)
    {
        return new JsonNull();
    }

    public static byte[] serialize(JsonString object)
    {
        return BasicDataSerializer.serialize(object.getValue());
    }

    public static JsonString deserializeJsonString(byte[] bytes)
    {
        return new JsonString(BasicDataSerializer.deserializeString(bytes));
    }

    public static byte[] serialize(JsonObj object)
    {
        return object.toString().getBytes();
    }

    public static JsonObj deserializeJsonObj(byte[] bytes)
    {
        return JsonObj.fromString(new String(bytes));
    }


}
