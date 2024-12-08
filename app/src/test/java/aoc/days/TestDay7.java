package aoc.days;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDay7 {

    String sample = """
                    190: 10 19
                    3267: 81 40 27
                    83: 17 5
                    156: 15 6
                    7290: 6 8 6 15
                    161011: 16 10 13
                    192: 17 8 14
                    21037: 9 7 18 13
                    292: 11 6 16 20

                    """;

    Day7 day7;

    @BeforeEach
    public void beforeEach() {
        day7 = new Day7();
    }

    @Test
    public void testPart1() throws Exception {
        long expected = 3749;
        long actual = day7.part1(sample);
        assertEquals(expected, actual);
    }

    @Test
    public void testPart2() throws Exception {
        long expected = 11387;
        long actual = day7.part2(sample);
        assertEquals(expected, actual);
    }
    
}
