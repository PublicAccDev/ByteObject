package org.pacc.ByteObj;

import org.pacc.ByteObj.BasicData.BInteger;
import org.pacc.ByteObj.BasicData.BString;
import org.pacc.ByteObj.Container.BArrayDeque;
import org.pacc.ByteObj.Container.BHashMap;
import org.pacc.ByteObj.Format.Object.CSVObj;
import org.pacc.ByteObj.Format.Object.Json.JsonFormatConfig;
import org.pacc.ByteObj.Format.Object.Json.JsonFormatOption;
import org.pacc.ByteObj.Format.Object.Json.JsonFormatter;
import org.pacc.ByteObj.Format.Object.Json.Value.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;

public class Test
{
    static void main(String[] args)
    {
        testMethod4();
    }

    private static void testMethod1()
    {
        try
        {
            File file = new File("test.csv");
            CSVObj csv = CSVObj.fromCSVString(Files.readString(file.toPath()));
            System.out.println(csv.toCSVString());
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    private static void testMethod2()
    {
        try
        {
            File file = new File("test.json");

            JsonObj json = new JsonObj();
            json.addProperty(new JsonProperty("name", "John"));
            json.addProperty(new JsonProperty("age", 30));
            json.addProperty(new JsonProperty("isStudent", true));
            json.addProperty(new JsonProperty("grades", new JsonArray(new JsonString("A"), new JsonBoolean(false), new JsonInteger(666))));
            Files.writeString(Path.of("testOut.json"), json.toString());
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    private static void testMethod3()
    {
        try
        {
            File file = new File("test.json");
            JsonObj json = JsonObj.fromString(Files.readString(file.toPath()));
            Files.writeString(Path.of("testOut.json"), JsonFormatter.format(json, new JsonFormatConfig(JsonFormatOption.PRETTY_TAB)));
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    private static void testMethod4()
    {

    }

    private static void testMethod5()
    {
        long s = System.currentTimeMillis();

        long e = System.currentTimeMillis();
        System.out.println("Time: " + (e - s) + " ms");
    }
}
