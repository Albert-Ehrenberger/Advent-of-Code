package day01;

import java.util.ArrayList;
import java.util.List;

public class Elf {

    private final List<Integer> foodItems;

    private final int totalWeight;

    Elf(List<String> foodItems){
        List<Integer> convertedFoodItems = new ArrayList<>();
        foodItems.forEach(string -> convertedFoodItems.add(Integer.parseInt(string)));
        this.foodItems = convertedFoodItems;
        this.totalWeight = calculateWeight(convertedFoodItems);
    }

    private int calculateWeight(List<Integer> foodItems){
        int totalWeight = 0;
        for (Integer foodItem : foodItems) {
            totalWeight += foodItem;
        }
        return totalWeight;
    }

    public int getTotalWeight(){
        return this.totalWeight;
    }
}
