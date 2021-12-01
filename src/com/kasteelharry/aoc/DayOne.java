package com.kasteelharry.aoc;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DayOne extends Day {

    private static final String FILENAME = "\\input\\day1.txt";

    public DayOne() {
        super(FILENAME);
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
        super.convertLinesFromFile();
        super.convertToIntegers();
        List<Integer> list =
                super.getLines().stream().map(e -> (int) e).collect(Collectors.toList());
        System.out.println(calcNextHigher(list));
        System.out.println(calcSlidingWindow(list));
    }


    public static void main(String[] args) throws IOException {
        DayOne dayOne = new DayOne();
        dayOne.start();


        System.out.println("Done");
    }
}
