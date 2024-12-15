package aoc.days;

import java.util.Collections;
import java.util.stream.IntStream;
import aoc.util.Readers;

public class Day9 implements Day {

    @Override
    public void run(String fileName) throws Exception {
        String data = Readers.fileToString(fileName).trim();
        System.out.println(part1(data));
        System.out.println(part2(data));
    }
    
    long part1(String data) {
        int[] disk = decompress(data);
        fragmentByBlock(disk);
        return checkSum(disk);
    }

    long part2(String data) {
        int[] disk = decompress(data);
        fragmentByFile(disk);
        return checkSum(disk);
    }

    private int[] decompress(String data) {
        return IntStream.range(0, data.length())
                        .mapToObj(i -> Collections.nCopies(data.charAt(i) - '0', i % 2 == 0 ? i / 2 : -1))
                        .flatMapToInt(collection -> collection.stream().mapToInt(Integer::valueOf))
                        .toArray();
    }

    private void fragmentByBlock(int[] disk) {
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

    private void fragmentByFile(int[] disk) {
        int maxID = disk[disk.length - 1];
        for (int ID = maxID; ID >= 0; --ID) {
            int[] file = findFile(ID, disk);
            int fileBegin = file[0];
            int fileEnd = file[1];
            int fileLength = fileEnd - fileBegin;
            int[] newLocation = findNewLocation(fileBegin, fileLength, disk);
            int newBegin = newLocation[0];
            int newEnd = newLocation[1];
            if (newEnd - newBegin == fileLength && disk[newBegin] == -1) {
                moveFile(fileBegin, fileLength, newBegin, disk);
            }
        }
    }

    private int[] findFile(int ID, int[] disk) {
        int fileBegin = 0;
        while (fileBegin < disk.length && disk[fileBegin] != ID) {
            ++fileBegin;
        }
        int fileEnd = fileBegin + 1;
        while (fileEnd < disk.length && disk[fileEnd] == ID) {
            ++fileEnd;
        }
        return new int[]{fileBegin, fileEnd};
    }

    // TODO clean this up
    private int[] findNewLocation(int fileBegin, int fileLength, int[] disk) {
        int newBegin = 0;
        int newEnd = -1;
        while (newBegin < fileBegin - fileLength) {
            while (newBegin < fileBegin - fileLength && disk[newBegin] != -1) {
                ++newBegin;
            }
            newEnd = newBegin + 1;
            while (newEnd <= fileBegin && disk[newEnd] == -1 && newEnd - newBegin < fileLength) {
                ++newEnd;
            }
            if (newEnd - newBegin == fileLength) {
                break;
            } else {
                newBegin = newEnd;
            }
        }
        return new int[]{newBegin, newEnd};
    }

    private void moveFile(int fileBegin, int fileLength, int newBegin, int[] disk) {
        for (int i = 0; i < fileLength; ++i) {
            int temp = disk[newBegin + i];
            disk[newBegin + i] = disk[fileBegin + i];
            disk[fileBegin + i] = temp;
        }
    }

    private long checkSum(int[] disk) {
        long sum = 0;
        for (int i = 0; i < disk.length; ++i) {
            if (disk[i] == -1) {
                continue;
            }
            sum += disk[i] * i;
        }
        return sum;
    }

}
