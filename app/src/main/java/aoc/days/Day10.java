package aoc.days;

import java.util.HashSet;
import java.util.Set;

import aoc.util.Parsers;
import aoc.util.Readers;

public class Day10 implements Day {

    final int[] di = {-1, 1, 0, 0};
    final int[] dj = {0, 0, -1, 1};

    int[][] grid;
    int M;
    int N;

    @Override
    public void run(String fileName) throws Exception {
        String data = Readers.fileToString(fileName);
        System.out.println(part1(data));
        System.out.println(part2(data));
    }

    int part1(String data) throws Exception {
        grid = Parsers.intMatrix(data);
        M = grid.length;
        N = grid[0].length;
        return scoreSum(true);
    }

    int part2(String data) throws Exception {
        grid = Parsers.intMatrix(data);
        M = grid.length;
        N = grid[0].length;
        return scoreSum(false);
    }

    int scoreSum(boolean countDistinct) {
        int sum = 0;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == 0) {
                    sum += dfs(i, j, countDistinct ? new HashSet<>() : null);
                }
            }
        }
        return sum;
    }

    int dfs(int i, int j, Set<Integer> visited) {
        if (visited != null) {
            visited.add(i * N + j);
        }
        if (grid[i][j] == 9) {
            return 1;
        }
        int sum = 0;
        for (int d = 0; d < 4; ++d) {
            int nr = i + di[d];
            int nc = j + dj[d];
            if (isValidNextPoint(i, j, nr, nc) && (visited == null || !visited.contains(nr * N + nc))) {
                sum += dfs(nr, nc, visited);
            }
        }
        return sum;
    }

    private boolean isValidNextPoint(int i1, int j1, int i2, int j2) {
        return i2 >= 0 && i2 < M && j2 >= 0 && j2 < N && grid[i2][j2] == grid[i1][j1] + 1;
    }
    
}
