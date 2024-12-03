package aoc.days;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import aoc.util.Readers;

public class Day3 implements Day {

    @Override
    public void run(String fileName) throws Exception {
        String data = Readers.fileToFlattenedString(fileName);
        long part1 = uncorruptedSum(data);
        String enabledData = getEnabledDataOnly(data);
        long part2 = uncorruptedSum(enabledData);
        System.out.printf("part 1: %d\npart 2: %d\n", part1, part2);
    }

    long uncorruptedSum(String data) {
        Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)");
        Matcher matcher = pattern.matcher(data);
        return matcher.results()
                      .mapToLong(result -> calculate(result.group()))
                      .sum();
    }

    private long calculate(String instruction) {
        return Arrays.stream(instruction.replaceAll("[^\\d,]", "").split(","))
                     .mapToInt(Integer::parseInt)
                     .reduce(1, Math::multiplyExact);
    }

    String getEnabledDataOnly(String data) {
        return String.join("", data.split("(don't\\(\\).*?do\\(\\))|(don't\\(\\).*)"));
    }

}
