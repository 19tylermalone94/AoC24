package aoc.days;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.IntStream;
import aoc.util.Readers;

public class Day4 implements Day {

    static final String word = "XMAS";
    static final int[] di = {-1, -1, -1, 0, 0, 1, 1, 1};
    static final int[] dj = {-1, 0, 1, -1, 1, -1, 0, 1};

    char[][] grid;
    int M;
    int N;

    private void init(String data) throws IOException{
        grid = Arrays.stream(data.split("\n"))
                     .map(String::toCharArray)
                     .toArray(char[][]::new);
        M = grid.length;
        N = grid[0].length;
    }

    @Override
    public void run(String filename) throws IOException {
        String data = Readers.fileToString(filename);
        System.out.printf("part 1: %d\npart 2: %d\n", part1(data), part2(data));
    }

    int part1(String data) throws IOException {
        init(data);
        return IntStream.range(0, M * N)
                        .map(i -> checkDirections(i / N, i % N))
                        .sum();
    }

    int checkDirections(int i, int j) {
        return IntStream.range(0, 8)
                        .map(d -> isFound(i, j, d) ? 1 : 0)
                        .sum();
    }

    boolean isFound(int i, int j, int d) {
        int k = 0;
        while (i >= 0 && i < M && j >= 0 && j < N && k < word.length()) {
            if (grid[i][j] != word.charAt(k)) {
                return false;
            }
            i += di[d];
            j += dj[d];
            k++;
        }
        return k == word.length();
    }

    int part2(String data) throws IOException {
        init(data);
        int count = 0;
        for (int i = 1; i < M - 1; ++i) {
            for (int j = 1; j < N - 1; ++j) {
                if (isX(i, j)) {
                    ++count;
                }
            }
        }
        return count;
    }

    boolean isX(int i, int j) {
        char tl = grid[i - 1][j - 1];
        char tr = grid[i - 1][j + 1];
        char bl = grid[i + 1][j - 1];
        char br = grid[i + 1][j + 1];
        return (grid[i][j] == 'A')
            && (tl == 'M' && br == 'S' || tl == 'S' && br == 'M')
            && (bl == 'M' && tr == 'S' || bl == 'S' && tr == 'M');
    }

}
