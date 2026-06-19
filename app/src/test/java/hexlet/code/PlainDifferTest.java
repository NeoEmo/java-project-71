package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PlainDifferTest {
    static final String EXPECTED_1 = "expected_plain1";
    static final String EXPECTED_2 = "expected_plain2";
    static final String FORMAT = "plain";
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
        return Files.readString(path).trim().replace("\r\n", "\n");
    }

    @Test
    void testPlainDiffer() throws Exception {
        assertEquals(result1, Differ.generate("file1.json", "file2.json", FORMAT));
        assertEquals(result1, Differ.generate("file1.json", "file2.yaml", FORMAT));
        assertEquals(result1, Differ.generate("file1.yml", "file2.json", FORMAT));
        assertEquals(result1, Differ.generate("file1.yml", "file2.yaml", FORMAT));

        assertEquals(result2, Differ.generate("file3.json", "file4.json", FORMAT));
    }
}
