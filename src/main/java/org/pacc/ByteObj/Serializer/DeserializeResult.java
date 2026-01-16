package org.pacc.ByteObj.Serializer;

import java.lang.reflect.Constructor;

public record DeserializeResult(Object object, Constructor<?>[] constructor)
{
}
