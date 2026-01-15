package org.pacc.ByteObj.Serializer;

import org.pacc.ByteObj.Exception.InvalidFormatException;

import java.io.*;

public class SerializableSerializer
{
    public static byte[] serialize(Object object)
    {
        if(!(object instanceof Serializable))
        {
            throw new InvalidFormatException(object);
        }
        try(ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream())
        {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static Object deserialize(byte[] objectBytesData) throws InvalidFormatException
    {
        try(ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(objectBytesData))
        {
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e)
        {
            throw new InvalidFormatException(e);
        }
    }
}
