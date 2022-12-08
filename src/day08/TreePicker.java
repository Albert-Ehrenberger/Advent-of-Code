package day08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

public class TreePicker {

    private final TreeMap<String, Tree> visibleTrees = new TreeMap<>();
    private final TreeMap<String, Tree> visibleTrees2 = new TreeMap<>();
    private int currentRowNumber;
    private List<String> rotatedTreeMap;

    public TreeMap<String, Tree> findVisible(List<String> treeRows) {
        for (currentRowNumber = 0; currentRowNumber < treeRows.size(); currentRowNumber++) {
            findVisibleTreesByRow(treeRows.get(currentRowNumber));
        }
        initializeRotatedTreeMap(treeRows.size(), treeRows.get(0).length());
        createRotatedTreeMap(treeRows);
        for (currentRowNumber = 0; currentRowNumber < rotatedTreeMap.size(); currentRowNumber++) {
            findVisibleTreesByColumn(rotatedTreeMap.get(currentRowNumber));
        }
        return visibleTrees;
    }

    public void addFirstAndLastPotentialRows(List<String> treeRows) {
        String firstRow = treeRows.get(0);
        String lastRow = treeRows.get(treeRows.size() - 1);
        for (int column = 0; column < firstRow.length(); column++) {
            addTree(0, column, Integer.parseInt(firstRow.substring(column, column + 1)));
            addTree(treeRows.size() - 1, column, Integer.parseInt(lastRow.substring(column, column + 1)));
        }
    }

    public void addFirstAndLastVisibleRows() {
        String firstRow = rotatedTreeMap.get(0);
        String lastRow = rotatedTreeMap.get(rotatedTreeMap.size() - 1);
        for (int column = 0; column < firstRow.length(); column++) {
            addRotatedTree(0, column, Integer.parseInt(firstRow.substring(column, column + 1)));
            addRotatedTree(rotatedTreeMap.size() - 1, column, Integer.parseInt(lastRow.substring(column, column + 1)));
        }
    }


    private void findVisibleTreesByRow(String row) {
        leftToRightRowSearch(row);
        rightToLeftRowSearch(row);
    }

    private void addTree(int row, int column, int height) {
        String treeName = String.valueOf(row) + "x" + String.valueOf(column);
        Tree tree = new Tree(row, column, height);
        visibleTrees.put(treeName, tree);

    }

    private void addRotatedTree(int column, int row, int height) {
        String treeName = String.valueOf(row) + "x" + String.valueOf(column);
        Tree tree = new Tree(row, column, height);
        visibleTrees.put(treeName, tree);

    }

    private void leftToRightRowSearch(final String row) {
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

    private void rightToLeftRowSearch(final String row) {
        int totalColumns = row.length();
        int highestTree = Integer.parseInt(row.substring(totalColumns - 1, totalColumns));
        addTree(currentRowNumber, totalColumns - 1, highestTree);
        for (int column = totalColumns - 1; column >= 0; column--) {
            int currentHeight = Integer.parseInt(row.substring(column, column + 1));
            if (currentHeight > highestTree) {
                highestTree = currentHeight;
                addRotatedTree(currentRowNumber, column, currentHeight);
            }
        }
    }

    private void createRotatedTreeMap() {
        Collection<Tree> trees = visibleTrees.values();
        for (Tree tree : trees) {
            StringBuilder oldRow = new StringBuilder(rotatedTreeMap.get(tree.column()));
            oldRow.setCharAt(tree.row(), String.valueOf(tree.height()).charAt(0));
            rotatedTreeMap.set(tree.column(), oldRow.toString());
        }
    }

    private void createRotatedTreeMap(List<String> treeRows) {
        for (int row = 0; row < treeRows.size(); row++) {
            char[] trees = treeRows.get(row).toCharArray();
            for (int column = 0; column < trees.length; column++) {
                StringBuilder oldRow = new StringBuilder(rotatedTreeMap.get(column));
                oldRow.setCharAt(row, trees[column]);
                rotatedTreeMap.set(column, oldRow.toString());
            }
        }
    }

    private void initializeRotatedTreeMap(int rows, int columns) {
        rotatedTreeMap = new ArrayList<>(columns);
        for (int i = 0; i < columns; i++) {
            StringBuilder sb = new StringBuilder();
            while (sb.length() < rows) {
                sb.append('0');
            }
            rotatedTreeMap.add(sb.toString());
        }
    }

    private void findVisibleTreesByColumn(String row) {
        leftToRightColumnSearch(row);
        rightToLeftColumnSearch(row);
    }

    private void rightToLeftColumnSearch(String row) {
        int highestTree = Integer.parseInt(row.substring(0, 1));
        addRotatedTree(currentRowNumber, 0, highestTree);
        for (int column = 0; column < row.length(); column++) {
            int currentHeight = Integer.parseInt(row.substring(column, column + 1));
            if (currentHeight > highestTree) {
                highestTree = currentHeight;
                addRotatedTree(currentRowNumber, column, currentHeight);
            }
        }
    }

    private void leftToRightColumnSearch(String row) {
        int totalColumns = row.length();
        int highestTree = Integer.parseInt(row.substring(totalColumns - 1, totalColumns));
        addRotatedTree(currentRowNumber, totalColumns - 1, highestTree);
        for (int column = totalColumns - 1; column >= 0; column--) {
            int currentHeight = Integer.parseInt(row.substring(column, column + 1));
            if (currentHeight > highestTree) {
                highestTree = currentHeight;
                addRotatedTree(currentRowNumber, column, currentHeight);
            }
        }
    }
}

