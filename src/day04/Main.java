package day04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> sectionPairs = Files.readAllLines(Paths.get("./src/day04/input.txt"));
        int totalContains1 = 0;
        int totalContains2 = 0;
        for (String pair : sectionPairs) {
            if (SectionPair.isOverlapping(pair)) {
                totalContains1 += 1;
            }
            if (SectionPair.isOverlapping2(pair)) {
                totalContains2 += 1;
            }
        }
        System.out.println(totalContains1);
        System.out.println(totalContains2);
    }
}
