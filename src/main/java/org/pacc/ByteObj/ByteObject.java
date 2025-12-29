package org.pacc.ByteObj;

import lombok.Getter;
import lombok.Setter;

/**
 * Abstract base class for handling serialization and deserialization between objects and byte arrays.
 *
 * <p><b>Important Implementation Specification:</b>
 * All subclasses inheriting from {@link ByteObject} must implement the {@code ByteObject(byte[])} constructor
 * to enable the creation of object instances from byte array data.
 *
 * @param <ObjectType> The type of object to be serialized/deserialized
 */
public abstract class ByteObject<ObjectType>
{
    @Getter
    @Setter
    private byte[] bytes = new byte[0];

    public ByteObject(ObjectType object)
    {
        this.setObject(object);
    }

    public ByteObject(byte[] objectBytesData)
    {
        this.bytes = objectBytesData;
    }

    public ByteObject(byte[] objectBytesData, boolean ignoreThis)
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
}
