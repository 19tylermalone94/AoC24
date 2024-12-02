package aoc.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class FileReaders {

    public static String fileToString(String resourcePath) throws IOException {
        try (InputStream inputStream = FileReaders.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new IOException("Resource not found: " + resourcePath);
            }
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    public static List<List<Integer>> readLinesAsLists(String resourcePath) throws IOException {
        String data = fileToString(resourcePath);
        return Arrays.stream(data.trim().split("\n"))
                     .map(line -> readLineAList(line))
                     .collect(Collectors.toList());
    }

    public static List<Integer> readLineAList(String line) {
        return Arrays.stream(line.split(" "))
                     .mapToInt(Integer::parseInt)
                     .boxed()
                     .collect(Collectors.toList());
    }

}