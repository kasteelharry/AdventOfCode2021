package com.kasteelharry.aoc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Day6 extends Day {

    private static final String FILENAME = "\\input\\day6.txt";
    private static final String FILENAME_TEST = "\\input\\day6Test.txt";
    private static final int DAYS = 80;
    private static final int DAYS_PART_TWO = 256;
    private List<String> inputList;
    private List<Integer> fish;
    private long[] totalFish = new long[9];
    private long count = 0;

    public Day6(boolean partTwo) {
        super(FILENAME, partTwo);
        this.inputList = new ArrayList<>();
        this.fish = new ArrayList<>();
    }

    public Day6(int exp, boolean partTwo) {
        super(FILENAME_TEST, partTwo, exp);
        this.inputList = new ArrayList<>();
        this.fish = new ArrayList<>();
    }

    private void parseInput() {
        String[] arr = inputList.get(0).trim().split(",");
        for (String s : arr) {
            this.fish.add(Integer.parseInt(s));
        }
    }

    public void duplicate() {
        List<Integer> fishState = new ArrayList<>(this.fish);
        for (int i = 0; i < fishState.size(); i++) {
            int cycle = this.fish.get(i);
            cycle--;
            if (cycle < 0) {
                this.fish.set(i, 6);
                this.fish.add(8);
            } else {
                this.fish.set(i, cycle);
            }
        }
    }

    private void dayCycle() {
        for (int i = 0; i < DAYS; i++) {
            try {
                duplicate();
            } catch (OutOfMemoryError ignore){
                System.out.println("Failed on day " + i);
                return;
            }

        }
    }

    private void dayCycle2() {
        for (Integer integer : this.fish) {
            totalFish[integer]++;
        }
        for (int i = 0; i < DAYS_PART_TWO; i++) {
            try {

                long val = totalFish[0];
                System.arraycopy(totalFish, 1, totalFish, 0, totalFish.length - 1);
                totalFish[8] = val;
                totalFish[6] += val;
            } catch (OutOfMemoryError ignore){
                System.out.println("Failed on day " + i);
                return;
            }

        }
    }

    @Override
    public void start() {
        this.inputList = getLines();
        parseInput();
        dayCycle();
        System.out.println(fish.size());
    }

    @Override
    public void startPart2() {
        this.inputList = getLines();
        parseInput();
        dayCycle2();
        System.out.println(Arrays.stream(totalFish).sum());
    }


    public static void main(String[] args) {
        Day6 dayTest = new Day6(5934, false);
        dayTest.run();
        Day6 day6 = new Day6(false);
        day6.run();
        dayTest = new Day6(0, true);
        dayTest.run();
        day6 = new Day6(true);
        day6.run();
    }
}
