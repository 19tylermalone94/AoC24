package aoc.days;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import aoc.util.Readers;

public class Day25 implements Day {

    @Override
    public void run(String fileName) throws Exception {
        String data = Readers.fileToString(fileName);
        System.out.println(part1(data));
    }

    int part1(String data) {
        List<String> elements = Arrays.stream(data.trim().split("\n\n")).collect(Collectors.toList());
        List<int[]> locks = elements.stream().filter(e -> {
            return e.split("\n")[0].equals("#####");
        })
        .map(e -> {
            int[] lock = new int[5];
            String[] rows = e.split("\n");
            for (int i = 1; i < rows.length; ++i) {
                for (int j = 0; j < rows[0].length(); ++j) {
                    if (rows[i].charAt(j) == '#') {
                        lock[j]++;
                    }
                }
            }
            return lock;
        })
        .collect(Collectors.toList());

        List<int[]> keys = elements.stream().filter(e -> {
            return e.split("\n")[6].equals("#####");
        })
        .map(e -> {
            int[] key = new int[5];
            String[] rows = e.split("\n");
            for (int i = 0; i < rows.length - 1; ++i) {
                for (int j = 0; j < rows[0].length(); ++j) {
                    if (rows[i].charAt(j) == '#') {
                        key[j]++;
                    }
                }
            }
            return key;
        })
        .collect(Collectors.toList());


        int result = 0;

        for (int i = 0; i < locks.size(); ++i) {
            for (int j = 0; j < keys.size(); ++j) {
                if (fits(locks.get(i), keys.get(j))) {
                    ++result;
                }
            }
        }

        return result;
    }

    boolean fits(int[] lock, int[] key) {
        for (int i = 0; i < lock.length; ++i) {
            if (lock[i] + key[i] > 5) {
                return false;
            }
        }
        return true;
    }
  
}
