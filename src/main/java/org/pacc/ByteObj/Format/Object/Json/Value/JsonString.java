package org.pacc.ByteObj.Format.Object.Json.Value;

import lombok.Setter;
import org.pacc.ByteObj.Format.Object.Json.JsonParser;

public final class JsonString implements JsonValue
{
    @Setter
    private String value;

    public JsonString(String value)
    {
        this.value = value;
    }

    public static JsonString fromString(String json)
    {
        return (JsonString) JsonParser.parse(json);
    }

    @Override
    public String getValue()
    {
        return value;
    }

    @Override
    public String toString()
    {
        if (value == null)
        {
            return "null";
        }

        return '"'
               + escapeJsonString(value)
               + '"';
    }

    private String escapeJsonString(String input)
    {
        if (input == null)
        {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (char c : input.toCharArray())
        {
            switch (c)
            {
                case '"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                default:
                    if (c < 0x20)
                    {
                        sb.append(String.format("\\u%04x", (int) c));
                    }
                    else
                    {
                        sb.append(c);
                    }
                    break;
            }
        }
        return sb.toString();
    }
}
