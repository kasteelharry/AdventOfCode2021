package com.kasteelharry.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Day {

    private String filePath;
    private boolean isTest;
    private List<String> lines;
    private final int expectedValue;
    private boolean isPartTwo;

    public Day(String filePath) {
        this.filePath = filePath;
        this.expectedValue = 0;
    }

    public Day(String filePath, boolean partTwo) {
        this.filePath = filePath;
        this.expectedValue = 0;
        this.isPartTwo = partTwo;
    }

    public Day(String filePath, boolean partTwo, int exp) {
        this.filePath = filePath;
        this.isTest = true;
        this.expectedValue = exp;
        this.isPartTwo = partTwo;
    }

    public List<String> getLines() {
        return lines;
    }

    public int getExpectedValue() {
        return expectedValue;
    }

    public boolean isTest() {
        return isTest;
    }

    public boolean isPartTwo() {
        return isPartTwo;
    }

    public void convertLinesFromFile() {
        try {
            Path pwd = Paths.get("").toAbsolutePath();
            Path target = Paths.get(pwd + "\\src\\com\\kasteelharry\\aoc" + filePath);
            this.lines =
                    Files.readAllLines(target).stream().map(String::trim).collect(Collectors.toList());
        } catch (IOException | InvalidPathException e) {
            System.err.println("Encountered error while parsing the file. Please check your path.");
        }
    }

    public List<Integer> convertToIntegers() {
        try {
            return
                    this.lines.stream().map(Integer::parseInt).collect(Collectors.toList());
        } catch (NumberFormatException ignored) {
            System.err.println("List contained a non-integer.");
        }
        return null;
    }

    public abstract void start();

    public abstract void startPart2();

    public void run() {
        System.out.println("############# Advent of Code 2021 #############");
        convertLinesFromFile();
        if (isTest) {
            if (isPartTwo) {
                runTestPartTwo();
            } else {
                runTest();
            }
        } else {
            if (isPartTwo) {
                System.out.print("\n\tYour answer for part two is: ");
                startPart2();
            } else {
                System.out.print("\n\tYour answer for part one is: ");
                start();
                System.out.print("\n\tYour answer for part two is: ");
                startPart2();
            }

        }

        System.out.println("\n############# Advent of Code 2021 #############");
    }

    public void runTest() {
        System.out.print("\n\tYour value is:              ");
        start();
        System.out.print("\n\tThe expected value was:     " + expectedValue);
    }

    public void runTestPartTwo() {
        System.out.print("\n\tYour value is:              ");
        startPart2();
        System.out.print("\n\tThe expected value was:     " + expectedValue);
    }
}
