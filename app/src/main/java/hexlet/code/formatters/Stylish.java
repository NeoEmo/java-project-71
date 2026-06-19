package hexlet.code.formatters;

import static hexlet.code.DiffBuilder.DiffFormat;

import java.util.ArrayList;
import java.util.List;

public class Stylish implements Formatter {
    @Override
    public String format(List<DiffFormat> diffLines) throws Exception {
        List<String> lines = new ArrayList<>();
        for (DiffFormat diffFormat : diffLines) {
            String removed = "  - " + diffFormat.key() + ": " + diffFormat.oldValue();
            String added = "  + " + diffFormat.key() + ": " + diffFormat.newValue();
            switch (diffFormat.type()) {
                case REMOVED -> lines.add(removed);
                case ADDED -> lines.add(added);
                case UNCHANGED -> lines.add("    " +  diffFormat.key() + ": " + diffFormat.oldValue());
                case CHANGED -> {
                    lines.add(removed);
                    lines.add(added);
                }
                default -> throw new Exception("Невозможно сравнить строчку! " + diffFormat.type() + ": "
                        + diffFormat.key() + " " + diffFormat.oldValue());
            }
        }
        return "{\n" + String.join("\n", lines) + "\n}";
    }
}
