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

        Map<LocalDate, Integer> rawScores = new TreeMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(scoreLogFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    LocalDate date = LocalDate.parse(parts[0].trim());
                    int score = Integer.parseInt(parts[1].trim());
                    rawScores.put(date, score);
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to read log.");
            return;
        }

        LocalDate today = LocalDate.now();
        LocalDate weekAgo = today.minusDays(6);

        Map<DayOfWeek, Integer> dailyScores = new EnumMap<>(DayOfWeek.class);
        LocalDate prevDate = null;
        int prevScore = 0;

        for (Map.Entry<LocalDate, Integer> entry : rawScores.entrySet()) {
            LocalDate date = entry.getKey();
            int score = entry.getValue();

            if (date.isBefore(weekAgo)) continue;

            int delta = prevDate == null ? score : (score - prevScore);
            dailyScores.put(date.getDayOfWeek(), delta);

            prevDate = date;
            prevScore = score;
        }

        System.out.println("\n📈 Weekly Score Chart:\n");

        for (DayOfWeek day : DayOfWeek.values()) {
            String label = String.format("%-3s", day.toString().substring(0, 3));
            int score = dailyScores.getOrDefault(day, 0);
            String bar = "█".repeat(score / 10);
            System.out.printf("%s | %-20s %d pts%n", label, bar, score);
        }

        int total = dailyScores.values().stream().mapToInt(Integer::intValue).sum();
        long above100 = dailyScores.values().stream().filter(s -> s >= 100).count();

        System.out.printf("\n📊 Total weekly score: %d pts%n", total);
        System.out.printf("Days with 100+ points: %d/7%n", above100);
    }

}
