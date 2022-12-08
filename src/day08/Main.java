package day08;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> treeRows = Files.readAllLines(Paths.get("./src/day08/input.txt"));
        TreePicker picker = new TreePicker();
        TreeMap<String, Tree> visibleTrees = picker.findVisible(treeRows);
        TreeMap<String, Integer> scoredTrees = picker.gradeTrees(visibleTrees);
        List< Integer > scenicScores = new ArrayList<>( scoredTrees.values() );
        Collections.sort( scenicScores );
        int highestScore = scenicScores.get(scenicScores.size()-1);
        System.out.println(highestScore);
    }
}
