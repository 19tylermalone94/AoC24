package aoc.days;

import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import aoc.util.Readers;

public class Day4 implements Day {

    Pattern frontPattern = Pattern.compile("XMAS");
    Pattern backPattern = Pattern.compile("SAMX");

    @Override
    public void run(String filename) throws IOException {
        String data = Readers.fileToString(filename);
        int xmasCount = part1(data);
        System.out.println(xmasCount);
        System.out.println(part2(data));
    }

    int part1(String data) {
        String[] rows = getRows(data);
        String[] columns = getColumns(rows);
        String[] leftDiagonals = getDiagonals(rows, true);
        String[] rightDiagonals = getDiagonals(rows, false);
        return countXMASOccurrences(rows) + countXMASOccurrences(columns) 
            + countXMASOccurrences(leftDiagonals) + countXMASOccurrences(rightDiagonals);
    }

    private int countXMASOccurrences(String[] data) {
        return Arrays.stream(data)
                     .mapToInt(this::countXMASOccurrences)
                     .sum();
    }

    private int countXMASOccurrences(String string) {
        return (int) (frontPattern.matcher(string).results().count() + backPattern.matcher(string).results().count());

    }

    private String[] getRows(String data) {
        return data.trim().split("\n");
    }

    private String[] getColumns(String[] rows) {
        return IntStream.range(0, rows[0].length())
                        .mapToObj(i -> Arrays.stream(rows)
                                             .map(row -> row.substring(i, i + 1))
                                             .collect(Collectors.joining()))
                        .toArray(String[]::new);
    }

    private String[] getDiagonals(String[] rows, boolean left) {
        int numDiags = rows[0].length() + rows.length - 1;
        String[] diags = new String[numDiags];
        for (int row = 0; row < numDiags; ++row) {
            diags[row] = getDiag(row, rows, left);
            diags[numDiags - row - 1] = getDiag(numDiags - row - 1, rows, left);
        }
        return diags;
    }

    private String getDiag(int row, String[] rows, boolean left) {
        StringBuilder sb = new StringBuilder();
        int i = row;
        int j = left ? 0 : rows[0].length() - 1;
        while (i >= rows.length) {
            i--;
            j += left ? 1 : -1;
        }
        while (i >= 0 && j < rows[0].length() && j >= 0) {
            sb.append(rows[i--].charAt(left ? j++ : j--));
        }
        return sb.toString();
    }

    int part2(String data) {
        int count = 0;
        char[][] d = Arrays.stream(data.split("\n"))
                           .map(String::toCharArray)
                           .toArray(char[][]::new);
        for (int i = 1; i < d.length - 1; ++i) {
            for (int j = 1; j < d[0].length - 1; ++j) {
                if (d[i][j] == 'A') {
                    char topLeft = d[i - 1][j - 1];
                    char topRight = d[i - 1][j + 1];
                    char bottomLeft = d[i + 1][j - 1];
                    char bottomRight = d[i + 1][j + 1];
                    if (isX(topLeft, topRight, bottomLeft, bottomRight)) {
                        ++count;
                    }
                }
            }
        }
        return count;
    }

    boolean isX(char tl, char tr, char bl, char br) {
        return (tl == 'M' && br == 'S' || tl == 'S' && br == 'M')
            && (bl == 'M' && tr == 'S' || bl == 'S' && tr == 'M');
    }

}
