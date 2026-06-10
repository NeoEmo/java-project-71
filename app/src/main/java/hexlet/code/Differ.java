package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String contentFile1, String contentFile2, String format) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map1 = mapper.readValue(contentFile1, Map.class);
        Map<String, Object> map2 = mapper.readValue(contentFile2, Map.class);

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
}
