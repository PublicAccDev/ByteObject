package org.pacc.ByteObj;

import lombok.Getter;
import lombok.Setter;
import org.pacc.ByteObj.Exception.InvalidFormatException;

/**
 * Abstract base class for handling serialization and deserialization between objects and byte arrays.
 *
 * <p><b>Important Implementation Specification:</b>
 * All subclasses inheriting from {@link BasicByteObject} must implement the {@code ByteObject(byte[])} constructor
 * to enable the creation of object instances from byte array data.
 *
 * @param <ObjectType> The type of object to be serialized/deserialized
 */
public abstract class BasicByteObject<ObjectType>
{
    @Getter
    @Setter
    private byte[] bytes = new byte[0];

    public BasicByteObject(ObjectType object)
    {
        this.setObject(object);
    }

    public BasicByteObject(byte[] objectBytesData)
    {
        this.bytes = objectBytesData;
    }

    public BasicByteObject(byte[] objectBytesData, boolean ignoreThis)
    {
        this.bytes = objectBytesData;
    }

    public abstract byte[] serialize(ObjectType object);

    public abstract ObjectType deserialize(byte[] objectBytesData) throws InvalidFormatException;

    public ObjectType getObject()
    {
        return this.deserialize(this.bytes);
    }

    public void setObject(ObjectType object)
    {
        this.setBytes(serialize(object));
    }
}
