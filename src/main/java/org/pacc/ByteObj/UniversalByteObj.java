package org.pacc.ByteObj;

import java.io.*;

public class UniversalByteObj<ObjectType> extends CacheByteObj<ObjectType>
{
    public UniversalByteObj(ObjectType object)
    {
        super(object);
    }

    public UniversalByteObj(byte[] objectBytesData)
    {
        super(objectBytesData);
    }

    @Override
    public byte[] serialize(ObjectType object)
    {
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

    @SuppressWarnings("unchecked")
    @Override
    public ObjectType deserialize(byte[] objectBytesData)
    {
        try(ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(objectBytesData))
        {
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return (ObjectType) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }
}
