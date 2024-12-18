package aoc.days;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDay16 implements TestDay {

    String sample = """
            #################
            #...#...#...#..E#
            #.#.#.#.#.#.#.#.#
            #.#.#.#...#...#.#
            #.#.#.#.###.#.#.#
            #...#.#.#.....#.#
            #.#.#.#.#.#####.#
            #.#...#.#.#.....#
            #.#.#####.#.###.#
            #.#.#.......#...#
            #.#.###.#####.###
            #.#.#...#.....#.#
            #.#.#.#####.###.#
            #.#.#.........#.#
            #.#.#.#########.#
            #S#.............#
            #################

            """;

            Day16 day16;

    @Override
    @BeforeEach
    public void beforeEach() throws Exception {
        day16 = new Day16();
    }

    @Override
    @Test
    public void testPart1() throws Exception {
        long expected = 11048;
        long actual = day16.part1(sample);
        assertEquals(expected, actual);
    }

    @Override
    public void testPart2() throws Exception {
        long expected = 45;
        long actual = day16.part2(sample);
        assertEquals(expected, actual);
    }
    
}
