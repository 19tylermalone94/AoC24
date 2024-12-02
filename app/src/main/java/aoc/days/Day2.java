package aoc.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import aoc.util.FileReaders;

public class Day2 implements Day {

    @Override
    public void run(String fileName) throws Exception {
        String input = FileReaders.fileToString(fileName);
        List<List<Integer>> reports = Arrays.stream(input.trim().split("\n"))
                                            .map(line -> Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList()))
                                            .collect(Collectors.toList());
        int numberSafeBeforeDampening = numSafeReports(reports);
        problemDampener(reports);
        int numberSafeAfterDampening = numSafeReports(reports);
        System.out.printf("num safe before dampening: %d\nnum safe after dampening: %d\n", numberSafeBeforeDampening, numberSafeAfterDampening);
    }

    int numSafeReports(List<List<Integer>> reports) {
        return (int) reports.stream()
                            .filter(report -> isSafe(report))
                            .count();
    }

    private boolean isSafe(List<Integer> report) {
        return (isSorted(report, (a, b) -> a < b) || isSorted(report, (a, b) -> a > b)) && allDifferByOneToThree(report);
    }

    private boolean isSorted(List<Integer> report, BiPredicate<Integer, Integer> comparator) {
        for (int i = 1; i < report.size(); ++i) {
            if (!comparator.test(report.get(i), report.get(i - 1))) {
                return false;
            }
        }
        return true;
    }

    private boolean allDifferByOneToThree(List<Integer> report) {
        for (int i = 1; i < report.size(); ++i) {
            int diff = Math.abs(report.get(i) - report.get(i - 1));
            if (diff < 1 || diff > 3) {
                return false;
            }
        }
        return true;
    }

    void problemDampener(List<List<Integer>> reports) {
        for (List<Integer> report : reports) {
            if (!isSafe(report)) {
                dampen(report);
            }
        }
    }

    private void dampen(List<Integer> report) {
        List<Integer> shortenedReport = new ArrayList<>(report);
        for (int i = 0; i < report.size(); ++i) {
            int dropped = shortenedReport.remove(i);
            if (isSafe(shortenedReport)) {
                report.remove(i);
                return;
            }
            shortenedReport.add(i, dropped);
        }
    }
    
}
