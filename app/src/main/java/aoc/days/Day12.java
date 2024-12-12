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

    long part2(String data) {
        return 0L;
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
    
}
