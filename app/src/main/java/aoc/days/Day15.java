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
        System.out.println(part1(data));
        System.out.println(part2(data));
    }

    long part1(String data) throws Exception {
        return solve(data, false);
    }

    long part2(String data) throws Exception {
        return solve(data, true);
    }

    long solve(String data, boolean part2) throws Exception {
        String[] dataParts = data.trim().split("\n\n");
        String mapData = dataParts[0];
        String directionData = dataParts[1];
        grid = part2 ? bigGrid(mapData) : Parsers.charMatrix(mapData);
        M = grid.length;
        N = grid[0].length;
        char[] directions = directionData.replaceAll("\n", "").toCharArray();
        simulateMovement(directions);
        return sumGPSCoords();
    }

    private void simulateMovement(char[] directions) {
        Pair<Integer, Integer> robotPos = findRobot();
        for (char direction : directions) {
            robotPos = move(robotPos, di[map.get(direction)], dj[map.get(direction)]);
        }
    }

    private Pair<Integer, Integer> move(Pair<Integer, Integer> robotPos, int di, int dj) {
        Pair<Integer, Integer> nextPos = new Pair<>(robotPos.key() + di, robotPos.val() + dj);
        if (getCell(nextPos) == '#') {
            return robotPos;
        }
        if (getCell(nextPos) == '.') {
            setCell(nextPos, getCell(robotPos));
            setCell(robotPos, '.');
            return nextPos;
        }
        boolean move = true;
        Set<Pair<Integer, Integer>> visited = new HashSet<>();
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.add(robotPos);
        while (!queue.isEmpty()) {
            Pair<Integer, Integer> curr = queue.poll();
            if (visited.contains(curr)) {
                continue;
            }
            visited.add(curr);
            nextPos = new Pair<>(curr.key() + di, curr.val() + dj);
            if (getCell(nextPos) == '#') {
                move = false;
                break;
            }
            if (getCell(nextPos) == 'O') {
                queue.add(nextPos);
            }
            if (getCell(nextPos) == '[') {
                queue.add(nextPos);
                queue.add(new Pair<>(nextPos.key(), nextPos.val() + 1));
            }
            if (getCell(nextPos) == ']') {
                queue.add(new Pair<>(nextPos.key(), nextPos.val() - 1));
                queue.add(nextPos);
            }
        }
        if (move) {
            while (!visited.isEmpty()) {
                for (Pair<Integer, Integer> pos : new ArrayList<>(visited)) {
                    nextPos = new Pair<>(pos.key() + di, pos.val() + dj);
                    if (!visited.contains(nextPos)) {
                        setCell(nextPos, getCell(pos));
                        setCell(pos, '.');
                        visited.remove(pos);
                    }
                }
            }
            nextPos = new Pair<>(robotPos.key() + di, robotPos.val() + dj);
            setCell(robotPos, '.');
            setCell(nextPos, '@');
            return nextPos;
        }
        return robotPos;
    }

    long sumGPSCoords() {
        long coordSum = 0;
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (grid[i][j] == '[' || grid[i][j] == 'O') {
                    coordSum += 100*i + j;
                }
            }
        }
        return coordSum;
    }

    char getCell(Pair<Integer, Integer> pos) {
        return grid[pos.key()][pos.val()];
    }

    void setCell(Pair<Integer, Integer> pos, char val) {
        grid[pos.key()][pos.val()] = val;
    }

    Pair<Integer, Integer> findRobot() {
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (grid[i][j] == '@') {
                    return new Pair<>(i, j);
                }
            }
        }
        return null;
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
    
}
