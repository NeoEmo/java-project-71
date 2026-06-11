package hexlet.code;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Differ {
    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        String content1 = readFile(filePath1);
        String content2 = readFile(filePath2);
        String[] splitExt1 = filePath1.split("\\.");
        String[] splitExt2 = filePath2.split("\\.");

        Map<String, Object> map1 = Parser.parse(content1, splitExt1[1]);
        Map<String, Object> map2 = Parser.parse(content2, splitExt2[1]);

        Set<String> keys = new TreeSet<>();
        keys.addAll(map1.keySet());
        keys.addAll(map2.keySet());

        List<String> lines = new ArrayList<>();
        for (String key : keys) {
            boolean inFirst = map1.containsKey(key);
            boolean inSecond = map2.containsKey(key);
            Object value1 = map1.get(key);
            Object value2 = map2.get(key);

            String type;
            if (inFirst && !inSecond) {
                type = "removed";
            } else if (!inFirst && inSecond) {
                type = "added";
            } else if (inFirst && inSecond && Objects.equals(value1, value2)) {
                type = "unchanged";
            } else {
                type = "changed";
            }

            switch (type) {
                case "removed" -> lines.add("  - " + key + ": " + value1);
                case "added" -> lines.add("  + " + key + ": " + value2);
                case "unchanged" -> lines.add("    " + key + ": " + value1);
                case "changed" -> {
                    lines.add("  - " + key + ": " + value1);
                    lines.add("  + " + key + ": " + value2);
                }
                default -> throw new Exception("Невозможно сравнить строчку! " + type + ": " +  key + " " + value1);
            }
        }

        StringBuilder result = new StringBuilder("{\n");
        for (String line : lines) {
            result.append(line).append("\n");
        }
        result.append("}");
        return result.toString();
    }

    protected static String readFile(String fileName) throws Exception {
        List<Path> allPossiblePaths = Arrays.asList(
                Paths.get(fileName),
                Paths.get("").toAbsolutePath().resolve(fileName),
                Paths.get("src/main/resources").resolve(fileName));

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
