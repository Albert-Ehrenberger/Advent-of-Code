package day07;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import com.google.common.collect.Multimap;


public class Main {

    public static void main(String[] args) throws IOException {
        List<String> consoleLines = Files.readAllLines(Paths.get("./src/day07/input.txt"));
        FilesystemBuilder FSBuilder = new FilesystemBuilder();
        Directory myFS = FSBuilder.build(consoleLines);
        FilesystemOverviewBuilder overviewBuilder = new FilesystemOverviewBuilder();
        Multimap<String, Integer> overview = overviewBuilder.build(myFS);
        int smallDirsTotalSize = 0;
        for (int size : overview.values()) {
            if (size < 100000) {
                smallDirsTotalSize += size;
            }
        }
        System.out.println(smallDirsTotalSize);

        int maxSpace = 70000000;
        int neededSpace = 30000000;
        int usedSpace = (int) overview.get("/").toArray()[0];
        int minSizeToDelete = neededSpace + usedSpace - maxSpace;
        Collection<Integer> sizes = overview.values();
        ArrayList<Integer> sortedSizes = new ArrayList<>(sizes);
        Collections.sort(sortedSizes);
        for (int size : sortedSizes) {
            if (size >= minSizeToDelete) {
                System.out.println(size);
                break;
            }
        }
    }
}