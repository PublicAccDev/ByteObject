package org.pacc.ByteObj.Exception;

public class BytesConstructorMissingException extends RuntimeException
{
    public BytesConstructorMissingException(Class<?> clazz)
    {
        super(clazz.getName() + " is missing constructor <init>([B)V and cannot be created using bytes data");
    }

    public BytesConstructorMissingException(Class<?> keyClass, Class<?> valueClass)
    {
        super(keyClass.getName() + " or " + valueClass.getName() + " is missing constructor <init>([B)V and cannot be created using bytes data");
    }

    public BytesConstructorMissingException(String message)
    {
        super(message);
    }
}
