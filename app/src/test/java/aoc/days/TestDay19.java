package aoc.days;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDay19 implements TestDay {

    String sample = """
            r, wr, b, g, bwu, rb, gb, br

            brwrr
            bggr
            gbbr
            rrbgbr
            ubwu
            bwurrg
            brgr
            bbrgwb

            """;
    Day19 day19;

    @Override
    @BeforeEach
    public void beforeEach() throws Exception {
        day19 = new Day19();
    }

    @Override
    @Test
    public void testPart1() throws Exception {
        int expected = 6;
        int actual = day19.part1(sample);
        assertEquals(expected, actual);
    }

    @Override
    public void testPart2() throws Exception {
        long expected = 16;
        long actual = day19.part2(sample);
        assertEquals(expected, actual);
    }
    
}
