import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ScoreManager scoreManager = new ScoreManager();
        MealMoodLogger logger = new MealMoodLogger();
        MoodBasedRecommender recommender = new MoodBasedRecommender();
        RandomTipProvider tipProvider = new RandomTipProvider();
        SummaryGenerator summary = new SummaryGenerator();
        ChallengeProvider challenge = new ChallengeProvider();
        QuoteProvider quote = new QuoteProvider();
        CustomRewardSystem rewardSystem = new CustomRewardSystem();
        WeeklyScoreChart chart = new WeeklyScoreChart();
        DefaultRewardManager defaultRewards = new DefaultRewardManager();

        LoggingAndMealsHandler loggingHandler = new LoggingAndMealsHandler(scanner, logger, scoreManager);
        StatsAndScoresHandler statsHandler = new StatsAndScoresHandler(scoreManager, logger);
        InsightsAndWellnessHandler wellnessHandler = new InsightsAndWellnessHandler(scanner, recommender, tipProvider, challenge);
        TrendsAndChartsHandler trendsHandler = new TrendsAndChartsHandler(scoreManager, logger, summary, chart);
        RewardsHandler rewardsHandler = new RewardsHandler(scanner, scoreManager, rewardSystem, defaultRewards);

        System.out.println("\n💬 Quote of the Day: " + quote.getQuote());

        boolean running = true;
        while (running) {
            System.out.println("""
            \n🌿 Healthy Mind & Body Tracker 🌿
            1. Log Meals, Add/Search Foods
            2. Score, History & Meal Logs
            3. Tips, Suggestions & Challenges
            4. Mood/Score Trends & Charts
            5. Rewards & Goals
            6. Exit
            """);

            System.out.print("Select: ");
            int choice = scanner.nextInt(); scanner.nextLine();

            switch (choice) {
                case 1 -> loggingHandler.handle();
                case 2 -> statsHandler.handle();
                case 3 -> wellnessHandler.handle();
                case 4 -> trendsHandler.handle();
                case 5 -> rewardsHandler.handle();
                case 6 -> {
                    System.out.println("👋 Goodbye! Stay strong, stay healthy.");
                    running = false;
                }
                default -> System.out.println("Invalid option. Try again.");
            }

            if (running) {
                System.out.println("\nPress Enter to return to the menu...");
                scanner.nextLine();
            }
        }
    }
}
