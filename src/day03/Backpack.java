package day03;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class Backpack {



    private Backpack(){
    }

    public static String findMatch(String backpackContent){
        int backpackSize = backpackContent.length();
        String firstContainer = backpackContent.substring(0, backpackSize/2);
        String secondContainer = backpackContent.substring(backpackSize/2 , backpackSize);
        Set<Character> set1 = firstContainer.chars()
                .mapToObj(e->(char)e).collect(Collectors.toSet());
        Set<Character> set2 = secondContainer.chars()
                .mapToObj(e->(char)e).collect(Collectors.toSet());
        for (Character item: set1) {
            if (set2.contains(item)){
                return item.toString();
            }
        }
        return "";
    }

    public static String findMatch(List<String> group){
        String firstBackpack = group.get(0);
        String secondBackpack = group.get(1);
        String thirdBackpack = group.get(2);
        Set<Character> set1 = firstBackpack.chars()
                .mapToObj(e->(char)e).collect(Collectors.toSet());
        Set<Character> set2 = secondBackpack.chars()
                .mapToObj(e->(char)e).collect(Collectors.toSet());
        Set<Character> set3 = thirdBackpack.chars()
                .mapToObj(e->(char)e).collect(Collectors.toSet());
        for (Character item: set1) {
            if (set2.contains(item) && set3.contains(item)){
                return item.toString();
            }
        }
        return "";
    }
}
