package day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> instructions = Files.readAllLines(Paths.get("./src/day10/testinput.txt"));
        SignalStrengthCalculator calculator = new SignalStrengthCalculator();
        HashMap<Integer, Integer> signalStrengths = calculator.calculateSignalStrength(instructions);
        Collection<Integer> values = signalStrengths.values();
        int valuesSum = 0;
        for (Integer value: values) {
            valuesSum += value;
        }
        System.out.println(valuesSum);
    }
}
