package aoc.days;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import aoc.util.Readers;

/*
 * I did not write a general solution for part 2 here. I solved it with a little reverse engineering and manual inspection of how the inputs are mapped.
 */

public class Day24 implements Day {

    @Override
    public void run(String fileName) throws Exception {
        String data = Readers.fileToString(fileName);
        System.out.println(part1(data));
        part2(data); // displays the index of the bits that are messed up if any. In the case for my input, the mistakes were found in bits 10, 17, 35, and 39
        Map<String, String> map = gateMap(data);
        printTree(map, "z39", 0); // shows the tree of mappings for bit 39
        List<String> result = new LinkedList<>(List.of("fhg", "z17", "z10", "vcf", "fsq", "dvb", "z39", "tnc"));
        result.sort((s1, s2) -> s1.compareTo(s2));
        System.out.println(String.join(",", result));
    }

    long part1(String data) {
        String[] parts = data.trim().split("\n\n");
        Map<String, Integer> wires = new HashMap<>();
        Arrays.stream(parts[1].trim().split("\n")).forEach(line -> {
            String[] p = line.split(" ");
            String wire1 = p[0];
            String wire2 = p[2];
            String wire3 = p[4];
            for (String wire : new String[]{wire1, wire2, wire3}) {
                wires.putIfAbsent(wire, -1);
            }
        });
        Arrays.stream(parts[0].trim().split("\n")).forEach(line -> {
            String[] p = line.split(": ");
            wires.put(p[0], Integer.parseInt(p[1]));
        });

        while (true) {
            boolean changed = false;
            for (String line : parts[1].split("\n")) {
                String[] p = line.split(" ");
                String wire1 = p[0];
                String wire2 = p[2];
                String wire3 = p[4];
                String operator = p[1];
                if (wires.get(wire1) != -1 && wires.get(wire2) != -1 && wires.get(wire3) == -1) {
                    changed = true;
                    evaluate(wire1, wire2, wire3, operator, wires);
                }
            }
            if (!changed) {
                break;
            }
        }

        List<Map.Entry<String, Integer>> zWires = new LinkedList<>();
        for (Map.Entry<String, Integer> entry : wires.entrySet()) {
            if (entry.getKey().startsWith("z")) {
                zWires.add(entry);
            }
        }

        zWires.sort((w1, w2) -> w2.getKey().compareTo(w1.getKey()));
        String binaryString = zWires.stream().map(entry -> entry.getValue().toString()).collect(Collectors.joining());
        return Long.parseLong(binaryString, 2);
    }

    void evaluate(String w1, String w2, String w3, String op, Map<String, Integer> wires) {
        if (op.equals("OR")) {
            wires.put(w3, wires.get(w1) == 1 || wires.get(w2) == 1 ? 1 : 0);
        }
        if (op.equals("AND")) {
            wires.put(w3, wires.get(w1) == 1 && wires.get(w2) == 1 ? 1 : 0);
        }
        if (op.equals("XOR")) {
            wires.put(w3, wires.get(w1) != wires.get(w2) ? 1 : 0);
        }
    }

    void part2(String data) {
        List<String> x = new LinkedList<>(Collections.nCopies(45, "0"));
        List<String> y = new LinkedList<>(Collections.nCopies(45, "0"));
        for (int i = 0; i < 45; ++i) {
            for (int j = 0; j < 45; ++j) {
                x.set(i, "1");
                y.set(j, "1");
                String newX = x.stream().collect(Collectors.joining());
                String newY = y.stream().collect(Collectors.joining());
                long xNum = Long.parseLong(Arrays.stream(newX.split("")).collect(Collectors.toList()).reversed().stream().collect(Collectors.joining()), 2);
                long yNum = Long.parseLong(Arrays.stream(newY.split("")).collect(Collectors.toList()).reversed().stream().collect(Collectors.joining()), 2);
                long sum = xNum + yNum;
                if (eval(data, newX, newY) != sum) {
                    System.out.println(i + " " + j);
                }
                x.set(i, "0");
                y.set(j, "0");
            }
        }
    }

    String randomBinary() {
        Random rand = new Random();
        String result = "";
        for (int i = 0; i < 45; ++i) {
            result += rand.nextInt(2) == 0 ? "0" : "1";
        }
        return result;
    }

    Long eval(String data, String x, String y) {
        String[] parts = data.trim().split("\n\n");
        Map<String, Integer> wires = new HashMap<>();
        Arrays.stream(parts[1].trim().split("\n")).forEach(line -> {
            String[] p = line.split(" ");
            String wire1 = p[0];
            String wire2 = p[2];
            String wire3 = p[4];
            for (String wire : new String[]{wire1, wire2, wire3}) {
                wires.putIfAbsent(wire, -1);
            }
        });

        for (int i = 0; i < x.length(); ++i) {
            String xKey = "";
            String yKey = "";
            if (i < 10) {
                xKey = "x0";
                yKey = "y0";
            } else {
                xKey = "x";
                yKey = "y";
            }

            wires.put(xKey + i, x.charAt(i) - '0');
            wires.put(yKey + i, y.charAt(i) - '0');
        }

        while (true) {
            boolean changed = false;
            for (String line : parts[1].split("\n")) {
                String[] p = line.split(" ");
                String wire1 = p[0];
                String wire2 = p[2];
                String wire3 = p[4];
                String operator = p[1];
                if (wires.get(wire1) != -1 && wires.get(wire2) != -1 && wires.get(wire3) == -1) {
                    changed = true;
                    evaluate(wire1, wire2, wire3, operator, wires);
                }
            }
            if (!changed) {
                break;
            }
        }
        List<Map.Entry<String, Integer>> zWires = new LinkedList<>();
        for (Map.Entry<String, Integer> entry : wires.entrySet()) {
            if (entry.getKey().startsWith("z")) {
                zWires.add(entry);
            }
        }

        zWires.sort((w1, w2) -> w2.getKey().compareTo(w1.getKey()));
        String binaryString = zWires.stream().map(entry -> entry.getValue().toString()).collect(Collectors.joining());
        return Long.parseLong(binaryString, 2);
    }

    Map<String, String> gateMap(String data) {
        String[] parts = data.split("\n\n");
        Map<String, String> map = new HashMap<>();
        Arrays.stream(parts[1].trim().split("\n"))
              .forEach(gate -> {
                String[] p = gate.split(" -> ");
                map.put(p[1], p[0]);
              });

        return map;
    }

    void printTree(Map<String, String> map, String key, int level) {
        if (key.startsWith("y") || key.startsWith("x")) {
            return;
        }
        System.out.println(String.join("|" + "", Collections.nCopies(level, "  ")) + key + " = " + map.get(key));
        String input1 = map.get(key).substring(0, map.get(key).indexOf(" "));
        String input2 = map.get(key).substring(map.get(key).lastIndexOf(" ") + 1);
        printTree(map, input1, level + 1);
        printTree(map, input2, level + 1);
    }
  
}
