import java.util.*;

public class ChallengeProvider {
    private final String[] challenges = {
            "Drink 8 glasses of water today!",
            "Eat at least 3 colorful vegetables!",
            "Avoid sugary drinks for 24 hours!",
            "Take a 30-minute walk!",
            "Try a new healthy recipe!",
            "Do 10 minutes of meditation or deep breathing.",
            "Avoid eating processed snacks today.",
            "Sleep for at least 7 hours tonight.",
            "Swap refined grains for whole grains in one meal.",
            "Do 20 squats and 10 push-ups!",
            "Stretch for 5 minutes after waking up.",
            "No screens 1 hour before bed.",
            "Write down 3 things you're grateful for.",
            "Cook dinner using only fresh ingredients.",
            "Eat slowly and mindfully during your meals."
    };

    public String getChallenge() {
        return challenges[new Random().nextInt(challenges.length)];
    }
}
