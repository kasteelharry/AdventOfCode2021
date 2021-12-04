package com.kasteelharry.aoc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day4 extends Day {
    private static final String FILENAME = "\\input\\day4.txt";
    private static final String FILENAME_TEST = "\\input\\day4Test.txt";
    private List<String> inputList;
    private List<Integer> numbers = new ArrayList<>();
    private List<String[][]> tables = new ArrayList<>();
    private String[][] bingo = new String[5][];
    private final List<String[][]> bingoList = new ArrayList<>();

    public Day4() {
        super(FILENAME);
    }

    public Day4(int exp, boolean partTwo) {
        super(FILENAME_TEST, partTwo, exp);
    }

    private void parseInput() {
        String lineOne = inputList.get(0);
        String[] split = lineOne.split(",");
        for (String s: split) {
            numbers.add(Integer.parseInt(s));
        }
        inputList.remove(lineOne);
    }

    private void parseTable() {
        for (int i = 5; i < inputList.size(); i += 6) {
            String[][] table = new String[5][];
            table[0] = inputList.get(i - 4).split("\\s+");
            table[1] = inputList.get(i - 3).split("\\s+");
            table[2] = inputList.get(i - 2).split("\\s+");
            table[3] = inputList.get(i - 1).split("\\s+");
            table[4] = inputList.get(i).split("\\s+");
            tables.add(table);
        }
    }

    public void checkLine(int num) {
        for(String[][] table: tables) {
            for (String[] line : table) {
                for (int j = 0; j < line.length; j++) {
                    try {
                        if (Integer.parseInt(line[j]) == num) {
                            line[j] = "X";
                        }
                    } catch (NumberFormatException ignore) {
                    }
                }

            }
        }
    }

    public boolean checkVertical() {
        for (String[][] table: tables) {
            for (String[] value : table) {
                if (Arrays.stream(value).allMatch(s -> s.trim().equals("X") || s.isEmpty() || s.isBlank())) {
                    this.bingo = table;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkHorizontal() {
        for (String[][] table: tables) {
            for (int i = 0; i < 5; i++) {
                boolean streak = false;
                for (String[] line: table) {
                    if (!line[i].equals("X")) {
                        streak = false;
                        break;
                    } else {
                        streak = true;
                    }
                }
                if (streak) {
                    this.bingo = table;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkVertical2() {
        for (String[][] table: tables) {
            for (String[] value : table) {
                if (Arrays.stream(value).allMatch(s -> s.trim().equals("X") || s.isEmpty() || s.isBlank())) {
                    this.bingoList.add(table);

                }
            }
        }
        return bingoList.size() > 0;
    }

    public boolean checkHorizontal2() {
        for (String[][] table: tables) {
            for (int i = 0; i < 5; i++) {
                boolean streak = false;
                for (String[] line: table) {
                    if (!line[i].equals("X")) {
                        streak = false;
                        break;
                    } else {
                        streak = true;
                    }
                }
                if (streak) {
                    this.bingoList.add(table);
                }
            }
        }
        return bingoList.size() > 0;
    }

    private int calcSum() {
        int sum = 0;
        for (String[] line : bingo) {
            for (String s : line) {
                try {
                    sum += Integer.parseInt(s);
                } catch (NumberFormatException ignore){

                }
            }

        }
        return sum;
    }

    private void mainLoop() {
        int curNum = -1;
        for (int draw : numbers) {
            curNum = draw;
            checkLine(draw);
            if (checkHorizontal()) {
                break;
            } else if (checkVertical()) {
                break;
            }
        }
        System.out.println(curNum * calcSum());
    }



    @Override
    public void start() {
        this.inputList = new ArrayList<>(getLines());
        parseInput();
        parseTable();
        mainLoop();
    }

    @Override
    public void startPart2() {
        numbers = new ArrayList<>();
        tables = new ArrayList<>();
        bingo = new String[5][];
        this.inputList = new ArrayList<>(getLines());
        parseInput();
        parseTable();
        mainLoop2();
    }

    private void mainLoop2() {
        int curNum = -1;
        for (int draw : numbers) {
            curNum = draw;
            checkLine(draw);
            if (checkHorizontal2() || checkVertical2()) {
                if (tables.size() == 1) {
                    this.bingo = tables.get(0);
                    break;
                }
                for (String[][] bing : bingoList) {
                    tables.remove(bing);
                }
                bingoList.clear();
            }

        }
        System.out.println(curNum * calcSum());
    }

    public static void main(String[] args) {
        Day4 day = new Day4(4512, false);
        day.run();
        day = new Day4(1924, true);
        day.run();

        Day4 day4 = new Day4();
        day4.run();

    }
}
