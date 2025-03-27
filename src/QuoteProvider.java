import java.util.*;

public class QuoteProvider {
    private final String[] quotes = {
            "Let food be thy medicine. – Hippocrates",
            "Healthy eating is a form of self-respect.",
            "Eat well. Live well.",
            "Small steps make big changes.",
            "Nourish your body and mind.",
            "Your body deserves the best fuel.",
            "What you eat today walks and talks tomorrow.",
            "A healthy outside starts from the inside.",
            "The food you eat can be the safest medicine or slowest poison.",
            "You don't have to eat less, just eat right.",
            "Invest in your health, it pays lifetime dividends.",
            "Food is the most abused anxiety drug. Exercise is the most underutilized antidepressant.",
            "Your future is created by what you do today, not tomorrow.",
            "Take care of your body. It's the only place you have to live.",
            "Every healthy choice is a victory."
    };

    public String getQuote() {
        return quotes[new Random().nextInt(quotes.length)];
    }
}
