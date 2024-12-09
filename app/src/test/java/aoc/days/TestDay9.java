package aoc.days;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDay9 implements TestDay {

    String sample = "2333133121414131402";
    Day9 day9;

    @Override
    public void beforeEach() throws Exception {
        day9 = new Day9();
    }

    @Override
    public void testPart1() throws Exception {
        long expected = 1928;
        long actual = day9.part1(sample);
        assertEquals(expected, actual);
    }

    @Override
    public void testPart2() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testPart2'");
    }
    
}
