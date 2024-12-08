package aoc.days;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDay4 implements TestDay {

   final String sample1 = """
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

    final String sample2 = """
                .M.S......
                ..A..MSMS.
                .M.S.MAA..
                ..A.ASMSM.
                .M.S.M....
                ..........
                S.S.S.S.S.
                .A.A.A.A..
                M.M.M.M.M.
                ..........
                    """;

    Day4 day4;

    @BeforeEach
    public void beforeEach() {
        day4 = new Day4();
    }

    @Test
    public void testPart1() throws IOException {
        int expected = 18;
        int actual = day4.part1(sample1);
        assertEquals(expected, actual);
    }

    @Test
    public void testPart2() throws IOException {
        int expected = 9;
        int actual = day4.part2(sample2);
        assertEquals(expected, actual);
    }
    
}
