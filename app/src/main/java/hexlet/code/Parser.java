package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;


import java.util.Map;

public class Parser {
    protected static Map<String, Object> parse(String filePath, String extension) throws Exception {
        ObjectMapper mapper = switch (extension) {
            case "json" -> new ObjectMapper();
            case "yaml", "yml" -> new ObjectMapper(new YAMLFactory());
            default -> throw new Exception("Неподдерживаемое расширение файла");
        };
        return mapper.readValue(filePath, Map.class);
    }
}
