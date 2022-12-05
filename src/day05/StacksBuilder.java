package day05;

import java.util.ArrayDeque;
import java.util.List;

import static day05.Main.*;

public final class StacksBuilder {

    private StacksBuilder() {

    }

    public static void buildStacks(List<ArrayDeque<Character>> stacks, List<String> inputs) {
        for (String input : inputs) {
            input = fixStringSize(input);
            for (int i = 0; i < 9; i++) {
                int stackPosition = STARTING_POSITION + i * DISTANCE_BETWEEN_CRATES;
                char currentCrate = input.charAt(stackPosition);
                if (currentCrate != ' ') {
                    stacks.get(i).addFirst(currentCrate);
                }
            }
        }
    }

    private static String fixStringSize(String input) {
        if (input.length() < LENGTH_OF_CRATE_INPUTS) {
            return input.concat(" ".repeat(LENGTH_OF_CRATE_INPUTS - input.length()));
        }
        return input;
    }
}
