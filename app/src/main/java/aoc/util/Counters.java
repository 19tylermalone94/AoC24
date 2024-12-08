package aoc.util;

public class Counters {

    public static <T> int countOccurrences(T val, T[][] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; ++i) {
            for (int j = 0; j < arr[0].length; ++j) {
                if (arr[i][j].equals(val)) {
                    ++count;
                }
            }
        }
        return count;
    }
    
    public static int countOccurrences(char val, char[][] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; ++i) {
            for (int j = 0; j < arr[0].length; ++j) {
                if (arr[i][j] == val) {
                    ++count;
                }
            }
        }
        return count;
    }

    public static int countOccurrences(int val, int[][] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; ++i) {
            for (int j = 0; j < arr[0].length; ++j) {
                if (arr[i][j] == val) {
                    ++count;
                }
            }
        }
        return count;
    }

    public static int countOccurrences(boolean[][] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; ++i) {
            for (int j = 0; j < arr[0].length; ++j) {
                if (arr[i][j]) {
                    ++count;
                }
            }
        }
        return count;
    }

}
