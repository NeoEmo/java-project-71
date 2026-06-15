package hexlet.code;

import hexlet.code.formatters.Formatter;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Differ {
    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        String content1 = readFile(filePath1);
        String content2 = readFile(filePath2);
        String[] splitExt1 = filePath1.split("\\.");
        String[] splitExt2 = filePath2.split("\\.");

        Map<String, Object> map1 = Parser.parse(content1, splitExt1[1]);
        Map<String, Object> map2 = Parser.parse(content2, splitExt2[1]);

        Formatter formatter = switch (format) {
            case "stylish" -> new Stylish();
            case "plain" -> new Plain();
            case "json" -> new Json();
            default -> throw new Exception("Неподдерживаемый формат " + format);
        };

        return formatter.format(map1, map2);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    protected static String readFile(String fileName) throws Exception {
        List<Path> allPossiblePaths = Arrays.asList(
                Paths.get(fileName),
                Paths.get("").toAbsolutePath().resolve(fileName),
                Paths.get("src/main/resources").resolve(fileName),
                Paths.get("src/main/resources/fixtures").resolve(fileName));

        for (Path path : allPossiblePaths) {
            Path absolutePath = path.toAbsolutePath().normalize();
            if (Files.exists(absolutePath)) {
                return Files.readString(absolutePath).trim();
            }
        }

        StringBuilder allPaths = new StringBuilder();
        allPaths.append("\n");
        for (Path path : allPossiblePaths) {
            allPaths.append(path).append("\n");
        }

        throw new FileNotFoundException("Файл " + fileName + " не найден в следующих местах: " + allPaths.toString());
    }
}
