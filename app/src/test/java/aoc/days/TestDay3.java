package aoc.days;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDay3 {

    final String DATA_PART1 = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))\n";
    final String DATA_PART2 = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))\n";

    Day3 day3;

    @BeforeEach
    public void beforeEach() {
        day3 = new Day3();
    }

    @Test
    public void testPart1() {
        long expected = 161;
        long actual = day3.uncorruptedSum(DATA_PART1);
        assertEquals(expected, actual);
    }

    @Test
    public void testPart2() {
        long expected = 48;
        String enabledData = day3.getEnabledDataOnly(DATA_PART2);
        long actual = day3.uncorruptedSum(enabledData);
        assertEquals(expected, actual);
    }
    
}
