package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Command(
        name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference."
)
public class App implements Runnable {

    @Parameters(index = "0", description = "path to first File")
    private String filePath1;

    @Parameters(index = "1", description = "path to second File")
    private String filePath2;

    @Option(names = {"-f", "--format"}, description = "Output format (default: stylish)")
    private String format = "stylish";

    public static void main(String[] args) {
        int exitcode = new CommandLine(new App()).execute(args);
        System.exit(exitcode);
    }

    @Override
    public void run() {
        try {
            String contentFile1 = readFile(filePath1);
            String contentFile2 = readFile(filePath2);
            String diff = Differ.generate(contentFile1, contentFile2, format);
            System.out.println(diff);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }

    protected String readFile(String fileName) throws Exception {
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
