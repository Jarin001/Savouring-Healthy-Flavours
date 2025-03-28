import java.util.Scanner;

public class InsightsAndWellnessHandler {
    private final Scanner scanner;
    private final MoodBasedRecommender recommender;
    private final RandomTipProvider tipProvider;
    private final ChallengeProvider challengeProvider;

    public InsightsAndWellnessHandler(Scanner scanner, MoodBasedRecommender recommender,
                                      RandomTipProvider tipProvider, ChallengeProvider challengeProvider) {
        this.scanner = scanner;
        this.recommender = recommender;
        this.tipProvider = tipProvider;
        this.challengeProvider = challengeProvider;
    }

    public void handle() {
        while (true) {
            System.out.println("""
                \n💡 Wellness Options
                1. Get food suggestion
                2. Get a tip
                3. Daily challenge
                4. Back to main menu
            """);

            System.out.print("Select: ");
            int choice = scanner.nextInt(); scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Mood: ");
                    System.out.println("🍽️ Try: " + recommender.suggest(scanner.nextLine()));
                }
                case 2 -> System.out.println("💡 Tip: " + tipProvider.getTip());
                case 3 -> System.out.println("🔥 Challenge: " + challengeProvider.getChallenge());
                case 4 -> { return; }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
