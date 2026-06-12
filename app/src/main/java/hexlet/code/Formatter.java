package hexlet.code;

import java.util.*;

public class Formatter {

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
        Set<String> keys = new TreeSet<>();
        keys.addAll(map1.keySet());
        keys.addAll(map2.keySet());

        List<String> lines = new ArrayList<>();
        for (String key : keys) {
            boolean inFirst = map1.containsKey(key);
            boolean inSecond = map2.containsKey(key);
            Object value1 = map1.get(key);
            Object value2 = map2.get(key);

            String type;
            if (inFirst && !inSecond) {
                type = "removed";
            } else if (!inFirst && inSecond) {
                type = "added";
            } else if (inFirst && inSecond && Objects.equals(value1, value2)) {
                type = "unchanged";
            } else {
                type = "changed";
            }

            switch (type) {
                case "removed" -> lines.add("  - " + key + ": " + value1);
                case "added" -> lines.add("  + " + key + ": " + value2);
                case "unchanged" -> lines.add("    " + key + ": " + value1);
                case "changed" -> {
                    lines.add("  - " + key + ": " + value1);
                    lines.add("  + " + key + ": " + value2);
                }
                default -> throw new Exception("Невозможно сравнить строчку! " + type + ": " +  key + " " + value1);
            }
        }

        StringBuilder result = new StringBuilder("{\n");
        for (String line : lines) {
            result.append(line).append("\n");
        }
        result.append("}");
        return result.toString();
    }

    private static String plain(Map<String, Object> map1, Map<String, Object> map2) {
        return null;
    }

    private static String json(Map<String, Object> map1, Map<String, Object> map2) {
        return null;
    }
}
