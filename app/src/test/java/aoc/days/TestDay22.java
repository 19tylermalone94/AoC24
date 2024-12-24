package aoc.days;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDay22 implements TestDay {

    String sample1 = """
            1
            10
            100
            2024

            """;

    String sample2 = """
            1
            2
            3
            2024

            """;

    Day22 day22;

    @Override
    public void beforeEach() throws Exception {
        day22 = new Day22();
    }

    @Override
    public void testPart1() throws Exception {
        long expected = 37327623L;
        long actual = day22.part1(sample1);
        assertEquals(expected, actual);
    }

    @Override
    public void testPart2() throws Exception {
        long expected = 23;
        long actual = day22.part1(sample2);
        assertEquals(expected, actual);
    }
  
}
