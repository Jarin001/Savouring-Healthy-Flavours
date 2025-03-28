import java.util.Scanner;
public class RewardsHandler {
    private final Scanner scanner;
    private final ScoreManager scoreManager;
    private final CustomRewardSystem rewardSystem;
    private final DefaultRewardManager defaultRewards;

    public RewardsHandler(Scanner scanner, ScoreManager scoreManager,
                          CustomRewardSystem rewardSystem, DefaultRewardManager defaultRewards) {
        this.scanner = scanner;
        this.scoreManager = scoreManager;
        this.rewardSystem = rewardSystem;
        this.defaultRewards = defaultRewards;
    }

    public void handle() {
        while (true) {
            System.out.println("""
                \n🎁 Rewards & Goals
                1. Check default rewards
                2. Add custom reward
                3. Check custom rewards
                4. Edit a reward
                5. Delete a reward
                6. View all custom rewards
                7. Back to main menu
            """);

            System.out.print("Select: ");
            int choice = scanner.nextInt(); scanner.nextLine();

            switch (choice) {
                case 1 -> defaultRewards.showRewards(scoreManager.getScore());
                case 2 -> {
                    System.out.print("Goal name (or 'back' to cancel): ");
                    String name = scanner.nextLine();
                    if (name.equalsIgnoreCase("back")) break;
                    System.out.print("Points: ");
                    int pts = scanner.nextInt(); scanner.nextLine();
                    rewardSystem.addReward(name, pts);
                }
                case 3 -> rewardSystem.checkRewards(scoreManager.getScore());
                case 4 -> rewardSystem.editReward(scanner);
                case 5 -> rewardSystem.deleteReward(scanner);
                case 6 -> rewardSystem.viewRewards();
                case 7 -> { return; }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
