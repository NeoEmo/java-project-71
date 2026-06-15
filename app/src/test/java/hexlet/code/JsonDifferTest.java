package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonDifferTest {

    @Test
    public void testJsonDiffer2() throws Exception {
        var format = "json";
        var result = """
                {
                  "follow" : false,
                  "host" : "hexlet.io",
                  "proxy" : "123.234.53.22",
                  "timeout" : {
                    "old" : 50,
                    "new" : 20
                  },
                  "verbose" : true
                }""";

        assertEquals(result, Differ.generate("file1.json", "file2.json", format).replace("\r\n", "\n"));
        System.out.println(Differ.generate("file3.json", "file4.json", format));
    }

    @Test
    public void testJsonDiffer3() throws Exception {
        var format = "json";
        var result = """
                {
                  "chars1" : [ "a", "b", "c" ],
                  "chars2" : {
                    "old" : [ "d", "e", "f" ],
                    "new" : false
                  },
                  "checked" : {
                    "old" : false,
                    "new" : true
                  },
                  "default" : {
                    "old" : null,
                    "new" : [ "value1", "value2" ]
                  },
                  "id" : {
                    "old" : 45,
                    "new" : null
                  },
                  "key1" : "value1",
                  "key2" : "value2",
                  "numbers1" : [ 1, 2, 3, 4 ],
                  "numbers2" : {
                    "old" : [ 2, 3, 4, 5 ],
                    "new" : [ 22, 33, 44, 55 ]
                  },
                  "numbers3" : [ 3, 4, 5 ],
                  "numbers4" : [ 4, 5, 6 ],
                  "obj1" : {
                    "nestedKey" : "value",
                    "isNested" : true
                  },
                  "setting1" : {
                    "old" : "Some value",
                    "new" : "Another value"
                  },
                  "setting2" : {
                    "old" : 200,
                    "new" : 300
                  },
                  "setting3" : {
                    "old" : true,
                    "new" : "none"
                  }
                }""";

        assertEquals(result, Differ.generate("file3.json", "file4.json", format).replace("\r\n", "\n"));
    }
}
