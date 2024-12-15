package aoc.days;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDay11 implements TestDay {

    String sample = "125 17";
    Day11 day11;

    @Override
    @BeforeEach
    public void beforeEach() throws Exception {
        day11 = new Day11();
    }

    @Override
    @Test
    public void testPart1() throws Exception {
        long expected = 55312;
        long actual = day11.part1(sample);
        assertEquals(expected, actual);
    }

    @Override
    @Test
    public void testPart2() throws Exception {
        long expected = 65601038650482L;
        long actual = day11.part2(sample);
        assertEquals(expected, actual);
    }
    
}
