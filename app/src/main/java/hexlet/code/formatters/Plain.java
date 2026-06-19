package hexlet.code.formatters;

import static hexlet.code.DiffBuilder.DiffFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Plain implements Formatter {

//    @Override
//    public String format(Map<String, Object> map1, Map<String, Object> map2) throws Exception {
//        String property = "Property '";
//        List<DiffFormat> diffLines = Formatter.formatDifference(map1, map2);
//        List<String> lines = new ArrayList<>();
//        for (DiffFormat format : diffLines) {
//            switch (format.type()) {
//                case REMOVED -> lines.add(property + format.key() + "' was removed");
//
//                case ADDED -> lines.add(property + format.key() + "' was added with value: "
//                        + formatValue(format.newValue()));
//
//                case UNCHANGED -> {
//                    continue;
//                }
//
//                case CHANGED -> lines.add(property + format.key() + "' was updated. From "
//                        + formatValue(format.oldValue()) + " to " + formatValue(format.newValue()));
//
//                default -> throw new Exception("Невозможно сравнить строчку! "
//                        + format.type() + ": " + format.key() + " " + formatValue(format.oldValue()));
//            }
//        }
//        return String.join("\n", lines);
//    }
    @Override
    public String format(List<DiffFormat> diffLines) throws Exception {
        String property = "Property '";
        List<String> lines = new ArrayList<>();
        for (DiffFormat diffFormat : diffLines) {
            switch (diffFormat.type()) {
                case REMOVED -> lines.add(property + diffFormat.key() + "' was removed");

                case ADDED -> lines.add(property + diffFormat.key() + "' was added with value: "
                        + formatValue(diffFormat.newValue()));

                case UNCHANGED -> {
                    continue;
                }

                case CHANGED -> lines.add(property + diffFormat.key() + "' was updated. From "
                        + formatValue(diffFormat.oldValue()) + " to " +  formatValue(diffFormat.newValue()));

                default -> throw new Exception("Невозможно сравнить строчку! "
                        + diffFormat.type() + ": " + diffFormat.key() + " " + formatValue(diffFormat.oldValue()));
            }
        }
        return String.join("\n", lines);
    }

    private static String formatValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        return value.toString();
    }


}
