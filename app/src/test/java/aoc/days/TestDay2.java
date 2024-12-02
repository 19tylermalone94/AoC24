package aoc.days;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDay2 {

    Day2 day2;

    @BeforeEach
    public void beforeEach() {
        day2 = new Day2();
    }

    @Test
    public void testPart1() {
        List<List<Integer>> reports = List.of(
            List.of(7, 6, 4, 2, 1),
            List.of(1, 2, 7, 8, 9),
            List.of(9, 7, 6, 2, 1),
            List.of(1, 3, 2, 4, 5),
            List.of(8, 6, 4, 4, 1),
            List.of(1, 3, 6, 7, 9)
        );
        int expected = 2;
        int actual = day2.numSafeReports(reports);
        assertEquals(expected, actual);
    }

    @Test
    public void testPart2() {
        List<List<Integer>> reports = List.of(
            new ArrayList<>(List.of(7, 6, 4, 2, 1)),
            new ArrayList<>(List.of(1, 2, 7, 8, 9)),
            new ArrayList<>(List.of(9, 7, 6, 2, 1)),
            new ArrayList<>(List.of(1, 3, 2, 4, 5)),
            new ArrayList<>(List.of(8, 6, 4, 4, 1)),
            new ArrayList<>(List.of(1, 3, 6, 7, 9))
        );
        int expected = 4;
        day2.problemDampener(reports);
        int actual = day2.numSafeReports(reports);
        assertEquals(expected, actual);
    }
    
}
