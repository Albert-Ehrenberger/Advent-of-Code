package day02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> rounds = Files.readAllLines(Paths.get("/home/albert/Projects/git/Advent of Code 22/AoC22/src/day02/input.txt"));
        int totalPoints = 0;
        for (String round: rounds) {
            totalPoints += PointsCalculator.calculateRoundPoints(round);
        }
        System.out.println(totalPoints);
    }
}