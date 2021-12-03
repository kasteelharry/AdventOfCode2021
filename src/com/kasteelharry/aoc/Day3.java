package com.kasteelharry.aoc;

import java.util.ArrayList;
import java.util.List;

public class Day3 extends Day {

    private static final String FILENAME = "\\input\\day3.txt";
    private static final String FILENAME_TEST = "\\input\\day3Test.txt";
    private int lsb;
    private int msb;
    private final char[] arrayLSB;
    private final char[] arrayMSB;
    private final int[] equals;
    private final int length;
    private List<String> input;

    public Day3(int length) {
        super(FILENAME);
        this.length = length;
        this.arrayLSB = new char[length];
        this.arrayMSB = new char[length];
        this.equals = new int[length];
    }

    public Day3(int length, int exp) {
        super(FILENAME_TEST, true, exp);
        this.length = length;
        this.arrayLSB = new char[length];
        this.arrayMSB = new char[length];
        this.equals = new int[length];
    }

    public void findMSB(List<String> list) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.length; i++) {
            int one = 0;
            int zero = 0;
            for (String s : list) {
                if (s.charAt(i) == '0') {
                    zero++;
                } else {
                    one++;
                }
            }
            if (one == zero) {
                equals[i] = 2;
            }
            if (one > zero) {
                result.append("1");
                arrayMSB[i] = '1';
                arrayLSB[i] = '0';
            } else {
                result.append("0");
                arrayMSB[i] = '0';
                arrayLSB[i] = '1';
            }
        }
        msb = Integer.parseInt(result.toString(), 2);
    }

    public char getMSBChar(int pos) {
        if (equals[pos] == 0) {
            return arrayMSB[pos];
        } else {
            return '1';
        }
    }

    public char getLSBChar(int pos) {
        if (equals[pos] == 0) {
            return arrayLSB[pos];
        } else {
            return '0';
        }
    }

    public int filterOxygen(List<String> list, int pos) {

        if (list.size() == 1) {
            return Integer.parseInt(list.get(0), 2);
        }
        List<String> copy = new ArrayList<>();
        for (String s : list) {
            if (s.charAt(pos) == getMSBChar(pos)) {
                copy.add(s);
            }
        }
        pos++;
        findMSB(copy);
        return filterOxygen(copy, pos);
    }

    public int filterCO2(List<String> list, int pos) {

        if (list.size() == 1) {
            return Integer.parseInt(list.get(0), 2);
        }
        List<String> copy = new ArrayList<>();
        for (String s : list) {
            if (s.charAt(pos) == getLSBChar(pos)) {
                copy.add(s);
            }
        }
        pos++;
        findMSB(copy);
        findLSB();
        return filterCO2(copy, pos);
    }

    public void findLSB() {
        int mask = (1 << length) - 1;
        lsb = ~msb & mask;
    }

    public void start() {
        startUp();
        System.out.println("multiplying " + msb + " and " + lsb);
        System.out.println("Calculated value is: " + msb * lsb);

    }

    public void start2() {
        startUp();
        int ox = filterOxygen(input, 0);
        int co = filterCO2(input, 0);
        System.out.println("Calculated value is: " + ox * co);
    }

    private void startUp() {
        super.convertLinesFromFile();
        List<String> list = getLines();
        this.input = list;
        findMSB(list);
        findLSB();

        if (isTest()) {
            System.out.println("Expected value is: " + getExpectedValue());
        } else {
            System.out.println("Your answer is: ");
        }
    }

    public static void main(String[] args) {
        Day3 dayTest = new Day3(5, 198);
        dayTest.start();
        Day3 day3 = new Day3(12);
        day3.start();
        Day3 day3Test = new Day3(5, 230);
        day3Test.start2();
        Day3 day32 = new Day3(12);
        day3.start2();
        day32.start2();
    }
}
