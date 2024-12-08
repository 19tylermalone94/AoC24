package aoc.days;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDay1 implements TestDay {

    Day1 day1;
    List<Integer> left;
    List<Integer> right;

    @BeforeEach
    public void beforeEach() {
        day1 = new Day1();
        left = Arrays.asList(3, 4, 2, 1, 3, 3);
        right = Arrays.asList(4, 3, 5, 3, 9, 3);
    }

    @Test
    public void testPart1() throws IOException {
        int expected = 11;
        int result = day1.part1(left, right);
        assertEquals(expected, result);
    }

    @Test
    public void testPart2() throws IOException {
        int expected = 31;
        int result = day1.part2(left, right);
        assertEquals(expected, result);
    }
    
}
