package aoc.days;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import aoc.util.Pair;
import aoc.util.Parsers;
import aoc.util.Readers;

public class Day11 implements Day {

    Map<Pair<Integer, Long>, Long> map;
    ArrayList<Integer> list;

    @Override
    public void run(String fileName) throws Exception {
        String data = Readers.fileToString(fileName);
        System.out.println(part1(data));
        System.out.println(part2(data));
    }

    long part1(String data) {
        map = new HashMap<>();
        List<Long> stones = Parsers.readLongs(data.trim());
        long sum = 0;
        for (long stone : stones) {
            sum +=  blink(25, stone);
        }
        return sum;
    }

    long part2(String data) {
        map = new HashMap<>();
        List<Long> stones = Parsers.readLongs(data.trim());
        long sum = 0;
        for (long stone : stones) {
            sum += blink(75, stone);
        }
        return sum;
    }

    private long blink(int numBlinks, long stone) {
        Pair<Integer, Long> pair = new Pair<Integer, Long>(numBlinks, stone);
        long result = 0;
        if (map.containsKey(pair)) {
            return map.get(pair);
        } else if (numBlinks == 0) {
            result = 1;
        } else if (stone == 0) {
            result = blink(numBlinks - 1, 1);
        } else if (numDigits(stone) % 2 == 0) {
            long[] halves = splitDigitsInHalf(stone);
            result = blink(numBlinks - 1, halves[0]) + blink(numBlinks - 1, halves[1]);
        } else {
            result = blink(numBlinks - 1, stone * 2024);
        }
        map.put(pair, result);
        return map.get(pair);
    }

    private int numDigits(long num) {
        int count = 0;
        while (num > 0) {
            num /= 10;
            ++count;
        }
        return count;
    }

    private long[] splitDigitsInHalf(long num) {
        int numDigits = numDigits(num);
        long thing = (long)Math.pow(10, numDigits / 2);
        long firstHalf = num / thing;
        long secondHalf = num % (firstHalf * thing);
        return new long[]{firstHalf, secondHalf};
    }
    
}
