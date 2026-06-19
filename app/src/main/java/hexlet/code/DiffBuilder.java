package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class DiffBuilder {
    public record DiffFormat(String key, String type, Object oldValue, Object newValue) { }
    static String removed = "removed";
    static String added = "added";
    static String changed = "changed";
    static String unchanged = "unchanged";

    protected static List<DiffFormat> formatDifference(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> keys = new TreeSet<>();
        keys.addAll(map1.keySet());
        keys.addAll(map2.keySet());

        List<DiffFormat> diffLines = new ArrayList<>();
        for (String key : keys) {
            boolean inFirst = map1.containsKey(key);
            boolean inSecond = map2.containsKey(key);
            Object value1 = map1.get(key);
            Object value2 = map2.get(key);

            String type;
            if (inFirst && !inSecond) {
                type = removed;
            } else if (!inFirst && inSecond) {
                type = added;
            } else if (Objects.equals(value1, value2)) {
                type = unchanged;
            } else {
                type = changed;
            }

            diffLines.add(new DiffFormat(key, type, value1, value2));
        }
        return diffLines;
    }
}
