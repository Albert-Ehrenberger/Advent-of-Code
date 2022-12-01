package day01;


import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class ElfConvoy {

    private static int elfCounter = 0;
    private final List<Elf> elfs;

    private final SortedMap<Integer, Integer> weightList;

    ElfConvoy() {
        this.elfs = new ArrayList<>();
        this.weightList = new TreeMap<>();
    }

    public void addElf(List<String> foodItems) {
        Elf elf = new Elf(foodItems);
        elfs.add(elf);
        weightList.put(elf.getTotalWeight(), elfCounter);
        elfCounter++;
    }

    public int getHeaviesElf() {
        return weightList.get(weightList.lastKey());
    }

    public int getMostHeavyBag() {
        return weightList.lastKey();
    }

    public int getMostHeavyBag(int fromTheTop) {
        Integer[] weightsArray = new Integer[weightList.size()];
        weightList.keySet().toArray(weightsArray);
        int sum = 0;
        for (int i = weightsArray.length; i> weightsArray.length - fromTheTop; i--){
            sum += weightsArray[i-1];
        }
        return sum;
    }
}
