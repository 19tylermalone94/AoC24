package aoc.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import aoc.util.Pair;
import aoc.util.Parsers;
import aoc.util.Readers;

public class Day15 implements Day {

    final int[] di = {-1, 1, 0, 0};
    final int[] dj = {0, 0, -1, 1};
    final Map<Character, Integer> map = new HashMap<>() {{
        put('^', 0);
        put('v', 1);
        put('<', 2);
        put('>', 3);
    }};

    char[][] grid;
    int M;
    int N;

    @Override
    public void run(String fileName) throws Exception {
        String data = Readers.fileToString(fileName);
        // System.out.println(part1(data));
        System.out.println(part2(data));
    }

    long part1(String data) throws Exception {
        String[] dataParts = data.trim().split("\n\n");
        String mapData = dataParts[0];
        String directionData = dataParts[1];
        grid = Parsers.charMatrix(mapData);
        M = grid.length;
        N = grid[0].length;
        char[] directions = directionData.replaceAll("\n", "").toCharArray();
        simulateMovement(directions);
        return sumGPSCoords();
    }

    private void simulateMovement(char[] directions) {
        for (char direction : directions) {
            int[] robotPos = findRobot();
            int i = robotPos[0];
            int j = robotPos[1];
            move(i, j, map.get(direction));
        }
    }

    private boolean move(int i, int j, int d) {
        int nextI = i + di[d];
        int nextJ = j + dj[d];
        if (grid[nextI][nextJ] == '#') {
            return false;
        }
        if (grid[nextI][nextJ] == '.' || move(nextI, nextJ, d)) {
            grid[nextI][nextJ] = grid[i][j];
            grid[i][j] = '.';
            return true;
        }
        return false;
    }

    int[] findRobot() {
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (grid[i][j] == '@') {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    long sumGPSCoords() {
        long coordSum = 0;
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (grid[i][j] == 'O') {
                    coordSum += 100*i + j;
                }
            }
        }
        return coordSum;
    }

    private char[][] bigGrid(String data) {
        return Arrays.stream(data.trim().split("\n"))
                     .map(line -> {
                        line = line.replaceAll("#", "##");
                        line = line.replaceAll("O", "[]");
                        line = line.replaceAll("\\.", "..");
                        return line.replaceAll("@", "@.").toCharArray();
                     })
                     .toArray(char[][]::new);
    }

    long part2(String data) throws Exception {
        String[] dataParts = data.trim().split("\n\n");
        String mapData = dataParts[0];
        String directionData = dataParts[1];
        grid = bigGrid(mapData);
        M = grid.length;
        N = grid[0].length;
        char[] directions = directionData.replaceAll("\n", "").toCharArray();
        simulate2(directions);
        return sumGPSCoords2();
    }

    private void simulate2(char[] directions) {
        for (char direction : directions) {
            int[] robotPos = findRobot();
            int i = robotPos[0];
            int j = robotPos[1];
            move2(i, j, di[map.get(direction)], dj[map.get(direction)]);
        }
    }

    private void move2(int i, int j, int di, int dj) {
        int nextI = i + di;
        int nextJ = j + dj;
        if (grid[nextI][nextJ] == '#') {
            return;
        }
        if (grid[nextI][nextJ] == '.') {
            grid[nextI][nextJ] = grid[i][j];
            grid[i][j] = '.';
            return;
        }
        if (grid[nextI][nextJ] == '[' || grid[nextI][nextJ] == ']') {
            boolean move = true;
            Set<Pair<Integer, Integer>> visited = new HashSet<>();
            Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
            queue.add(new Pair<>(i, j));
            while (!queue.isEmpty()) {
                Pair<Integer, Integer> pos = queue.poll();
                if (visited.contains(pos)) {
                    continue;
                }
                visited.add(pos);
                int currI = pos.getA();
                int currJ = pos.getB();
                nextI = currI + di;
                nextJ = currJ + dj;
                char next = grid[nextI][nextJ];
                if (next == '#') {
                    move = false;
                    break;
                }
                if (next == '[') {
                    queue.add(new Pair<>(nextI, nextJ));
                    queue.add(new Pair<>(nextI, nextJ + 1));
                }
                if (next == ']') {
                    queue.add(new Pair<>(nextI, nextJ - 1));
                    queue.add(new Pair<>(nextI, nextJ));
                }
            }
            if (move) {
                while (!visited.isEmpty()) {
                    for (Pair<Integer, Integer> pos : new ArrayList<>(visited)) {
                        nextI = pos.getA() + di;
                        nextJ = pos.getB() + dj;
                        Pair<Integer, Integer> nextPos = new Pair<>(nextI, nextJ);
                        if (!visited.contains(nextPos)) {
                            grid[nextI][nextJ] = grid[pos.getA()][pos.getB()];
                            grid[pos.getA()][pos.getB()] = '.';
                            visited.remove(pos);
                        }
                    }
                }
                grid[i][j] = '.';
                grid[i + di][j + dj] = '@';
            }
        }
    }

    long sumGPSCoords2() {
        long coordSum = 0;
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (grid[i][j] == '[') {
                    coordSum += 100*i + j;
                }
            }
        }
        return coordSum;
    }
    
}
