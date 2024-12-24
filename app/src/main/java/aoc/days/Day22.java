package aoc.days;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import aoc.util.Readers;

public class Day22 implements Day {

    @Override
    public void run(String fileName) throws Exception {
        String data = Readers.fileToString(fileName);
        System.out.println(part1(data));
        System.out.println(part2(data));
    }

    long part1(String data) {
        List<Integer> initialNums = Arrays.stream(data.trim().split("\n"))
                                          .mapToInt(Integer::parseInt)
                                          .boxed()
                                          .collect(Collectors.toList());
        long sum = 0L;
        for (long initial : initialNums) {
            sum += predictNthSecret(initial, 2000);
        }
        return sum;
    }

    long part2(String data) {
        List<Integer> initialNums = Arrays.stream(data.trim().split("\n"))
                                          .mapToInt(Integer::parseInt)
                                          .boxed()
                                          .collect(Collectors.toList());
        
        Map<List<Integer>, Integer> score = new HashMap<>();
        for (int initial : initialNums) {
            List<List<Integer>> pc = getPriceAndChange(initial, 2000);
            List<Integer> prices = pc.getFirst();
            List<Integer> changes = pc.getLast();
            Map<List<Integer>, Integer> scores = getScores(prices, changes);
            for (Map.Entry<List<Integer>, Integer> entry : scores.entrySet()) {
                if (!score.containsKey(entry.getKey())) {
                    score.put(entry.getKey(), entry.getValue());
                } else {
                    score.put(entry.getKey(), entry.getValue() + score.get(entry.getKey()));
                }
            }
        }
        return score.entrySet().stream()
                               .mapToInt(Map.Entry::getValue)
                               .max()
                               .getAsInt();
    }

    Map<List<Integer>, Integer> getScores(List<Integer> prices, List<Integer> changes) {
        Map<List<Integer>, Integer> scores = new HashMap<>();
        for (int i = 0; i < changes.size() - 3; ++i) {
            List<Integer> seq = changes.subList(i, i + 4);
            if (!scores.containsKey(seq)) {
                scores.put(seq, prices.get(i + 4));
            }
        }
        return scores;
    }

    long predictNthSecret(long initial, int n) {
        long curr = initial;
        for (int i = 0; i < n; ++i) {
            long result = curr * 64;
            long secret = result ^ curr;
            secret %= 16777216;
            result = secret / 32;
            secret = result ^ secret;
            secret %= 16777216;
            result = secret * 2048;
            secret = result ^ secret;
            secret %= 16777216;
            curr = secret;
        }
        return curr;
    }

    List<List<Integer>> getPriceAndChange(int initial, int n) {
        List<Integer> prices = new LinkedList<>();
        List<Integer> changes = new LinkedList<>();
        long curr = initial;
        for (int i = 0; i < n; ++i) {
            prices.add((int)(curr % 10));
            long result = curr * 64;
            long secret = result ^ curr;
            secret %= 16777216;
            result = secret / 32;
            secret = result ^ secret;
            secret %= 16777216;
            result = secret * 2048;
            secret = result ^ secret;
            secret %= 16777216;
            changes.add((int)(secret % 10) - (int)(curr % 10));
            curr = secret;
        }
        changes.removeLast();
        return List.of(prices, changes);
    }

}
