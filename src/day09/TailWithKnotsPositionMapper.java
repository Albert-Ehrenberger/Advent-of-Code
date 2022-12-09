package day09;

import java.util.HashSet;
import java.util.List;
import java.lang.Math;
import java.util.Set;

public class TailWithKnotsPositionMapper {

    private final int knotsAmount;
    private final int[][] knotsPosition;
    private final Set<String> tailBreadCrumb = new HashSet<>();

    public TailWithKnotsPositionMapper(int knotsAmount) {
        this.knotsAmount = knotsAmount;
        knotsPosition = new int[knotsAmount][2];
        for (int[] position : knotsPosition) {
            position = new int[]{0, 0};
        }
    }

    public Set<String> calculateBreadCrumb(List<String> steps) {
        addBreadCrumb();
        for (String step : steps) {
            String[] stepParts = step.split(" ");
            String direction = stepParts[0];
            int stepsAmount = Integer.parseInt(stepParts[1]);
            moveHead(direction, stepsAmount);
        }
        return tailBreadCrumb;
    }

    private void moveHead(String direction, int stepsAmount) {
        switch (direction) {
            case "L" -> moveHeadLeft(stepsAmount);
            case "R" -> moveHeadRight(0, stepsAmount);
            case "U" -> moveHeadUp(stepsAmount);
            case "D" -> moveHeadDown(stepsAmount);
        }
    }

    private void moveHeadLeft(int stepsAmount) {
        if (stepsAmount != 0) {
            for (int knot = 0; knot < knotsAmount - 1; knot++) {
                int[] newKnotPosition = {knotsPosition[knot][0] - 1, knotsPosition[knot][1]};
                if (euclideanDistanceTail(knotsPosition[knot + 1], newKnotPosition) > Math.sqrt(2)) {
                    moveKnot(knot + 1, knotsPosition[knot]);
                }
                knotsPosition[knot] = newKnotPosition;
            }
            moveHeadLeft(stepsAmount - 1);
        }
    }

    private void moveHeadRight(int knot, int stepsAmount) {
        if (stepsAmount != 0 && knot < knotsAmount) {
            int[] newKnotPosition = {knotsPosition[knot][0] + 1, knotsPosition[knot][1]};
            moveSuccessor(knot + 1, newKnotPosition);
            knotsPosition[knot] = newKnotPosition;
            moveHeadRight(knot, stepsAmount - 1);
        }
    }

    private void moveSuccessor(int knot, int[] newPosition) {
            if (knot < knotsAmount - 1 && euclideanDistanceTail(knotsPosition[knot], newPosition) > Math.sqrt(2)) {
                moveSuccessor(knot + 1, knotsPosition[knot -1]);
                moveKnot(knot , knotsPosition[knot -1]);
            }
    }

    private void moveHeadUp(int stepsAmount) {
        if (stepsAmount != 0) {
            for (int knot = 0; knot < knotsAmount - 1; knot++) {
                int[] newKnotPosition = {knotsPosition[knot][0], knotsPosition[knot][1] + 1};
                if (euclideanDistanceTail(knotsPosition[knot + 1], newKnotPosition) > Math.sqrt(2)) {
                    moveKnot(knot + 1, knotsPosition[knot]);
                }
                knotsPosition[knot] = newKnotPosition;
            }
            moveHeadLeft(stepsAmount - 1);
        }
    }

    private void moveHeadDown(int stepsAmount) {
        if (stepsAmount != 0) {
            for (int knot = 0; knot < knotsAmount - 1; knot++) {
                int[] newKnotPosition = {knotsPosition[knot][0], knotsPosition[knot][1] - 1};
                if (euclideanDistanceTail(knotsPosition[knot + 1], newKnotPosition) > Math.sqrt(2)) {
                    moveKnot(knot + 1, knotsPosition[knot]);
                }
                knotsPosition[knot] = newKnotPosition;
            }
            moveHeadLeft(stepsAmount - 1);
        }
    }

    private void moveKnot(int knot, int[] oldPredecessorPosition) {
        knotsPosition[knot] = oldPredecessorPosition;
        if (knot == knotsAmount - 1) {
            addBreadCrumb();
        }
    }

    private double euclideanDistanceTail(int[] knotPosition, int[] newPositionOfPredecessor) {
        int xHNew = newPositionOfPredecessor[0];
        int yHNew = newPositionOfPredecessor[1];
        int xT = knotPosition[0];
        int yT = knotPosition[1];
        return Math.sqrt(Math.pow(xHNew - xT, 2) + Math.pow(yHNew - yT, 2));
    }

    private void addBreadCrumb() {
        tailBreadCrumb.add(knotsPosition[knotsAmount - 1][0] + "x" + knotsPosition[knotsAmount - 1][1]);
    }
}
