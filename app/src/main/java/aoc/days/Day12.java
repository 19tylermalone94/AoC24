package aoc.days;

import aoc.util.Parsers;
import aoc.util.Readers;

public class Day12 implements Day {

    final int[] di = {-1, 1, 0, 0};
    final int[] dj = {0, 0, -1, 1};

    char[][] grid;
    int M;
    int N;
    boolean[][] visited;
    long area;
    long perimeter;

    @Override
    public void run(String fileName) throws Exception {
        String data = Readers.fileToString(fileName);
        System.out.println(part1(data));
        System.out.println(part2(data));
    }

    long part1(String data) throws Exception {
        grid = Parsers.charMatrix(data);
        M = grid.length;
        N = grid[0].length;
        visited = new boolean[M][N];
        area = 0;
        perimeter = 0;
        long sum = 0;
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (!visited[i][j]) {
                    dfs(grid[i][j], i, j);
                    sum += area * perimeter;
                    area = 0;
                    perimeter = 0;
                }
            }
        }
        return sum;
    }

    void dfs(char origin, int i, int j) {
        visited[i][j] = true;
        ++area;
        for (int d = 0; d < 4; ++d) {
            int nr = i + di[d];
            int nc = j + dj[d];
            if (nr < 0 || nr >= M || nc < 0 || nc >= N || grid[nr][nc] != origin) {
                ++perimeter;
            } else if (!visited[nr][nc] && grid[nr][nc] == origin) {
                dfs(origin, nr, nc);
            }
        }
    }

    long part2(String data) throws Exception {
        grid = Parsers.charMatrix(data);
        M = grid.length;
        N = grid[0].length;
        visited = new boolean[M][N];
        area = 0;
        long sum = 0;
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (!visited[i][j]) {
                    char origin = grid[i][j];
                    dfs2(origin, i, j);
                    int numSides = countSides();
                    sum += numSides * area;
                    reset(origin);
                    area = 0;
                }
            }
        }
        return sum;
    }

    void dfs2(char origin, int i, int j) {
        visited[i][j] = true;
        grid[i][j] = '.';
        ++area;
        for (int d = 0; d < 4; ++d) {
            int nr = i + di[d];
            int nc = j + dj[d];
            if (nr < 0 || nr >= M || nc < 0 || nc >= N || grid[nr][nc] != origin) {
                continue;
            } else if (!visited[nr][nc] && grid[nr][nc] == origin) {
                dfs2(origin, nr, nc);
            }
        }
    }

    // TODO clean this up
    int countSides() {
        int sum = 0;
        // check up sides
        for (int i = 0; i < M; ++i) {
            int numSides = 0;
            int left = 0;
            while (left < N) {
                while (left < N && (grid[i][left] != '.' || (grid[i][left] == '.' && i > 0 && grid[i - 1][left] == '.'))) {
                    ++left;
                }
                if (left < N) {
                    int right = left;
                    while (right < N && grid[i][right] == '.' && (i == 0 || grid[i - 1][right] != '.')) {
                        ++right;
                    }
                    ++numSides;
                    left = right;
                }
            }
            sum += numSides;
        }
        // check down sides
        for (int i = 0; i < M; ++i) {
            int numSides = 0;
            int left = 0;
            while (left < N) {
                while (left < N && (grid[i][left] != '.' || (grid[i][left] == '.' && i < M - 1 && grid[i + 1][left] == '.'))) {
                    ++left;
                }
                if (left < N) {
                    int right = left;
                    while (right < N && grid[i][right] == '.' && (i == M - 1 || grid[i + 1][right] != '.')) {
                        ++right;
                    }
                    ++numSides;
                    left = right;
                }
            }
            sum += numSides;
        }
        // check left sides
        for (int j = 0; j < N; ++j) {
            int numSides = 0;
            int up = 0;
            while (up < M) {
                while (up < M && (grid[up][j] != '.' || (grid[up][j] == '.' && j > 0 && grid[up][j - 1] == '.'))) {
                    ++up;
                }
                if (up < M) {
                    int down = up;
                    while (down < M && grid[down][j] == '.' && (j == 0 || grid[down][j - 1] != '.')) {
                        ++down;
                    }
                    ++numSides;
                    up = down;
                }
            }
            sum += numSides;
        }
        // check right sides
        for (int j = 0; j < N; ++j) {
            int numSides = 0;
            int up = 0;
            while (up < M) {
                while (up < M && (grid[up][j] != '.' || (grid[up][j] == '.' && j < N - 1 && grid[up][j + 1] == '.'))) {
                    ++up;
                }
                if (up < M) {
                    int down = up;
                    while (down < M && grid[down][j] == '.' && (j == N - 1 || grid[down][j + 1] != '.')) {
                        ++down;
                    }
                    ++numSides;
                    up = down;
                }
            }
            sum += numSides;
        }
        return sum;
    }

    void reset(char origin) {
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (grid[i][j] == '.') {
                    grid[i][j] = origin;
                }
            }
        }
    }
    
}
