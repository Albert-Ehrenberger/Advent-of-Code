package day02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> rounds = Files.readAllLines(Paths.get("./src/day02/input.txt"));
        int totalPointsRound1 = 0;
        int totalPointsRound2 = 0;
        for (String round: rounds) {
            totalPointsRound1 += PointsCalculator.calculateRoundPoints(round);
            totalPointsRound2 += PointsCalculator2.calculateRoundPoints(round);
        }
        System.out.println(totalPointsRound1);
        System.out.println(totalPointsRound2);
    }
}