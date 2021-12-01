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
    private List<Object> lines;

    public Day(String filePath) {
        this(filePath, true);
    }

    public Day(String filePath, boolean test) {
        this.filePath = filePath;
        this.isTest = test;
    }

    public void run() {

    }

    public List<Object> getLines() {
        return lines;
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

    public void convertToIntegers() {
        try {
            this.lines =
                    this.lines.stream().map(s -> Integer.parseInt((String) s)).collect(Collectors.toList());
        } catch (NumberFormatException ignored) {
            System.err.println("List contained a non-integer.");
        }
    }
}
