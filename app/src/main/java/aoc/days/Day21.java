package aoc.days;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day21 implements Day {

    char[][] numPad = {
        {'7', '8', '9'},
        {'4', '5', '6'},
        {'1', '2', '3'},
        {' ', '0', 'A'}
    };

    char[][] dirPad = {
        {' ', '^', 'A'},
        {'<', 'V', '>'}
    };

    @Override
    public void run(String fileName) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

    long part1(String data) {
        List<String> codes = Arrays.stream(data.trim().split("\n\n"))
                                   .collect(Collectors.toList());

        long sum = 0;
        for (String code : codes) {
            sum += solve(code);
        }
    }

    // for each char in target seq, generate all shortest subseq to
    // all possible shortest seq is the permutation of these subseq

}