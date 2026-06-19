package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import static hexlet.code.DiffBuilder.DiffFormat;
import java.util.List;



public class Json implements Formatter {
    @Override
    public String format(List<DiffFormat> diffLines) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(diffLines);
    }
}
