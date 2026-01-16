package org.pacc.ByteObj.Format.ByteObject;

import org.pacc.ByteObj.DirectByteObj;
import org.pacc.ByteObj.Exception.IllegalCSVException;
import org.pacc.ByteObj.Format.Object.CSVObj;
import org.pacc.ByteObj.Serializer.FormatObjSerializer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class BCSVObj extends DirectByteObj<CSVObj>
{
    public BCSVObj(CSVObj object)
    {
        super(object);
    }

    public BCSVObj(List<String> headers, List<String> values)
    {
        super(new CSVObj(headers, values));
    }

    public BCSVObj(List<List<String>> values)
    {
        super(new CSVObj(values));
    }

    public BCSVObj(byte[] objectBytesData)
    {
        super(objectBytesData);
    }

    @Override
    public byte[] serialize(CSVObj object)
    {
        return FormatObjSerializer.serialize(object);
    }

    @Override
    public CSVObj deserialize(byte[] objectBytesData)
    {
        return FormatObjSerializer.deserializeCSValues(objectBytesData);
    }

    public String toCSVString()
    {
        return this.getObject().toCSVString();
    }

    public List<String> getHeaders()
    {
        return this.getObject().getHeaders();
    }

    public int getLineAmount()
    {
        return this.getObject().getLineAmount();
    }

    public int getColumnAmount()
    {
        return this.getObject().getColumnAmount();
    }

    public boolean has(String header)
    {
        return this.getObject().has(header);
    }

    public void addLine(List<String> values)
    {
        CSVObj result = this.getObject();
        result.addLine(values);
        this.setObject(result);
    }

    public void addLine(int index, List<String> values)
    {
        CSVObj result = this.getObject();
        result.addLine(index, values);
        this.setObject(result);
    }

    public void addColumn(int index, String header, List<String> values)
    {
        CSVObj result = this.getObject();
        result.addColumn(index, header, values);
        this.setObject(result);
    }

    public void addColumn(String header, List<String> values)
    {
        CSVObj result = this.getObject();
        result.addColumn(header, values);
        this.setObject(result);
    }

    public void addColumn(int index, List<String> values)
    {
        CSVObj result = this.getObject();
        result.addColumn(index, values);
        this.setObject(result);
    }

    public void addColumn(List<String> values)
    {
        CSVObj result = this.getObject();
        result.addColumn(values);
        this.setObject(result);
    }

    public List<String> getLine(int index)
    {
        return this.getObject().getLine(index);
    }

    public List<String> getColumn(int index)
    {
        return this.getObject().getColumn(index);
    }

    public List<String> getColumn(String header)
    {
        return this.getObject().getColumn(header);
    }

    public boolean hasHeaders()
    {
        return this.getObject().hasHeaders();
    }

    public void addHeader(String header)
    {
        CSVObj result = this.getObject();
        result.addHeader(header);
        this.setObject(result);
    }

    public void addHeader(int index, String header)
    {
        CSVObj result = this.getObject();
        result.addHeader(index, header);
        this.setObject(result);
    }

    public void addHeader(List<String> headers)
    {
        CSVObj result = this.getObject();
        result.addHeader(headers);
        this.setObject(result);
    }

    public void addHeader(String header, String defaultValue)
    {
        CSVObj result = this.getObject();
        result.addHeader(header, defaultValue);
        this.setObject(result);
    }

    public void addHeader(String header, List<String> values)
    {
        CSVObj result = this.getObject();
        result.addHeader(header, values);
        this.setObject(result);
    }
}
