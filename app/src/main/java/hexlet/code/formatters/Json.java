package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Json implements Formatter {
    @Override
    public String format(Map<String, Object> map1, Map<String, Object> map2) throws Exception {
        List<DiffFormat> diffLines = Formatter.formatDifference(map1, map2);
        Map<String, Object> result = new LinkedHashMap<>();
        for (DiffFormat format : diffLines) {
            switch (format.type()) {
                case REMOVED -> {
                    result.put(format.key(), format.oldValue());
                    break;
                }
                case ADDED -> {
                    result.put(format.key(), format.newValue());
                    break;
                }
                case UNCHANGED -> {
                    result.put(format.key(), format.oldValue());
                    break;
                }
                case CHANGED -> {
                    Map<String, Object> changed = new LinkedHashMap<>();
                    changed.put("old", format.oldValue());
                    changed.put("new", format.newValue());
                    result.put(format.key(), changed);
                }
                default -> throw new Exception("Неизвестный тип: " + format.type());
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
    }
}
