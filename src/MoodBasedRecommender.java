import java.util.*;

public class MoodBasedRecommender {
        private final Map<String, List<String>> suggestions = Map.ofEntries(
                Map.entry("happy", List.of(
                        "Keep it up! Enjoy a smoothie.",
                        "Have a fruit bowl to maintain your energy.",
                        "Snack on almonds to stay sharp."
                )),
                Map.entry("sad", List.of(
                        "Dark chocolate might help lift your mood.",
                        "Try a warm bowl of lentil soup.",
                        "Grilled salmon can help with feel-good omega-3s."
                )),
                Map.entry("anxious", List.of(
                        "Chamomile tea can be calming.",
                        "Have a bowl of oats with fruit.",
                        "Try avocado toast for mental balance."
                )),
                Map.entry("tired", List.of(
                        "Banana is a quick energy booster.",
                        "Greek yogurt with honey for sustained energy.",
                        "Green tea is a light caffeine boost."
                )),
                Map.entry("stressed", List.of(
                        "Oatmeal with cinnamon helps reduce cortisol.",
                        "Snack on berries like blueberries.",
                        "Try herbal tea for relaxation."
                )),
                Map.entry("neutral", List.of(
                        "A balanced meal like rice, veggies, and grilled chicken.",
                        "Try a simple stir-fry with lots of colors.",
                        "How about a vegetable wrap with hummus?"
                )),
                Map.entry("stomach-ache", List.of(
                        "Plain rice or toast is gentle on the stomach.",
                        "Ginger tea can ease discomfort.",
                        "Bananas are soothing and easy to digest."
                )),
                Map.entry("head-ache", List.of(
                        "Stay hydrated — start with water!",
                        "Eat a handful of nuts for magnesium.",
                        "A small coffee or tea might help if you're caffeine-deprived."
                )),
                Map.entry("depressed", List.of(
                        "Fatty fish like salmon can help mood.",
                        "Eggs are rich in B vitamins.",
                        "Dark leafy greens like spinach support brain function."
                )),
                Map.entry("dehydrated", List.of(
                        "Coconut water can replenish electrolytes.",
                        "Watermelon is hydrating and refreshing.",
                        "Cucumber slices with lemon water are great!"
                )),
                Map.entry("sick", List.of(
                        "Warm chicken soup helps soothe symptoms.",
                        "Ginger tea can ease nausea and congestion.",
                        "Plain rice or toast is gentle on a sensitive stomach."
                ))
        );

    public String suggest(String mood) {
        List<String> options = suggestions.get(mood.toLowerCase());
        if (options != null && !options.isEmpty()) {
            return options.get(new Random().nextInt(options.size()));
        }

        List<String> fallback = List.of(
                "Try a simple, balanced meal with protein, carbs, and vegetables.",
                "How about some brown rice with grilled veggies and tofu?",
                "A quinoa salad with chickpeas and colorful veggies can nourish you.",
                "Consider soup and whole grain bread for a hearty, comforting meal.",
                "Mix whole grains, lean proteins, and greens for a nutritious plate.",
                "Try something light like steamed fish, spinach, and lentils.",
                "Oats with fruits and seeds make a satisfying choice.",
                "A boiled egg, avocado toast, and some fruit is a good combo."
        );
        return fallback.get(new Random().nextInt(fallback.size()));
    }

}


