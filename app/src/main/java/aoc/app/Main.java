package aoc.app;

import aoc.days.Day;

public class Main {

    public static void main(String[] args) {
        try {
            String dayNumber = args[0];
            String inputFile = args[1];
            String className = String.format("aoc.days.Day%s", dayNumber);
            Day day = (Day) Class.forName(className).getDeclaredConstructor().newInstance();
            day.run(inputFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
