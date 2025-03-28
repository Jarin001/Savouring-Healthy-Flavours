import java.util.Scanner;

public class LoggingAndMealsHandler {
    private final Scanner scanner;
    private final MealMoodLogger logger;
    private final ScoreManager scoreManager;

    public LoggingAndMealsHandler(Scanner scanner, MealMoodLogger logger, ScoreManager scoreManager) {
        this.scanner = scanner;
        this.logger = logger;
        this.scoreManager = scoreManager;
    }

    public void handle() {
        while (true) {
            System.out.println("""
                \n📘 Log & Meal Options
                1. Log meal & mood
                2. Add new meal
                3. Search meal
                4. Back to main menu
            """);
            System.out.print("Select: ");
            int choice = scanner.nextInt(); scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Meal: ");
                    String meal = scanner.nextLine();
                    System.out.print("Mood: ");
                    String mood = scanner.nextLine();
                    logger.log(meal, mood);
                    scoreManager.addMealPoints(meal);
                }
                case 2 -> {
                    System.out.print("New Meal: ");
                    String meal = scanner.nextLine();
                    System.out.print("Points: ");
                    int pts = scanner.nextInt(); scanner.nextLine();
                    scoreManager.addMealToFile(meal, pts);
                }
                case 3 -> {
                    System.out.print("Search: ");
                    String kw = scanner.nextLine().toLowerCase();
                    scoreManager.getAllMeals().stream()
                            .filter(m -> m.contains(kw))
                            .forEach(m -> System.out.println(m + ": " + scoreManager.getMealPoint(m)));
                }
                case 4 -> { return; }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
