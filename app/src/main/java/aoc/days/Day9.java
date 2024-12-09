package aoc.days;

import java.util.Collections;
import java.util.stream.IntStream;
import aoc.util.Readers;

public class Day9 implements Day {

    @Override
    public void run(String fileName) throws Exception {
        String data = Readers.fileToString(fileName).trim();
        System.out.println(part1(data));
    }
    
    long part1(String data) {
        int[] disk = decompress(data);
        defragment(disk);
        return checkSum(disk);
    }

    int[] decompress(String data) {
        return IntStream.range(0, data.length())
                        .mapToObj(i -> Collections.nCopies(data.charAt(i) - '0', i % 2 == 0 ? i / 2 : -1))
                        .flatMapToInt(collection -> collection.stream().mapToInt(Integer::valueOf))
                        .toArray();
    }

    void defragment(int[] disk) {
        int left = 0;
        int right = disk.length - 1;
        while (left < right) {
            while (left < right && disk[left] != -1) {
                ++left;
            }
            while (right > left && disk[right] == -1) {
                --right;
            }
            if (left < disk.length && right >= 0) {
                int temp = disk[left];
                disk[left] = disk[right];
                disk[right] = temp;
            }
        }
    }

    long checkSum(int[] disk) {
        long sum = 0;
        for (int i = 0; i < disk.length; ++i) {
            if (disk[i] == -1) {
                break;
            }
            sum += ((long)(disk[i])) * ((long)i);
        }
        return sum;
    }

}
