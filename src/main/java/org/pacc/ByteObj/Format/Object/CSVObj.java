package org.pacc.ByteObj.Format.Object;

import org.pacc.ByteObj.Exception.IllegalCSVException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class CSVObj
{
    private final LinkedHashMap<String, List<String>> headerCSValuesMap = new LinkedHashMap<>();
    private final List<List<String>> noHeaderCSValues = new ArrayList<>();
    private final boolean hasHeaders;
    private final int columnCount;
    private final List<String> headerList;

    public CSVObj(List<String> headers, List<String> values)
    {
        if (headers == null || headers.isEmpty())
        {
            throw new IllegalCSVException("Headers cannot be null or empty");
        }
        if (values == null)
        {
            throw new IllegalCSVException("Values cannot be null");
        }
        if (values.size() % headers.size() != 0)
        {
            throw new IllegalCSVException(headers, values);
        }

        int lines = values.size() / headers.size();
        this.columnCount = headers.size();
        this.headerList = new ArrayList<>(headers);

        for (int headerIndex = 0; headerIndex < headers.size(); headerIndex++)
        {
            String header = headers.get(headerIndex);
            List<String> valuesSet = new ArrayList<>(lines);
            for (int i = 0; i < lines; i++)
            {
                int valuesIndex = headerIndex + i * headers.size();
                valuesSet.add(values.get(valuesIndex));
            }
            headerCSValuesMap.put(header, valuesSet);
        }

        this.hasHeaders = true;
    }

    public CSVObj(List<List<String>> values)
    {
        if (values == null)
        {
            throw new IllegalCSVException("Values cannot be null");
        }

        this.noHeaderCSValues.addAll(values);
        this.hasHeaders = false;

        if (!values.isEmpty())
        {
            this.columnCount = values.getFirst().size();
        }
        else
        {
            this.columnCount = 0;
        }

        this.headerList = new ArrayList<>();
    }

    public static CSVObj fromCSVString(String csvString)
    {
        return fromCSVString(csvString, null);
    }

    public static CSVObj fromCSVString(String csvString, Boolean hasHeader)
    {
        if (csvString == null || csvString.isEmpty())
        {
            return new CSVObj(new ArrayList<>());
        }

        List<List<String>> lines = parseCSVString(csvString);

        if (lines.isEmpty())
        {
            return new CSVObj(new ArrayList<>());
        }

        List<String> headers = lines.getFirst();

        boolean shouldHaveHeader = (hasHeader != null) ? hasHeader : shouldHaveHeader(headers, lines);

        if (shouldHaveHeader && lines.size() > 1)
        {
            List<String> allValues = new ArrayList<>();

            for (int i = 1; i < lines.size(); i++)
            {
                List<String> line = lines.get(i);
                while (line.size() < headers.size())
                {
                    line.add("");
                }
                allValues.addAll(line);
            }

            return new CSVObj(headers, allValues);
        }
        else
        {
            return new CSVObj(lines);
        }
    }

    private static boolean shouldHaveHeader(List<String> firstLine, List<List<String>> allLines)
    {
        if (allLines.size() <= 1)
        {
            return false;
        }

        boolean hasNonNumeric = false;
        for (String field : firstLine)
        {
            if (!isNumeric(field.trim()))
            {
                hasNonNumeric = true;
                break;
            }
        }

        return hasNonNumeric;
    }

    private static boolean isNumeric(String str)
    {
        if (str == null || str.isEmpty())
        {
            return false;
        }
        try
        {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e)
        {
            return false;
        }
    }

    private static List<List<String>> parseCSVString(String csvString)
    {
        List<List<String>> result = new ArrayList<>();
        String[] lines = csvString.split("\\r?\\n");

        for (String line : lines)
        {
            if (line.isEmpty() && !csvString.endsWith("\n"))
            {
                continue;
            }
            result.add(parseCSVLine(line));
        }

        return result;
    }

    private static List<String> parseCSVLine(String line)
    {
        List<String> fields = new ArrayList<>();
        StringBuilder currentField = new StringBuilder();
        boolean inQuotes = false;
        char[] chars = line.toCharArray();

        for (int i = 0; i < chars.length; i++)
        {
            char c = chars[i];

            if (c == '"')
            {
                if (i + 1 < chars.length && chars[i + 1] == '"')
                {
                    currentField.append('"');
                    i++;
                }
                else
                {
                    inQuotes = !inQuotes;
                }
            }
            else if (c == ',' && !inQuotes)
            {
                fields.add(currentField.toString());
                currentField = new StringBuilder();
            }
            else
            {
                currentField.append(c);
            }
        }

        fields.add(currentField.toString());

        return fields;
    }

    public static CSVObj withHeaders(List<String> headers)
    {
        if (headers == null || headers.isEmpty())
        {
            throw new IllegalCSVException("Headers cannot be null or empty");
        }

        List<String> emptyValues = new ArrayList<>();

        return new CSVObj(headers, emptyValues);
    }

    public String toCSVString()
    {
        StringBuilder sb = new StringBuilder();

        if (hasHeaders)
        {
            for (int i = 0; i < headerList.size(); i++)
            {
                sb.append(escapeCSVField(headerList.get(i)));
                if (i < headerList.size() - 1)
                {
                    sb.append(",");
                }
            }
            sb.append("\n");

            int lineCount = getLineAmount();
            for (int lineIndex = 0; lineIndex < lineCount; lineIndex++)
            {
                List<String> line = getLine(lineIndex);
                for (int colIndex = 0; colIndex < line.size(); colIndex++)
                {
                    sb.append(escapeCSVField(line.get(colIndex)));
                    if (colIndex < line.size() - 1)
                    {
                        sb.append(",");
                    }
                }
                if (lineIndex < lineCount - 1)
                {
                    sb.append("\n");
                }
            }
        }
        else
        {
            for (int i = 0; i < noHeaderCSValues.size(); i++)
            {
                List<String> line = noHeaderCSValues.get(i);
                for (int j = 0; j < line.size(); j++)
                {
                    sb.append(escapeCSVField(line.get(j)));
                    if (j < line.size() - 1)
                    {
                        sb.append(",");
                    }
                }
                if (i < noHeaderCSValues.size() - 1)
                {
                    sb.append("\n");
                }
            }
        }

        return sb.toString();
    }

    private String escapeCSVField(String field)
    {
        if (field == null)
        {
            return "";
        }

        boolean needQuotes = field.contains(",") ||
                             field.contains("\"") ||
                             field.contains("\n") ||
                             field.contains("\r") ||
                             field.contains("\t");

        if (needQuotes)
        {
            field = field.replace("\"", "\"\"");
            return "\"" + field + "\"";
        }

        return field;
    }

    public List<String> getHeaders()
    {
        if (hasHeaders)
        {
            return new ArrayList<>(headerList);
        }
        else
        {
            return new ArrayList<>();
        }
    }

    public int getLineAmount()
    {
        if (hasHeaders)
        {
            return headerCSValuesMap.isEmpty() ? 0 : headerCSValuesMap.values().iterator().next().size();
        }
        else
        {
            return noHeaderCSValues.size();
        }
    }

    public int getColumnAmount()
    {
        if (hasHeaders)
        {
            return headerCSValuesMap.size();
        }
        else
        {
            return noHeaderCSValues.isEmpty() ? 0 : noHeaderCSValues.getFirst().size();
        }
    }

    public boolean has(String header)
    {
        if (hasHeaders)
        {
            return headerCSValuesMap.containsKey(header);
        }
        else
        {
            return false;
        }
    }

    public void addLine(List<String> values)
    {
        if (values.size() != columnCount)
        {
            throw new IllegalCSVException("The values size is expected to be " + columnCount + " but found " + values.size());
        }
        if (hasHeaders)
        {
            for (int i = 0; i < headerList.size(); i++)
            {
                List<String> valueList = headerCSValuesMap.get(headerList.get(i));
                valueList.add(values.get(i));
            }
        }
        else
        {
            noHeaderCSValues.add(new ArrayList<>(values));
        }
    }

    public void addLine(int index, List<String> values)
    {
        if (values.size() != columnCount)
        {
            throw new IllegalCSVException("The values size is expected to be " + columnCount + " but found " + values.size());
        }
        if (hasHeaders)
        {
            for (int i = 0; i < headerList.size(); i++)
            {
                List<String> valueList = headerCSValuesMap.get(headerList.get(i));
                valueList.add(index, values.get(i));
            }
        }
        else
        {
            noHeaderCSValues.add(index, new ArrayList<>(values));
        }
    }

    public void addColumn(int index, String header, List<String> values)
    {
        if (hasHeaders)
        {
            if (values.size() != getColumn(0).size())
            {
                throw new IllegalCSVException("The values size is expected to be " + getColumn(0).size() + " but found " + values.size());
            }

            List<String> newHeaderList = new ArrayList<>();
            LinkedHashMap<String, List<String>> newMap = new LinkedHashMap<>();

            for (int i = 0; i < headerList.size(); i++)
            {
                if (i == index)
                {
                    newHeaderList.add(header);
                    newMap.put(header, new ArrayList<>(values));
                }
                newHeaderList.add(headerList.get(i));
                newMap.put(headerList.get(i), headerCSValuesMap.get(headerList.get(i)));
            }

            if (index >= headerList.size())
            {
                newHeaderList.add(header);
                newMap.put(header, new ArrayList<>(values));
            }

            this.headerList.clear();
            this.headerList.addAll(newHeaderList);
            this.headerCSValuesMap.clear();
            this.headerCSValuesMap.putAll(newMap);
        }
        else
        {
            throw new IllegalCSVException("There is no header in CSV");
        }
    }

    public void addColumn(String header, List<String> values)
    {
        if (hasHeaders)
        {
            if (values.size() != getColumn(0).size())
            {
                throw new IllegalCSVException("The values size is expected to be " + getColumn(0).size() + " but found " + values.size());
            }
            headerList.add(header);
            headerCSValuesMap.put(header, new ArrayList<>(values));
        }
        else
        {
            throw new IllegalCSVException("There is no header in CSV");
        }
    }

    public void addColumn(int index, List<String> values)
    {
        if (hasHeaders)
        {
            throw new IllegalCSVException("Missing header");
        }
        else
        {
            if (values.size() != getColumn(0).size())
            {
                throw new IllegalCSVException("The values size is expected to be " + getColumn(0).size() + " but found " + values.size());
            }
            for (int i = 0; i < noHeaderCSValues.size(); i++)
            {
                noHeaderCSValues.get(i).add(index, values.get(i));
            }
        }
    }

    public void addColumn(List<String> values)
    {
        if (hasHeaders)
        {
            throw new IllegalCSVException("Missing header");
        }
        else
        {
            if (values.size() != getColumn(0).size())
            {
                throw new IllegalCSVException("The values size is expected to be " + getColumn(0).size() + " but found " + values.size());
            }
            for (int i = 0; i < noHeaderCSValues.size(); i++)
            {
                noHeaderCSValues.get(i).add(values.get(i));
            }
        }
    }

    public List<String> getLine(int index)
    {
        if (hasHeaders)
        {
            List<String> line = new ArrayList<>(headerList.size());
            for (String header : headerList)
            {
                line.add(headerCSValuesMap.get(header).get(index));
            }
            return line;
        }
        else
        {
            return new ArrayList<>(noHeaderCSValues.get(index));
        }
    }

    public List<String> getColumn(int index)
    {
        if (hasHeaders)
        {
            String header = headerList.get(index);
            return new ArrayList<>(headerCSValuesMap.get(header));
        }
        else
        {
            List<String> column = new ArrayList<>(noHeaderCSValues.size());
            for (List<String> noHeaderCSValue : noHeaderCSValues)
            {
                column.add(noHeaderCSValue.get(index));
            }
            return column;
        }
    }

    public List<String> getColumn(String header)
    {
        if (hasHeaders)
        {
            List<String> column = headerCSValuesMap.get(header);
            if (column == null)
            {
                throw new IllegalCSVException("Header '" + header + "' does not exist");
            }
            return new ArrayList<>(column);
        }
        else
        {
            throw new IllegalCSVException("There is no header in CSV");
        }
    }

    public boolean hasHeaders()
    {
        return this.hasHeaders;
    }

    public void addHeader(String header)
    {
        if (!hasHeaders)
        {
            throw new IllegalCSVException("Cannot add header to CSV without existing headers");
        }

        if (headerCSValuesMap.containsKey(header))
        {
            throw new IllegalCSVException("Header '" + header + "' already exists");
        }

        int lineCount = getLineAmount();

        List<String> emptyValues = new ArrayList<>(lineCount);
        for (int i = 0; i < lineCount; i++)
        {
            emptyValues.add("");
        }

        headerList.add(header);
        headerCSValuesMap.put(header, emptyValues);
    }

    public void addHeader(int index, String header)
    {
        if (!hasHeaders)
        {
            throw new IllegalCSVException("Cannot add header to CSV without existing headers");
        }

        if (headerCSValuesMap.containsKey(header))
        {
            throw new IllegalCSVException("Header '" + header + "' already exists");
        }

        if (index < 0 || index > headerList.size())
        {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + headerList.size());
        }

        int lineCount = getLineAmount();

        List<String> emptyValues = new ArrayList<>(lineCount);
        for (int i = 0; i < lineCount; i++)
        {
            emptyValues.add("");
        }

        headerList.add(index, header);
        headerCSValuesMap.put(header, emptyValues);
    }

    public void addHeader(List<String> headers)
    {
        if (headers == null || headers.isEmpty())
        {
            return;
        }

        if (!hasHeaders)
        {
            throw new IllegalCSVException("Cannot add headers to CSV without existing headers");
        }

        int lineCount = getLineAmount();

        for (String header : headers)
        {
            if (headerCSValuesMap.containsKey(header))
            {
                throw new IllegalCSVException("Header '" + header + "' already exists");
            }

            List<String> emptyValues = new ArrayList<>(lineCount);
            for (int i = 0; i < lineCount; i++)
            {
                emptyValues.add("");
            }

            headerList.add(header);
            headerCSValuesMap.put(header, emptyValues);
        }
    }

    public void addHeader(String header, String defaultValue)
    {
        if (!hasHeaders)
        {
            throw new IllegalCSVException("Cannot add header to CSV without existing headers");
        }

        if (headerCSValuesMap.containsKey(header))
        {
            throw new IllegalCSVException("Header '" + header + "' already exists");
        }

        int lineCount = getLineAmount();

        List<String> defaultValues = new ArrayList<>(lineCount);
        for (int i = 0; i < lineCount; i++)
        {
            defaultValues.add(defaultValue != null ? defaultValue : "");
        }

        headerList.add(header);
        headerCSValuesMap.put(header, defaultValues);
    }

    public void addHeader(String header, List<String> values)
    {
        if (!hasHeaders)
        {
            throw new IllegalCSVException("Cannot add header to CSV without existing headers");
        }

        if (headerCSValuesMap.containsKey(header))
        {
            throw new IllegalCSVException("Header '" + header + "' already exists");
        }

        int lineCount = getLineAmount();
        if (values == null || values.size() != lineCount)
        {
            throw new IllegalCSVException("Values size is expected to be " + lineCount + " but found " +
                                          (values != null ? values.size() : "null"));
        }

        headerList.add(header);
        headerCSValuesMap.put(header, new ArrayList<>(values));
    }

}
