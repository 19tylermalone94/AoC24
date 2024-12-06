package aoc.days;

import java.util.HashSet;
import java.util.Set;
import aoc.util.Parsers;
import aoc.util.Readers;

public class Day6 implements Day {

    class Node {
        String direction;
        int i, j;

        Node(String direction, int i, int j) {
            this.direction = direction;
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object obj) {
            Node o = (Node) obj;
            return o.direction.equals(this.direction)
                && o.i == this.i
                && o.j == this.j;
        }

        @Override
        public int hashCode() {
            return direction.hashCode() + i + j;
        }

    }

    Set<Node> visited;
    char[][] grid;
    int M, N;

    @Override
    public void run(String fileName) throws Exception {
        visited = new HashSet<>();
        String data = Readers.fileToString(fileName);
        System.out.println(part1(data));
        System.out.println(part2(data));
    }

    int part1(String data) throws Exception {
        grid = Parsers.charMatrix(data);
        M = grid.length;
        N = grid[0].length;
        boolean inLoop = simulatePatrol();
        return countDistinctGuardPoses();
    }

    int part2(String data) throws Exception {
        int count = 0;
        char[][] og = Parsers.charMatrix(data);
        grid = new char[M][N];
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                grid[i][j] = og[i][j];
            }
        }
        M = grid.length;
        N = grid[0].length;
        int[] guardPos = findGuard();
        char guard = og[guardPos[0]][guardPos[1]];
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (grid[i][j] != guard) {
                    grid[i][j] = '#';
                    boolean inLoop = simulatePatrol();
                    if (inLoop) {
                        ++count;
                    }
                    grid = new char[M][N];
                    for (int x = 0; x < M; ++x) {
                        for (int y = 0; y < N; ++y) {
                            grid[x][y] = og[x][y];
                        }
                    }
                    visited.clear();
                }
            }
        }
        return count;
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

    private boolean simulatePatrol() {
        int[] guardPos = findGuard();
        int i = guardPos[0];
        int j = guardPos[1];
        String direction = getDirection(grid[i][j]);
        Node curr = new Node(direction, i, j);
        while (!visited.contains(curr) && i < M && i >= 0 && j < N && j >= 0) {
            visited.add(curr);
            if (curr.direction == "up") {
                if (i > 0) {
                    if (grid[i - 1][j] == '#') {
                        grid[i][j] = '>';
                        curr = new Node("right", i, j);
                    } else {
                        grid[i][j] = 'X';
                        grid[--i][j] = '^';
                        curr = new Node("up", i, j);
                    }
                } else {
                    grid[i][j] = 'X';
                    i--;
                    curr = new Node("none", i, j);
                }
            } else if (curr.direction == "down") {
                if (i < M - 1) {
                    if (grid[i + 1][j] == '#') {
                        grid[i][j] = '<';
                        curr = new Node("left", i, j);
                    } else {
                        grid[i][j] = 'X';
                        grid[++i][j] = 'V';
                        curr = new Node("down", i, j);
                    }
                } else {
                    grid[i][j] = 'X';
                    i++;
                    curr = new Node("none", i, j);
                }
            } else if (curr.direction == "left") {
                if (j > 0) {
                    if (grid[i][j - 1] == '#') {
                        grid[i][j] = '^';
                        curr = new Node("up", i, j);
                    } else {
                        grid[i][j] = 'X';
                        grid[i][--j] = '<';
                        curr = new Node("left", i, j);
                    }
                } else {
                    grid[i][j] = 'X';
                    j--;
                    curr = new Node("none", i, j);
                }
            } else if (curr.direction == "right") {
                if (j < N - 1) {
                    if (grid[i][j + 1] == '#') {
                        grid[i][j] = 'V';
                        curr = new Node("down", i, j);
                    } else {
                        grid[i][j] = 'X';
                        grid[i][++j] = '>';
                        curr = new Node("right", i, j);
                    }
                } else {
                    grid[i][j] = 'X';
                    j++;
                    curr = new Node("none", i, j);
                }
            }
            if (visited.contains(curr)) {
                return true;
            }
        }
        return false;
    }

    private int[] findGuard() {
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                char c = grid[i][j];
                if (c == '<' || c == '>' || c == 'V' || c == '^') {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    
    private String getDirection(char guard) {
        if (guard == '^') {
            return "up";
        } else if (guard == 'V') {
            return "down";
        } else if (guard == '<') {
            return "left";
        } else if (guard == '>') {
            return "right";
        } else {
            throw new RuntimeException();
        }
    }

}
