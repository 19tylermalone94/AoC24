package aoc.days;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import aoc.util.FileReaders;

public class Day1 implements Day {

    @Override
    public void run(String fileName) throws IOException {
        String input = FileReaders.fileToString(fileName);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        Arrays.stream(input.trim().split("\n"))
              .forEach(line -> {
                String[] parts = line.split("\\s+");
                left.add(Integer.parseInt(parts[0]));
                right.add(Integer.parseInt(parts[1]));
              });
        int part1Result = part1(left, right);
        int part2Result = part2(left, right);
        System.out.printf("part1: %d\npart2: %d\n", part1Result, part2Result);
    }

    int part1(List<Integer> left, List<Integer> right) throws IOException {
        Collections.sort(left);
        Collections.sort(right);
        return IntStream.range(0, left.size())
                        .map(i -> Math.abs(left.get(i) - right.get(i)))
                        .sum();
}

    int part2(List<Integer> left, List<Integer> right) throws IOException {
        Map<Integer, Integer> map = new HashMap<>();
        return left.stream()
                   .mapToInt(n -> {
                        map.putIfAbsent(n, countOccurrences(n, right));
                        int numOccurrences = map.get(n);
                        return n * numOccurrences;
                   })
                   .sum();
    }

    private int countOccurrences(int val, List<Integer> data) {
        return (int) data.stream()
                         .filter(n -> n == val)
                         .count();
    }
    
}
