package com.kasteelharry.aoc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day5 extends Day {

    private static final String FILENAME = "\\input\\day5.txt";
    private static final String FILENAME_TEST = "\\input\\day5Test.txt";
    private List<String> inputList;
    private final List<int[][]> parsedInput = new ArrayList<>();
    private final int[][] map;

    public Day5(int exp, boolean partTwo) {
        super(FILENAME_TEST, partTwo, exp);
        this.map = new int[10][10];
    }

    public Day5(boolean partTwo) {
        super(FILENAME, partTwo);
        this.map = new int[1000][1000];
    }

    public void parseList() {
        for (String s : inputList) {
            int[][] input = new int[2][2];
            String[] values = s.split("\\s+->\\s+");
            for (int i = 0; i < input.length; i++) {
                input[i][0] = Integer.parseInt(values[i].split(",")[0]);
                input[i][1] = Integer.parseInt(values[i].split(",")[1]);
            }

            parsedInput.add(input);
        }
    }

    public void addPipes() {
        for (int[][] pipe : parsedInput) {
            int xStart = pipe[0][0];
            int yStart = pipe[0][1];
            int xEnd = pipe[1][0];
            int yEnd = pipe[1][1];
            int offSetX = Math.abs(xStart - xEnd);
            int offSetY = Math.abs(yStart - yEnd);
            if (offSetY == offSetX && offSetY != 0 && !isPartTwo()) {
                continue;
            } else if (offSetX != 0 && offSetY !=0) {
                int[] pos = new int[]{xStart, yStart};
                int offSetX1 = xStart - xEnd;
                int offSetY1 = yStart - yEnd;
                while (pos[0] != xEnd && pos[1] != yEnd) {
                    int xPos = pos[0];
                    int yPos = pos[1];
                    map[yPos][xPos]++;
                    if (offSetX1 > 0){
                        pos[0]--;
                    } else if (offSetX1 < 0) {
                        pos[0]++;
                    }
                    if (offSetY1 > 0){
                        pos[1]--;
                    } else if (offSetY1 < 0){
                        pos[1]++;
                    }


                }
                map[yEnd][xEnd]++;
            } else {
                if (offSetX != 0){
                    for (int i = 0; i <= offSetX; i++) {
                        map[yEnd][Math.min(xStart, xEnd) + i]++;
                    }
                }
                if (offSetY == 0) {
                    continue;
                }
                for (int i = 0; i <= offSetY; i++) {
                    map[Math.min(yStart, yEnd) + i][xStart]++;
                }
            }
            }

    }

    public int calcSum() {
        int count = 0;
        for (int[] line : map) {
            for (int value : line) {
                if (value > 1) {
                    count++;
                }
            }
        }
        return count;
    }



    @Override
    public void start() {
        this.inputList = getLines();
        parseList();
        addPipes();
        System.out.println(calcSum());
        if (isTest()) {
            for (int[] line : map) {
                System.out.println(Arrays.toString(line));
            }
        }
    }

    @Override
    public void startPart2() {
        this.inputList = getLines();
        parseList();
        addPipes();
        System.out.println(calcSum());
        if (isTest()) {
            for (int[] line : map) {
                System.out.println(Arrays.toString(line));
            }
        }
    }


    public static void main(String[] args) {
        Day5 dayTest = new Day5(5, false);
        dayTest.run();
        dayTest = new Day5(12, true);
        dayTest.run();
        Day5 day5 = new Day5(false);
        day5.run();
        day5 = new Day5(true);
        day5.run();
    }
}
