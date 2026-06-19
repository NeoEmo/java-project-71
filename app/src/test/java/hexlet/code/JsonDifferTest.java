package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonDifferTest {

    @Test
    public void testJsonDiffer2() throws Exception {
        var format = "json";
        var result = """
                    [ {
                      "key" : "follow",
                      "type" : "removed",
                      "oldValue" : false,
                      "newValue" : null
                    }, {
                      "key" : "host",
                      "type" : "unchanged",
                      "oldValue" : "hexlet.io",
                      "newValue" : "hexlet.io"
                    }, {
                      "key" : "proxy",
                      "type" : "removed",
                      "oldValue" : "123.234.53.22",
                      "newValue" : null
                    }, {
                      "key" : "timeout",
                      "type" : "changed",
                      "oldValue" : 50,
                      "newValue" : 20
                    }, {
                      "key" : "verbose",
                      "type" : "added",
                      "oldValue" : null,
                      "newValue" : true
                    } ]""";

        assertEquals(result, Differ.generate("file1.json", "file2.json", format).replace("\r\n", "\n"));
        System.out.println(Differ.generate("file3.json", "file4.json", format));
    }

    @Test
    public void testJsonDiffer3() throws Exception {
        var format = "json";
        var result = """
                    [ {
                      "key" : "chars1",
                      "type" : "unchanged",
                      "oldValue" : [ "a", "b", "c" ],
                      "newValue" : [ "a", "b", "c" ]
                    }, {
                      "key" : "chars2",
                      "type" : "changed",
                      "oldValue" : [ "d", "e", "f" ],
                      "newValue" : false
                    }, {
                      "key" : "checked",
                      "type" : "changed",
                      "oldValue" : false,
                      "newValue" : true
                    }, {
                      "key" : "default",
                      "type" : "changed",
                      "oldValue" : null,
                      "newValue" : [ "value1", "value2" ]
                    }, {
                      "key" : "id",
                      "type" : "changed",
                      "oldValue" : 45,
                      "newValue" : null
                    }, {
                      "key" : "key1",
                      "type" : "removed",
                      "oldValue" : "value1",
                      "newValue" : null
                    }, {
                      "key" : "key2",
                      "type" : "added",
                      "oldValue" : null,
                      "newValue" : "value2"
                    }, {
                      "key" : "numbers1",
                      "type" : "unchanged",
                      "oldValue" : [ 1, 2, 3, 4 ],
                      "newValue" : [ 1, 2, 3, 4 ]
                    }, {
                      "key" : "numbers2",
                      "type" : "changed",
                      "oldValue" : [ 2, 3, 4, 5 ],
                      "newValue" : [ 22, 33, 44, 55 ]
                    }, {
                      "key" : "numbers3",
                      "type" : "removed",
                      "oldValue" : [ 3, 4, 5 ],
                      "newValue" : null
                    }, {
                      "key" : "numbers4",
                      "type" : "added",
                      "oldValue" : null,
                      "newValue" : [ 4, 5, 6 ]
                    }, {
                      "key" : "obj1",
                      "type" : "added",
                      "oldValue" : null,
                      "newValue" : {
                        "nestedKey" : "value",
                        "isNested" : true
                      }
                    }, {
                      "key" : "setting1",
                      "type" : "changed",
                      "oldValue" : "Some value",
                      "newValue" : "Another value"
                    }, {
                      "key" : "setting2",
                      "type" : "changed",
                      "oldValue" : 200,
                      "newValue" : 300
                    }, {
                      "key" : "setting3",
                      "type" : "changed",
                      "oldValue" : true,
                      "newValue" : "none"
                    } ]""";

        assertEquals(result, Differ.generate("file3.json", "file4.json", format).replace("\r\n", "\n"));
    }
}
