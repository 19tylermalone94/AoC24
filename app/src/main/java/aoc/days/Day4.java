package aoc.days;

import java.io.IOException;
import aoc.util.Parsers;
import aoc.util.Readers;

public class Day4 implements Day {

    static final String word = "XMAS";
    static final int[] di = {-1, -1, -1, 0, 0, 1, 1, 1};
    static final int[] dj = {-1, 0, 1, -1, 1, -1, 0, 1};

    char[][] grid;
    int M;
    int N;

    private void init(String data) throws IOException{
        grid = Parsers.charMatrix(data);
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
        int numFinds = 0;
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                numFinds += searchDirections(i, j);
            }
        }
        return numFinds;
    }

    int searchDirections(int i, int j) {
        int numFinds = 0;
        for (int d = 0; d < 8; ++d) {
            numFinds += isFound(i, j, d) ? 1 : 0;
        }
        return numFinds;
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
                count += isX(i, j) ? 1 : 0;
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
