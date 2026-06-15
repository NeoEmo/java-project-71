package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonDifferTest {

    @Test
    public void testJsonDiffer2() throws Exception {
        var format = "json";
        var result = """
                {
                  "timeout": 50
                  "verbose": true
                }""";

        assertEquals(result, Differ.generate("file1.json", "file2.json", format));
    }

    @Test
    public void testJsonDiffer3() throws Exception {
        var format = "json";
        var result = """
                {
                  "chars2": [d, e, f]
                  "checked": false
                  "default": null
                  "id": 45
                  "key2": "value2"
                  "numbers2": [2, 3, 4, 5]
                  "numbers4": [4, 5, 6]
                  "obj1": {
                    "nestedKey": value,
                    "isNested": true
                   }
                  "setting1": "Some value"
                  "setting2": 200
                  "setting3": true
                }""";

        assertEquals(result, Differ.generate("file3.json", "file4.json", format));
    }
}
