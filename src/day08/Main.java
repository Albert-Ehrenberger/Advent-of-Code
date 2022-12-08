package day08;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> treeRows = Files.readAllLines(Paths.get("./src/day08/input.txt"));
        TreePicker picker = new TreePicker();
        TreeMap<String, Tree> visibleTrees = picker.findVisible(treeRows);
        System.out.println(visibleTrees.size());
    }
}
