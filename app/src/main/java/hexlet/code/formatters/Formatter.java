package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public interface Formatter {
    record DiffFormat(String key, String type, Object oldValue, Object newValue) { }
    String REMOVED = "removed";
    String ADDED = "added";
    String CHANGED = "changed";
    String UNCHANGED = "unchanged";

    static List<DiffFormat> formatDifference(Map<String, Object> map1, Map<String, Object> map2) {
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
                type = REMOVED;
            } else if (!inFirst && inSecond) {
                type = ADDED;
            } else if (Objects.equals(value1, value2)) {
                type = UNCHANGED;
            } else {
                type = CHANGED;
            }

            diffLines.add(new DiffFormat(key, type, value1, value2));
        }
        return diffLines;
    }

    String format(Map<String, Object> map1, Map<String, Object> map2) throws Exception;
}
