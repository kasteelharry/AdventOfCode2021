package com.kasteelharry.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Day {

    private String filePath;
    private boolean isTest;
    private List<String> lines;
    private final int expectedValue;

    public Day(String filePath) {
        this.filePath = filePath;
        this.expectedValue = 0;
    }

    public Day(String filePath, boolean test, int exp) {
        this.filePath = filePath;
        this.isTest = test;
        this.expectedValue = exp;
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
}
