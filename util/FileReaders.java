package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class FileReaders {

    public static String fileToString(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

}