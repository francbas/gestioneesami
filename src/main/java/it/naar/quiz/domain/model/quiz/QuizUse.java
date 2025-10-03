package it.naar.quiz.domain.model.quiz;

public class QuizUse {
    int id;
    String text;
    String description;
    public QuizUse(String text, String description) {
        this.text = text;
        this.description = description;
    }
    public QuizUse() {
    }
}
