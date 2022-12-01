package day01;

import util.AoCUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        ElfConvoy convoy = new ElfConvoy();
        List<String> content = Files.readAllLines(Paths.get("/home/albert/Projects/git/Advent of Code 22/AoC22/src/day01/input.txt"));
        List<List<String>> groupedContent = AoCUtil.groupStrings(content);
        for (List<String> group: groupedContent) {
            convoy.addElf(group);
        }
        System.out.println(convoy.getMostHeavy());
    }
}