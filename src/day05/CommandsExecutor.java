package day05;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CommandsExecutor {

    private CommandsExecutor() {

    }

    private static final int REPEAT_POSITION = 0;
    private static final int FROM_POSITION = 1;
    private static final int TO_POSITION = 2;

    public static void executeCommands(List<ArrayDeque<Character>> stacks, List<String> inputs) {
        for (String input: inputs) {
            List<Integer> command = extractCommand(input);
            for (int i = 0; i < command.get(REPEAT_POSITION); i++){
                ArrayDeque<Character> fromStack = stacks.get(command.get(FROM_POSITION) - 1);
                ArrayDeque<Character> toStack = stacks.get(command.get(TO_POSITION) - 1);
                toStack.addLast(fromStack.removeLast());
            }
        }
    }

    public static void executeCommands2(List<ArrayDeque<Character>> stacks, List<String> inputs) {
        for (String input: inputs) {
            List<Integer> command = extractCommand(input);
            ArrayDeque<Character> tempStack = new ArrayDeque<>(0);
            for (int i = 0; i < command.get(REPEAT_POSITION); i++){
                ArrayDeque<Character> fromStack = stacks.get(command.get(FROM_POSITION) - 1);
                tempStack.addLast(fromStack.removeLast());
            }
            for (int i = 0; i < command.get(REPEAT_POSITION); i++){
                ArrayDeque<Character> toStack = stacks.get(command.get(TO_POSITION) - 1);
                toStack.addLast(tempStack.removeLast());
            }
        }
    }

    private static List<Integer> extractCommand(String stringToSearch){
        Pattern integerPattern = Pattern.compile("-?\\d+");
        Matcher matcher = integerPattern.matcher(stringToSearch);

        List<Integer> integerList = new ArrayList<>();
        while (matcher.find()) {
            integerList.add(Integer.valueOf(matcher.group()));
        }

        return integerList;
    }
}
