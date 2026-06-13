package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class PlainDifferTest {

    @Test
    public void testPlainDiffer() throws Exception {
        var format = "plain";
        var result = """
                Property 'follow' was removed
                Property 'host' was unchanged. Value 'hexlet.io'
                Property 'proxy' was removed
                Property 'timeout' was updated. From '50' to '20'
                Property 'verbose' was added with value: 'true'
                """;
        assertEquals(result, Differ.generate("file1.json", "file2.json", format));
    }
}
