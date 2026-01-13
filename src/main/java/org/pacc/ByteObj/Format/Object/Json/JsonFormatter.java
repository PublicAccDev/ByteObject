package org.pacc.ByteObj.Format.Object.Json;

import org.pacc.ByteObj.Format.Object.Json.Value.*;

import java.util.List;

public class JsonFormatter
{
    public static String format(JsonValue jsonValue, JsonFormatOption option)
    {
        return format(jsonValue, new JsonFormatConfig.Builder()
                .withOption(option)
                .build());
    }

    public static String format(String jsonString ,JsonFormatOption option)
    {
        return format(jsonString, new JsonFormatConfig.Builder()
                .withOption(option)
                .build());
    }

    public static String format(JsonValue jsonValue)
    {
        return format(jsonValue, new JsonFormatConfig.Builder()
                .withOption(JsonFormatOption.PRETTY)
                .build());
    }

    public static String format(String jsonString)
    {
        return format(jsonString, new JsonFormatConfig.Builder()
                .withOption(JsonFormatOption.PRETTY)
                .build());
    }

    public static String format(JsonValue jsonValue, JsonFormatConfig config)
    {
        StringBuilder result = new StringBuilder();
        formatValue(jsonValue, config, result, 0);
        return result.toString();
    }

    public static String format(String jsonString, JsonFormatConfig config)
    {
        JsonValue jsonValue = JsonParser.parse(jsonString);
        return format(jsonValue, config);
    }

    private static void formatValue(JsonValue value, JsonFormatConfig config,
                                    StringBuilder result, int depth)
    {
        if (value instanceof JsonString)
        {
            result.append(value);
        }
        else if (value instanceof JsonDouble
                 || value instanceof JsonFloat
                 || value instanceof JsonInteger
                 || value instanceof JsonLong)
        {
            result.append(value);
        }
        else if (value instanceof JsonBoolean)
        {
            result.append(value);
        }
        else if (value instanceof JsonNull)
        {
            result.append(value);
        }
        else if (value instanceof JsonArray)
        {
            formatArray((JsonArray) value, config, result, depth);
        }
        else if (value instanceof JsonObj)
        {
            formatObject((JsonObj) value, config, result, depth);
        }
    }

    private static void formatArray(JsonArray array, JsonFormatConfig config,
                                    StringBuilder result, int depth)
    {
        if (config.getIndent().isEmpty())
        {
            result.append("[");
            List<JsonValue> values = array.getValue();
            for (int i = 0; i < values.size(); i++)
            {
                if (i > 0)
                {
                    result.append(",");
                }
                formatValue(values.get(i), config, result, depth + 1);
            }
            result.append("]");
        }
        else
        {
            result.append("[");
            List<JsonValue> values = array.getValue();
            if (!values.isEmpty())
            {
                result.append("\n");

                for (int i = 0; i < values.size(); i++)
                {
                    for (int j = 0; j <= depth; j++)
                    {
                        result.append(config.getIndent());
                    }

                    formatValue(values.get(i), config, result, depth + 1);

                    if (i < values.size() - 1)
                    {
                        result.append(",");
                    }
                    result.append("\n");
                }

                for (int j = 0; j < depth; j++)
                {
                    result.append(config.getIndent());
                }
            }
            result.append("]");
        }
    }

    private static void formatObject(JsonObj obj, JsonFormatConfig config,
                                     StringBuilder result, int depth)
    {
        if (config.getIndent().isEmpty())
        {
            result.append("{");
            List<JsonProperty> properties = obj.getValue();
            for (int i = 0; i < properties.size(); i++)
            {
                if (i > 0)
                {
                    result.append(",");
                }
                JsonProperty prop = properties.get(i);
                result.append("\"").append(prop.getName()).append("\"");
                if (config.isSpaceAfterColon())
                {
                    result.append(":");
                }
                else
                {
                    result.append(":");
                }
                formatValue(prop.getValue(), config, result, depth + 1);
            }
            result.append("}");
        }
        else
        {
            result.append("{");
            List<JsonProperty> properties = obj.getValue();
            if (!properties.isEmpty())
            {
                result.append("\n");

                for (int i = 0; i < properties.size(); i++)
                {

                    for (int j = 0; j <= depth; j++)
                    {
                        result.append(config.getIndent());
                    }

                    JsonProperty prop = properties.get(i);
                    result.append("\"").append(prop.getName()).append("\"");

                    if (config.isSpaceAfterColon())
                    {
                        result.append(": ");
                    }
                    else
                    {
                        result.append(":");
                    }

                    formatValue(prop.getValue(), config, result, depth + 1);

                    if (i < properties.size() - 1)
                    {
                        result.append(",");
                    }
                    result.append("\n");
                }


                for (int j = 0; j < depth; j++)
                {
                    result.append(config.getIndent());
                }
            }
            result.append("}");
        }
    }

    public static String formatCompact(JsonValue jsonValue)
    {
        return format(jsonValue, new JsonFormatConfig.Builder()
                .withOption(JsonFormatOption.COMPACT)
                .build());
    }

    public static String formatPretty(JsonValue jsonValue)
    {
        return format(jsonValue, new JsonFormatConfig.Builder()
                .withOption(JsonFormatOption.PRETTY)
                .build());
    }

    public static String formatWithTab(JsonValue jsonValue)
    {
        return format(jsonValue, new JsonFormatConfig.Builder()
                .withOption(JsonFormatOption.PRETTY_TAB)
                .build());
    }

    public static String formatCompact(String jsonString)
    {
        JsonValue jsonValue = JsonParser.parse(jsonString);
        return formatCompact(jsonValue);
    }

    public static String formatPretty(String jsonString)
    {
        JsonValue jsonValue = JsonParser.parse(jsonString);
        return formatPretty(jsonValue);
    }

    public static String formatWithTab(String jsonString)
    {
        JsonValue jsonValue = JsonParser.parse(jsonString);
        return formatWithTab(jsonValue);
    }
}
