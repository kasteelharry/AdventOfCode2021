package com.kasteelharry.aoc;

import java.util.List;
import java.util.stream.Collectors;

public class Day2 extends Day {

    private static final String FILENAME = "\\input\\day2.txt";
    private static final String FILENAME_TEST = "\\input\\day2Test.txt";
    private int xPos;
    private int yPos;
    private int depth;

    public Day2() {
        super(FILENAME);
        this.xPos = 0;
        this.yPos = 0;
        this.depth = 0;
    }

    public Day2(int exp) {
        super(FILENAME_TEST, true, exp);
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
        super.convertLinesFromFile();
        List<String[]> input =
                getLines().stream().map(s -> s.split("\\s")).collect(Collectors.toList());
        for (String[] cmd :
                input) {
            parseCommand(cmd);
        }
        if (isTest()) {
           System.out.println("Expected value is: " + getExpectedValue());
        } else {
            System.out.println("Your answer is: ");
        }

        System.out.println("Calculated value is: " + xPos * yPos);
    }

    public void startPart2() {
        super.convertLinesFromFile();
        List<String[]> input =
                getLines().stream().map(s -> s.split("\\s")).collect(Collectors.toList());
        for (String[] cmd :
                input) {
            parseCommand2(cmd);
        }
        if (isTest()) {
            System.out.println("Expected value is: " + getExpectedValue());
        } else {
            System.out.println("Your answer is: ");
        }

        System.out.println("Calculated value is: " + xPos * depth);
    }

    public static void main(String[] args) {
        Day2 day = new Day2(150);
        day.start();
        Day2 day2 = new Day2();
        day2.start();
        Day2 dayP2 = new Day2(900);
        dayP2.startPart2();
        Day2 day2P2 = new Day2();
        day2P2.startPart2();
    }
}
