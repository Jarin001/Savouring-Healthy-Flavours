import java.io.*;
import java.util.*;

public class CustomRewardSystem {
    private final File rewardFile = new File("custom_rewards.txt");
    private final Map<String, Integer> rewards = new LinkedHashMap<>();

    public CustomRewardSystem() {
        loadRewards();
    }

    private void loadRewards() {
        if (!rewardFile.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(rewardFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0].trim();
                    int points = Integer.parseInt(parts[1].trim());
                    rewards.put(name, points);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading rewards.");
        }
    }

    private void saveRewards() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rewardFile))) {
            for (Map.Entry<String, Integer> entry : rewards.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving rewards.");
        }
    }

    public void addReward(String name, int points) {
        if (rewards.containsKey(name)) {
            System.out.println("Reward already exists.");
            return;
        }
        rewards.put(name, points);
        saveRewards();
        System.out.println("Reward \"" + name + "\" added with goal " + points + " pts.");
    }

    public void editReward(String name, int newPoints) {
        if (!rewards.containsKey(name)) {
            System.out.println("Reward not found.");
            return;
        }
        rewards.put(name, newPoints);
        saveRewards();
        System.out.println("Reward \"" + name + "\" updated to " + newPoints + " pts.");
    }

    public void deleteReward(String name) {
        if (rewards.remove(name) != null) {
            saveRewards();
            System.out.println("Reward \"" + name + "\" deleted.");
        } else {
            System.out.println("Reward not found.");
        }
    }

    public void viewRewards() {
        if (rewards.isEmpty()) {
            System.out.println("No rewards set.");
            return;
        }
        System.out.println("All Custom Rewards:");
        rewards.forEach((name, points) ->
                System.out.println("- " + name + ": " + points + " pts"));
    }

    public void checkRewards(int score) {
        if (rewards.isEmpty()) {
            System.out.println("No custom goals set.");
            return;
        }
        System.out.println("Custom Rewards Status:");
        for (Map.Entry<String, Integer> entry : rewards.entrySet()) {
            String name = entry.getKey();
            int goal = entry.getValue();
            if (score >= goal) {
                System.out.println("Congratulations! You unlocked: " + name);
            } else {
                System.out.println(name + " - " + (goal - score) + " pts to unlock.");
            }
        }
    }

    public Set<String> getAllRewardNames() {
        return rewards.keySet();
    }
}
