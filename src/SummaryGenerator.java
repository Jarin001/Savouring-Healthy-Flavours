import java.util.*;

public class SummaryGenerator {

    public void generate(List<String[]> history) {
        if (history.isEmpty()) {
            System.out.println("No mood history to summarize.");
            return;
        }

        Map<String, Integer> moodCounts = new HashMap<>();
        for (String[] entry : history) {
            String mood = entry[1].trim().toLowerCase();
            moodCounts.put(mood, moodCounts.getOrDefault(mood, 0) + 1);
        }

        System.out.println("Mood Summary:");
        moodCounts.forEach((mood, count) ->
                System.out.println(mood + ": " + count + " time(s)")
        );
    }
}
