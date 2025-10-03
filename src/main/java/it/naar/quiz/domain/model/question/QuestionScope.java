package it.naar.quiz.domain.model.question;

import it.naar.quiz.domain.model.quiz.QuizUse;

import java.util.List;

public class QuestionScope {
    List<QuizUse> quizUses;
    public List<QuizUse> getQuizUses() {
        return quizUses;
    }
    public void setQuizUses(List<QuizUse> quizUses) {
        this.quizUses = quizUses;
    }
    public QuestionScope() {}

    public void addUse(QuizUse use)
    {
        this.quizUses.add(use);
    }

    public void removeUse(QuizUse use)
    {
        this.quizUses.remove(use);
    }

    public void allUses()
    {}

}
