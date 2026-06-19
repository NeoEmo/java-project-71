package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import static hexlet.code.DiffBuilder.DiffFormat;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class Json implements Formatter {
    @Override
    public String format(List<DiffFormat> diffLines) throws Exception {
        Map<String, Object> result = new LinkedHashMap<>();

        for (DiffFormat diffFormat : diffLines) {
            switch (diffFormat.type()) {
                case REMOVED, UNCHANGED -> result.put(diffFormat.key(), diffFormat.oldValue());

                case ADDED -> result.put(diffFormat.key(), diffFormat.newValue());

                case CHANGED -> {
                    Map<String, Object> changed = new LinkedHashMap<>();
                    changed.put("old", diffFormat.oldValue());
                    changed.put("new", diffFormat.newValue());
                    result.put(diffFormat.key(), changed);
                }
                default -> throw  new Exception("Неизвестный тип: " + diffFormat.type());
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
    }
}
