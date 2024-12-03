package aoc.days;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import aoc.util.Parsers;
import aoc.util.Readers;

public class Day2 implements Day {

    @Override
    public void run(String fileName) throws Exception {
        List<List<Integer>> reports = Parsers.readMatrix(Readers.fileToString(fileName));
        int numSafeBeforeDampening = numSafeReports(reports);
        problemDampener(reports);
        int numSafeAfterDampening = numSafeReports(reports);
        System.out.printf("num safe before dampening: %d\nnum safe after dampening: %d\n", numSafeBeforeDampening, numSafeAfterDampening);
    }

    int numSafeReports(List<List<Integer>> reports) {
        return (int) reports.stream()
                            .filter(report -> isSafe(report))
                            .count();
    }

    private boolean isSafe(List<Integer> report) {
        return (meetsCriteria(report, (a, b) -> a < b) || meetsCriteria(report, (a, b) -> a > b)) 
            && meetsCriteria(report, (a, b) -> Math.abs(a - b) >= 1 && Math.abs(a - b) <= 3);
    }

    private boolean meetsCriteria(List<Integer> report, BiPredicate<Integer, Integer> criteria) {
        for (int i = 1; i < report.size(); ++i) {
            if (!criteria.test(report.get(i), report.get(i - 1))) {
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
