package aoc.util;

public class Pair<A, B> {

    A a;
    B b;

    public Pair(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public A getA() {
        return a;
    }

    public B getB() {
        return b;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Pair) {
            Pair<?, ?> pair = (Pair<?, ?>) other;
            return pair.a.equals(a) && pair.b.equals(b);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return a.hashCode() * 31 + b.hashCode();
    }

    @Override
    public String toString() {
        return a.toString() + " " + b.toString();
    }
    
}
