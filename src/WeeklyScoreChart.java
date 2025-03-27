import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class WeeklyScoreChart {
    private final File scoreLogFile = new File("score_log.txt");

    public void displayChart() {
        if (!scoreLogFile.exists()) {
            System.out.println("No score log found.");
            return;
        }
        Map<DayOfWeek, Integer> weeklyScores = new EnumMap<>(DayOfWeek.class);
        for (DayOfWeek day : DayOfWeek.values()) {
            weeklyScores.put(day, 0);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(scoreLogFile))) {
            String line;
            LocalDate today = LocalDate.now();
            LocalDate weekAgo = today.minusDays(6);

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 2) continue;

                LocalDate date = LocalDate.parse(parts[0].trim());
                int score = Integer.parseInt(parts[1].trim());

                if (!date.isBefore(weekAgo)) {
                    DayOfWeek day = date.getDayOfWeek();
                    weeklyScores.put(day, score);
                }
            }

        } catch (IOException | DateTimeException | NumberFormatException e) {
            System.out.println("Error reading score log.");
            return;
        }

        System.out.println("\nWeekly Score Chart:\n");

        for (DayOfWeek day : DayOfWeek.values()) {
            String dayLabel = String.format("%-3s", day.toString().substring(0, 3));
            int score = weeklyScores.get(day);
            String bar = "█".repeat(score / 10);
            System.out.printf("%s | %-20s %d pts\n", dayLabel, bar, score);
        }

        int total = weeklyScores.values().stream().mapToInt(Integer::intValue).sum();
        long above100 = weeklyScores.values().stream().filter(s -> s >= 100).count();

        System.out.printf("\nTotal weekly score: %d pts\n", total);
        System.out.printf("Days with 100+ points: %d/7\n", above100);
    }
}
