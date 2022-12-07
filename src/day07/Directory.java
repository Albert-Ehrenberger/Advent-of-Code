package day07;

import java.util.LinkedHashMap;
import java.util.HashMap;

public class Directory {

    String name;
    Directory parent;
    LinkedHashMap<String, Directory> children = new LinkedHashMap<>();

    HashMap<String, Integer> files = new HashMap<>();

    Directory(String name, Directory parent){
        this.name = name;
        this.parent = parent;
    }
}
