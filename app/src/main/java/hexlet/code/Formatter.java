package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Formatter {
    private record DiffFormat(String key, String type, Object oldValue, Object newValue) { }
    private static final String REMOVED = "removed";
    private static final String ADDED = "added";
    private static final String CHANGED = "changed";
    private static final String UNCHANGED = "unchanged";

    private static List<DiffFormat> formatDifference(Map<String, Object> map1, Map<String, Object> map2) {
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

    public static String format(Map<String, Object> map1, Map<String, Object> map2, String format) throws Exception {
        switch (format) {
            case "plain" -> {
                return plain(map1, map2);
            }
            case "json" -> {
                return json(map1, map2);
            }
            case "stylish" -> {
                return stylish(map1, map2);
            }
            default -> {
                throw new Exception(format + " не поддерживается программой");
            }
        }
    }

    private static String stylish(Map<String, Object> map1, Map<String, Object> map2) throws Exception {
        List<DiffFormat> diffLines = formatDifference(map1, map2);
        List<String> lines = new ArrayList<>();
        for (DiffFormat format : diffLines) {
            switch (format.type) {
                case REMOVED -> lines.add("  - " + format.key + ": " + format.oldValue);
                case ADDED -> lines.add("  + " + format.key + ": " + format.newValue);
                case UNCHANGED -> lines.add("    " + format.key + ": " + format.oldValue);
                case CHANGED -> {
                    lines.add("  - " + format.key + ": " + format.oldValue);
                    lines.add("  + " + format.key + ": " + format.newValue);
                }
                default -> throw new Exception("Невозможно сравнить строчку! " + format.type + ": " +  format.key
                        + " " + format.oldValue);
            }
        }
        return  "{\n" + String.join("\n", lines) + "\n}";
    }

    private static String plain(Map<String, Object> map1, Map<String, Object> map2) throws Exception {
        String property = "Property '";
        List<DiffFormat> diffLines = formatDifference(map1, map2);
        List<String> lines = new ArrayList<>();
        for (DiffFormat format : diffLines) {
            switch (format.type) {
                case REMOVED -> lines.add(property + format.key + "' was removed");

                case ADDED -> lines.add(property + format.key + "' was added with value: '"
                        + format.newValue + "'");

                case UNCHANGED -> lines.add(property + format.key + "' was unchanged. Value '"
                        + format.oldValue + "'");

                case CHANGED -> lines.add(property + format.key + "' was updated. From '" + format.oldValue
                        + "' to '" + format.newValue + "'");

                default -> throw new Exception("Невозможно сравнить строчку! " + format.type + ": " + format.key + " "
                        + format.oldValue);
            }
        }
        String result = String.join("\n", lines);
        return result + "\n";
    }


    // не актуально, не факт что будет
    private static String json(Map<String, Object> map1, Map<String, Object> map2) {
        return null;
    }
}
