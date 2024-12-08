package aoc.days;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import aoc.util.Parsers;

public class TestDay2  implements TestDay {

    static final String SAMPLE_DATA = """
                                    7 6 4 2 1
                                    1 2 7 8 9
                                    9 7 6 2 1
                                    1 3 2 4 5
                                    8 6 4 4 1
                                    1 3 6 7 9

                                      """;

    Day2 day2;
    List<List<Integer>> reports;

    @BeforeEach
    public void beforeEach() throws IOException {
        day2 = new Day2();
        reports = Parsers.readMatrix(SAMPLE_DATA);
    }

    @Test
    public void testPart1() {
        int expected = 2;
        int actual = day2.numSafeReports(reports);
        assertEquals(expected, actual);
    }

    @Test
    public void testPart2() {
        int expected = 4;
        day2.problemDampener(reports);
        int actual = day2.numSafeReports(reports);
        assertEquals(expected, actual);
    }
    
}
