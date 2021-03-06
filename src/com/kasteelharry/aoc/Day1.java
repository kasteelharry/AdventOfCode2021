package com.kasteelharry.aoc;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day1 extends Day {

    private static final String FILENAME = "\\input\\day1.txt";

    public Day1(boolean partTwo) {
        super(FILENAME, partTwo);
    }

    public long calcNextHigher(List<Integer> list) {
        return IntStream.rangeClosed(1, list.size() - 1).filter(i -> list.get(i - 1) < list.get(i)).count();
    }

    public long calcSlidingWindow(List<Integer> list) {
        return IntStream.rangeClosed(1, list.size() - 3)
                .filter(i -> getSumOfWindow(list, i)
                        > getSumOfWindow(list, i - 1)).count();
    }

    public int getSumOfWindow(List<Integer> list, int index) {
        return list.get(index) + list.get(index + 1) + list.get(index + 2);
    }

    public void start() {
        List<Integer> list = super.convertToIntegers();
        System.out.println("Part one: " + calcNextHigher(list));
    }

    public void startPart2() {
        List<Integer> list = super.convertToIntegers();
        System.out.println("Part two: " + calcSlidingWindow(list));
    }


    public static void main(String[] args) {
        Day1 dayOne = new Day1(false);
        dayOne.run();
        dayOne = new Day1(true);
        dayOne.run();
    }
}
