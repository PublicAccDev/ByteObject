package org.pacc.ByteObj;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

/**
 * Abstract base class for handling serialization and deserialization between objects and byte arrays.
 *
 * @param <ObjectType> The type of object to be serialized/deserialized
 * @implSpec {@link #serialize(Object)} {@link #deserialize(byte[])}
 */
public abstract class BaseByteObj<ObjectType>
{
    @Getter
    @Setter
    private byte[] bytes = new byte[0];

    public BaseByteObj(ObjectType object)
    {
        this.setObject(object);
    }

    public BaseByteObj(byte[] objectBytesData)
    {
        this.bytes = objectBytesData;
    }

    public BaseByteObj(byte[] objectBytesData, boolean ignoreThis)
    {
        this.bytes = objectBytesData;
    }

    public abstract byte[] serialize(ObjectType object);

    public abstract ObjectType deserialize(byte[] objectBytesData);

    public ObjectType getObject()
    {
        return this.deserialize(this.bytes);
    }

    public void setObject(ObjectType object)
    {
        this.setBytes(serialize(object));
    }

    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof BaseByteObj<?> && Arrays.equals(this.bytes, ((BaseByteObj<?>) obj).bytes);
    }

    @Override
    public int hashCode()
    {
        return this.getObject().hashCode();
    }
}
