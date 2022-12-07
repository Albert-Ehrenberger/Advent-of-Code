package day07;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> consoleLines = Files.readAllLines(Paths.get("./src/day07/input.txt"));
        FilesystemBuilder builder = new FilesystemBuilder();
        Directory myFS = builder.build(consoleLines);
    }
}