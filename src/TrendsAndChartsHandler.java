import java.util.Map;
import java.util.Scanner;
public class TrendsAndChartsHandler {
    private final ScoreManager scoreManager;
    private final MealMoodLogger logger;
    private final SummaryGenerator summary;
    private final WeeklyScoreChart chart;

    public TrendsAndChartsHandler(ScoreManager scoreManager, MealMoodLogger logger,
                                  SummaryGenerator summary, WeeklyScoreChart chart) {
        this.scoreManager = scoreManager;
        this.logger = logger;
        this.summary = summary;
        this.chart = chart;
    }

    public void handle() {
        while (true) {
            System.out.println("""
                \n📈 Trends & Analytics
                1. Weekly mood summary
                2. Top 5 meals/moods
                3. Weekly score trend
                4. Weekly score chart
                5. Back to main menu
            """);

            Scanner scanner = new Scanner(System.in);
            System.out.print("Select: ");
            int choice = scanner.nextInt(); scanner.nextLine();

            switch (choice) {
                case 1 -> summary.generate(logger.getHistory());
                case 2 -> {
                    System.out.println("🍴 Top Meals:");
                    logger.getMealLogCounts().entrySet().stream()
                            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                            .limit(5).forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));

                    System.out.println("😄 Top Moods:");
                    logger.getMoodLogCounts().entrySet().stream()
                            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                            .limit(5).forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));
                }
                case 3 -> scoreManager.getWeeklyScores()
                        .forEach((d, v) -> System.out.println(d + ": " + v));
                case 4 -> chart.displayChart();
                case 5 -> { return; }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
