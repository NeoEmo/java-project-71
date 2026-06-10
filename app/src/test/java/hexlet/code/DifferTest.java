package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DifferTest {

    @Test
    public void testDiffer() throws Exception {
        App app = new App();
        var file1 = app.readFile("file1.json");
        var file2 = app.readFile("file2.json");
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
        assertEquals(result, Differ.generate(file1, file2, format));
    }

    @Test
    public void testDiffer2() throws Exception {
        App app = new App();
        Exception exception = assertThrows(FileNotFoundException.class, () -> {
            app.readFile("file3.json");
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
        var format = "stylish";
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
        assertEquals(result, Differ.generate(json1, json2, format));
    }
}
