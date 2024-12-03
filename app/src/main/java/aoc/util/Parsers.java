package aoc.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Parsers {

    public static List<List<Integer>> readMatrix(String data) throws IOException {
        return Arrays.stream(data.trim().split("\n"))
                     .map(line -> readRow(line))
                     .collect(Collectors.toList());
    }

    public static List<Integer> readRow(String data) {
        return Arrays.stream(data.split(" "))
                     .mapToInt(Integer::parseInt)
                     .boxed()
                     .collect(Collectors.toList());
    }
    
}