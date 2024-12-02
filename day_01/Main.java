package day_01;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import util.FileReaders;

public class Main {

    public static void main(String[] args) throws IOException {
        String input = FileReaders.fileToString("day_01/input/part_1.txt");
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        Arrays.stream(input.trim().split("\n"))
              .forEach(line -> {
                String[] parts = line.split("\\s+");
                left.add(Integer.parseInt(parts[0]));
                right.add(Integer.parseInt(parts[1]));
              });
        part1(left, right);
        part2(left, right);
    }

    private static void part1(List<Integer> left, List<Integer> right) throws IOException {
        Collections.sort(left);
        Collections.sort(right);
        int result = IntStream.range(0, left.size())
                              .map(i -> Math.abs(left.get(i) - right.get(i)))
                              .sum();
        System.out.printf("part 1: %d\n", result);
    }

    private static void part2(List<Integer> left, List<Integer> right) throws IOException {
        Map<Integer, Integer> map = new HashMap<>();
        int result = left.stream()
                         .mapToInt(n -> {
                            map.putIfAbsent(n, countOccurrences(n, right));
                            int numOccurrences = map.get(n);
                            return n * numOccurrences;
                         })
                         .sum();
        System.out.printf("part 2: %d\n", result);
    }

    private static int countOccurrences(int val, List<Integer> data) {
        return (int) data.stream()
                         .filter(n -> n == val)
                         .count();
    }
    
}
