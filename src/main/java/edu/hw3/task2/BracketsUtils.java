package edu.hw3.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import org.apache.commons.lang3.StringUtils;

public final class BracketsUtils {

    private static final String WRONG_INPUT_MESSAGE = "Line should contain only '(' and ')' symbols";
    private static final String CANT_BE_CLUSTERIZED_MESSAGE = "This brackets line can`t be clusterized";

    private BracketsUtils() {
    }

    public static List<String> clusterize(String bracketsLine) {
        if (StringUtils.isBlank(bracketsLine)) {
            throw new IllegalArgumentException("Brackets line is empty");
        }
        List<String> clusters = new ArrayList<>();
        Stack<Character> leftBrackets = new Stack<>();
        Stack<Character> rightBrackets = new Stack<>();
        StringBuilder bracketCluster = new StringBuilder();
        int currentPosition = 0;
        while (currentPosition < bracketsLine.length() - 1) {
            char bracket = bracketsLine.charAt(currentPosition);
            bracketCluster.append(bracket);
            currentPosition++;
            addBracketToStack(bracket, leftBrackets, rightBrackets);
            while (!leftBrackets.isEmpty() || !rightBrackets.isEmpty()) {
                bracket = bracketsLine.charAt(currentPosition);
                bracketCluster.append(bracket);
                currentPosition++;
                addBracketToStack(bracket, leftBrackets, rightBrackets);
                if (!leftBrackets.isEmpty() && !rightBrackets.isEmpty()) {
                    leftBrackets.pop();
                    rightBrackets.pop();
                }
                if (currentPosition == bracketsLine.length()) {
                    break;
                }
            }
            if (leftBrackets.isEmpty() && rightBrackets.isEmpty()) {
                clusters.add(bracketCluster.toString());
                bracketCluster.delete(0, bracketCluster.length());
            }
        }
        if (!bracketCluster.isEmpty()) {
            throw new IllegalArgumentException(CANT_BE_CLUSTERIZED_MESSAGE);
        }
        return clusters;
    }

    private static void addBracketToStack(char bracket, Stack<Character> leftBrackets, Stack<Character> rightBrackets) {
        switch (bracket) {
            case '(' -> leftBrackets.push(bracket);
            case ')' -> rightBrackets.push(bracket);
            default -> throw new IllegalArgumentException(WRONG_INPUT_MESSAGE);
        }
    }
}
