package aoc.days;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDay6 implements TestDay {

    String sample = """
            ....#.....
            .........#
            ..........
            ..#.......
            .......#..
            ..........
            .#..^.....
            ........#.
            #.........
            ......#...

            """;

    Day6 day6;

    @BeforeEach
    public void beforeEach() {
        day6 = new Day6();
    }

    @Test
    public void testPart1() throws Exception {
        int expected = 41;
        int actual = day6.part1(sample);
        assertEquals(expected, actual);
    }

    @Test
    public void testPart2() throws Exception {
        int expected = 6;
        int actual = day6.part2(sample);
        assertEquals(expected, actual);
    }
    
}
