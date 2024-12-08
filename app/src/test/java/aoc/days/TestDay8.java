package aoc.days;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDay8 implements TestDay {
    
    String sample = """
            ............
            ........0...
            .....0......
            .......0....
            ....0.......
            ......A.....
            ............
            ............
            ........A...
            .........A..
            ............
            ............

            """;

    Day8 day8;

    @BeforeEach
    public void beforeEach() {
        day8 = new Day8();
    }

    @Test
    public void testPart1() throws Exception {
        int expected = 14;
        int actual = day8.part1(sample);
        assertEquals(expected, actual);
    }

    @Test
    public void testPart2() throws Exception {
        int expected = 34;
        int actual = day8.part2(sample);
        assertEquals(expected, actual);
    }

}
