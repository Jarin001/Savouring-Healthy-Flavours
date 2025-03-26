import java.util.*;

public class SummaryGenerator {
    public void generate(List<String[]> history) {
        Map<String, Integer> moodCounts = new HashMap<>();
        for (String[] entry : history) {
            moodCounts.put(entry[1], moodCounts.getOrDefault(entry[1], 0) + 1);
        }
        System.out.println("Mood Summary:");
        moodCounts.forEach((k, v) -> System.out.println(k + " - " + v + " times"));
    }
}
