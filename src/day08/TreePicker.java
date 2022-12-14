package day08;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TreePicker {

    private final TreeMap<String, Tree> visibleTrees = new TreeMap<>();
    private final TreeMap<String, Integer> scoredTrees = new TreeMap<>();
    private Tree[][] treeArray;
    private int rowMax;
    private int columnMax;

    public TreeMap<String, Tree> findVisible(List<String> treeRows) {
        createArray(treeRows);
        leftToRightSearch();
        rightToLeftSearch();
        topToBottomSearch();
        bottomToTopSearch();
        return visibleTrees;
    }

    public TreeMap<String, Integer> gradeTrees(TreeMap<String, Tree> visibleTrees) {
        for (Map.Entry<String, Tree> entry : visibleTrees.entrySet()) {
            String key = entry.getKey();
            Tree value = entry.getValue();
            determineScenicScore(key, value);
        }
        return scoredTrees;
    }

    private void determineScenicScore(String key, Tree tree) {
        int scenicScore = 1;
        scenicScore *= lookLeft(tree);
        scenicScore *= lookRight(tree);
        scenicScore *= lookDown(tree);
        scenicScore *= lookUp(tree);
        scoredTrees.put(key, scenicScore);
    }

    private int lookLeft(Tree tree) {
        int height = tree.height();
        int visibleTreesAmount = 1;
        try {
            while (height > treeArray[tree.row()][tree.column() - visibleTreesAmount].height()) {
                visibleTreesAmount ++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return visibleTreesAmount - 1;
        }
        return visibleTreesAmount;
    }

    private int lookRight(Tree tree) {
        int height = tree.height();
        int visibleTreesAmount = 1;
        try {
            while (height > treeArray[tree.row()][tree.column() + visibleTreesAmount].height()) {
                visibleTreesAmount ++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return visibleTreesAmount - 1;
        }
        return visibleTreesAmount;
    }

    private int lookDown(Tree tree) {
        int height = tree.height();
        int visibleTreesAmount = 1;
        try {
            while (height > treeArray[tree.row() + visibleTreesAmount][tree.column()].height()) {
                visibleTreesAmount ++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return visibleTreesAmount - 1;
        }
        return visibleTreesAmount;
    }

    private int lookUp(Tree tree) {
        int height = tree.height();
        int visibleTreesAmount = 1;
        try {
            while (height > treeArray[tree.row() - visibleTreesAmount][tree.column()].height()) {
                visibleTreesAmount ++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return visibleTreesAmount - 1;
        }
        return visibleTreesAmount;
    }

    private void createArray(List<String> treeRows) {
        rowMax = treeRows.size();
        columnMax = treeRows.get(0).length();
        treeArray = new Tree[rowMax][columnMax];
        for (int row = 0; row < rowMax; row++) {
            String currentRow = treeRows.get(row);
            for (int column = 0; column < columnMax; column++) {
                int currentHeight = Integer.parseInt(currentRow.substring(column, column + 1));
                treeArray[row][column] = new Tree(row, column, currentHeight);
            }
        }
    }

    private void leftToRightSearch() {
        for (int row = 0; row < rowMax; row++) {
            int highestTree = treeArray[row][0].height();
            addTree(row, 0, highestTree);
            for (int column = 0; column < columnMax; column++) {
                int currentHeight = treeArray[row][column].height();
                if (currentHeight > highestTree) {
                    highestTree = currentHeight;
                    addTree(row, column, currentHeight);
                }
            }
        }
    }

    private void rightToLeftSearch() {
        for (int row = rowMax - 1; row > 0; row--) {
            int highestTree = treeArray[row][columnMax - 1].height();
            addTree(row, columnMax - 1, highestTree);
            for (int column = columnMax - 1; column >= 0; column--) {
                int currentHeight = treeArray[row][column].height();
                if (currentHeight > highestTree) {
                    highestTree = currentHeight;
                    addTree(row, column, currentHeight);
                }
            }
        }
    }

    private void topToBottomSearch() {
        for (int column = 0; column < columnMax; column++) {
            int highestTree = treeArray[0][column].height();
            addTree(0, column, highestTree);
            for (int row = 0; row < rowMax; row++) {
                int currentHeight = treeArray[row][column].height();
                if (currentHeight > highestTree) {
                    highestTree = currentHeight;
                    addTree(row, column, currentHeight);
                }
            }
        }
    }

    private void bottomToTopSearch() {
        for (int column = columnMax - 1; column > 0; column--) {
            int highestTree = treeArray[rowMax - 1][column].height();
            addTree(rowMax - 1, column - 1, highestTree);
            for (int row = rowMax - 1; row >= 0; row--) {
                int currentHeight = treeArray[row][column].height();
                if (currentHeight > highestTree) {
                    highestTree = currentHeight;
                    addTree(row, column, currentHeight);
                }
            }
        }
    }

    private void addTree(int row, int column, int height) {
        String treeName = row + "x" + column;
        Tree tree = new Tree(row, column, height);
        visibleTrees.put(treeName, tree);

    }
}

