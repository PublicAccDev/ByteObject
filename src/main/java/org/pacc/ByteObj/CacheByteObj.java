package org.pacc.ByteObj;

/**
 * A ByteObject that caches both the object and its serialized byte array.
 *
 * <p>This improves performance by avoiding repeated serialization/deserialization.
 *
 * @param <ObjectType> The type of the object to be serialized/deserialized
 */
public abstract class CacheByteObj<ObjectType> extends BasicByteObject<ObjectType>
{

    private byte[] cacheBytes = null;
    private ObjectType cacheObject = null;

    private boolean dirtyBytes;
    private boolean dirtyObject = true;

    public CacheByteObj(ObjectType object)
    {
        super(object);
        this.cacheObject = object;
        this.dirtyBytes = true;
        this.dirtyObject = false;
    }

    public CacheByteObj(byte[] objectBytesData)
    {
        super(objectBytesData);
        this.cacheBytes = objectBytesData;
        this.dirtyBytes = false;
    }

    public CacheByteObj(byte[] objectBytesData, boolean ignoreThis)
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
