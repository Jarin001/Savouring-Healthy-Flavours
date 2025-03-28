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


        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("💬 Quote of the Day: " + quote.getQuote());

        boolean running = true;
        while (running) {
            System.out.println("""
            \n🌿 Healthy Mind & Body Tracker 🌿
            1. Log meal & mood
            2. View score
            3. View meal history
            4. Get food suggestion
            5. Get a tip
            6. Weekly mood summary
            7. Check default reward
            8. Daily challenge
            9. Add new meal
            10. Search meal
            11. View all meals
            12. Top 5 meals/moods
            13. Weekly score trend
            14. Add custom reward
            15. Check custom rewards
            16. Edit a reward
            17. Delete a reward
            18. View all custom rewards
            19.View Weekly Score Chart (Graph)
            20. Exit
            """);

            System.out.print("Select: ");
            int choice = scanner.nextInt(); scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Meal (or 'back' to cancel): ");
                    String meal = scanner.nextLine();
                    if (meal.equalsIgnoreCase("back")) break;

                    System.out.print("Mood (or 'back' to cancel): ");
                    String mood = scanner.nextLine();
                    if (mood.equalsIgnoreCase("back")) break;

                    logger.log(meal, mood);
                    scoreManager.addMealPoints(meal);
                    System.out.println("Logged successfully.");
                }

                case 2 -> System.out.println("Current Score: " + scoreManager.getScore());

                case 3 -> {
                    System.out.println("📜 Meal History:");
                    logger.getHistory().forEach(e -> System.out.println(e[0] + " - " + e[1]));
                }

                case 4 -> {
                    System.out.print("Enter mood (or 'back'): ");
                    String mood = scanner.nextLine();
                    if (mood.equalsIgnoreCase("back")) break;
                    System.out.println("🍽️ Suggested: " + recommender.suggest(mood));
                }

                case 5 -> System.out.println("💡 Tip: " + tipProvider.getTip());

                case 6 -> summary.generate(logger.getHistory());

                case 7 -> defaultRewards.showRewards(scoreManager.getScore());

                case 8 -> System.out.println("🔥 Challenge: " + challenge.getChallenge());

                case 9 -> {
                    System.out.print("New meal name (or 'back'): ");
                    String meal = scanner.nextLine();
                    if (meal.equalsIgnoreCase("back")) break;

                    System.out.print("Point value: ");
                    int pts = scanner.nextInt(); scanner.nextLine();
                    scoreManager.addMealToFile(meal, pts);
                }

                case 10 -> {
                    System.out.print("Search keyword (or 'back'): ");
                    String kw = scanner.nextLine();
                    if (kw.equalsIgnoreCase("back")) break;

                    scoreManager.getAllMeals().stream()
                            .filter(m -> m.contains(kw.toLowerCase()))
                            .forEach(m -> System.out.println(m + ": " + scoreManager.getMealPoint(m)));
                }

                case 11 -> {
                    System.out.println("📋 All Meals:");
                    scoreManager.getAllMeals()
                            .forEach(m -> System.out.println(m + ": " + scoreManager.getMealPoint(m)));
                }

                case 12 -> {
                    System.out.println("🍴 Top Meals:");
                    logger.getMealLogCounts().entrySet().stream()
                            .sorted(Map.Entry.<String,Integer>comparingByValue().reversed())
                            .limit(5).forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));
                    System.out.println("😄 Top Moods:");
                    logger.getMoodLogCounts().entrySet().stream()
                            .sorted(Map.Entry.<String,Integer>comparingByValue().reversed())
                            .limit(5).forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));
                }

                case 13 -> {
                    System.out.println("📆 Weekly Score Log:");
                    scoreManager.getWeeklyScores().forEach((d, v) -> System.out.println(d + ": " + v));
                }

                case 14 -> {
                    System.out.print("Reward name (or 'back'): ");
                    String name = scanner.nextLine();
                    if (name.equalsIgnoreCase("back")) break;

                    System.out.print("Required points: ");
                    int pts = scanner.nextInt(); scanner.nextLine();
                    rewardSystem.addReward(name, pts);
                }

                case 15 -> rewardSystem.checkRewards(scoreManager.getScore());

                case 16 -> rewardSystem.editReward(scanner);

                case 17 -> {
                    rewardSystem.viewRewards();
                    System.out.print("Reward to delete (or 'back'): ");
                    String name = scanner.nextLine();
                    if (name.equalsIgnoreCase("back")) break;
                    rewardSystem.deleteReward(name);
                }

                case 18 -> rewardSystem.viewRewards();

                case 19 -> {
                    chart.displayChart();
                }

                case 20 -> {
                    System.out.println("👋 Goodbye! Stay strong, stay healthy.");
                    running = false;
                    continue;
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
