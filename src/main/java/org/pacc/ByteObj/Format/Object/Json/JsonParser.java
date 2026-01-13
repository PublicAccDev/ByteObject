package org.pacc.ByteObj.Format.Object.Json;

import org.pacc.ByteObj.Exception.JsonParseException;
import org.pacc.ByteObj.Format.Object.Json.Value.*;

import java.util.LinkedList;
import java.util.List;

public class JsonParser
{
    public static JsonValue fromString(String json)
    {
        return parse(json);
    }

    public static JsonValue parse(String json)
    {
        try
        {
            if (json == null || json.trim().isEmpty())
            {
                throw new JsonParseException("Cannot parse empty JSON string");
            }

            json = json.trim();

            if (json.startsWith("\"") && json.endsWith("\""))
            {

                String unescaped = unescapeJsonString(json.substring(1, json.length() - 1));
                return new JsonString(unescaped);
            }
            else if (json.equals("true"))
            {
                return new JsonBoolean(true);
            }
            else if (json.equals("false"))
            {
                return new JsonBoolean(false);
            }
            else if (json.equals("null"))
            {
                return new JsonNull();
            }
            else if (json.startsWith("{") && json.endsWith("}"))
            {

                return parseObject(json);
            }
            else if (json.startsWith("[") && json.endsWith("]"))
            {

                return parseArray(json);
            }
            else
            {

                try
                {
                    if (json.contains(".") || json.toLowerCase().contains("e"))
                    {
                        return new JsonDouble(Double.parseDouble(json));
                    }
                    else
                    {
                        return new JsonLong(Long.parseLong(json));
                    }
                } catch (NumberFormatException e)
                {
                    throw new JsonParseException("Invalid JSON number: " + json);
                }
            }
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    private static String unescapeJsonString(String str)
    {
        StringBuilder sb = new StringBuilder();
        boolean escape = false;

        for (int i = 0; i < str.length(); i++)
        {
            char c = str.charAt(i);
            if (escape)
            {
                switch (c)
                {
                    case '"':
                        sb.append('"');
                        break;
                    case '\\':
                        sb.append('\\');
                        break;
                    case '/':
                        sb.append('/');
                        break;
                    case 'b':
                        sb.append('\b');
                        break;
                    case 'f':
                        sb.append('\f');
                        break;
                    case 'n':
                        sb.append('\n');
                        break;
                    case 'r':
                        sb.append('\r');
                        break;
                    case 't':
                        sb.append('\t');
                        break;
                    case 'u':

                        if (i + 4 < str.length())
                        {
                            String hex = str.substring(i, i + 4);
                            try
                            {
                                int codePoint = Integer.parseInt(hex, 16);
                                sb.append((char) codePoint);
                                i += 3;
                            } catch (NumberFormatException e)
                            {
                                sb.append("\\u").append(hex);
                            }
                        }
                        else
                        {
                            sb.append("\\u");
                        }
                        break;
                    default:
                        sb.append(c);
                        break;
                }
                escape = false;
            }
            else if (c == '\\')
            {
                escape = true;
            }
            else
            {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    private static JsonObj parseObject(String json)
    {
        try
        {
            json = json.trim().substring(1, json.length() - 1);
            if (json.trim().isEmpty())
            {
                return new JsonObj();
            }

            List<JsonProperty> properties = new LinkedList<>();
            int braceLevel = 0;
            int bracketLevel = 0;
            int start = 0;
            boolean inString = false;
            char stringDelimiter = 0;

            for (int i = 0; i < json.length(); i++)
            {
                char c = json.charAt(i);

                if (c == '"' && (i == 0 || json.charAt(i - 1) != '\\'))
                {
                    if (!inString)
                    {
                        inString = true;
                        stringDelimiter = '"';
                    }
                    else if (stringDelimiter == '"')
                    {
                        inString = false;
                    }
                }
                else if (c == '\'' && (i == 0 || json.charAt(i - 1) != '\\'))
                {
                    if (!inString)
                    {
                        inString = true;
                        stringDelimiter = '\'';
                    }
                    else if (stringDelimiter == '\'')
                    {
                        inString = false;
                    }
                }

                if (!inString)
                {
                    if (c == '{')
                    {
                        braceLevel++;
                    }
                    else if (c == '}')
                    {
                        braceLevel--;
                    }
                    else if (c == '[')
                    {
                        bracketLevel++;
                    }
                    else if (c == ']')
                    {
                        bracketLevel--;
                    }
                    else if (c == ',' && braceLevel == 0 && bracketLevel == 0)
                    {

                        String property = json.substring(start, i).trim();
                        if (!property.isEmpty())
                        {
                            properties.add(parseProperty(property));
                        }
                        start = i + 1;
                    }
                }
            }


            String lastProperty = json.substring(start).trim();
            if (!lastProperty.isEmpty())
            {
                properties.add(parseProperty(lastProperty));
            }

            return new JsonObj(properties.toArray(new JsonProperty[0]));
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    private static JsonProperty parseProperty(String property)
    {
        try
        {
            int colonIndex = findUnescapedColon(property);
            if (colonIndex == -1)
            {
                throw new JsonParseException("Invalid property format: " + property);
            }

            String key = property.substring(0, colonIndex).trim();
            String value = property.substring(colonIndex + 1).trim();


            if (key.startsWith("\"") && key.endsWith("\""))
            {
                key = key.substring(1, key.length() - 1);
            }

            JsonValue jsonValue = parse(value);
            return new JsonProperty(key, jsonValue);
        } catch (JsonParseException e)
        {
            throw new RuntimeException(e);
        }
    }

    private static int findUnescapedColon(String str)
    {
        boolean escaped = false;
        boolean inString = false;
        char stringDelimiter = 0;

        for (int i = 0; i < str.length(); i++)
        {
            char c = str.charAt(i);

            if (c == '\\' && !escaped)
            {
                escaped = true;
                continue;
            }

            if (!escaped)
            {
                if ((c == '"' || c == '\'') && !inString)
                {
                    inString = true;
                    stringDelimiter = c;
                }
                else if (c == stringDelimiter && inString && str.charAt(i - 1) != '\\')
                {
                    inString = false;
                }
                else if (c == ':' && !inString)
                {
                    return i;
                }
            }

            escaped = false;
        }

        return -1;
    }

    private static JsonArray parseArray(String json)
    {
        try
        {
            json = json.trim().substring(1, json.length() - 1);
            if (json.trim().isEmpty())
            {
                return new JsonArray();
            }

            List<JsonValue> values = new LinkedList<>();
            int braceLevel = 0;
            int bracketLevel = 0;
            int start = 0;
            boolean inString = false;
            char stringDelimiter = 0;

            for (int i = 0; i < json.length(); i++)
            {
                char c = json.charAt(i);

                if (c == '"' && (i == 0 || json.charAt(i - 1) != '\\'))
                {
                    if (!inString)
                    {
                        inString = true;
                        stringDelimiter = '"';
                    }
                    else if (stringDelimiter == '"')
                    {
                        inString = false;
                    }
                }
                else if (c == '\'' && (i == 0 || json.charAt(i - 1) != '\\'))
                {
                    if (!inString)
                    {
                        inString = true;
                        stringDelimiter = '\'';
                    }
                    else if (stringDelimiter == '\'')
                    {
                        inString = false;
                    }
                }

                if (!inString)
                {
                    if (c == '{')
                    {
                        braceLevel++;
                    }
                    else if (c == '}')
                    {
                        braceLevel--;
                    }
                    else if (c == '[')
                    {
                        bracketLevel++;
                    }
                    else if (c == ']')
                    {
                        bracketLevel--;
                    }
                    else if (c == ',' && braceLevel == 0 && bracketLevel == 0)
                    {
                        String element = json.substring(start, i).trim();
                        if (!element.isEmpty())
                        {
                            values.add(parse(element));
                        }
                        start = i + 1;
                    }
                }
            }

            String lastElement = json.substring(start).trim();
            if (!lastElement.isEmpty())
            {
                values.add(parse(lastElement));
            }

            return new JsonArray(values.toArray(new JsonValue[0]));
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
