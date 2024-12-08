package aoc.days;

import java.util.Arrays;
import aoc.util.Parsers;
import aoc.util.Readers;

public class Day6 implements Day {

    final int[] di = {-1, 0, 1, 0};
    final int[] dj = {0, 1, 0, -1};

    char[][] grid;
    int M, N;

    @Override
    public void run(String fileName) throws Exception {
        String data = Readers.fileToString(fileName);
        System.out.println(part1(data));
        System.out.println(part2(data));
    }

    int part1(String data) throws Exception {
        grid = Parsers.charMatrix(data);
        M = grid.length;
        N = grid[0].length;
        simulate();
        return countDistinctGuardPoses();
    }

    int part2(String data) throws Exception {
        int count = 0;
        grid = Parsers.charMatrix(data);
        M = grid.length;
        N = grid[0].length;
        char[][] original = copyArray(grid);
        int[] guardPos = findGuard();
        char guard = original[guardPos[0]][guardPos[1]];
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (grid[i][j] != guard) {
                    grid[i][j] = '#';
                    boolean inLoop = simulate();
                    if (inLoop) {
                        ++count;
                    }
                    grid = copyArray(original);
                }
            }
        }
        return count;
    }

    private char[][] copyArray(char[][] original) {
        char[][] copy = new char[original.length][original[0].length];
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                copy[i][j] = original[i][j];
            }
        }
        return copy;
    }

    private boolean simulate() {
        boolean[][][] visited = new boolean[4][M][N];
        int[] guardPos = findGuard();
        int i = guardPos[0];
        int j = guardPos[1];
        int dir = 0;
        while (i >= 0 && i < M && j >= 0 && j < N && !visited[dir][i][j]) {
            visited[dir][i][j] = true;
            if (outOfFrame(i + di[dir], j + dj[dir]) || !isObstacle(i, j, dir)) {
                grid[i][j] = 'X';
                i += di[dir];
                j += dj[dir];
            }
            else {
                dir = (dir + 1) % 4;
            }
            if (!outOfFrame(i, j) && visited[dir][i][j]) {
                return true;
            }
        }
        return false;
    }

    private boolean isObstacle(int i, int j, int dir) {
        return grid[i + di[dir]][j + dj[dir]] == '#';
    }

    private boolean outOfFrame(int i, int j) {
        return i < 0 || i >= M || j < 0 || j >= N;
    }

    private int[] findGuard() {
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (grid[i][j] == '^') {
                    return new int[]{i, j};
                }
            }
        }
        Arrays.stream(grid).forEach(row -> {
            for (char c : row) {
                System.out.print(c + " (" + (int)c + ") ");
            }
            System.out.println();
        });
        throw new RuntimeException();
    }

    private int countDistinctGuardPoses() {
        int count = 0;
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (grid[i][j] == 'X') {
                    ++count;
                }
            } 
        }
        return count;
    }

}
