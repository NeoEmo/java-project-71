package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

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

    @Option(names = {"-f", "--format"}, description = "Output format (default: stylish)", defaultValue = "stylish")
    private String format;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        try {
            String diff = Differ.generate(filePath1, filePath2, format);
            System.out.println(diff);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
}
