package aoc.days;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.IntStream;
import aoc.util.Node2;
import aoc.util.Parsers;
import aoc.util.Readers;

public class Day20 implements Day {

    final int THRESHOLD;

    public Day20() {
        THRESHOLD = 100;
    }

    public Day20(int threshold) {
        THRESHOLD = threshold;
    }

    int[] di = {-1, 1, 0, 0};
    int[] dj = {0, 0, -1, 1};

    char[][] grid;
    int M;
    int N;

    @Override
    public void run(String fileName) throws Exception {
        String data = Readers.fileToString(fileName);
        System.out.println(part1(data));
        System.out.println(part2(data));
    }

    int part1(String data) throws Exception {
        return solve(data, THRESHOLD, 2);
    }

    int part2(String data) throws Exception {
        return solve(data, THRESHOLD, 20);
    }

    private int solve(String data, int threshold, int cheatLimit) throws Exception {
        grid = Parsers.charMatrix(data);
        M = grid.length;
        N = grid[0].length;
        int sum = 0;
        int[] startPos = find('S');
        Node2 start = new Node2(startPos[0], startPos[1]);
        List<Node2> path = getPath(start);
        int[][] distances = getDistances(path, threshold);
        int pathDist = path.size() - 1;;
        for (int i = 0; i < path.size(); ++i) {
            for (int j = i + threshold; j < path.size(); ++j) {
                int startToCurr = i;
                int currToNext = distances[i][j];
                int nextToEnd = pathDist - j;
                int newDist = startToCurr + currToNext + nextToEnd;
                if (currToNext <= cheatLimit && pathDist - newDist >= threshold) {
                    sum++;
                }
            }
        }
        return sum;
    }

    List<Node2> getPath(Node2 start) {
        List<Node2> path = new LinkedList<>();
        Queue<Node2> queue = new LinkedList<>();
        Set<Node2> visited = new HashSet<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Node2 u = queue.poll();
            visited.add(u);
            path.add(u);
            if (grid[u.x][u.y] == 'E') {
                return path;
            }
            for (int d = 0; d < 4; ++d) {
                int i = u.x + di[d];
                int j = u.y + dj[d];
                Node2 v = new Node2(i, j);
                if (!offFrame(i, j) && !visited.contains(v) && grid[i][j] != '#') {
                    queue.add(v);
                }
            }
        }
        return null;
    }

    private int[][] getDistances(List<Node2> path, int threshold) {
        int[][] distances = new int[path.size()][path.size()];
        IntStream.range(0, path.size()).parallel().forEach(i -> {
            Node2 from = path.get(i);
            for (int j = i + threshold; j < path.size(); ++j) {
                Node2 to = path.get(j);
                distances[i][j] = distanceBetween(from, to);
            }
        });
        return distances;
    }

    int[] find(char val) {
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (grid[i][j] == val) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    boolean offFrame(int i, int j) {
        return i < 0 || i >= M || j < 0 || j >= N;
    }

    int distanceBetween(Node2 from, Node2 to) {
        return Math.abs(from.x - to.x) + Math.abs(from.y - to.y);
    }
  
}
