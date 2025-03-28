import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;


class ScoreManager implements IScoreManager {
    private final File scoreFile = new File("score.txt");
    private final File mealsFile = new File("meals.txt");
    private final File scoreLogFile = new File("score_log.txt");
    private final Map<String, Integer> mealPoints = new HashMap<>();
    private int score = 0;

    public ScoreManager() {
        loadMealPointsFromFile();
        loadScore();
    }

    private void loadMealPointsFromFile() {
        if (!mealsFile.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(mealsFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    mealPoints.put(parts[0].trim().toLowerCase(), Integer.parseInt(parts[1].trim()));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading meal points.");
        }
    }

    private void loadScore() {
        try (Scanner scanner = new Scanner(scoreFile)) {
            if (scanner.hasNextInt()) score = scanner.nextInt();
        } catch (IOException e) {
            score = 0;
        }
    }

    private void saveScore() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(scoreFile))) {
            writer.write(String.valueOf(score));
        } catch (IOException e) {
            System.out.println("Could not save score.");
        }
    }

    @Override
    public void addMealPoints(String meal) {
        Integer points = mealPoints.get(meal.toLowerCase());
        if (points == null) {
            System.out.println("Unknown meal: '" + meal + "'. Try one of these: " + mealPoints.keySet());
            return;
        }
        score += points;
        saveScore();
        logDailyScore();
        System.out.println("Points earned: " + points);
    }

    @Override
    public void addMealToFile(String meal, int points) {
        meal = meal.toLowerCase();
        if (mealPoints.containsKey(meal)) {
            System.out.println("Meal already exists.");
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(mealsFile, true))) {
            writer.write(meal + "," + points);
            writer.newLine();
            mealPoints.put(meal, points);
            System.out.println("Added meal: " + meal + " (" + points + " pts)");
        } catch (IOException e) {
            System.out.println("Could not add meal.");
        }
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public Set<String> getAllMeals() {
        return mealPoints.keySet();
    }

    @Override
    public Integer getMealPoint(String meal) {
        return mealPoints.get(meal.toLowerCase());
    }

    @Override
    public void logDailyScore() {
        String today = LocalDate.now().toString();
        int currentTotalScore = getScore();

        String lastDate = "";
        int lastCumulativeScore = 0;

        if (scoreLogFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(scoreLogFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        lastDate = parts[0].trim();
                        lastCumulativeScore = Integer.parseInt(parts[1].trim());
                    }
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Error reading score log.");
            }
        }

        if (lastDate.equals(today)) {
            return;
        }

        int delta = currentTotalScore - getLastCumulativeScoreBefore(today);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(scoreLogFile, true))) {
            writer.write(today + "," + delta);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Couldn't log today's score.");
        }
    }

    private int getLastCumulativeScoreBefore(String today) {
        int lastScore = 0;
        if (!scoreLogFile.exists()) return 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(scoreLogFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String date = parts[0].trim();
                    int score = Integer.parseInt(parts[1].trim());
                    if (date.compareTo(today) < 0) {
                        lastScore += score;
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error calculating previous score.");
        }

        return lastScore;
    }

    @Override
    public Map<String, Integer> getWeeklyScores() {
        Map<String, Integer> weeklyScores = new LinkedHashMap<>();

        if (!scoreLogFile.exists()) return weeklyScores;

        LocalDate today = LocalDate.now();
        LocalDate weekAgo = today.minusDays(6);

        try (BufferedReader reader = new BufferedReader(new FileReader(scoreLogFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    LocalDate date = LocalDate.parse(parts[0].trim());
                    int score = Integer.parseInt(parts[1].trim());

                    if (!date.isBefore(weekAgo)) {
                        weeklyScores.put(date.toString(), score);
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading weekly scores.");
        }

        return weeklyScores;
    }

}
