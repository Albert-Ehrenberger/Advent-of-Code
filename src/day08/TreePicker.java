package day08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

public class TreePicker {

    private final TreeMap<String, Tree> potentialTrees = new TreeMap<>();
    private final TreeMap<String, Tree> visibleTrees = new TreeMap<>();
    private int currentRowNumber;
    private List<String> rotatedPotentialMap;

    public void findVisible(List<String> treeRows) {
        addFirstAndLastPotentialRows(treeRows);
        for (currentRowNumber = 1; currentRowNumber < treeRows.size() - 1; currentRowNumber++) {
            findPotentialTreesInRow(treeRows.get(currentRowNumber));
        }
        initializeRotatedPotentialMap(treeRows.size(), treeRows.get(0).length());
        createRotatedPotentialMap();
        addFirstAndLastVisibleRows();
        for (currentRowNumber = 1; currentRowNumber < rotatedPotentialMap.size() - 1; currentRowNumber++) {
            findVisibleTreesInRow(rotatedPotentialMap.get(currentRowNumber));
        }
    }

    public void addFirstAndLastPotentialRows(List<String> treeRows) {
        String firstRow = treeRows.get(0);
        String lastRow = treeRows.get(treeRows.size() - 1);
        for (int column = 0; column < firstRow.length(); column++) {
            addPotentialTree(0, column, Integer.parseInt(firstRow.substring(column, column + 1)));
            addPotentialTree(treeRows.size() - 1, column, Integer.parseInt(lastRow.substring(column, column + 1)));
        }
    }

    public void addFirstAndLastVisibleRows() {
        String firstRow = rotatedPotentialMap.get(0);
        String lastRow = rotatedPotentialMap.get(rotatedPotentialMap.size() - 1);
        for (int column = 0; column < firstRow.length(); column++) {
            addVisibleTree(0, column, Integer.parseInt(firstRow.substring(column, column + 1)));
            addVisibleTree(rotatedPotentialMap.size() - 1, column, Integer.parseInt(lastRow.substring(column, column + 1)));
        }
    }


    private void findPotentialTreesInRow(String row) {
        leftToRightSearch(row);
        rightToLeftSearch(row);
    }

    private boolean addPotentialTree(int row, int column, int height) {
        String treeName = row + String.valueOf(column);
        if (!potentialTrees.containsKey(treeName)) {
            Tree tree = new Tree(row, column, height);
            potentialTrees.put(treeName, tree);
            return true;
        }
        return false;
    }

    private boolean addVisibleTree(int column, int row, int height) {
        String treeName = row + String.valueOf(column);
        if (!visibleTrees.containsKey(treeName)) {
            Tree tree = new Tree(row, column, height);
            visibleTrees.put(treeName, tree);
            return true;
        }
        return false;
    }

    private void leftToRightSearch(final String row) {
        int highestTree = Integer.parseInt(row.substring(0, 1));
        addPotentialTree(currentRowNumber, 0, highestTree);
        for (int column = 0; column < row.length(); column++) {
            int currentHeight = Integer.parseInt(row.substring(column, column + 1));
            if (currentHeight > highestTree) {
                highestTree = currentHeight;
                addPotentialTree(currentRowNumber, column, currentHeight);
            }
        }
    }

    private void rightToLeftSearch(final String row) {
        int totalColumns = row.length();
        int highestTree = Integer.parseInt(row.substring(totalColumns - 1, totalColumns));
        addPotentialTree(currentRowNumber, totalColumns - 1, highestTree);
        for (int column = totalColumns - 1; column >= 0; column--) {
            int currentHeight = Integer.parseInt(row.substring(column, column + 1));
            if (currentHeight > highestTree) {
                highestTree = currentHeight;
                if (!addPotentialTree(currentRowNumber, column, currentHeight)) {
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

    private void findVisibleTreesInRow(String row) {
        leftToRightVisibleSearch(row);
        rightToLeftVisibleSearch(row);
    }

    private void rightToLeftVisibleSearch(String row) {
        int highestTree = Integer.parseInt(row.substring(0, 1));
        addVisibleTree(currentRowNumber, 0, highestTree);
        for (int column = 0; column < row.length(); column++) {
            int currentHeight = Integer.parseInt(row.substring(column, column + 1));
            if (currentHeight > highestTree) {
                highestTree = currentHeight;
                addVisibleTree(currentRowNumber, column, currentHeight);
            }
        }
    }

    private void leftToRightVisibleSearch(String row) {
        int totalColumns = row.length();
        int highestTree = Integer.parseInt(row.substring(totalColumns - 1, totalColumns));
        addVisibleTree(currentRowNumber, totalColumns - 1, highestTree);
        for (int column = totalColumns - 1; column >= 0; column--) {
            int currentHeight = Integer.parseInt(row.substring(column, column + 1));
            if (currentHeight > highestTree) {
                highestTree = currentHeight;
                if (!addVisibleTree(currentRowNumber, column, currentHeight)) {
                    break;
                }
            }
        }
    }
}
