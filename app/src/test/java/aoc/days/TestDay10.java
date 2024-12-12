package aoc.days;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDay10 implements TestDay {

    String sample = """
            89010123
            78121874
            87430965
            96549874
            45678903
            32019012
            01329801
            10456732
            """;

    Day10 day10;

    @Override
    @BeforeEach
    public void beforeEach() throws Exception {
        day10 = new Day10();
    }

    @Override
    @Test
    public void testPart1() throws Exception {
        long expected = 36;
        long actual = day10.part1(sample);
        assertEquals(expected, actual);
    }

    @Override
    @Test
    public void testPart2() throws Exception {
        long expected = 81;
        long actual = day10.part2(sample);
        assertEquals(expected, actual);
    }
    
}
