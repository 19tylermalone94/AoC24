package aoc.days;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDay18 implements TestDay {

    String sample = """
    5,4
    4,2
    4,5
    3,0
    2,1
    6,3
    2,4
    1,5
    0,6
    3,3
    2,6
    5,1
    1,2
    5,5
    2,5
    6,5
    1,4
    0,4
    6,4
    1,1
    6,1
    1,0
    0,5
    1,6
    2,0

            """;

    Day18 day18;

    @Override
    @BeforeEach
    public void beforeEach() throws Exception {
        day18 = new Day18();
    }

    @Override
    @Test
    public void testPart1() throws Exception {
        int expected = 22;
        int actual = day18.part1(sample);
        assertEquals(expected, actual);
    }

    @Override
    @Test
    public void testPart2() throws Exception {
        String expected = "6,1";
        String actual = day18.part2(sample);
        assertEquals(expected, actual);
    }
    
}
