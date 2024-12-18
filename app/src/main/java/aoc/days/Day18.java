package aoc.days;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import aoc.util.Node2;
import aoc.util.Pair;
import aoc.util.Printers;
import aoc.util.Readers;

public class Day18 implements Day {

    int[] di = {-1, 1, 0, 0};
    int[] dj = {0, 0, -1, 1};

    char[][] grid;
    int M = 7;
    int N = 7;
    int limit = 12;

    // char[][] grid;
    // int M = 71;
    // int N = 71;
    // int limit = 1024;

    @Override
    public void run(String fileName) throws Exception {
        String data = Readers.fileToString(fileName);
        System.out.println(part1(data));
        System.out.println(part2(data));
    }

    int part1(String data) {
        initGrid();
        List<Node2> points = parseNodes(data);
        points.subList(0, limit).stream().forEach(p -> grid[p.x][p.y] = '#');
        Printers.printArr(grid);
        return shortedPath();
    }

    String part2(String data) throws Exception {
        initGrid();
        List<Node2> points = parseNodes(data);
        Node2 blocker = null;
        for (Node2 p : points) {
            grid[p.x][p.y] = '#';
            if (!dfs(M - 1, N - 1, new HashSet<>())) {
                blocker = p;
                break;
            }
        }
        return blocker.y + "," + blocker.x;
    }

    private void initGrid() {
        grid = IntStream.range(0, M)
                        .mapToObj(i -> {
                            char[] row = new char[M];
                            Arrays.fill(row, '.');
                            return row;
                        })
                        .toArray(char[][]::new);
    }

    private List<Node2> parseNodes(String data) {
        return Arrays.stream(data.trim().split("\n"))
                     .map(line ->{
                        String[] parts = line.split(",");
                        int y = Integer.parseInt(parts[0]);
                        int x = Integer.parseInt(parts[1]);
                        return new Node2(x, y);
                     })
                     .collect(Collectors.toList());
    }

    private int shortedPath() {
        Map<Node2, Integer> distance = new HashMap<>();
        Queue<Pair<Node2, Integer>> queue = new PriorityQueue<>((p1, p2) -> p1.val().compareTo(p2.val()));
        Node2 start = new Node2(0, 0);
        queue.add(new Pair<>(start, 0));
        while (!queue.isEmpty()) {
            Pair<Node2, Integer> entry = queue.poll();
            Node2 u = entry.key();
            int dist = entry.val();
            if (u.x == M - 1 && u.y == N - 1) {
                return dist;
            }
            for (int d = 0; d < 4; ++d) {
                Node2 v = new Node2(u.x + di[d], u.y + dj[d]);
                if (v.x >= 0 && v.x < M && v.y >= 0 && v.y < N && grid[v.x][v.y] != '#' && dist + 1 < distance.getOrDefault(v, Integer.MAX_VALUE)) {
                    distance.put(v, dist + 1);
                    queue.add(new Pair<>(v, dist + 1));
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    private boolean dfs(int i, int j, Set<Node2> visited) {
        if (i == 0 && j == 0) {
            return true;
        }
        visited.add(new Node2(i, j));
        boolean goalReached = false;
        for (int d = 0; d < 4; ++d) {
            int ni = i + di[d];
            int nj = j + dj[d];
            if (ni >= 0 && ni < M && nj >= 0 && nj < N && grid[ni][nj] != '#' && !visited.contains(new Node2(ni, nj))) {
                goalReached = goalReached || dfs(ni, nj, visited);
            }
        }
        return goalReached;
    }
    
}
