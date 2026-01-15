package org.pacc.ByteObj;

/**
 * Abstract base class which will be treated as being able to directly accept a byte array and deserialize it directly into the object without any additional parameters
 *
 * @param <ObjectType> The type of object to be serialized/deserialized
 * @implSpec {@link #DirectByteObj(byte[])} {@link #serialize(Object)} {@link #deserialize(byte[])}
 */
public abstract class DirectByteObj<ObjectType> extends FastByteObj<ObjectType>
{
    public DirectByteObj(ObjectType object)
    {
        super(object);
    }

    public DirectByteObj(byte[] objectBytesData)
    {
        super(objectBytesData);
    }

    public DirectByteObj(byte[] objectBytesData, boolean ignoreThis)
    {
        super(objectBytesData, false);
    }
}
