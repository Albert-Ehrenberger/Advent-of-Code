package day05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public final class Main {

    final static int STARTING_POSITION = 1;
    final static int DISTANCE_BETWEEN_CRATES = 4;
    final static int NUMBER_OF_STACKS = 9;
    final static int MAX_HEIGHT_OF_STACK = 8;
    final static int LENGTH_OF_CRATE_INPUTS = 35;
    final static int FIRST_COMMAND_INPUT = 11;

    public static void main(String[] args) throws IOException {
        List<String> inputs = Files.readAllLines(Paths.get("./src/day05/input.txt"));

        final List<ArrayDeque<Character>> stacks = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_STACKS; i++) {
            stacks.add(new ArrayDeque<>(0));
        }
        StacksBuilder.buildStacks(stacks, inputs.subList(0, MAX_HEIGHT_OF_STACK));
        CommandsExecutor.executeCommands2(stacks, inputs.subList(FIRST_COMMAND_INPUT - 1, inputs.size()));
        System.out.println(printTopOfStacks(stacks));
    }

        private static String printTopOfStacks(List<ArrayDeque<Character>> stacks){
            StringBuilder ret = new StringBuilder();
            for (ArrayDeque<Character> stack:stacks) {
                ret.append(stack.getLast());
            }
            return ret.toString();
        }
}
