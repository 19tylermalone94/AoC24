package aoc.util;

public class Finders {
    
    public static int countOccurrences(int val, char[][] arr) {
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
