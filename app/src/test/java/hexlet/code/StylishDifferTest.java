package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StylishDifferTest {
    static final String EXPECTED_1 = "expected_stylish1";
    static final String EXPECTED_2 = "expected_stylish2";
    static final String FORMAT = "stylish";
    static String result1;
    static String result2;

    @BeforeAll
    static void beforeAll() throws IOException {
        result1 =  readFixture(EXPECTED_1);
        result2 =  readFixture(EXPECTED_2);
    }

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName).toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws IOException {
        var path = getFixturePath(fileName);
        return Files.readString(path).trim();
    }

    @Test
    void testStylishDiffer() throws Exception {
        assertEquals(result1, Differ.generate("file1.json", "file2.json", FORMAT));
        assertEquals(result1, Differ.generate("file1.json", "file2.yaml", FORMAT));
        assertEquals(result1, Differ.generate("file1.yml", "file2.json", FORMAT));
        assertEquals(result1, Differ.generate("file1.yml", "file2.yaml", FORMAT));

        assertEquals(result2, Differ.generate("file3.json", "file4.json", FORMAT));
    }


    @Test
    public void testStylishDiffer2() {
        Exception exception = assertThrows(FileNotFoundException.class, () -> {
            Differ.generate("file1.json", "file10.json", FORMAT);
        });
        String expectedStart = "Файл file10.json не найден в следующих местах:";
        assertTrue(exception.getMessage().startsWith(expectedStart));
    }

    @Test
    public void testDefaultDiffer() throws Exception {
        assertEquals(result1, Differ.generate("file1.json", "file2.json"));
        assertEquals(result1, Differ.generate("file1.json", "file2.yaml"));
        assertEquals(result1, Differ.generate("file1.yml", "file2.json"));
        assertEquals(result1, Differ.generate("file1.yml", "file2.yaml"));

        assertEquals(result2, Differ.generate("file3.json", "file4.json"));
    }
}
