package aoc.util;

public class Node2 {
    
    public int x, y;

    public Node2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        Node2 other = (Node2) o;
        return other.x == x && other.y == y;
    }

    @Override
    public int hashCode() {
        return 31*x + y;
    }

    @Override
    public String toString() {
        return x + " " + y;
    }

}
