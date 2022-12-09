package day09;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> steps = Files.readAllLines(Paths.get("./src/day09/input.txt"));
        TailPositionMapper mapper = new TailPositionMapper();
        Set<String> tailBreadCrumb = mapper.calculateBreadCrumb(steps);
        System.out.println(tailBreadCrumb.size());
    }
}
