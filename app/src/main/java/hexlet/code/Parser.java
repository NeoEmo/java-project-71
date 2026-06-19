package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;


import java.util.Map;

public class Parser {
    protected static Map<String, Object> parse(String content, String dataType) throws Exception {
        ObjectMapper mapper = switch (dataType) {
            case "json" -> new ObjectMapper();
            case "yaml", "yml" -> new ObjectMapper(new YAMLFactory());
            default -> throw new Exception("Unsupported data format: " + dataType);
        };
        return mapper.readValue(content, Map.class);
    }
}
