package aoc.days;

import aoc.util.Counters;
import aoc.util.Parsers;
import aoc.util.Readers;

public class Day8 implements Day {

    char[][] grid;
    boolean[][] isAntinode;
    int M;
    int N;

    @Override
    public void run(String fileName) throws Exception {
        String data = Readers.fileToString(fileName);
        System.out.println(part1(data));
        System.out.println(part2(data));
    }

    int part1(String data) throws Exception {
        return countAntiNodes(data, false);
    }

    int part2(String data) throws Exception {
        return countAntiNodes(data, true);
    }

    private int countAntiNodes(String data, boolean useResonance) throws Exception {
        grid = Parsers.charMatrix(data);
        M = grid.length;
        N = grid[0].length;
        isAntinode = new boolean[M][N];
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (grid[i][j] != '.' && grid[i][j] != '#') {
                    findAntiNodes(i, j, useResonance);
                }
            }
        }
        return Counters.countOccurrences(isAntinode);
    }

    private void findAntiNodes(int i1, int j1, boolean useResonance) {
        if (useResonance) {
            isAntinode[i1][j1] = true;
        }
        for (int i2 = 0; i2 < M; ++i2) {
            for (int j2 = 0; j2 < N; ++j2) {
                if (i2 == i1 && j2 == j1) {
                    continue;
                }
                if (grid[i2][j2] == grid[i1][j1]) {
                    markAntiNode(i1, j1, i2, j2, useResonance);
                }
            }
        }
    }

    private void markAntiNode(int i1, int j1, int i2, int j2, boolean useResonance) {
        int di = i2 - i1;
        int dj = j2 - j1;
        int i = i2 + di;
        int j = j2 + dj;
        while (i >= 0 && i < M && j >= 0 && j < N) {
            if (useResonance) {
                isAntinode[i][j] = true;
                i += di;
                j += dj;
            } else {
                if (grid[i][j] != grid[i1][j1]) {
                    isAntinode[i][j] = true;
                    break;
                } else {
                    i = i2 + di;
                    j = j2 + dj;
                }
                if (grid[i][j] != grid[i1][j1]) {
                    isAntinode[i][j] = true;
                }
            }
        }
    }
    
}
