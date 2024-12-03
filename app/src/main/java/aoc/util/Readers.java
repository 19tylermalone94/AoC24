package aoc.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public final class Readers {

    public static String fileToString(String resourcePath) throws IOException {
        try (InputStream inputStream = Readers.class.getClassLoader().getResourceAsStream(resourcePath)) {
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    public static String fileToFlattenedString(String resourcePath) throws IOException {
        return String.join("", fileToString(resourcePath).split("\n"));
    }

}