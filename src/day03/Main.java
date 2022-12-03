package day03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import com.google.common.collect.Lists;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> backpacks = Files.readAllLines(Paths.get("/home/albert/Projects/git/Advent of Code 22/AoC22/src/day03/input.txt"));
        String possibleItemsString = "abcdefghijklmnopqrstuvwxyz" + "abcdefghijklmnopqrstuvwxyz".toUpperCase();
        String[] possibleItems = possibleItemsString.split("");
        int initialPriority = 1;
        HashMap<String, Integer> priorityList = new HashMap<>();
        for (String item: possibleItems) {
            priorityList.put(item, initialPriority++);
        }
        int totalPriorityValue = 0;
        for (String backpack: backpacks) {
           String commonItem = Backpack.findMatch(backpack);
            totalPriorityValue += priorityList.get(commonItem);
        }
        System.out.println(totalPriorityValue);

        List <List<String>> groupedBackpacks = Lists.partition(backpacks, 3);
        totalPriorityValue = 0;
        for (List<String> group: groupedBackpacks) {
            String commonItem = Backpack.findMatch(group);
            totalPriorityValue += priorityList.get(commonItem);
        }
        System.out.println(totalPriorityValue);
    }
}
