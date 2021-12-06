package com.kasteelharry.aoc;

import java.util.ArrayList;
import java.util.List;

public class Day6 extends Day {

    private static final String FILENAME = "\\input\\day6.txt";
    private static final String FILENAME_TEST = "\\input\\day6Test.txt";
    private static final int DAYS = 80;
    private List<String> inputList;
    private List<Integer> fish;
    private long count = 0;

    public Day6(boolean partTwo) {
        super(FILENAME, false);
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
        int removed = 0;
        for (int i = 0; i < DAYS; i++) {
            try {
                duplicate();

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

    }

    public static void main(String[] args) {
        Day6 dayTest = new Day6(0, false);
        dayTest.run();
        Day6 day6 = new Day6(false);
        day6.run();
    }
}
