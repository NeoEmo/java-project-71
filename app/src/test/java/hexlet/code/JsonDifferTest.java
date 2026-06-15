package hexlet.code;

import org.junit.jupiter.api.Test;

public class JsonDifferTest {
    @Test
    public void testJsonDiffer() throws Exception {
        var format = "json";
        System.out.println(Differ.generate("file1.json", "file2.json", format));
        System.out.println(Differ.generate("file3.json", "file4.json", format));
    }
}
