package aoc.days;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import aoc.util.Node2;
import aoc.util.Node3;
import aoc.util.Pair;
import aoc.util.Parsers;
import aoc.util.Readers;

public class Day16 implements Day {

    final int[] di = {-1, 0, 1, 0};
    final int[] dj = {0, 1, 0, -1};

    char[][] grid;
    int M;
    int N;

    int p1;
    int p2;

    @Override
    public void run(String fileName) throws Exception {
        String data = Readers.fileToString(fileName);
        System.out.println(part1(data));
        System.out.println(part2(data));
    }

    long part1(String data) throws Exception {
        init(data);
        return p1;
    }

    long part2(String data) throws Exception {
        init(data);
        return p2;
    }

    private void init(String data) throws Exception {
        grid = Parsers.charMatrix(data);
        M = grid.length;
        N = grid[0].length;
        int[] start = find('S');
        shortestPath(new Node3(start[0], start[1], 1), 'E');
    }

    void shortestPath(Node3 start, char target) {
        Node3 end = null;
        Map<Node3, Set<Node3>> from = new HashMap<>();
        Map<Node3, Integer> distance = new HashMap<>();
        Queue<Pair<Node3, Integer>> queue = new PriorityQueue<>((p1, p2) -> p1.val().compareTo(p2.val()));
        queue.add(new Pair<>(start, 0));
        while (!queue.isEmpty()) {
            Pair<Node3, Integer> entry = queue.poll();
            Node3 u = entry.key();
            int dist = entry.val();
            if (grid[u.x][u.y] == target) {
                if (p1 == 0) {
                    p1 = dist;
                    end = new Node3(u.x, u.y, u.z);
                }
            }
            for (int d = -1; d <= 1; ++d) {
                if (d == 0) {
                    int vx = u.x + di[u.z], vy = u.y + dj[u.z];
                    Node3 v = new Node3(vx, vy, u.z);
                    if (grid[v.x][v.y] != '#' && dist + 1 < distance.getOrDefault(v, Integer.MAX_VALUE)) {
                        distance.put(v, dist + 1);
                        queue.add(new Pair<>(v, dist + 1));
                        if (!from.containsKey(v)) {
                            from.put(v, new HashSet<>(List.of(u)));
                        } else {
                            from.get(v).add(u);
                        }
                    } else if (grid[v.x][v.y] != '#' && dist + 1 <= distance.getOrDefault(v, Integer.MAX_VALUE)) {
                        if (!from.containsKey(v)) {
                            from.put(v, new HashSet<>(List.of(u)));
                        } else {
                            from.get(v).add(u);
                        }
                    }
                } else {
                    int nd = (u.z + d + 4) % 4;
                    Node3 v = new Node3(u.x, u.y, nd);
                    if (dist + 1000 < distance.getOrDefault(v, Integer.MAX_VALUE)) {
                        distance.put(v, dist + 1000);
                        queue.add(new Pair<>(v, dist + 1000));
                        if (!from.containsKey(v)) {
                            from.put(v, new HashSet<>(List.of(u)));
                        } else {
                            from.get(v).add(u);
                        }
                    } else if (dist + 1000 <= distance.getOrDefault(v, Integer.MAX_VALUE)) {
                        if (!from.containsKey(v)) {
                            from.put(v, new HashSet<>(List.of(u)));
                        } else {
                            from.get(v).add(u);
                        }
                    }
                }
            }
        }
        Stack<Node3> stack = new Stack<>();
        stack.push(end);
        Set<Node3> goodNodes = new HashSet<>(List.of(end));
        while (!stack.isEmpty()) {
            Node3 some = stack.pop();
            for (Node3 other : from.get(some)) {
                if (!goodNodes.contains(other)) {
                    goodNodes.add(other);
                    stack.push(other);
                }
            }
        }
        p2 = goodNodes.stream()
                      .map(n3 -> new Node2(n3.x, n3.y))
                      .collect(Collectors.toCollection(HashSet::new))
                      .size();
    }

    private int[] find(char val) {
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (grid[i][j] == val) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
    
}
