package org.pacc.ByteObj;

import org.pacc.ByteObj.Exception.InvalidFormatException;
import org.pacc.ByteObj.Serializer.UniversalSerializer;

/**
 * Abstract base class extends {@link BaseByteObj} that use {@link java.io.Serializable} to serialize and deserialize any objects.
 *
 * @param <ObjectType> The type of object to be serialized/deserialized
 */
public class UniversalByteObj<ObjectType> extends FastByteObj<ObjectType>
{
    public UniversalByteObj(ObjectType object)
    {
        super(object);
    }

    public UniversalByteObj(byte[] objectBytesData)
    {
        super(objectBytesData);
    }

    public UniversalByteObj()
    {
        super(UniversalSerializer.serialize(null));
    }

    @Override
    public byte[] serialize(ObjectType object)
    {
        return UniversalSerializer.serialize(object);
    }

    @SuppressWarnings("unchecked")
    @Override
    public ObjectType deserialize(byte[] objectBytesData) throws InvalidFormatException
    {
        try
        {
            return (ObjectType) UniversalSerializer.deserialize(objectBytesData);
        } catch (Exception e)
        {
            throw new InvalidFormatException(e);
        }
    }
}
