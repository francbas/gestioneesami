package it.naar.quiz.domain.question;

import java.util.List;

public class QuestionScope {
    List<QuizFinalita> finalitas;
    public List<QuizFinalita> getFinalitas() {
        return finalitas;
    }
    public void setFinalitas(List<QuizFinalita> finalitas) {
        this.finalitas = finalitas;
    }
    public QuestionScope() {}

    public void addFinalita(QuizFinalita finalita)
    {
        this.finalitas.add(finalita);
    }

    public void removeFinalita(QuizFinalita finalita)
    {
        this.finalitas.remove(finalita);
    }

    public void allFinalitas()
    {}

}
