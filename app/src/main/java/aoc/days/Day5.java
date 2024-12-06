package aoc.days;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;
import aoc.util.Parsers;
import aoc.util.Readers;

public class Day5 implements Day {

    Map<String, Set<String>> rules;
    
    @Override
    public void run(String filename) throws IOException {
        String data = Readers.fileToString(filename);
        String[] parts = data.split("\n\n");
        String ruleData = parts[0];
        String updateData = parts[1];
        initRules(ruleData);
        System.out.println(part1(updateData));
        System.out.println(part2(updateData));
    }

    void initRules(String data) {
        rules = new HashMap<>();
        Parsers.readLines(data).stream()
                               .forEach(line -> {
                                    String[] parts = line.split("\\|");
                                    if (rules.containsKey(parts[0])) {
                                        rules.get(parts[0]).add(parts[1]);
                                    } else {
                                        rules.put(parts[0], new HashSet<>(List.of(parts[1])));
                                    }
                               });
    }

    int part1(String data) {
        String[] updates = data.split("\n");
        return Arrays.stream(updates)
                    .map(update -> update.split(","))
                    .filter(update -> isValidUpdate(update))
                    .map(validUpdate -> validUpdate[validUpdate.length / 2])
                    .mapToInt(Integer::parseInt)
                    .sum();

    }

    int part2(String data) {
        String[] updates = data.split("\n");
        return Arrays.stream(updates)
                     .map(update -> update.split(","))
                     .filter(update -> !isValidUpdate(update))
                     .map(invalidUpdate -> {
                        makeValid(invalidUpdate);
                        return invalidUpdate;
                     })
                     .map(validUpdate -> validUpdate[validUpdate.length / 2])
                     .mapToInt(Integer::parseInt)
                     .sum();
    }

    private boolean isValidUpdate(String[] update) {
        return IntStream.range(1, update.length)
                        .filter(i -> !validPosition(i, update))
                        .count() == 0;
    }

    private boolean validPosition(int i, String[] update) {
        Set<String> rule = rules.get(update[i]);
        return rule == null || IntStream.range(0, i)
                                        .filter(j -> rule.contains(update[j]))
                                        .count() == 0;
    }

    private void makeValid(String[] invalidUpdate) {
        for (int i = invalidUpdate.length - 1; i >= 0; --i) {
            for (int j = 0; j < i; ++j) {
                if (rules.get(invalidUpdate[i]) != null && rules.get(invalidUpdate[i]).contains(invalidUpdate[j])) {
                    String temp = invalidUpdate[i];
                    invalidUpdate[i] = invalidUpdate[j];
                    invalidUpdate[j] = temp;
                }
            }
        }
    }

}
