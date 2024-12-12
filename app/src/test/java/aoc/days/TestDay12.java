package aoc.days;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDay12 implements TestDay {

    String sample = """
            RRRRIICCFF
            RRRRIICCCF
            VVRRRCCFFF
            VVRCCCJFFF
            VVVVCJJCFE
            VVIVCCJJEE
            VVIIICJJEE
            MIIIIIJJEE
            MIIISIJEEE
            MMMISSJEEE
            """;

    Day12 day12;

    @Override
    @BeforeEach
    public void beforeEach() throws Exception {
        day12 = new Day12();
    }

    @Override
    @Test
    public void testPart1() throws Exception {
        long expected = 1930;
        long actual = day12.part1(sample);
        assertEquals(expected, actual);
    }

    @Override
    @Test
    public void testPart2() throws Exception {
        long expected = 1206;
        long actual = day12.part2(sample);
        assertEquals(expected, actual);
    }
    
}
