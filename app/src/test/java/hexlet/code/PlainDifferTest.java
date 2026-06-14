package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class PlainDifferTest {

    @Test
    public void testPlainDiffer() throws Exception {
        var format = "plain";
        var result = """
                Property 'follow' was removed
                Property 'proxy' was removed
                Property 'timeout' was updated. From 50 to 20
                Property 'verbose' was added with value: true
                """;

        assertEquals(result, Differ.generate("file1.json", "file2.json", format));
    }

    @Test
    public void testPlainDiffer2() throws Exception {
        var format = "plain";
        var result = """
                Property 'chars2' was updated. From [complex value] to false
                Property 'checked' was updated. From false to true
                Property 'default' was updated. From null to [complex value]
                Property 'id' was updated. From 45 to null
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'numbers2' was updated. From [complex value] to [complex value]
                Property 'numbers3' was removed
                Property 'numbers4' was added with value: [complex value]
                Property 'obj1' was added with value: [complex value]
                Property 'setting1' was updated. From 'Some value' to 'Another value'
                Property 'setting2' was updated. From 200 to 300
                Property 'setting3' was updated. From true to 'none'
                """;

        assertEquals(result, Differ.generate("file3.json", "file4.json", format));
    }
}
