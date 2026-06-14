package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Stylish implements Formatter {
    @Override
    public String format(Map<String, Object> map1, Map<String, Object> map2) throws Exception {
        List<DiffFormat> diffLines = Formatter.formatDifference(map1, map2);
        List<String> lines = new ArrayList<>();
        for (DiffFormat format : diffLines) {
            switch (format.type()) {
                case REMOVED -> lines.add("  - " + format.key() + ": " + format.oldValue());
                case ADDED -> lines.add("  + " + format.key() + ": " + format.newValue());
                case UNCHANGED -> lines.add("    " + format.key() + ": " + format.oldValue());
                case CHANGED -> {
                    lines.add("  - " + format.key() + ": " + format.oldValue());
                    lines.add("  + " + format.key() + ": " + format.newValue());
                }
                default -> throw new Exception("Невозможно сравнить строчку! " + format.type() + ": " + format.key()
                        + " " + format.oldValue());
            }
        }
        return  "{\n" + String.join("\n", lines) + "\n}";
    }
}
