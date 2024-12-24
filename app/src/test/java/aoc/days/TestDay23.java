package aoc.days;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDay23 implements TestDay {

    String sample;

    Day23 day23;

    @Override
    public void beforeEach() throws Exception {
        day23 = new Day23();
    }

    @Override
    public void testPart1() throws Exception {
        int expected = 0;
        int actual = day23.part1(sample);
        assertEquals(expected, actual);
    }

    @Override
    public void testPart2() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testPart2'");
    }
  
}
