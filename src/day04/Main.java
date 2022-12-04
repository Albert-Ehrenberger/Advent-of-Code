package day04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> sectionPairs = Files.readAllLines(Paths.get("/home/albert/Projects/git/Advent of Code 22/AoC22/src/day04/input.txt"));
        int totalContains = 0;
        for (String pair : sectionPairs) {
            if (SectionPair.isOverlapping(pair)) {
                totalContains += 1;
            }
        }
        System.out.println(totalContains);
    }
}
