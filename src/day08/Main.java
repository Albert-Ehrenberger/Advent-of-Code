package day08;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> treeRows = Files.readAllLines(Paths.get("./src/day08/input.txt"));
        TreePicker picker = new TreePicker();
        picker.findVisible(treeRows);
    }
}
