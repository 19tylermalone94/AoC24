package aoc.days;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public interface TestDay {

    @BeforeEach
    public void beforeEach() throws Exception;

    @Test
    public void testPart1() throws Exception;

    @Test
    public void testPart2() throws Exception;
    
}
