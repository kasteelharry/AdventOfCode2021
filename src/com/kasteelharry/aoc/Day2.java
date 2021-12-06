package com.kasteelharry.aoc;

import java.util.List;
import java.util.stream.Collectors;

public class Day2 extends Day {

    private static final String FILENAME = "\\input\\day2.txt";
    private static final String FILENAME_TEST = "\\input\\day2Test.txt";
    private int xPos;
    private int yPos;
    private int depth;

    public Day2(boolean partTwo) {
        super(FILENAME, partTwo);
        this.xPos = 0;
        this.yPos = 0;
        this.depth = 0;
    }

    public Day2(int exp, boolean partTwo) {
        super(FILENAME_TEST, partTwo, exp);
        this.xPos = 0;
        this.yPos = 0;
        this.depth = 0;
    }

    public void moveUp(int amount) {
        yPos += amount;
    }

    public void moveDown(int amount) {
        yPos -= amount;
    }

    public void horizontal(int amount) {
        xPos += amount;
    }

    public void parseCommand(String[] command) {
        try {
            int amount = Integer.parseInt(command[1]);
            switch (command[0]) {
                case "down":
                    moveUp(amount);
                    break;
                case "up":
                    moveDown(amount);
                    break;
                case "forward":
                    horizontal(amount);
                    break;
                default:
                    System.err.println("Weird command found, check input");
                    break;
            }

        } catch (NumberFormatException ignore) {
            System.err.println("Wrong command format found");
        }
    }

    private void horizontalAim(int amount) {
        xPos += amount;
        depth += amount * yPos;
    }

    public void parseCommand2(String[] command) {
        try {
            int amount = Integer.parseInt(command[1]);
            switch (command[0]) {
                case "down":
                    moveUp(amount);
                    break;
                case "up":
                    moveDown(amount);
                    break;
                case "forward":
                    horizontalAim(amount);
                    break;
                default:
                    System.err.println("Weird command found, check input");
                    break;
            }

        } catch (NumberFormatException ignore) {
            System.err.println("Wrong command format found");
        }
    }



    public void start() {
        List<String[]> input =
                getLines().stream().map(s -> s.split("\\s")).collect(Collectors.toList());
        for (String[] cmd :
                input) {
            parseCommand(cmd);
        }

        System.out.println(xPos * yPos);
    }

    public void startPart2() {
        this.xPos = 0;
        this.yPos = 0;
        this.depth = 0;
        List<String[]> input =
                getLines().stream().map(s -> s.split("\\s")).collect(Collectors.toList());
        for (String[] cmd :
                input) {
            parseCommand2(cmd);
        }

        System.out.println(xPos * depth);
    }

    public static void main(String[] args) {
        Day2 day = new Day2(150, false);
        day.run();
        Day2 day2 = new Day2(false);
        day2.run();
        day = new Day2(900, true);
        day.run();
        day2 = new Day2(true);
        day2.run();
    }
}
