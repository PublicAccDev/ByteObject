package org.pacc.ByteObj;

/**
 * Abstract base class extends {@link BaseByteObj} that caches both the object and its serialized byte array to improve performances
 *
 * @param <ObjectType> The type of object to be serialized/deserialized
 * @implSpec {@link #serialize(Object)} {@link #deserialize(byte[])}
 */
public abstract class FastByteObj<ObjectType> extends BaseByteObj<ObjectType>
{

    private byte[] cacheBytes = null;
    private ObjectType cacheObject = null;

    private boolean dirtyBytes;
    private boolean dirtyObject = true;

    public FastByteObj(ObjectType object)
    {
        super(object);
        this.cacheObject = object;
        this.dirtyBytes = true;
        this.dirtyObject = false;
    }

    public FastByteObj(byte[] objectBytesData)
    {
        super(objectBytesData);
        this.cacheBytes = objectBytesData;
        this.dirtyBytes = false;
    }

    public FastByteObj(byte[] objectBytesData, boolean ignoreThis)
    {
        super(objectBytesData);
        this.cacheBytes = objectBytesData;
        this.dirtyBytes = false;
    }

    @Override
    public byte[] getBytes()
    {
        if (dirtyBytes)
        {
            cacheBytes = serialize(getObjectInternal());
            dirtyBytes = false;
        }
        return cacheBytes;
    }

    @Override
    public void setBytes(byte[] bytes)
    {
        this.cacheBytes = bytes;
        dirtyBytes = false;
        dirtyObject = true;
    }

    @Override
    public ObjectType getObject()
    {
        if (dirtyObject)
        {
            cacheObject = deserialize(cacheBytes);
            dirtyObject = false;
        }
        return cacheObject;
    }

    @Override
    public void setObject(ObjectType object)
    {
        this.cacheObject = object;
        dirtyObject = false;
        dirtyBytes = true;
    }

    private ObjectType getObjectInternal()
    {
        if (cacheObject == null)
        {
            cacheObject = deserialize(cacheBytes);
            dirtyObject = false;
        }
        return cacheObject;
    }
}
