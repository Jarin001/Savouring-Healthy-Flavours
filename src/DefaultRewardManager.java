import java.util.*;

public class DefaultRewardManager {
    private final Map<Integer, String> rewardTiers = new LinkedHashMap<>();

    public DefaultRewardManager() {
        rewardTiers.put(100, "Treat yourself to an ice cream!");
        rewardTiers.put(150, "Get yourself some comfy wellness gear!");
        rewardTiers.put(200, "Buy that book or gadget you've been wanting!");
        rewardTiers.put(250, "Treat yourself to a fancy dinner!");
        rewardTiers.put(300, "Take a relaxing weekend trip!");
    }

    public void showRewards(int score) {
        System.out.println("Your current score: " + score + " pts");

        boolean unlockedAny = false;

        for (Map.Entry<Integer, String> entry : rewardTiers.entrySet()) {
            if (score >= entry.getKey()) {
                System.out.println("Reward Unlocked (" + entry.getKey() + " pts): " + entry.getValue());
                unlockedAny = true;
            } else {
                System.out.println((entry.getKey() - score) + " pts to unlock: " + entry.getValue());
                break;
            }
        }

        if (!unlockedAny) {
            System.out.println("Keep going! Your first reward is coming soon!");
        }
    }
}
