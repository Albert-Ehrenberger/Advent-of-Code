package day09;

import java.util.HashSet;
import java.util.List;
import java.lang.Math;
import java.util.Set;

public class TailPositionMapper {

    private int[] headPosition = {0, 0};
    private int[] tailPosition = {0, 0};
    private final Set<String> tailBreadCrumb = new HashSet<>();

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
            case "R" -> moveHeadRight(stepsAmount);
            case "U" -> moveHeadUp(stepsAmount);
            case "D" -> moveHeadDown(stepsAmount);
        }
    }

    private void moveHeadLeft(int stepsAmount) {
        if (stepsAmount != 0) {
            int[] newHeadPosition = {headPosition[0] - 1, headPosition[1]};
            if (euclideanDistanceTail(newHeadPosition) > Math.sqrt(2)) {
                moveTail(headPosition);
            }
            headPosition = newHeadPosition;
            moveHeadLeft(stepsAmount - 1);
        }
    }

    private void moveHeadRight(int stepsAmount) {
        if (stepsAmount != 0) {
            int[] newHeadPosition = {headPosition[0] + 1, headPosition[1]};
            if (euclideanDistanceTail(newHeadPosition) > Math.sqrt(2)) {
                moveTail(headPosition);
            }
            headPosition = newHeadPosition;
            moveHeadRight(stepsAmount - 1);
        }
    }

    private void moveHeadUp(int stepsAmount) {
        if (stepsAmount != 0) {
            int[] newHeadPosition = {headPosition[0], headPosition[1] + 1};
            if (euclideanDistanceTail(newHeadPosition) > Math.sqrt(2)) {
                moveTail(headPosition);
            }
            headPosition = newHeadPosition;
            moveHeadUp(stepsAmount - 1);
        }
    }

    private void moveHeadDown(int stepsAmount) {
        if (stepsAmount != 0) {
            int[] newHeadPosition = {headPosition[0], headPosition[1] - 1};
            if (euclideanDistanceTail(newHeadPosition) > Math.sqrt(2)) {
                moveTail(headPosition);
            }
            headPosition = newHeadPosition;
            moveHeadDown(stepsAmount - 1);
        }
    }

    private void moveTail(int[] oldHeadPosition) {
        addBreadCrumb();
        tailPosition = oldHeadPosition;
    }

    private double euclideanDistanceTail(int[] newHeadPosition) {
        int xHNew = newHeadPosition[0];
        int yHNew = newHeadPosition[1];
        int xT = tailPosition[0];
        int yT = tailPosition[1];
        return Math.sqrt(Math.pow(xHNew - xT, 2) + Math.pow(yHNew - yT, 2));
    }

    private void addBreadCrumb() {
        tailBreadCrumb.add(headPosition[0] + "x" + headPosition[1]);
    }
}
