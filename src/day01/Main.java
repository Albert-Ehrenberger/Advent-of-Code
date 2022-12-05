package day01;

import util.AoCUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        ElfConvoy convoy = new ElfConvoy();
        List<String> content = Files.readAllLines(Paths.get("./src/day01/input.txt"));
        List<List<String>> groupedContent = AoCUtil.groupStrings(content);
        for (List<String> group: groupedContent) {
            convoy.addElf(group);
        }
        System.out.println(convoy.getMostHeavyBag(3));
    }
}