package aoc.days;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import aoc.util.Readers;

public class Day23 implements Day {

    @Override
    public void run(String fileName) throws Exception {
        String data = Readers.fileToString(fileName);
        System.out.println(part12(data));
        System.out.println(part2(data));
    }

    int part12(String data) {
        Map<String, Set<String>> connections = getConnections(data);
        int count = 0;
        for (Map.Entry<String, Set<String>> entry : connections.entrySet()) {
            String key = entry.getKey();
            Set<String> val = entry.getValue();
            Set<String> machines = new HashSet<>(val);
            machines.add(key);
            while (!allConnected(machines, connections)) {
                machines.remove(machineWithFewestConnections(machines, connections));
            }
            if (machines.size() == 3 && machines.stream().filter(m -> m.startsWith("t")).count() > 0) {
                ++count;
            }
        }
        return count;
    }

    String part2(String data) {
        Map<String, Set<String>> connections = getConnections(data);
        Set<String> largestGroup = new HashSet<>();
        for (Map.Entry<String, Set<String>> entry : connections.entrySet()) {
            String key = entry.getKey();
            Set<String> val = entry.getValue();
            Set<String> machines = new HashSet<>(val);
            machines.add(key);
            while (!allConnected(machines, connections)) {
                machines.remove(machineWithFewestConnections(machines, connections));
            }
            if (machines.size() > largestGroup.size()) {
                largestGroup = machines;
            }
        }
        return formatPassword(largestGroup);
    }

    Map<String, Set<String>> getConnections(String data) {
        Map<String, Set<String>> connections = new HashMap<>();
        Arrays.stream(data.trim().split("\n"))
              .forEach(line -> {
                String[] machines = line.split("-");
                if (!connections.containsKey(machines[0])) {
                    connections.put(machines[0], new HashSet<>(List.of(machines[1])));
                } else {
                    connections.get(machines[0]).add(machines[1]);
                }

                if (!connections.containsKey(machines[1])) {
                    connections.put(machines[1], new HashSet<>(List.of(machines[0])));
                } else {
                    connections.get(machines[1]).add(machines[0]);
                }
              });
        return connections;
    }

    String formatPassword(Set<String> machines) {
        return machines.stream().sorted().collect(Collectors.joining(","));
    }

    private boolean allConnected(Set<String> machines, Map<String, Set<String>> connections) {
        for (String machine : machines) {
            for (String other : machines) {
                if (other.equals(machine)) {
                    continue;
                }
                if (!connections.get(other).contains(machine)) {
                    return false;
                }
            }
        }
        return true;
    }

    private String machineWithFewestConnections(Set<String> machines, Map<String, Set<String>> connections) {
        String result = null;
        int fewest = Integer.MAX_VALUE;
        for (String machine : machines) {
            int numConnections = 0;
            for (String other : machines) {
                if (other.equals(machine)) {
                    continue;
                }
                if (connections.get(machine).contains(other)) {
                    numConnections++;
                }
            }
            if (numConnections < fewest) {
                fewest = numConnections;
                result = machine;
            }
        }
        return result;
    }
  
}
