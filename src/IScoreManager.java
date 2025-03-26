import java.io.*;
import java.util.*;

interface IScoreManager {
    void addMealPoints(String meal);
    void addMealToFile(String meal, int points);
    int getScore();
    Set<String> getAllMeals();
    Integer getMealPoint(String meal);
    void logDailyScore();
    Map<String, Integer> getWeeklyScores();
}