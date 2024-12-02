package aoc.days;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class TestDay1 {

    @Test
    void testPart1() throws IOException {
        List<Integer> left = Arrays.asList(3, 4, 2, 1, 3, 3);
        List<Integer> right = Arrays.asList(4, 3, 5, 3, 9, 3);
        int expected = 11;
        Day1 day1 = new Day1();
        int result = day1.part1(left, right);
        assertEquals(expected, result, "Part1 calculation is incorrect");
    }

    @Test
    void testPart2() throws IOException {
        List<Integer> left = Arrays.asList(3, 4, 2, 1, 3, 3);
        List<Integer> right = Arrays.asList(4, 3, 5, 3, 9, 3);
        int expected = 31;
        Day1 day1 = new Day1();
        int result = day1.part2(left, right);
        assertEquals(expected, result, "Part2 calculation is incorrect");
    }
    
}
