import java.util.Scanner;

public class StatsAndScoresHandler {
    private final ScoreManager scoreManager;
    private final MealMoodLogger logger;

    public StatsAndScoresHandler(ScoreManager scoreManager, MealMoodLogger logger) {
        this.scoreManager = scoreManager;
        this.logger = logger;
    }

    public void handle() {
        while (true) {
            System.out.println("""
                \n📊 Stats & Scores
                1. View score
                2. View meal history
                3. View all meals
                4. Back to main menu
            """);

            Scanner scanner = new Scanner(System.in);
            System.out.print("Select: ");
            int choice = scanner.nextInt(); scanner.nextLine();

            switch (choice) {
                case 1 -> System.out.println("✅ Score: " + scoreManager.getScore());
                case 2 -> logger.getHistory().forEach(e -> System.out.println(e[0] + " - " + e[1]));
                case 3 -> scoreManager.getAllMeals()
                        .forEach(m -> System.out.println(m + ": " + scoreManager.getMealPoint(m)));
                case 4 -> { return; }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
