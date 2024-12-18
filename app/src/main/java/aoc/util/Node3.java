package aoc.util;

public class Node3 {

    public int x, y, z;

    public Node3(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        Node3 other = (Node3) o;
        return other.x == x && other.y == y && other.z == z;
    }

    @Override
    public int hashCode() {
        return 31*x + 11*y + 7*z;
    }

    @Override
    public String toString() {
        return x + " " + y + " " + z;
    }
    
}
