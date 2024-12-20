package aoc.days;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDay17 implements TestDay {

    String sample1 = """
            Register A: 729
            Register B: 0
            Register C: 0

            Program: 0,1,5,4,3,0

            """;

    String sample2 = """
            Register A: 2024
            Register B: 0
            Register C: 0

            Program: 0,3,5,4,3,0

            """;

    Day17 day17;

    @Override
    @BeforeEach
    public void beforeEach() throws Exception {
        day17 = new Day17();
    }

    @Override
    @Test
    public void testPart1() throws Exception {
        String expected = "[4, 6, 3, 5, 6, 3, 5, 2, 1, 0]";
        String actual = day17.part1(sample1);
        assertEquals(expected, actual);
    }

    @Override
    @Test
    public void testPart2() throws Exception {
        long expected = 117440;
        long actual = day17.part2(sample2);
        assertEquals(expected, actual);
    }
    
}
