package aoc.days;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDay20 implements TestDay {

    String sample = """    
    ###############
    #...#...#.....#
    #.#.#.#.#.###.#
    #S#...#.#.#...#
    #######.#.#.###
    #######.#.#...#
    #######.#.###.#
    ###..E#...#...#
    ###.#######.###
    #...###...#...#
    #.#####.#.###.#
    #.#...#.#.#...#
    #.#.#.#.#.#.###
    #...#...#...###
    ###############

            """;
    Day20 day20;

    @Override
    @BeforeEach
    public void beforeEach() throws Exception {
        day20 = new Day20(50);
    }

    @Override
    public void testPart1() throws Exception {
        int expected = 1;
        int actual = day20.part1(sample);
        assertEquals(expected, actual);
    }

    @Override
    @Test
    public void testPart2() throws Exception {
        int expected = 285;
        int actual = day20.part2(sample);
        assertEquals(expected, actual);
    }
  


}
