package aoc.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import aoc.util.Parsers;
import aoc.util.Readers;

public class Day7 implements Day {

    @Override
    public void run(String fileName) throws Exception {
        String data = Readers.fileToString(fileName);
        System.out.println(part1(data));
        System.out.println(part2(data));
    }

    long part1(String data) throws Exception {
        return possibleSum(data, new char[]{'+', '*'});
    }

    long part2(String data) throws Exception {
        return possibleSum(data, new char[]{'+', '*', '|'});
    }

    long possibleSum(String data, char[] ops) {
        long sum = 0;
        List<String> lines = Parsers.readLines(data);
        for (String line : lines) {
            long result = Long.parseLong(line.substring(0, line.indexOf(":")));
            List<Long> operands = Arrays.stream(line.split(": ")[1].split(" "))
                                           .mapToLong(Long::parseLong)
                                           .boxed()
                                           .collect(Collectors.toList());
            if (isPossible(result, operands, ops)) {
                sum += result;
            }
        }
        return sum;
    }

    private boolean isPossible(long result, List<Long> operands, char[] ops) {
        return checkCombos(result, 0, new ArrayList<>(Collections.nCopies(operands.size() - 1, '+')), operands, ops);
    }

    private boolean checkCombos(long target, int i, List<Character> operators, List<Long> operands, char[] ops) {
        if (i == operators.size()) {
            return evaluate(operands, operators) == target;
        }
        for (char operator : ops) {
            operators.set(i, operator);
            if (checkCombos(target, i + 1, operators, operands, ops)) {
                return true;
            }
        }
        return false;
    }
        

    private long evaluate(List<Long> operands, List<Character> operators) {
        long result = operands.getFirst();
        for (int i = 1; i < operands.size(); ++i) {
            char operator = operators.get(i - 1);
            long operand = operands.get(i);
            if (operator == '*') {
                result *= operand;
            } else if (operator == '+') {
                result += operand;
            } else {
                result = Long.parseLong(Long.toString(result) + Long.toString(operand));
            }
        }
        return result;
    }
    
}
