package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonDifferTest {
    static final String expected1 = "expected_json1";
    static final String expected2 = "expected_json2";
    static final String format = "json";
    static String result1;
    static String result2;

    @BeforeAll
    static void beforeAll() throws IOException {
        result1 =  readFixture(expected1);
        result2 =  readFixture(expected2);
    }

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName).toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws IOException {
        var path = getFixturePath(fileName);
        return Files.readString(path).trim().replace("\r\n", "\n");
    }

    @Test
    void testJsonDiffer() throws Exception {
        assertEquals(result1, Differ.generate("file1.json", "file2.json", format));
        assertEquals(result1, Differ.generate("file1.json", "file2.yaml", format));
        assertEquals(result1, Differ.generate("file1.yml", "file2.json", format));
        assertEquals(result1, Differ.generate("file1.yml", "file2.yaml", format));

        assertEquals(result2, Differ.generate("file3.json", "file4.json", format));
    }
}
