package org.pacc.ByteObj;

import org.pacc.ByteObj.Serializer.SerializableSerializer;

import java.io.*;

public class SerializableByteObj<ObjectType extends Serializable> extends CacheByteObj<ObjectType>
{
    public SerializableByteObj(ObjectType object)
    {
        super(object);
    }

    public SerializableByteObj(byte[] objectBytesData)
    {
        super(objectBytesData);
    }

    @Override
    public byte[] serialize(ObjectType object)
    {
        return SerializableSerializer.serialize(object);
    }

    @SuppressWarnings("unchecked")
    @Override
    public ObjectType deserialize(byte[] objectBytesData)
    {
        return (ObjectType) SerializableSerializer.deserialize(objectBytesData);
    }
}
