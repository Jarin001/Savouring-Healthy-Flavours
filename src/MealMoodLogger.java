import java.io.*;
import java.util.*;

public class MealMoodLogger implements IMealMoodLogger{
    private final File historyFile = new File("meal_mood_history.txt");

    @Override
    public void log(String meal, String mood) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(historyFile, true))) {
            writer.write(meal + "," + mood);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Could not log meal and mood.");
        }
    }
    @Override
    public List<String[]> getHistory() {
        List<String[]> history = new ArrayList<>();
        if (!historyFile.exists()) return history;

        try (BufferedReader reader = new BufferedReader(new FileReader(historyFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] entry = line.split(",");
                if (entry.length == 2) history.add(entry);
            }
        } catch (IOException e) {
            System.out.println("❌ Could not read history.");
        }

        return history;
    }

    @Override
    public Map<String, Integer> getMealLogCounts() {
        Map<String, Integer> counts = new HashMap<>();
        for (String[] entry : getHistory()) {
            String meal = entry[0].toLowerCase();
            counts.put(meal, counts.getOrDefault(meal, 0) + 1);
        }
        return counts;
    }

    @Override
    public Map<String, Integer> getMoodLogCounts() {
        Map<String, Integer> counts = new HashMap<>();
        for (String[] entry : getHistory()) {
            String mood = entry[1].toLowerCase();
            counts.put(mood, counts.getOrDefault(mood, 0) + 1);
        }
        return counts;
    }
}
