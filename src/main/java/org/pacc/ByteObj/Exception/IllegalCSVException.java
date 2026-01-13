package org.pacc.ByteObj.Exception;

import java.util.List;

public class IllegalCSVException extends RuntimeException
{
    public IllegalCSVException(List<String> headers, List<String> values)
    {
        super("Expected to have " + ((values.size() / headers.size()) * headers.size()) + " or " + (((values.size() / headers.size()) + 1) * headers.size()) + " elements in CSV, but there are " + values.size());
    }

    public IllegalCSVException(String message)
    {
        super(message);
    }
}
