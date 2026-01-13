package org.pacc.ByteObj.Serializer;

import org.pacc.ByteObj.Container.BArray;
import org.pacc.ByteObj.Format.Object.CSVObj;
import org.pacc.ByteObj.Format.Object.Json.Value.*;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class FormatObjSerializer
{
    public static byte[] serialize(CSVObj object, Charset charset)
    {
        return object.toCSVString().getBytes(charset);
    }

    public static CSVObj deserializeCSValues(byte[] bytes, Charset charset)
    {
        return CSVObj.fromCSVString(new String(bytes, charset));
    }

    public static byte[] serialize(JsonProperty property)
    {
        byte[] name = BasicDataSerializer.serialize(property.getName(), StandardCharsets.UTF_8);
        int nameLength = name.length;
        byte[] reserved = new byte[0];
        switch (property.getValue())
        {
            case JsonArray obj ->
            {

            }
            case JsonBoolean obj ->
            {
                reserved = serialize(obj);

            }
            case JsonDouble obj ->
            {
                reserved = serialize(obj);
            }
            case JsonFloat obj ->
            {
                reserved = serialize(obj);
            }
            case JsonInteger obj ->
            {
                reserved = serialize(obj);
            }
            case JsonLong obj ->
            {
                reserved = serialize(obj);
            }
            case JsonNull obj ->
            {
                reserved = serialize(obj);
            }
            case JsonObj obj ->
            {

            }
            case JsonString obj ->
            {
                reserved = serialize(obj, StandardCharsets.UTF_8);
            }
        }
        ByteBuffer buffer = ByteBuffer.allocate(
                4
                + nameLength
                + reserved.length
        );
        buffer.putInt(nameLength);
        buffer.put(name);
        buffer.put(reserved);
        return buffer.array();
    }

    public static byte[] serialize(JsonArray object)
    {
        return null;
    }

    public static JsonArray deserializeJsonArray(byte[] bytes)
    {
        return null;
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

    public static byte[] serialize(JsonObj object)
    {
        return null;
    }

    public static JsonObj deserializeJsonObj(byte[] bytes)
    {
        return null;
    }

    public static byte[] serialize(JsonString object, Charset charset)
    {
        byte[] str = BasicDataSerializer.serialize(object.getValue(), charset);
        int length = str.length;
        ByteBuffer buffer = ByteBuffer.allocate(
                4
                + length
        );
        buffer.putInt(length);
        buffer.put(str);
        return buffer.array();
    }

    public static JsonString deserializeJsonString(byte[] bytes, Charset charset)
    {
        byte[] slice = new byte[bytes.length-4];
        System.arraycopy(bytes, 4, slice, 0, bytes.length-4);
        return new JsonString(BasicDataSerializer.deserializeString(slice, charset));
    }
}
