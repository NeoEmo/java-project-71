package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Json implements Formatter {

    private static String formatValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String) {
            return "\"" + value + "\"";
        }
        if (value instanceof List) {
            return value.toString();
        }
        if (value instanceof Map) {
            String[] values = value.toString().split(",");
            values[0] = values[0].substring(1);
            int length = values[1].length() - 1;
            values[1] = values[1].substring(0,  length);
            StringBuilder result = new StringBuilder("{\n");
            int lastIndex = values.length - 1;
            for (int i = 0; i < values.length; i++) {
                String[] pair = values[i].trim().split("=");
                if (i == lastIndex) {
                    String newPair = "    \"" + pair[0] + "\": " + pair[1];
                    values[i] = newPair;
                } else {
                    String newPair = "    \"" + pair[0] + "\": " + pair[1] + ",\n";
                    values[i] = newPair;
                }
                result.append(values[i]);
            }
            result.append("\n   }");
            return result.toString();
        }
        return value.toString();
    }

    private static String formatKey(String key) {
        return "\"" + key + "\": ";
    }

    @Override
    public String format(Map<String, Object> map1, Map<String, Object> map2) throws Exception {
        List<DiffFormat> diffLines = Formatter.formatDifference(map1, map2);
        List<String> lines = new ArrayList<>();
        for (DiffFormat format : diffLines) {
            switch (format.type()) {
                case REMOVED, UNCHANGED -> {
                    continue;
                }
                case ADDED -> lines.add("  " + formatKey(format.key()) + formatValue(format.newValue()));
                case CHANGED -> lines.add("  " + formatKey(format.key()) + formatValue(format.oldValue()));
                default -> throw new Exception("Невозможно сравнить строчку! " + format.type() + ": " + format.key()
                        + " " + format.oldValue());
            }
        }
        return  "{\n" + String.join("\n", lines) + "\n}";
    }
}
