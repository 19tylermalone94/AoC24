package aoc.app;

import aoc.days.Day;

public class Main {

    public static void main(String[] args) {
        try {
            String className = String.format("aoc.days.Day%s", args[0]);
            Day day = (Day) Class.forName(className).getDeclaredConstructor().newInstance();
            day.run(args[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
