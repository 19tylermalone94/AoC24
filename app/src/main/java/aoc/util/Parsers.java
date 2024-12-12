package aoc.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Parsers {

    public static List<String> readLines(String data) {
        return Arrays.stream(data.split("\n"))
                     .collect(Collectors.toList());
    }

    public static List<List<Integer>> readMatrix(String data) throws IOException {
        return Arrays.stream(data.trim().split("\n"))
                     .map(line -> readIntegers(line))
                     .collect(Collectors.toList());
    }

    public static char[][] charMatrix(String data) throws IOException {
        return Arrays.stream(data.trim().split("\n"))
                     .map(String::toCharArray)
                     .toArray(char[][]::new);
    }

    public static int[][] intMatrix(String data) throws IOException {
        char[][] charGrid = charMatrix(data);
        int[][] grid = new int[charGrid.length][charGrid[0].length];
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                grid[i][j] = charGrid[i][j] - '0';
            }
        }
        return grid;
    }

    public static List<Integer> readIntegers(String data) {
        return Arrays.stream(data.split(" "))
                     .mapToInt(Integer::parseInt)
                     .boxed()
                     .collect(Collectors.toList());
    }

    public static List<Long> readLongs(String data) {
        return Arrays.stream(data.split(" "))
                     .mapToLong(Long::parseLong)
                     .boxed()
                     .collect(Collectors.toList());
    }
    
}
