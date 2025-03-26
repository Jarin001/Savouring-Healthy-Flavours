import java.util.*;

class RandomTipProvider{
    private final String[] tips = {
            "Drink more water.",
            "Eat whole grains.",
            "Add veggies to every meal.",
            "Cut down on sugar.",
            "Eat smaller portions.",
            "Include protein in every meal.",
            "Avoid processed foods.",
            "Choose healthy fats.",
            "Limit sugary drinks.",
            "Practice mindful eating.",
            "Eat more fiber-rich foods.",
            "Don’t skip breakfast.",
            "Stay hydrated throughout the day.",
            "Cook meals at home.",
            "Use herbs and spices instead of salt.",
            "Keep healthy snacks handy.",
            "Read nutrition labels.",
            "Choose whole fruits over juice.",
            "Take smaller bites and chew well.",
            "Don't eat in front of screens.",
            "Practice portion control.",
            "Include probiotics in your diet.",
            "Drink green tea instead of soda.",
            "Eat colorful vegetables.",
            "Switch to whole grain bread.",
            "Snack on nuts instead of chips.",
            "Eat slowly to feel full.",
            "Avoid late-night snacking.",
            "Replace sweets with fruit.",
            "Get enough sleep.",
            "Plan your meals.",
            "Keep a food journal.",
            "Eat fish twice a week.",
            "Limit red meat.",
            "Enjoy your meals without guilt.",
            "Start your day with protein.",
            "Stay hydrated for mental clarity.",
            "Whole foods can stabilize your mood.",
            "Avoid too much caffeine when anxious.",
            "Fruits and greens keep you fresh.",
            "Fermented foods help your gut and brain.",
            "Chew slowly and savor your food.",
            "Don’t skip breakfast – it sets your tone.",
            "Add nuts or seeds to your salad for healthy fats.",
            "Plan meals ahead to avoid junk cravings.",
            "Get sunlight exposure to boost mood naturally."

    };

    public String getTip() {
        return tips[new Random().nextInt(tips.length)];
    }
}
