package it.naar.quiz.domain.question;

public class QuestionAta extends AbstractQuestion {
    ArgumentAta ataArgument;


    public ArgumentAta getAtaChapter() {
        return ataArgument;
    }

    public void setAtaChapter(ArgumentAta argomentoAta) {
        this.ataArgument = argomentoAta;
    }
}
