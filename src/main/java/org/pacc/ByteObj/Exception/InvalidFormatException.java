package org.pacc.ByteObj.Exception;

public class InvalidFormatException extends RuntimeException
{
    public InvalidFormatException(String message)
    {
        super(message);
    }

    public InvalidFormatException(Exception e, Object object)
    {
        super("Cannot deserialize bytes into " + object.getClass().getName() + ", " + e);
    }

    public InvalidFormatException(Exception e)
    {
        super("Cannot deserialize bytes into object, " + e);
        e.printStackTrace();
    }

    public InvalidFormatException(ClassCastException e, Class<?> c)
    {
        super("Cannot deserialize bytes into " + c.getName());
        e.printStackTrace();
    }
}
