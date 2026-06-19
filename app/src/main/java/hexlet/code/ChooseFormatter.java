package hexlet.code;

import hexlet.code.formatters.Formatter;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;

public class ChooseFormatter {
    static String chooseFormat(String format, List<DiffBuilder.DiffFormat> diffLines) throws Exception {
        Formatter formatter = switch (format) {
            case "stylish" -> new Stylish();
            case "plain" -> new Plain();
            case "json" -> new Json();
            default -> throw new Exception("Неподдерживаемый формат " + format);
        };

        return formatter.format(diffLines);
    }
}
