package aoc.days;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import aoc.util.Readers;
import Jama.Matrix;

public class Day13 implements Day {

    final long P2 = 10000000000000L;

    @Override
    public void run(String fileName) throws Exception {
        String data = Readers.fileToString(fileName);
        System.out.println(part1(data));
        System.out.println(part2(data));
    }

    long part1(String data) {
        return solve(data, false);
    }

    long part2(String data) {
        return solve(data, true);
    }

    long solve(String data, boolean part2) {
        List<long[]> machines = loadMachines(data);
        long totalTokens = 0;
        for (long[] m : machines) {
            long[] pv = {m[0], m[1]};
            if (part2) {
                pv[0] += P2;
                pv[1] += P2;
            }
            long[] av = {m[2], m[3]};
            long[] bv = {m[4], m[5]};
            Matrix solution = solve(m, pv, av, bv);
            double a = solution.get(0, 0);
            double b = solution.get(1, 0);
            long aRound = Math.round(a);
            long bRound = Math.round(b);
            if (av[0]*aRound + bv[0]*bRound == pv[0] && av[1]*aRound + bv[1]*bRound == pv[1]) {
                totalTokens += 3*aRound + bRound;
            }
        }
        return totalTokens;
    }

    Matrix solve(long[] machine, long[] p, long[] a, long[] b) {
        double[][] lhsArray = {{a[0], b[0]}, {a[1], b[1]}};
        double[] rhsArray = {p[0], p[1]};
        Matrix lhs = new Matrix(lhsArray);
        Matrix rhs = new Matrix(rhsArray, 2);
        return lhs.solve(rhs);
    }


    List<long[]> loadMachines(String data) {
        return Arrays.stream(data.trim().split("\n\n"))
                     .map(config -> {
                        String[] specs = config.split("\n");
                        String aSpec = specs[0];
                        String bSpec = specs[1];
                        String pSpec = specs[2];
                        long aX = Long.parseLong(specs[0].substring(aSpec.indexOf("+") + 1, aSpec.indexOf(",")));
                        long aY = Long.parseLong(specs[0].substring(aSpec.lastIndexOf("+") + 1));
                        long bX = Long.parseLong(specs[1].substring(bSpec.indexOf("+") + 1, bSpec.indexOf(",")));
                        long bY = Long.parseLong(specs[1].substring(bSpec.lastIndexOf("+") + 1));
                        long pX = Long.parseLong(specs[2].substring(pSpec.indexOf("=") + 1, pSpec.indexOf(",")));
                        long pY = Long.parseLong(specs[2].substring(pSpec.lastIndexOf("=") + 1));
                        return new long[]{pX, pY, aX, aY, bX, bY};
                     })
                     .collect(Collectors.toList());
    }
    
}
