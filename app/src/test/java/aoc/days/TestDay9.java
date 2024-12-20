package aoc.days;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDay9 implements TestDay {

    String sample = "2333133121414131402";
    Day9 day9;

    @Override
    @BeforeEach
    public void beforeEach() throws Exception {
        day9 = new Day9();
    }

    @Override
    @Test
    public void testPart1() throws Exception {
        long expected = 1928;
        long actual = day9.part1(sample);
        assertEquals(expected, actual);
    }

    @Override
    @Test
    public void testPart2() throws Exception {
        long expected = 2858;
        long actual = day9.part2(sample);
        assertEquals(expected, actual);
    }
    
}
