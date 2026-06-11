package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonDifferTest {

    @Test
    public void testDiffer() throws Exception {
        var format = "stylish";
        var result = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
        assertEquals(result, Differ.generate("file1.json", "file2.json", format));
    }

    @Test
    public void testDiffer2() throws Exception {
        var format = "stylish";
        Exception exception = assertThrows(FileNotFoundException.class, () -> {
            Differ.generate("file1.json", "file3.json", format);
        });
        String expectedStart = "Файл file3.json не найден в следующих местах:";
        assertTrue(exception.getMessage().startsWith(expectedStart));
    }

    @Test
    public void testDiffer3() throws Exception {
        var json1 = """
            {
            "a":1,
            "b":2,
            "c":3,
            "d":4,
            "e":5
            }""";
        var json2 = """
            {
            "a":3,
            "c":3,
            "d":2,
            "e":6
            }""";
        var result = """
            {
              - a: 1
              + a: 3
              - b: 2
                c: 3
              - d: 4
              + d: 2
              - e: 5
              + e: 6
            }""";

        Path tempFile1 = Files.createTempFile("test1", ".json");
        Path tempFile2 = Files.createTempFile("test2", ".json");
        try {
            Files.writeString(tempFile1, json1);
            Files.writeString(tempFile2, json2);
            String diff = Differ.generate(tempFile1.toString(), tempFile2.toString(), "stylish");
            assertEquals(result, diff);
        } finally {
            Files.deleteIfExists(tempFile1);
            Files.deleteIfExists(tempFile2);
        }
    }
}
