package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class YamlDifferTest {

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
        assertEquals(result, Differ.generate("file1.yaml", "file2.yaml", format));
        assertEquals(result, Differ.generate("file1.yml", "file2.yml", format));
    }

    @Test
    public void testDiffer2() {
        var format = "stylish";
        Exception exception = assertThrows(FileNotFoundException.class, () -> {
            Differ.generate("file1.yaml", "file3.yaml", format);
        });
        String expectedStart = "Файл file3.yaml не найден в следующих местах:";
        assertTrue(exception.getMessage().startsWith(expectedStart));
    }

    @Test
    public void testDiffer3() throws Exception {
        String yaml1 = """
            a: 1
            b: 2
            c: 3
            d: 4
            e: 5
            """;
        String yaml2 = """
            a: 3
            c: 3
            d: 2
            e: 6
            """;
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

        Path tempFile1 = Files.createTempFile("test1", ".yaml");
        Path tempFile2 = Files.createTempFile("test2", ".yaml");
        Path tempFile3 = Files.createTempFile("test3", ".yml");
        Path tempFile4 = Files.createTempFile("test4", ".yml");
        try {
            Files.writeString(tempFile1, yaml1);
            Files.writeString(tempFile2, yaml2);
            Files.writeString(tempFile3, yaml1);
            Files.writeString(tempFile4, yaml2);

            String diff = Differ.generate(tempFile1.toString(), tempFile2.toString(), "stylish");
            String diff2 = Differ.generate(tempFile3.toString(), tempFile4.toString(), "stylish");
            assertEquals(result, diff);
            assertEquals(result, diff2);
        } finally {
            Files.deleteIfExists(tempFile1);
            Files.deleteIfExists(tempFile2);
            Files.deleteIfExists(tempFile3);
            Files.deleteIfExists(tempFile4);
        }
    }
}
