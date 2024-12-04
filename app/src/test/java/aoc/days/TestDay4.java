package aoc.days;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDay4 {

   String sample = """
                MMMSXXMASM
                MSAMXMSMSA
                AMXSXMAAMM
                MSAMASMSMX
                XMASAMXAMM
                XXAMMXXAMA
                SMSMSASXSS
                SAXAMASAAA
                MAMMMXMMMM
                MXMXAXMASX
                    """;
    Day4 day4;

    @BeforeEach
    public void beforeEach() {
        day4 = new Day4();
    }

    @Test
    public void testPart1() {
        long expected = 18;
        long actual = day4.part1(sample);
        assertEquals(expected, actual);
    }
    
}
