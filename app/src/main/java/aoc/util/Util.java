package aoc.util;

public class Util {
    
    public static char[][] copyArray(char[][] original) {
        char[][] copy = new char[original.length][original[0].length];
        for (int i = 0; i < original.length; ++i) {
            for (int j = 0; j < original[0].length; ++j) {
                copy[i][j] = original[i][j];
            }
        }
        return copy;
    }

}
