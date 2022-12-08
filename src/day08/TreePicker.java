package day08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

public class TreePicker {

    private final TreeMap<String, Tree> potentialTrees = new TreeMap<>();
    private int currentRowNumber;
    private List<String> rotatedPotentialMap;

    public void findVisible(List<String> treeRows) {
        for (currentRowNumber = 0; currentRowNumber < treeRows.size(); currentRowNumber++) {
            findPotentialTreesInRow(treeRows.get(currentRowNumber));
        }
        initializeRotatedPotentialMap(treeRows.size(), treeRows.get(0).length());
        createRotatedPotentialMap();
    }


    private void findPotentialTreesInRow(String row) {
        leftToRightSearch(row);
        rightToLeftSearch(row);
    }

    private boolean addTree(int row, int column, int height) {
        String treeName = row + String.valueOf(column);
        if (!potentialTrees.containsKey(treeName)) {
            Tree tree = new Tree(row, column, height);
            potentialTrees.put(treeName, tree);
            return true;
        }
        return false;
    }

    private void leftToRightSearch(final String row) {
        int highestTree = Integer.parseInt(row.substring(0, 1));
        addTree(currentRowNumber, 0, highestTree);
        for (int column = 0; column < row.length(); column++) {
            int currentHeight = Integer.parseInt(row.substring(column, column + 1));
            if (currentHeight > highestTree) {
                highestTree = currentHeight;
                addTree(currentRowNumber, column, currentHeight);
            }
        }
    }

    private void rightToLeftSearch(final String row) {
        int totalColumns = row.length();
        int highestTree = Integer.parseInt(row.substring(totalColumns - 1, totalColumns));
        addTree(currentRowNumber, totalColumns - 1, highestTree);
        for (int column = totalColumns - 1; column >= 0; column--) {
            int currentHeight = Integer.parseInt(row.substring(column, column + 1));
            if (currentHeight > highestTree) {
                highestTree = currentHeight;
                if (!addTree(currentRowNumber, column, currentHeight)) {
                    break;
                }
            }
        }
    }

    private void createRotatedPotentialMap() {
        Collection<Tree> trees = potentialTrees.values();
        for (Tree tree : trees) {
            StringBuilder oldRow = new StringBuilder(rotatedPotentialMap.get(tree.column()));
            oldRow.setCharAt(tree.row(), String.valueOf(tree.height()).charAt(0));
            rotatedPotentialMap.set(tree.column(), oldRow.toString());
        }
    }

    private void initializeRotatedPotentialMap(int rows, int columns) {
        rotatedPotentialMap = new ArrayList<>(columns);
        for (int i = 0; i < columns; i++) {
            StringBuilder sb = new StringBuilder();
            while (sb.length() < rows) {
                sb.append('0');
            }
            rotatedPotentialMap.add(sb.toString());
        }
    }
}
