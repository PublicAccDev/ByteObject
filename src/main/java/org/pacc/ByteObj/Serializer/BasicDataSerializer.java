package org.pacc.ByteObj.Serializer;

import java.nio.ByteBuffer;

public class BasicDataSerializer
{

    public static byte[] serialize(boolean value)
    {
        return new byte[]{(byte) (value ? 1 : 0)};
    }

    public static Boolean deserializeBoolean(byte[] bytes)
    {
        return bytes[0] == 1;
    }

    public static byte[] serialize(char[] chars)
    {
        return new String(chars).getBytes();
    }

    public static char[] deserializeChars(byte[] bytes)
    {
        return new String(bytes).toCharArray();
    }

    public static byte[] serialize(double value)
    {
        return ByteBuffer.allocate(8).putDouble(value).array();
    }

    public static double deserializeDouble(byte[] bytes)
    {
        return ByteBuffer.wrap(bytes).getDouble();
    }

    public static byte[] serialize(float value)
    {
        return ByteBuffer.allocate(4).putFloat(value).array();
    }

    public static float deserializeFloat(byte[] bytes)
    {
        return ByteBuffer.wrap(bytes).getFloat();
    }

    public static byte[] serialize(int value)
    {
        return ByteBuffer.allocate(4).putInt(value).array();
    }

    public static int deserializeInteger(byte[] bytes)
    {
        return ByteBuffer.wrap(bytes).getInt();
    }

    public static byte[] serialize(long value)
    {
        return ByteBuffer.allocate(8).putLong(value).array();
    }

    public static Long deserializeLong(byte[] bytes)
    {
        return ByteBuffer.wrap(bytes).getLong();
    }

    public static byte[] serialize(String value)
    {
        return value.getBytes();
    }

    public static String deserializeString(byte[] bytes)
    {
        return new String(bytes);
    }
}
