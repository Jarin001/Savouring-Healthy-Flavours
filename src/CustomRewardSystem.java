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

    public void editReward(Scanner scanner) {
        if (rewards.isEmpty()) {
            System.out.println("No rewards to edit.");
            return;
        }

        List<String> rewardKeys = new ArrayList<>(rewards.keySet());

        System.out.println("Select a reward to edit:");
        for (int i = 0; i < rewardKeys.size(); i++) {
            String reward = rewardKeys.get(i);
            System.out.printf("%d. %s (%d pts)%n", i + 1, reward, rewards.get(reward));
        }

        System.out.print("Enter reward number: ");
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }

        if (choice < 1 || choice > rewardKeys.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        String selectedReward = rewardKeys.get(choice - 1);

        System.out.print("Enter new point value for \"" + selectedReward + "\": ");
        int newPoints;
        try {
            newPoints = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }

        rewards.put(selectedReward, newPoints);
        saveRewards();
        System.out.println("Reward \"" + selectedReward + "\" updated to " + newPoints + " pts.");
    }

    public void deleteReward(Scanner scanner) {
        if (rewards.isEmpty()) {
            System.out.println("No rewards to delete.");
            return;
        }

        List<String> rewardKeys = new ArrayList<>(rewards.keySet());

        System.out.println("Select a reward to delete:");
        for (int i = 0; i < rewardKeys.size(); i++) {
            String reward = rewardKeys.get(i);
            System.out.printf("%d. %s (%d pts)%n", i + 1, reward, rewards.get(reward));
        }

        System.out.print("Enter reward number: ");
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }

        if (choice < 1 || choice > rewardKeys.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        String selectedReward = rewardKeys.get(choice - 1);
        rewards.remove(selectedReward);
        saveRewards();

        System.out.println("Reward \"" + selectedReward + "\" deleted.");
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
