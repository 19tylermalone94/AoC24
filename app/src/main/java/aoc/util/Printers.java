package aoc.util;

import java.util.Arrays;

public class Printers {

    public static <T> void printArr(T[][] arr) {
        Arrays.stream(arr).map(Arrays::toString).forEach(System.out::println);
    }

    public static void printArr(boolean[][] arr) {
        Arrays.stream(arr).map(Arrays::toString).forEach(System.out::println);
    }

    public static void printArr(char[][] arr) {
        Arrays.stream(arr).map(Arrays::toString).forEach(System.out::println);
    }
    
}
