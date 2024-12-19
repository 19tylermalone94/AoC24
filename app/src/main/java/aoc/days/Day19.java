package aoc.days;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import aoc.util.Readers;

public class Day19 implements Day {

    Map<String, Long> map;

    @Override
    public void run(String fileName) throws Exception {
        String data = Readers.fileToString(fileName);
        // System.out.println(part1(data));
        System.out.println(part2(data));
    }

    int part1(String data) {
        String[] parts = data.trim().split("\n\n");
        List<String> availableTowels = Arrays.stream(parts[0].split(", "))
                                             .collect(Collectors.toList());
        List<String> desiredPatterns = Arrays.stream(parts[1].split("\n"))
                                             .collect(Collectors.toList());
        int sum = 0;
        for (String desired : desiredPatterns) {
            sum += makeCombo("", desired, availableTowels) ? 1 : 0;
        }                                             
        return sum;
    }

    long part2(String data) {
        map = new HashMap<>();
        String[] parts = data.trim().split("\n\n");
        List<String> availableTowels = Arrays.stream(parts[0].split(", "))
                                             .collect(Collectors.toList());
        List<String> desiredPatterns = Arrays.stream(parts[1].split("\n"))
                                             .collect(Collectors.toList());

        System.out.println(desiredPatterns.size());
        long sum = 0;
        for (int i = 0; i < desiredPatterns.size(); ++i) {
            System.out.println(i);
            sum += makeCombo2(desiredPatterns.get(i), availableTowels);
        }                                             
        return sum;
    }

    boolean makeCombo(String curr, String desired, List<String> available) {
        // System.out.println("curr: " + curr + ", desired: " + desired);
        if (curr.equals(desired)) {
            return true;
        }
        if (curr.length() >= desired.length() || !desired.startsWith(curr)) {
            return false;
        }

        boolean result = false;
        for (int i = 0; i < available.size(); ++i) {
            result = result || makeCombo(curr + available.get(i), desired, available);
        }
        return result;
    }

    long makeCombo2(String desired, List<String> available) {
        // System.out.println("curr: " + curr + ", desired: " + desired);
        if (map.containsKey(desired)) {
            return map.get(desired);
        }
        long result = 0;
        if (desired.length() == 0) {
            result = 1;
        }

        for (String word : available) {
            if (desired.startsWith(word)) {
                result += makeCombo2(desired.substring(word.length()), available);
            }
        }
        map.put(desired, result);
        return result;
    }
    
}
