package hexlet.code.formatters;

import static hexlet.code.DiffBuilder.DiffFormat;

import java.util.List;

public interface Formatter {
    String REMOVED = "removed";
    String ADDED = "added";
    String CHANGED = "changed";
    String UNCHANGED = "unchanged";
    String format(List<DiffFormat> diffLines) throws Exception;
}
