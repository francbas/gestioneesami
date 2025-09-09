package it.naar.quiz.domain.question;

public abstract class AbstractQuestion {
    int id;
    String testo;

    QuestionScope scope;

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public QuestionScope getScope() {
        return scope;
    }

    public void setScope(QuestionScope scope) {
        this.scope = scope;
    }


}
