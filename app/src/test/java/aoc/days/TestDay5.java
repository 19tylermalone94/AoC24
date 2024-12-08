package aoc.days;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDay5 implements TestDay {

    String sample = """
            47|53
            97|13
            97|61
            97|47
            75|29
            61|13
            75|53
            29|13
            97|29
            53|29
            61|53
            97|53
            61|29
            47|13
            75|47
            97|75
            47|61
            75|61
            47|29
            75|13
            53|13

            75,47,61,53,29
            97,61,53,29,13
            75,29,13
            75,97,47,61,53
            61,13,29
            97,13,75,29,47

                    """;

    Day5 day5;

    @BeforeEach
    public void beforeEach() {
        day5 = new Day5();
    }

    @Test
    public void testPart1() throws IOException {
        String[] parts = sample.split("\n\n");
        String ruleData = parts[0];
        String updateData = parts[1];
        day5.initRules(ruleData);
        int expected = 143;
        int actual = day5.part1(updateData);
        assertEquals(expected, actual);
    }

    @Test
    public void testPart2() throws IOException {
        String[] parts = sample.split("\n\n");
        String ruleData = parts[0];
        String updateData = parts[1];
        day5.initRules(ruleData);
        int expected = 123;
        int actual = day5.part2(updateData);
        assertEquals(expected, actual);
    }
}
