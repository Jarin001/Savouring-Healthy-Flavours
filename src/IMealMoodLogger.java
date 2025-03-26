import java.util.*;

public interface IMealMoodLogger {
    void log(String meal, String mood);
    List<String[]> getHistory();
}
