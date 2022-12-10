package day10;

import java.util.HashMap;
import java.util.List;

public class SignalStrengthCalculator {

    private static final int FIRST_CYCLE = 20;
    private static final int CYCLE_STEP = 40;
    private static final int LAST_CYCLE = 220;
    private static final int PIXELS_PER_ROW = 40;
    private static final int PIXELS_ROWS = 6;
    private int registerX = 1;
    private int cycle = 0;
    private final HashMap<Integer, Integer> signalStrengths = new HashMap<>();
    private final char[][] image = new char[PIXELS_ROWS][PIXELS_PER_ROW];

    public HashMap<Integer, Integer> calculateSignalStrength(List<String> instructions) {
        for (String instruction : instructions) {
            executeInstruction(instruction);
        }
        return signalStrengths;
    }

    public char[][] drawImage (List<String> instructions) {
        for (String instruction : instructions) {
            executeInstruction(instruction);
        }
        return image;
    }

    private void executeInstruction(String instruction) {
        String[] instructionParts = instruction.split(" ");
        switch (instructionParts.length) {
            case 1 -> incrementCycle();
            case 2 -> addToRegister(Integer.parseInt(instructionParts[1]));
        }
    }

    private void incrementCycle() {
        char newPixel;
        if ( cycle % PIXELS_PER_ROW >= registerX - 1 && cycle % PIXELS_PER_ROW  <= registerX + 1){
            newPixel = '#';
        } else {
            newPixel = '.';
        }
        image[cycle / PIXELS_PER_ROW][cycle % PIXELS_PER_ROW] = newPixel;
        cycle++;
        if (cycle >= FIRST_CYCLE && cycle <= LAST_CYCLE && (cycle - FIRST_CYCLE) % CYCLE_STEP == 0) {
            signalStrengths.put(cycle, cycle * registerX);
        }
    }

    private void addToRegister(int value) {
        incrementCycle();
        incrementCycle();
        registerX += value;
    }
}
