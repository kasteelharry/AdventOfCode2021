package com.kasteelharry.aoc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day7 extends Day {
    private static final String FILENAME = "\\input\\day7.txt";
    private static final String FILENAME_TEST = "\\input\\day7Test.txt";
    private List<String> inputList;
    private final List<Integer> input = new ArrayList<>();

    public Day7(boolean partTwo) {
        super(FILENAME, partTwo);
    }

    public Day7(int exp, boolean partTwo) {
        super(FILENAME_TEST, partTwo, exp);
    }

    private void parseInput(){
        String[] split = inputList.get(0).split(",");
        for (String s : split) {
            input.add(Integer.parseInt(s));
        }
    }

    private int findLowestFuel(){
        int minOffset = -1;
        for (int i = 0; i < Collections.max(input); i++) {
            int[] arr = new int[input.size()];
            for (int j = 0; j < input.size(); j++) {
                arr[j] = Math.abs(input.get(j) - i);
            }
            int fuel = Arrays.stream(arr).sum();
            if (fuel < minOffset || minOffset < 0) {
                minOffset = fuel;
            }
        }
        return minOffset;
    }

    private int findLowestIncFuel(){
        int minOffset = -1;
        for (int i = 0; i < Collections.max(input); i++) {
            int[] arr = new int[input.size()];
            for (int j = 0; j < input.size(); j++) {
                int displacement = Math.abs(input.get(j) - i);
                arr[j] = displacement + getSum(displacement);
            }
            int fuel = Arrays.stream(arr).sum();
            if (fuel < minOffset || minOffset < 0) {
                minOffset = fuel;
            }
        }
        return minOffset;
    }

    private int getSum(int n) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            count += i;
        }
        return count;
    }

    @Override
    public void start() {
        this.inputList = getLines();
        parseInput();
        System.out.println(findLowestFuel());

    }

    @Override
    public void startPart2() {
        this.inputList = getLines();
        parseInput();
        System.out.println(findLowestIncFuel());
    }

    public static void main(String[] args) {
        Day7 dayTest = new Day7(37, false);
        dayTest.run();
        Day7 day7 = new Day7(false);
        day7.run();
        dayTest = new Day7(168, true);
        dayTest.run();
        day7 = new Day7(true);
        day7.run();
    }

}
